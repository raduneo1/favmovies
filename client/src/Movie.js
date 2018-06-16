import React, { Component } from 'react';
import {Panel} from 'primereact/components/panel/Panel';
import {Rating} from 'primereact/components/rating/Rating';
import {Button} from 'primereact/components/button/Button';
import {InputTextarea} from 'primereact/components/inputtextarea/InputTextarea';
import postData from "./Utils"
import './index.css';

class Movie extends Component {
    constructor(props) {
        super(props);
        
        this.handleAddMovie = this.handleAddMovie.bind(this);
        
        this.state = {
            average: 0,
            id: -1,
            language: "",
            overview: "",
            release_date: "",
            title: "",
            movieId: this.props.movieId,
            rating: 0,
            review: ""
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

    handleAddMovie(event, movieId, title, rating, review) {	
    	postData('http://localhost:8080/api/movies', {movieId, title, rating, review})
    	  .then(data => {
    		  console.log(data); // JSON from `response.json()` call
    	  })
    	  .catch(error => console.error(error))
    }
    
    getMovieInfo(movieId) {
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
	            })
        })
        .catch(error => this.setState({ error, isLoading: false }));
    }
    
    render() {
        return (
	        <Panel header={this.state.title}>
		        <table id="movieTable">
			        <tbody>
			            <tr>
			              <th>Title:</th>
			              <th>{this.state.title}</th> 
			            </tr>
			            <tr>
			              <th>Release date:</th>
			              <th>{this.state.release_date}</th> 
			            </tr>
			            <tr>
			              <th>Language:</th>
			              <th>{this.state.language}</th> 
			            </tr>
			            <tr>
			              <th>Average:</th>
			              <th>{this.state.average}</th> 
			            </tr>
			            <tr>
			              <th>Your rating:</th>
			              <th><Rating value={this.state.rating} 
			                          onChange={(e) => this.setState({rating: e.value})} 
			                          stars={10}/>
			              </th> 
			            </tr>
			            <tr>
			              <th>Overview:</th>
			              <th>{this.state.overview}</th> 
			            </tr>
			            <tr>
			              <th>Review:</th>
			              <th><InputTextarea rows={3} cols={10} autoResize={true} 
			                                 value={this.state.review} 
			                                 onChange={(event) => this.setState({review: event.target.value})}/>
			              </th> 
			            </tr>
			            <tr>
			              <th></th>
			              <th><Button icon="fa-check" cornerStyleClass="ui-button-success"
			            	          disabled={this.state.review.length < 5}
			                          onClick={event => {this.handleAddMovie(event, this.props.movieId, 
			                        		                                 this.state.title, this.state.rating, 
			                        		                                 this.state.review)}}>
			                     Save
			                  </Button></th> 
			            </tr>
		            </tbody>
		        </table>
	        </Panel>
        )
    }
}

export default Movie;