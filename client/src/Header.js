import React, { Component } from 'react';
import Search from './Search'
import { Link, withRouter } from 'react-router-dom'
import { InputText } from 'primereact/components/inputtext/InputText';
import { Button } from 'primereact/components/button/Button';
import 'primereact/resources/primereact.min.css';
import 'primereact/resources/themes/omega/theme.css';
import 'font-awesome/css/font-awesome.css';

class Header extends Component {
    render() {
      return (
	  	  <header>
		    <nav>
	          <div className="ui-g ui-fluid">
	              <div className="ui-g-12 ui-md-4">
	                  <div className="ui-inputgroup">
	                      <Button icon="fa-home" cornerStyleClass="ui-button-secondary"><Link to='/'>Home</Link></Button>
	                      <Button icon="fa-search" cornerStyleClass="ui-button-secondary"><Link to='/search'>Search</Link></Button>
	                      <Button icon="fa-star" cornerStyleClass="ui-button-secondary"><Link to='/movies'>My Movies</Link></Button>
	                  </div>
	              </div>
	          </div>
		    </nav>
	     </header>
        )
    }
}

export default Header;