import React, { Component } from 'react';
import { Router, Route, Switch, withRouter } from 'react-router-dom'
import Home from './Home'
import Movie from './Movie'
import MyMovies from './MyMovies'
import Search from './Search'

class Main extends Component {

    render() {
        return (
		  <main>
		  <div className="ui-g ui-fluid">
          <div className="ui-g-12 ui-md-6">
		    <Switch>
		      <Route exact path='/' component={Home}/>
		      <Route path='/search' component={Search}/>
		      <Route path='/movies' component={MyMovies}/>
		    </Switch>
		      </div>
		      </div>
		  </main>
        )
    }
}

export default Main;