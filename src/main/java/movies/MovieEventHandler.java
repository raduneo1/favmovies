package movies;

import static movies.WebSocketConfiguration.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Movie.class)
public class MovieEventHandler {
	private final MovieRepository repository;
	private final SimpMessagingTemplate websocket;
	private final EntityLinks entityLinks;
	
	private final UserRepository userRepository;

	@Autowired
	public MovieEventHandler(MovieRepository repository, 
			                 SimpMessagingTemplate websocket, 
			                 EntityLinks entityLinks,
			                 UserRepository userRepository) {
		this.repository = repository;
		this.websocket = websocket;
		this.entityLinks = entityLinks;
		this.userRepository = userRepository;
	}
  


    @HandleAfterCreate
    public void handleAfterCreate(Movie movie) {
  	    if (!movie.getReview().isEmpty()) {
  	      String message = "User reviewed \'" + movie.getTitle() + "\' (" + movie.getRating() + "//10)";
		  this.websocket.convertAndSend(
				MESSAGE_PREFIX + "/newReview", "{\"message\" : \"" + message + "\"}");
	    }
    }
    
    @HandleAfterSave
    public void handleAfterSave(Movie movie) {
  	    if (!movie.getReview().isEmpty()) {
  	      String message = "User reviewed \'" + movie.getTitle() + "\' (" + movie.getRating() + "//10)";
		  this.websocket.convertAndSend(
				MESSAGE_PREFIX + "/changeReview", "{\"message\" : \"" + message + "\"}");
	    }
    }
    
	@HandleBeforeCreate
	@HandleBeforeSave
	public void applyUserInformationUsingSecurityContext(Movie movie) {

		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = this.userRepository.findByName(name);
		System.out.println("Event usersssssssssssssss");
		System.out.println(name);
		
		if (user == null) {
			System.out.println("New user");
			User newUser = new User();
			newUser.setName(name);
			newUser.setRoles(new String[]{"ROLE_MANAGER"});
			user = this.userRepository.save(newUser);
		}
		movie.setUser(user);
	}

}