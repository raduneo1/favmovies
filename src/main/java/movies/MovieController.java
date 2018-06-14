package movies;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RepositoryRestController
@RequestMapping(value = "/movies")
public class MovieController {	
	private MovieRepository repository;
	
	@Autowired
	public MovieController(MovieRepository repository) {
		this.repository = repository;
	}
	
//    @GetMapping("/movie{id}")
//    public Movie getMovie(@PathVariable int id) {
//        //return repository.findByMovieId(id);
//    	return new Movie(100, "Star Wars");
//    }
//	  
//	@GetMapping("/movies")
//    public List<Movie> movies() {
//		System.out.println(repository.findAll().toString());
//        return (List<Movie>) repository.findAll();
//    }
//	
//	@PostMapping(value = "/movies")
//	public ResponseEntity<?> add(@RequestBody Movie movie) {
//	    // Code that uses the employee object	    
//		System.out.println("testing");
//	    if (this.repository.findByMovieId(movie.getMovieId()) != null) {
//	    	Movie result = repository.save(new Movie(movie.getId(), movie.getName()));
//
//			URI location = ServletUriComponentsBuilder
//				.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(result.getId()).toUri();
//
//			return ResponseEntity.created(location).build();
//	    }
//				
//	    return ResponseEntity.noContent().build();
//	}

}
