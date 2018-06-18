package movies;

import java.net.URI;
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
	private MovieRepository repository;
	
	@Autowired
	public MovieController(MovieRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/movie_user")
	public @ResponseBody ResponseEntity<?>  readMovie(@Param(value = "name") String name, 
			                                          @Param(value = "movieId") int movieId) {
        List<Movie> producers = repository.findByMovieId(movieId);
        //
        System.out.println(producers.size());
//        List<Movie> movies = repository.findByMovieId(movieId);
//        for (Movie movie : movies) {
//        	System.out.println(movies[.get(0).getUser().getName());
//        }
//        repository.findByMovieId(movieId);
//		.stream()
//		.filter(movie -> movie.getUser().getName().equals(name))	
//   		.limit(1)
//		.collect(Collectors.toList())
//        System.out.println("TEEEEEEEEEEEEEEEEEEEEEEEEEEEEST");
//        producers.forEach(System.out::println);

        Resources<Movie> resources = new Resources<Movie>(producers); 

        return ResponseEntity.ok(resources); 
	}

}
