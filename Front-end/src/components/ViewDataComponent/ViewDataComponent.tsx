import styles from "./ViewDataComponent.module.scss";
import { useEffect, useState } from "react";
import { deleteData, getAllData } from "../../services/logic";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTimes, faTrashCan } from "@fortawesome/free-solid-svg-icons";
import { PostcodeDataPair } from "../../services/interfaces";
import PostcodeDataItem from "../PostcodeDataItem.jsx/PostcodeDataItem";

interface ViewDataComponentProps {
  closeModal: React.MouseEventHandler<HTMLButtonElement>;
}

const ViewDataComponent: React.FC<ViewDataComponentProps> = ({
  closeModal,
}) => {
  const [postcodeItems, setPostcodeItems] = useState<null | PostcodeDataPair[]>(
    null
  );

  const handleDeleteData = async (postcode: Number) => {
    try {
      await deleteData(postcode);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data: PostcodeDataPair[] = await getAllData();
        setPostcodeItems(data);
        console.log(data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, [handleDeleteData]);

  return (
    <>
      <div className={styles.viewData}>
        {postcodeItems &&
          postcodeItems.map((item, index) => (
            <div className={styles.viewData__dataWrap}>
              <button
                type="button"
                className={styles.viewData__delete}
                onClick={() => handleDeleteData(item.postcodeNumber)}
              >
                <FontAwesomeIcon icon={faTrashCan} />
              </button>
              <PostcodeDataItem
                key={index}
                postcode={item.postcodeNumber}
                suburb={item.suburbName}
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
