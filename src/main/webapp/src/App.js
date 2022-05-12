import logo from './logo.svg';
import './App.css';
import React, {useState, useEffect, Component} from "react";
import Hello from "./components/Hello";
import Axios from "./components/Axios";

class App extends Component {
    render() {
        const renderComponent = <Axios />
        let greeting = "spring boot & react";
        return (
            <div>
                <h1>React Component: {greeting}</h1>
                <ul>
                    <li>{renderComponent}</li>
                </ul>

            </div>
        );
    }
}

export default App;
