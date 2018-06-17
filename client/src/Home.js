import React, { Component } from 'react';
import {Card} from 'primereact/components/card/Card';
import SockJsClient from 'react-stomp';

class Home extends Component {
	constructor(props) {
		super(props);
		
        this.state = {
            reviews: [],
            error: {}
        };
        
        this.getReviews = this.getReviews.bind(this);
		
	}
	
	componentDidMount() {
		this.getReviews();
	}
	
    
    getReviews() {
        fetch('http://localhost:8080/api/movies/search/findByReviewNot?review=')
        .then(response => response.json())
        .then(data => {
            const movies = [];
            data._embedded.movies.forEach(movie => {
                movies.push({
                	title: movie.title, 
                	review: movie.review,
                	year: movie.year, 
                	rating: movie.rating, 
                	movieId: movie.movieId
                });
            });
            this.setState({ reviews: movies});
        	console.log("GETTING REVIEWS");
        })
        .catch(error => console.log(error));
    }
    
    render() {
        return (
            <div>
               <h3>Latest reviews</h3>
               
               <ul>
               {this.state.reviews.map(review => {
            	   return (
            	     <li className="review">
            	       <Card title={review.title} subtitle={review.rating} style={{width: '360px'}} className="ui-card-shadow">
                         <div>{review.review}</div>
                       </Card>
                       <br />
                     </li>)
               })}
               </ul>


            </div>
        )
    }
}

export default Home;