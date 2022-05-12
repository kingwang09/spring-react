import logo from './logo.svg';
import './App.css';
import React, {useState, useEffect, Component} from "react";
import Hello from "./components/Hello"

class App extends Component {
    render() {
        const helloComponent = <Hello />
        let greeting = "spring boot & react";
        return (
            <div>
                <h1>React Component</h1>
                <p>{greeting}</p>
                <ul>
                    <li>{helloComponent}</li>
                </ul>

            </div>
        );
    }
}

export default App;
