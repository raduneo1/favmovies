import React, { Component } from 'react';
import postData from "./Utils"
import './index.css';

class MyMovies extends Component {
    constructor(props) {
        super(props);

        this.state = {
            movies: [],
            error: {}
        }
        //this.handleSearchButton = this.handleSearchButton.bind(this);
    }
    
    componentDidMount() {
        fetch('http://localhost:8080/api/movies')
        .then(response => response.json())
        .then(data => {
            const movies = [];
            data._embedded.movies.forEach(movie => {
            	console.log(movie.movieId);
                movies.push({title: movie.name, movieId: movie.movieId});
            })
            this.setState({ movies: movies}) 
        })
        .catch(error => this.setState({ error }));
    }

    render() {
        return (
        	<ul>
            {this.state.movies.map(movie =>
	            <li key = {movie.movieId}>
	               {movie.title}
	            </li>
            )}
        	</ul>
        )
    }
}

export default MyMovies;