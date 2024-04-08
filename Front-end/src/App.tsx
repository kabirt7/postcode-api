import { useContext } from "react";
import "./App.scss";
import Container from "./components/Container/Container";
import ToastContextProvider, { ToastContext } from "./context/ToastContext";
import Toast from "./components/ToastComponent/ToastComponent";

function App() {
  return (
    <ToastContextProvider>
      <section className="home">
        <h1 className="home__title">Postcode Database</h1>
        <div className="home__container">
          <Container />
        </div>
      </section>
    </ToastContextProvider>
  );
}

export default App;
