import React,{Component} from "react";
import axios from "axios";

export default class AxiosList extends Component {
        constructor(props){
            super(props);
            this.state = {
                greets : []
            }
        }

        componentDidMount() {
            console.log("component did mount..");
            axios.get("http://localhost:8080/api/v1/greet")
                .then(res => {
                    console.log("response : ", res);
                    const data = res.data;
                    this.setState({
                        greets : data
                    })
                })
        }

        render() {
            console.log("render..")
            const list = this.state.greets.length == 0 ?
                <li>목록이 존재하지 않습니다.</li> : (
                    this.state.greets.map(greet =>
                        <li key={greet.id}>{greet.name}, {greet.greeting}</li>
                    )
                );

            return (
                <div>
                    <ul>
                        {
                            list
                        }
                    </ul>
                </div>
            )
        }
}