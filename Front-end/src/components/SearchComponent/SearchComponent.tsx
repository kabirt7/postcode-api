import { faCheck, faTimes } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import styles from "./SearchComponent.module.scss";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import * as z from "zod";
import { useContext, useEffect, useState } from "react";
import { getPostcodeOrSuburb } from "../../services/logic";
import { ToastContext } from "../../context/ToastContext";

interface FindPostcodeComponentProps {
  closeModal: React.MouseEventHandler<HTMLButtonElement>;
  type: String;
}

const SearchComponent = ({ closeModal, type }: FindPostcodeComponentProps) => {
  const [returnValue, setReturnValue] = useState<Array<
    string | number | undefined
  > | null>(null);

  const schema = z.object({
    input:
      type === "POSTCODE"
        ? z
            .string()
            .min(3, { message: "Suburb must contain at least 3 characters" })
            .regex(/^[A-Za-z\s,-]+$/, {
              message: "Suburb must contain only letters or commas",
            })
        : z
            .string()
            .min(3, { message: "Postcode must contain at least 3 characters" })
            .regex(/^\d+$/, { message: "Postcode must contain only numbers" }),
  });

  const { setMessage } = useContext(ToastContext);

  const {
    handleSubmit,
    register,
    formState: { errors },
  } = useForm<{ input: string }>({
    resolver: zodResolver(schema),
  });

  const placeholderValue =
    type === "POSTCODE" ? "Enter Suburb" : "Enter Postcode";

  function capitalizeFirstLetter(data: string): string {
    return data.charAt(0).toUpperCase() + data.slice(1);
  }

  const alertPostcode = () => {
    if (returnValue !== null && typeof returnValue === "object") {
      if (type == "POSTCODE") {
        alert(
          `The suburb with the postcode of ${returnValue[1]} is ${returnValue[0]}`
        );
      }
      if (type == "SUBURB") {
        alert(
          `The suburb(s) with the postcode of ${returnValue[1]} is/are: ${returnValue[0]}`
        );
      }
    } else {
      alert("No postcode available.");
    }
  };

  const onSubmit = async (data: { input: string | number }) => {
    try {
      if (type == "POSTCODE") {
        const postcode = await getPostcodeOrSuburb("POSTCODE", data.input);
        console.log(postcode);
        setReturnValue([data.input, postcode]);
      }

      if (type == "SUBURB") {
        const suburb = await getPostcodeOrSuburb("SUBURB", data.input);
        console.log(suburb);
        setReturnValue([suburb, data.input]);
      }
    } catch (error: any) {
      console.log(error);
      const errorMessage = error.message || "Not found";
      setMessage(errorMessage);
    }
  };

  useEffect(() => {
    console.log(errors);
    if (errors.input?.message) {
      const errorMessage = errors.input.message;
      setMessage(errorMessage);
    }
  }, [errors]);

  useEffect(() => {
    if (returnValue == null) return;
    alertPostcode();
  }, [returnValue]);
  return (
    <form className={styles.findPostcode} onSubmit={handleSubmit(onSubmit)}>
      <h1>Find {capitalizeFirstLetter(type.toLowerCase())}</h1>
      <div className={styles.findPostcode__inputs}>
        <input placeholder={placeholderValue} {...register("input")} />
      </div>
      <footer className={styles.findPostcode__footer}>
        <button type="submit">
          <FontAwesomeIcon icon={faCheck} />
        </button>
        <button type="button" onClick={closeModal}>
          <FontAwesomeIcon icon={faTimes} />
        </button>
      </footer>
    </form>
  );
};

export default SearchComponent;
