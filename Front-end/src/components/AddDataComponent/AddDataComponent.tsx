import styles from "./AddDataComponent.module.scss";
import { SubmitHandler, useForm } from "react-hook-form";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCheck, faTimes } from "@fortawesome/free-solid-svg-icons";
import { addData } from "../../services/logic";
import { ToastContext } from "../../context/ToastContext";
import { useContext, useEffect } from "react";
import { zodResolver } from "@hookform/resolvers/zod";
import { z } from "zod";

interface AddDataComponentProps {
  closeModal: React.MouseEventHandler<HTMLButtonElement>;
}

const AddDataComponent = ({ closeModal }: AddDataComponentProps) => {
  const { setMessage } = useContext(ToastContext);

  const schema = z.object({
    suburbInput: z
      .string()
      .min(3, { message: "Suburb must contain at least 3 characters" })
      .regex(/^[A-Za-z\s,-]+$/, {
        message: "Suburb must contain only letters or commas",
      }),
    postcodeInput: z
      .string()
      .min(3, { message: "Postcode must contain at least 3 integers" })
      .regex(/^\d+$/, { message: "Postcode must contain only numbers" }),
  });

  type ValidationSchema = z.infer<typeof schema>;

  const {
    handleSubmit,
    register,
    formState: { errors },
  } = useForm<ValidationSchema>({ resolver: zodResolver(schema) });

  const onSubmit: SubmitHandler<ValidationSchema> = async (data: {
    postcodeInput: string;
    suburbInput: string;
  }) => {
    try {
      const response = await addData(data.postcodeInput, data.suburbInput);
      if (response != null) {
        setMessage("Data added successfully");
      }
    } catch (error: any) {
      const errorMessage = error.message || "An error occurred";
      setMessage(errorMessage);
    }
  };

  useEffect(() => {
    console.log(errors);
    let errorMessage = "";

    if (errors.suburbInput?.message && errors.postcodeInput?.message) {
      errorMessage += errors.suburbInput.message += ", ";
    }

    if (errors.suburbInput?.message && !errors.postcodeInput?.message) {
      errorMessage += errors.suburbInput.message;
    }

    if (errors.postcodeInput?.message) {
      errorMessage += errors.postcodeInput.message;
    }

    setMessage(errorMessage);
  }, [errors]);

  return (
    <form className={styles.addData} onSubmit={handleSubmit(onSubmit)}>
      <div className={styles.addData__inputs}>
        <input
          placeholder="Enter Suburb"
          {...register("suburbInput", { required: true })}
        />
        {errors.suburbInput && (
          <span className={styles.error}>Suburb is required</span>
        )}
        <input
          placeholder="Enter Postcode"
          {...register("postcodeInput", { required: true })}
        />
        {errors.postcodeInput && (
          <span className={styles.error}>Postcode is required</span>
        )}
      </div>
      <footer className={styles.addData__footer}>
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

export default AddDataComponent;
