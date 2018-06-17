import React, { Component } from 'react';
import {Panel} from 'primereact/components/panel/Panel';
import {Rating} from 'primereact/components/rating/Rating';
import {Button} from 'primereact/components/button/Button';
import {InputTextarea} from 'primereact/components/inputtextarea/InputTextarea';
import { getRatingDescription, postData } from "./Utils"
import './index.css';

class Movie extends Component {
    constructor(props) {
        super(props);
        
        this.saveMovie = this.saveMovie.bind(this);
        
        this.state = {
            average: 0,
            id: -1,
            language: "",
            overview: "",
            release_date: "",
            title: "",
            movieId: this.props.movieId,
            rating: 0,
            review: "",
            isUserInfoModified: false,
            year: "",
            movieURL: ""
        }
        
    }
    

    componentWillReceiveProps(nextProps) {
    	  if (nextProps.movieId !== this.props.movieId) {
    		  this.getMovieInfo(nextProps.movieId);
    	}
    }
    
    componentDidMount() {
    	this.getMovieInfo(this.state.movieId);

    }

    saveMovie(event, state) {
    	// If the movie does not have a URL (does not exist on the server), then we use POST. Else we use PUT
    	const movieUrl = this.state.movieUrl;
    	const movieId = this.state.movieId;
    	const title = this.state.title;
    	const year = this.state.year;
    	const rating = this.state.rating;
    	const review = this.state.review;
    	
    	const hasPutURL = !(movieUrl === null || movieUrl === undefined || movieUrl === "");
    	const method = (hasPutURL) ? 'PUT' : 'POST';
    	const url = (hasPutURL) ? movieUrl : 'http://localhost:8080/api/movies';
        
    	// 1. Save movie info (POST)
    	postData(url, {movieId, title, year, rating, review}, method)
    	  .then(response => response.json()) // parses response to JSON  
    	  .then(data => {
    		  console.log(data); // JSON from `response.json()` call
    		  this.setState({ isUserInfoModified: false});
    		  
    		  // 2. Get updated parent myMovies info (GET)
    		  if (this.props.updateMovies !== undefined &&
    		      this.props.updateMovies !== null) {
    		     this.props.updateMovies();
    		  }
    	  })
    	  .catch(error => console.error(error))
    }
    
    getMovieInfo(movieId) {
    	// Get movie public info (tmdb)
        fetch('https://api.themoviedb.org/3/movie/' + movieId + '?api_key=7f705cf4bbb5ffb5e56e76e86c09947f')
          .then(response => response.json())
          .then(data => {
            this.setState(
	    		{
                average: data.vote_average,
                language: data.spoken_languages[0].name,
                overview: data.overview,
                release_date: data.release_date,
                title: data.title,
                year: data.release_date.slice(0, 4)
	            })
          })
          .catch(error => this.setState({ error }));
        
          //verify if movie is saved on the server
          fetch('http://localhost:8080/api/movies/search/findByMovieId?movieId=' + movieId)
          .then(response => {
        	  if (response.status === 200) {
        		  return response.json();
        	  } else { // Will break promise chain here
                  this.setState(
          	    	{ rating: 0, review: "" }
          	      ); 
        		  throw new Error('The movie does not exist on the server');
        	  }
          })
          .then(data => {
              this.setState(
      	         { movieUrl: data._links.self.href }
      	      );
              console.log(data._links.self.href);
              return data._links.self.href;
          })
          // Get movie user info (server)
          .then(movieUrl => fetch(movieUrl))
          .then(response => response.json())
          .then(data => {
        	  this.setState(
    	    	{ rating: data.rating, review: data.review }
    	      ); 
          })
          .catch(error => this.setState({ error }));
    }
    
    
    render() {
        return (
	        <Panel header={this.state.title}>
		        <table id="movieTable">
			        <tbody>
			            <tr>
			              <th>Title:</th>
			              <td>{this.state.title}</td> 
			            </tr>
			            <tr>
			              <th>Release date:</th>
			              <td>{this.state.release_date}</td> 
			            </tr>
			            <tr>
			              <th>Language:</th>
			              <td>{this.state.language}</td> 
			            </tr>
			            <tr>
			              <th>Average:</th>
			              <td>{this.state.average}</td> 
			            </tr>
			            <tr>
			              <th>Your rating:</th>
			              <td><span><Rating value={this.state.rating}
                                      stars={10}
			                          onChange={event => this.setState({ rating: event.value, 
			                        	                                 isUserInfoModified: true })} />
			                  {'  (' + getRatingDescription(this.state.rating) + ')'}
                              </span>
			              </td> 
			            </tr>
			            <tr>
			              <th>Overview:</th>
			              <td>{this.state.overview}</td> 
			            </tr>
			            <tr>
			              <th>Review:</th>
			              <td><br/><InputTextarea rows={2} cols={50} autoResize={true} 
			                                 value={this.state.review} 
			                                 onChange={(event) => this.setState({ review: event.target.value, 
			                                	                                  isUserInfoModified: true })} />
			              </td> 
			            </tr>
			            <tr>
			              <th></th>
			              <td><Button icon="fa-check" cornerStyleClass="ui-button-success"
			            	          disabled={!this.state.isUserInfoModified}
			                          onClick={this.saveMovie}>
			                    Save
			                  </Button>
			              </td>
			            </tr>
		            </tbody>
		        </table>
	        </Panel>
        )
    }
}

export default Movie;