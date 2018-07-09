import React, { Component } from 'react';
//const logo = require("./logo.svg");
//import './index.css';
import Nav from './Nav'
import Main from './Main'
import 'primereact/resources/primereact.min.css';
import 'primereact/resources/themes/omega/theme.css';
import 'font-awesome/css/font-awesome.css';
import 'primeicons/primeicons.css';
//import './index.css';
//import logo from './logo.svg';
//import css from './index.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <h1 className="App-title">Favorite movies application</h1>
        </header>
        <Nav/>
        <Main/>
      </div>
    );
  }
}

export default App;
