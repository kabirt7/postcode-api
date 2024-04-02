import "./App.scss";
import Container from "./components/Container/Container";

function App() {
  return (
    <section className="home">
      <h1 className="home__title">Postcode Database</h1>
      <div className="home__container">
        <Container />
      </div>
    </section>
  );
}

export default App;
