import React,{Component, useState} from "react";
import axios from "axios";

export default class AxiosForm extends Component {
    constructor(props){
        super(props);
        this.state = {
            name: '',
            greeting: ''
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(e){
        console.log("handleChange: ", e);
        let values = {};
        values[e.target.name] = e.target.value;
        console.log("values: ", values);
        console.log("state: ", this.state);
        this.setState(values);
    }

    handleSubmit(e){
        console.log("handleSubmit : ", e);
        e.preventDefault();

        const greet = {
            name: this.state.name,
            greeting: this.state.greeting
        };
        console.log("save greet :", greet);

        axios.post('http://localhost:8080/api/v1/greet', greet)
            .then(res => {
                console.log("save result : ", res);
            })
    }

    render(){
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <label>
                        name: <input type="text" name="name" onChange={this.handleChange} value={this.state.name}/>
                     </label>
                     <label>
                         greeting: <input type="text" name="greeting" onChange={this.handleChange} value={this.state.greeting}/>
                      </label>
                      <button type="submit">Add</button>
                </form>
            </div>
        )
    }

}