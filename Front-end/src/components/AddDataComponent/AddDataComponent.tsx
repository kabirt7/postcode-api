import styles from "./AddDataComponent.module.scss";
import { useForm } from "react-hook-form";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCheck, faTimes } from "@fortawesome/free-solid-svg-icons";
import { addData } from "../../services/logic";

interface AddDataComponentProps {
  closeModal: React.MouseEventHandler<HTMLButtonElement>;
}

const AddDataComponent = ({ closeModal }: AddDataComponentProps) => {
  const { handleSubmit, register } = useForm<{
    suburbInput: string;
    postcodeInput: Number;
  }>();

  const onSubmit = async (data: {
    postcodeInput: Number;
    suburbInput: string;
  }) => {
    try {
      await addData(data.postcodeInput, data.suburbInput);
    } catch (error: any) {
      console.log(error);
    }
  };

  return (
    <form className={styles.addData} onSubmit={handleSubmit(onSubmit)}>
      <div className={styles.addData__inputs}>
        <input placeholder="Enter Suburb" {...register("suburbInput")} />
        <input placeholder="Enter Postcode" {...register("postcodeInput")} />
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
