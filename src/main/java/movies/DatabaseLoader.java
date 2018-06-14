package movies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DatabaseLoader implements CommandLineRunner {
    
	private final MovieRepository repository;
	private static final Logger log = LoggerFactory.getLogger(DatabaseLoader.class);
	
	@Autowired
	public DatabaseLoader(MovieRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		// save a couple of movies
		repository.save(new Movie(268, "Batman"));
		repository.save(new Movie(272, "Batman Begins"));
		
		// fetch all customers
		log.info("Movies found with findAll():");
		log.info("-------------------------------");
		for (Movie movie : repository.findAll()) {
			log.info(movie.toString());
		}
		log.info("");

	}

}
