import React, { Component } from 'react';
import { GET_REST_MY_MOVIES_URL,
		POST_REST_MY_MOVIES_URL,
		REST_MOVIES_RES,
	    SEARCH_REST_MOVIES_BY_TMDBID_URL,
	     GET_TMDB_MOVIE_URL_1,
	     GET_TMDB_MOVIE_URL_2,
	     TMDB_IMG_SIZE_MED
	   } from './Const'
import {Panel} from 'primereact/components/panel/Panel';
import {Rating} from 'primereact/components/rating/Rating';
import {Button} from 'primereact/components/button/Button';
import {InputTextarea} from 'primereact/components/inputtextarea/InputTextarea';
import { getRatingDescription, postData } from "./Utils"

class Movie extends Component {
    constructor(props) {
        super(props);
        
        this.state = {
            average: 0,
            id: -1,
            language: "",
            overview: "",
            release_date: "",
            title: "",
            tmdbId: this.props.tmdbId,
            rating: 0,
            review: "",
            isUserInfoModified: false,
            year: "",
            movieURL: ""
        }
        
        this.saveMovie = this.saveMovie.bind(this);
    }
    
    // Necessary, or else selecting another suggestion 
    //   in Autocomplete does not update Movie component
    componentWillReceiveProps(nextProps) {
    	  if (nextProps.tmdbId !== this.props.tmdbId) {
    		  this.getMovieInfo(nextProps.tmdbId);
    	}
    }
    
    componentDidMount() {
    	this.getMovieInfo(this.state.tmdbId);
    }
    

    
    saveMovie(event, state) {
    	// If the movie does not have a URL (does not exist on the server), then we use POST. Else we use PUT
    	const genre = this.state.genre;
    	const movieUrl = this.state.movieUrl;
    	const tmdbId = this.state.tmdbId;
    	const posterPath = this.state.posterPath;
    	const rating = this.state.rating;
    	const review = this.state.review;
    	const title = this.state.title;
    	const year = this.state.year;
    	
    	const hasPutURL = !(movieUrl === null || movieUrl === undefined || movieUrl === "");
    	const method = (hasPutURL) ? 'PUT' : 'POST';
    	const url = (hasPutURL) ? movieUrl : POST_REST_MY_MOVIES_URL;
    	const payload = { genre, tmdbId, title, year, rating, review, posterPath };
        
    	// 1. Save movie info (POST)
    	postData(url, payload, method)
          .then(response => {
        	  if (response.ok) {
        		  return response.json();
        	  } else { // Will break promise chain here
        		  throw new Error(response.statusText);
        	  }
          })
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
    
    getMovieInfo(tmdbId) {
    	// 1. Get movie public info (tmdb)
        fetch(GET_TMDB_MOVIE_URL_1 + tmdbId + GET_TMDB_MOVIE_URL_2)
          .then(response => {
        	  if (response.ok) {
        		  return response.json();
        	  } else { // Will break promise chain here
        		  throw new Error(response.statusText);
        	  }
          })
          .then(data => {
        	const genre = data.genres
        	   .map(g => g.name)
        	   .reduce((genres, genre) => genres + ", " + genre);
        	
        	const director = data.credits.crew.find(person => person.job.toLowerCase() === 'director').name;
        	
            this.setState(
	    		{
                average: data.vote_average,
                director: director,
                genre: genre,
                language: data.spoken_languages[0].name,
                overview: data.overview,
                posterPath: data.poster_path,
                release_date: data.release_date,
                title: data.title,
                year: data.release_date.slice(0, 4)
	            })
          })
          .catch(error => this.setState({ error }));
        
          // 2. Verify if movie exists on REST API and get its URL
          fetch(SEARCH_REST_MOVIES_BY_TMDBID_URL + tmdbId, {credentials: "include", mode: 'cors'})
          .then(response => {
        	  if (response.ok) {
        		  return response.json();
        	  } else { // Will break promise chain here
        		  throw new Error(response.statusText);
        	  }
          })
          .then(data => {
              this.setState(
      	         { movieUrl: REST_MOVIES_RES + data._embedded.movies[0].id }
      	      );
              console.log(data._links.self.href);
              console.log(data._embedded.movies[0].id);
              return REST_MOVIES_RES + data._embedded.movies[0].id;
          })
          // 3. Get movie user info from REST API
          .then(movieUrl => fetch(movieUrl, {credentials: "include", mode: 'cors'}))
          .then(response => {
        	  if (response.ok) {
        		  return response.json();
        	  } else { // Will break promise chain here
        		  throw new Error(response.statusText);
        	  }
          })
          .then(data => {
        	  this.setState(
    	    	{ rating: data.rating, review: data.review }
    	      ); 
          })
          .catch(error => this.setState({ error }));
    }
    
    
    render() {
    	const imageUrl = this.props.imgBaseUrl + TMDB_IMG_SIZE_MED + this.state.posterPath;
        console.log("lalala" + imageUrl);
    	return (
	        <Panel header={this.state.title}>
		        <table id="movieTable"
		        	   style={{background: 'url(' + imageUrl + ') no-repeat right top'
		        		       }}>
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
			              <th>Genre:</th>
			              <td>{this.state.genre}</td> 
			            </tr>
			            <tr>
			              <th>Director:</th>
			              <td>{this.state.director}</td> 
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
			                  {'(' + getRatingDescription(this.state.rating) + ')'}
                              </span>
			              </td> 
			            </tr>
			            <tr>
			              <th>Overview:</th>
			              <td><br /><br /><br />{this.state.overview}</td> 
			            </tr>
			            <tr>
			              <th>Review:</th>
			              <td><br/><InputTextarea rows={2} cols={60} autoResize={true} 
			                                 value={this.state.review} 
			                                 onChange={(event) => this.setState({ review: event.target.value, 
			                                	                                  isUserInfoModified: true })} />
			              </td> 
			            </tr>
			            <tr>
			              <th></th>
			              <td><Button icon="fa fa-check" cornerStyleClass="ui-button-success"
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