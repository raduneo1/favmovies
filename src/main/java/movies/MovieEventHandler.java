package movies;

import static movies.WebSocketConfiguration.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Movie.class)
public class MovieEventHandler {
	private final MovieRepository repository;
	private final SimpMessagingTemplate websocket;
	private final EntityLinks entityLinks;
	
	@Autowired
	public MovieEventHandler(MovieRepository repository, 
			                 SimpMessagingTemplate websocket, 
			                 EntityLinks entityLinks) {
		this.repository = repository;
		this.websocket = websocket;
		this.entityLinks = entityLinks;
	}
  
    @HandleBeforeCreate
    public void handleBeforeCreate(Movie movie) {
	    if (this.repository.findByMovieId(movie.getMovieId()) != null) {
		    throw new DuplicateKeyException("Duplicate key is found");
	    }
    }
  
    @HandleAfterCreate
    public void handleAfterCreate(Movie movie) {
  	    if (!movie.getReview().isEmpty()) {
		  this.websocket.convertAndSend(
				MESSAGE_PREFIX + "/newReview", "{\"message\" : \"message\"}");
	    }
    }

	/**
	 * Take an {@link Employee} and get the URI using Spring Data REST's {@link EntityLinks}.
	 *
	 * @param employee
	 */
	private String getPath(Movie movie) {
		return this.entityLinks.linkForSingleResource(movie.getClass(),
				  movie.getId()).toUri().getPath();
	}
}