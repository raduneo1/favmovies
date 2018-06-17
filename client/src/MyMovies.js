import React, { Component } from 'react';
import Movie from "./Movie"
import postData from "./Utils"
import {DataTable} from 'primereact/components/datatable/DataTable';
import {Column} from 'primereact/components/column/Column';
import {Button} from 'primereact/components/button/Button';
import {InputText} from 'primereact/components/inputtext/InputText';
import {OverlayPanel} from 'primereact/components/overlaypanel/OverlayPanel';
import './index.css';

class MyMovies extends Component {
    constructor(props) {
        super(props);

        this.state = {
            movies: [],
            error: {}
        }
        
        this.getFavoriteMovies = this.getFavoriteMovies.bind(this);
        this.rowExpansionTemplate = this.rowExpansionTemplate.bind(this);
    }
    
    componentDidMount() {
    	this.getFavoriteMovies();
    }
    
    getFavoriteMovies() {
        fetch('http://localhost:8080/api/movies')
        .then(response => response.json())
        .then(data => {
            const movies = [];
            data._embedded.movies.forEach(movie => {
                movies.push({
                	title: movie.title, 
                	year: movie.year, 
                	rating: movie.rating, 
                	movieId: movie.movieId
                });
            })
            this.setState({ movies: movies});
        	console.log("GETTING MOVIES");
        })
        .catch(error => this.setState({ error }));
        
        //console.log(this.state.expandedRows);
    }
    
    rowExpansionTemplate(data) {
        return  <div className="ui-g ui-fluid">
	               <div>
	                 <Movie movieId={data.movieId} updateMovies={this.getFavoriteMovies}/>
	               </div>
                </div>;
    }
    
    render() {
	    const header = <div style={{'textAlign':'left'}}>
	        <div>
	             <h3>Favorite Movies <Button icon="fa-refresh" style={{'float':'right'}} 
	                                         onClick={this.getFavoriteMovies}/></h3> 
	        </div>
	        <br/>
	        <div className="ui-inputgroup">
		        <span className="ui-inputgroup-addon"><i className="fa fa-search"></i></span>
		        <InputText type="search" onInput={(e) => this.setState({globalFilter: e.target.value})} 
		                   placeholder="Global Search" size="30"/>
	        </div>
	    </div>;
	    
    	return (
            <div className="content-section implementation">
	            <DataTable value={this.state.movies} globalFilter={this.state.globalFilter} 
	                       header={header} 
                           responsive={true}
	                       expandedRows={this.state.expandedRows} 
	                       onRowToggle={(e) => this.setState({expandedRows:e.data})}
	                       rowExpansionTemplate={this.rowExpansionTemplate}>
	                <Column expander={true} style={{width: '2em'}} />
	                <Column field="title" header="Title" sortable={true}/>
	                <Column field="year" header="Year" sortable={true}/>
	                <Column field="rating" header="Rating" sortable={true}/>
	            </DataTable>
            </div>
    	  )
    }
}

export default MyMovies;