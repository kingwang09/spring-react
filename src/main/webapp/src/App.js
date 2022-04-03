import logo from './logo.svg';
import './App.css';
import React, {useState, useEffect, Component} from "react";

class App extends Component {

  render() {
    let greeting = "hello world!";
    return (
        <div>
          <h1>{greeting}</h1>
        </div>
    );
  }
}

export default App;
