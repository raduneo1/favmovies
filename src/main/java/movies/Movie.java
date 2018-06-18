package movies;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Movie {
	
	private @ManyToOne User user;
	
	private String genre = "";
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@LastModifiedDate
	private LocalDateTime modifiedTime;
	
	@Positive(message = "MovieId must be positive.")
	private int movieId;
	
	@Min(value = 0, message = "Rating cannot be less than 0.")
	@Max(value = 10, message = "Rating cannot be more than 10.")
	private int rating = 0;
	private String posterPath;
	
	@Column(name = "review", nullable = false, length = 500)
	private String review = "";
	
	@NotBlank(message = "Movie title cannot be blank.")
	private String title = "Unknown";
	
	@Min(value = 1900, message = "Enter a valid movie release year")
	@Max(value = 2050, message = "Enter a valid movie release year")
	private int year;


	public Movie() {}
	
	public Movie(int movieId, String title, int year, User user) {
		this.movieId = movieId;
		this.title = title;
		this.year = year;
	}
	
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
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
	
	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}
		
	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
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
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
