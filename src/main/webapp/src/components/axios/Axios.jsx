import React, {Component} from "react";
import axios from "axios";
import AxiosList from "./AxiosList";
import AxiosForm from "./AxiosForm";

//https://www.digitalocean.com/community/tutorials/react-axios-react
/**
    Todo list
    - new form으로 신규 등록 시 목록을 다시 가져오도록 개선이 필요함.
*/
export default class Axios extends Component {
    render() {
        return (
            <div>
                <h2>Axios Examples</h2>
                <h3>List</h3>
                <AxiosList />

                <h3>new Form</h3>
                <AxiosForm />
            </div>
        )
    }
}