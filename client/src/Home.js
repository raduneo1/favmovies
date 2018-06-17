import React, { Component } from 'react';
import SockJsClient from 'react-stomp';
import {OrderList} from 'primereact/components/orderlist/OrderList';

class Home extends Component {
	constructor(props) {
		super(props);
		
        this.state = {
            reviews: [],
            error: {}
        };
        
        this.getReviews = this.getReviews.bind(this);
        this.reviewTemplate = this.reviewTemplate.bind(this);
		
	}
	
	componentDidMount() {
//		stompClient.register([
//			{route: '/topic/reviews', callback: () => console.log(" ")}
//		]);
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
    
    reviewTemplate(movie) {        
        return (
            <div className="ui-helper-clearfix">
                <img src={""} alt={movie.title} style={{ display: 'inline-block', margin: '2px 0 2px 2px', width:48 }} />
                <div style={{ fontSize: '14px', float: 'right', margin: '15px 5px 0 0' }}>{movie.title} - {movie.year} - {movie.review}</div>
            </div>
        );
    }
    
    render() {
        return (
            <div>
               There is a lot of information about movies here.
               There is a lot of information about movies here.
               There is a lot of information about movies here.
               There is a lot of information about movies here.
               There is a lot of information about movies here.
               
               <br />
               <OrderList value={this.state.reviews} dragdrop={true} itemTemplate={this.reviewTemplate}
			              responsive={true} header="Responsive Reviews" listStyle={{ height: '20em' }} 
			              onChange={(e) => this.setState({reviews: e.value})}>
               </OrderList>
               
               <SockJsClient 
	               url = 'http://localhost:8080/reviews/'
	               topics={['/topic/newReview']} 
	               onConnect={console.log("Connection established!")} 
	               onDisconnect={console.log("Disconnected!")}
	               onMessage={() => this.getReviews()}
	               debug= {true}
	             /> 
            </div>
        )
    }
}

export default Home;