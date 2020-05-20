import React, { Component } from 'react';
import './App.css';
import Home from "./Home";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import ItemList from "./itemView/ItemList";
import ItemEdit from "./itemView/ItemEdit";

class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path="/" exact={true} component={Home} />
                    <Route path="/items" exact={true} component={ItemList} />
                    <Route path="/item/:id" component={ItemEdit} />
                </Switch>
            </Router>
        )
    }
}

export default App;
