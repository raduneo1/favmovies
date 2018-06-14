import React, { Component } from 'react';
import postData from "./Utils"
import './index.css';

class Search extends Component {
    constructor(props) {
        super(props);

        this.state = {
            movies: [],
            isLoading: false,
            error: null
        }
        this.handleSearchButton = this.handleSearchButton.bind(this);
        this.handleAddMovie = this.handleAddMovie.bind(this);
    }
    
    handleSearchButton(event) {
    	if (event.type === "keypress" &&
    		event.key !== "Enter")
    		return;
    	
        this.setState({ isLoading: true });
        const searchField = encodeURI(this.search.value);
        
        if (!searchField || 0 === searchField.length) {
          this.setState({ movies: [], isLoading: false }) 
          return;
          }

        fetch('https://api.themoviedb.org/3/search/movie?api_key=7f705cf4bbb5ffb5e56e76e86c09947f&query=' + searchField)
        .then(response => response.json())
        .then(data => {
            const movies = [];
            data.results.forEach(movie => {
                movies.push({title: movie.title, movieId: movie.id});
            })
            this.setState({ movies: movies, isLoading: false }) 
        })
        .catch(error => this.setState({ error, isLoading: false }));
    }
    
    handleAddMovie(event, title, movieId) {
    	postData('http://localhost:8080/api/movies', {movieId: movieId, name: title, rating: -1, review: ""})
    	  .then(data => {
    		  console.log(data); // JSON from `response.json()` call
    		  this.setState({ movies: [], isLoading: false }) ;
    	  })
    	  .catch(error => console.error(error))
    }

    render() {
        return (
            <div id="myDropdown" className="dropdown-content">
                <input type="text" 
                       placeholder="Search.."
                       id="myInput" 
                       ref= {input => this.search = input}
                       onKeyPress={this.handleSearchButton}/>
                <button onClick={this.handleSearchButton}>
                   Search
                </button>
                {this.state.movies.map(movie =>
                  <a onClick={(event) => this.handleAddMovie(event, movie.title, movie.movieId)}
                     key = {movie.movieId}>
                     {movie.title}
                  </a>
                )}
            </div>
        )
    }
}

export default Search;