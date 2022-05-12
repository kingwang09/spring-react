import React, {Component} from "react";

class Hello extends Component {
    constructor(props) {
        super(props);
        this.state = {counter: 0};
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick(){
        console.log("Hello Component click event..", this.state.counter);
        this.setState((state, props) => {
            return {counter: state.counter + 1};
        });
    }

    render() {
        return (
            <div>
                <p onClick={this.handleClick}>Hello Component</p>
            </div>
        )
    }
}

export default Hello;