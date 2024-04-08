import styles from "./ViewDataComponent.module.scss";
import { useContext, useEffect, useState } from "react";
import { deleteData, getAllData } from "../../services/logic";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTimes, faTrashCan } from "@fortawesome/free-solid-svg-icons";
import { PostcodeDataPair } from "../../services/interfaces";
import PostcodeDataItem from "../PostcodeItem.jsx/PostcodeDataItem";
import { ToastContext } from "../../context/ToastContext";

interface ViewDataComponentProps {
  closeModal: React.MouseEventHandler<HTMLButtonElement>;
}

const ViewDataComponent: React.FC<ViewDataComponentProps> = ({
  closeModal,
}) => {
  const [postcodeItems, setPostcodeItems] = useState<null | PostcodeDataPair[]>(
    null
  );

  const { message, setMessage } = useContext(ToastContext);

  const handleDeleteData = async (postcode: Number) => {
    try {
      const response = await deleteData(postcode);
      fetchData();
      if (response == null) {
        setMessage("Data deleted successfully");
      }
    } catch (error) {
      console.log(error);
      setMessage(error);
    }
  };

  const convertToString = (data: any) => {
    if (data.suburbs && data.suburbs.length > 0) {
      return data.suburbs.map((item: any) => item.suburbName).join(", ");
    } else {
      return "";
    }
  };

  const fetchData = async () => {
    try {
      const data: PostcodeDataPair[] = await getAllData();
      setPostcodeItems(data);
      console.log(data);
    } catch (error: any) {
      console.log(error);
      const errorMessage = error.message || "An error occurred";
      setMessage(errorMessage);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <>
      <div className={styles.viewData}>
        {postcodeItems &&
          postcodeItems.map((item, index) => (
            <div className={styles.viewData__dataWrap}>
              <button
                type="button"
                className={styles.viewData__delete}
                onClick={() => {
                  handleDeleteData(item.postcodeNumber);
                }}
              >
                <FontAwesomeIcon icon={faTrashCan} />
              </button>
              <PostcodeDataItem
                key={index}
                postcode={item.postcodeNumber}
                suburb={convertToString(item)}
              />
            </div>
          ))}
      </div>
      <button
        type="button"
        className={styles.viewData__button}
        onClick={closeModal}
      >
        <FontAwesomeIcon icon={faTimes} />
      </button>
    </>
  );
};

export default ViewDataComponent;
