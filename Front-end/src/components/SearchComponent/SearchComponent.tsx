import { faCheck, faTimes } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import styles from "./SearchComponent.module.scss";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import * as z from "zod";
import { useEffect, useState } from "react";
import { getPostcodeOrSuburb } from "../../services/logic";

interface FindPostcodeComponentProps {
  closeModal: React.MouseEventHandler<HTMLButtonElement>;
  type: String;
}

interface PostcodeData {
  postcodeNum: string | undefined;
  suburb: string;
}

const SearchComponent = ({ closeModal, type }: FindPostcodeComponentProps) => {
  const [returnValue, setReturnValue] = useState<PostcodeData | null>(null);

  const schema = z.object({
    input: z.string().min(3),
  });

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
      const { postcodeNum, suburb } = returnValue;
      if (type == "POSTCODE") {
        alert(`The postcode of ${suburb} is ${postcodeNum}`);
      }
      if (type == "SUBURB") {
        console.log(postcodeNum);
        console.log(suburb);
        alert(`The suburb(s) with the postcode of ${suburb} is ${postcodeNum}`);
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
        setReturnValue({ postcodeNum: postcode, suburb: data.input });
      }

      if (type == "SUBURB") {
        const suburb = await getPostcodeOrSuburb("SUBURB", data.input);
        console.log(suburb);
        setReturnValue({ suburb: suburb, postcodeNum: data.input });
      }
    } catch (error: any) {
      console.log(error);
    }
  };

  useEffect(() => {
    console.log(errors);
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
