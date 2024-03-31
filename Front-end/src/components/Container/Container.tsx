import AddDataComponent from "../AddDataComponent/AddDataComponent";
import PortalButtons from "../PortalButtons/PortalButtons";
import styles from "./Container.module.scss";
import { useState } from "react";

const Container = () => {
  const [displayHandler, setDisplayHandler] = useState<String | null>(null);

  const closeModal = () => {
    setDisplayHandler(null);
  };

  const addData = () => {
    setDisplayHandler("ADD");
  };
  return (
    <div className={styles.container}>
      {!displayHandler && (
        <PortalButtons shape={" "} clickFunction={addData}>
          Add Postcode Data
        </PortalButtons>
      )}
      {!displayHandler && (
        <PortalButtons
          shape={" "}
          clickFunction={function (arg: any) {
            throw new Error("Function not implemented.");
          }}
        >
          Find Suburb Name
        </PortalButtons>
      )}
      {!displayHandler && (
        <PortalButtons
          shape={" "}
          clickFunction={function (arg: any) {
            throw new Error("Function not implemented.");
          }}
        >
          Find Postcode Number
        </PortalButtons>
      )}
      {!displayHandler && (
        <PortalButtons
          shape={" "}
          clickFunction={function (arg: any) {
            throw new Error("Function not implemented.");
          }}
        >
          View Postcode Data
        </PortalButtons>
      )}
      {displayHandler == "ADD" && <AddDataComponent closeModal={closeModal} />}
    </div>
  );
};

export default Container;
