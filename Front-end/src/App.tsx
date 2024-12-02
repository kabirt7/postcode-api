import "./App.scss";
import Container from "./components/Container/Container";
import ToastContextProvider from "./context/ToastContext";

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
