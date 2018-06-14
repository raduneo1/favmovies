package movies;

import javax.persistence.*;

@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private int movieId;
	private String name = "Unknown";
	private int rating;
	private String review;
	
	public Movie() {}
	
	public Movie(int movieId, String name) {
		this.movieId = movieId;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
	
	
	public String toString() {
		return movieId + " : " + name;
	}
		
}
