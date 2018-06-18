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

}
