import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom'
import postData from "./Utils"
import Movie from "./Movie"
import { InputText } from 'primereact/components/inputtext/InputText';
import { Button } from 'primereact/components/button/Button';
import { AutoComplete } from 'primereact/components/autocomplete/AutoComplete';
import 'primereact/resources/primereact.min.css';
import 'primereact/resources/themes/omega/theme.css';
import 'font-awesome/css/font-awesome.css';

class Search extends Component {
    constructor(props) {
        super(props);

        this.state = {
            movies: [],
            titles: [],
            error: null,
            location: this.props.location
        }
        this.handleSearchButton = this.handleSearchButton.bind(this);
        this.handleSelectMovie = this.handleSelectMovie.bind(this);
    }
    
//    componentDidMount() {
//    	window.setInterval(() => {
//    		console.log(this.props.location);
//    	}, 500);
//    }
    handleSearchButton(event) {
    	setTimeout(() => {
	        fetch('https://api.themoviedb.org/3/search/movie?api_key=7f705cf4bbb5ffb5e56e76e86c09947f&query=' + event.query)
	        .then(response => response.json())
	        .then(data => {
	            const movies = [];
	            const titles = [];
	            data.results.forEach(movie => {
	            	const titleWithYear = movie.title + " - " + movie.release_date.slice(0, 4);
	                movies.push({title: titleWithYear, movieId: movie.id});
	            	titles.push(titleWithYear);
	            })
	            this.setState({ movies: movies, titles: titles, selected: false});
	        })
	        .catch(error => this.setState({ error }));
    	}, 50);
    }
    
    handleSelectMovie(event) {
    	const selectedMovie = this.state.movies.filter(movie => {
    		return (movie.title === event.value);
    	});
    	
    	this.setState({movieId: selectedMovie[0].movieId});
    	//console.log("MOVIE ID ::::: " + this.state.movieId);
    }

    render() {
    	let movie;
    	
    	if (this.state.movieId > 0)
    		movie = <Movie movieId={this.state.movieId} updateMovies={null}/>
    	else
    		movie = null;
    	
        return (
                <div>
	                <div className="ui-inputgroup">
		                <span className="ui-inputgroup-addon"><i className="fa fa-film"></i></span>
		                <AutoComplete value={this.state.title} 
		                              onChange={(event) => this.setState({title: event.value})}
		                              onSelect={this.handleSelectMovie}
		                              placeholder="Enter movie name..."
		                              suggestions={this.state.titles} 
		                              minLength={1}
		                              completeMethod={this.handleSearchButton}/>
	                </div>
	                <br /><br />
	                {movie}
                </div>
        )
    }
}

export default Search;