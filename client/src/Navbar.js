import React, { Component } from 'react';
import Search from './Search'
import './index.css';

class Navbar extends Component {

    render() {
        return (
            <div className="btn-group">
                <br/>
                <Search />
                <button>My movies</button>
            </div>
        )
    }
}

export default Navbar;