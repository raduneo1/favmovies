package movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Movie.class)
public class MovieEventHandler {
	MovieRepository repository;
	
	@Autowired
	public MovieEventHandler(MovieRepository repository) {
		this.repository = repository;
	}
  
  @HandleBeforeCreate
  @HandleBeforeSave
  public void handleMovieSave(Movie movie) {
	  if (this.repository.findByMovieId(movie.getMovieId()) != null) {
		  throw new DuplicateKeyException("Duplicate key is found");
	  }
  }
}