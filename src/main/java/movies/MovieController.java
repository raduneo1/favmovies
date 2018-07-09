package movies;

import java.net.URI;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RepositoryRestController
@RequestMapping(value = "/movies")
public class MovieController {	
	private MovieRepository movieRepository;
	private UserRepository userRepository;
	
	@Autowired
	public MovieController(MovieRepository movieRepository, UserRepository userRepository) {
		this.movieRepository = movieRepository;
		this.userRepository = userRepository;
	}
	
	@GetMapping("/movieByTmdbIdOfCurUser")
	public @ResponseBody ResponseEntity<?>  movieByTmdbIdOfCurUser(@Param(value = "tmdbId") int tmdbId,
			                                                             Principal principal) {
        List<Movie> producers = movieRepository.findByTmdbIdAndUser_Name(tmdbId, principal.getName());
        Resources<Movie> resources = new Resources<Movie>(producers); 

        return ResponseEntity.ok(resources); 
	}
	
	@GetMapping("/curMovies")
	public @ResponseBody ResponseEntity<?>  curMovies(Principal principal) {
        List<Movie> producers = movieRepository.findByUser_Name(principal.getName());
        Resources<Movie> resources = new Resources<Movie>(producers); 

        return ResponseEntity.ok(resources); 
	}

}
