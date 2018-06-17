package movies;

import javax.persistence.*;

@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private int movieId;
	private String title = "Unknown";
	private int rating;
	private String review = "";
	public int year;
	
	public Movie() {}
	
	public Movie(int movieId, String name) {
		this.movieId = movieId;
		this.title = name;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String toString() {
		return movieId + " : " + title;
	}
		
}
