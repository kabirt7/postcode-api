import AddDataComponent from "../AddDataComponent/AddDataComponent";
import SearchComponent from "../SearchComponent/SearchComponent";
import FindPostcodeComponent from "../SearchComponent/SearchComponent";
import PortalButtons from "../PortalButtons/PortalButtons";
import ViewDataComponent from "../ViewDataComponent/ViewDataComponent";
import styles from "./Container.module.scss";
import { useContext, useState } from "react";
import { ToastContext } from "../../context/ToastContext";
import Toast from "../ToastComponent/ToastComponent";

const Container = () => {
  const { message } = useContext(ToastContext);
  const [displayHandler, setDisplayHandler] = useState<String | null>(null);

  const closeModal = () => {
    setDisplayHandler(null);
  };

  const addData = () => {
    setDisplayHandler("ADD");
  };

  const findSuburb = () => {
    setDisplayHandler("SUBURB");
  };

  const findPostcode = () => {
    setDisplayHandler("POSTCODE");
  };

  const viewData = () => {
    setDisplayHandler("VIEW");
  };

  return (
    <div className={styles.container}>
      {!displayHandler && (
        <PortalButtons shape={" "} clickFunction={addData}>
          Add Postcode Data
        </PortalButtons>
      )}
      {!displayHandler && (
        <PortalButtons shape={" "} clickFunction={findSuburb}>
          Find Suburb Name
        </PortalButtons>
      )}
      {!displayHandler && (
        <PortalButtons shape={" "} clickFunction={findPostcode}>
          Find Postcode Number
        </PortalButtons>
      )}
      {!displayHandler && (
        <PortalButtons shape={" "} clickFunction={viewData}>
          View Postcode Data
        </PortalButtons>
      )}
      {displayHandler == "ADD" && <AddDataComponent closeModal={closeModal} />}
      {displayHandler == "SUBURB" && (
        <SearchComponent closeModal={closeModal} type={displayHandler} />
      )}
      {displayHandler == "POSTCODE" && (
        <SearchComponent closeModal={closeModal} type={displayHandler} />
      )}
      {displayHandler == "VIEW" && (
        <ViewDataComponent closeModal={closeModal} />
      )}

      {message && <Toast message={message} />}
    </div>
  );
};

export default Container;
