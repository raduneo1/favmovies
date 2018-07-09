package movies;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class DatabaseLoader implements CommandLineRunner {
    
	private final MovieRepository movies;
	private final UserRepository users;
	
	@Autowired
	public DatabaseLoader(MovieRepository movieRepository,
			              UserRepository userRepository) {
		this.movies = movieRepository;
		this.users = userRepository;
	}
	
	@Override
	public void run(String... strings) throws Exception {

		User radu = this.users.save(new User("radu", "radu",
							"ROLE_USER"));
		User james = this.users.save(new User("james", "james",
							"ROLE_USER"));

		SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken("radu", "radu",
				AuthorityUtils.createAuthorityList("ROLE_USER")));
        
		Movie movie1 = new Movie(192, "The Name of the Rose", 1986, radu);
		movie1.setRating(4);
		Movie movie2 = new Movie(242, "The Godfather: Part III", 1990, radu);
		Movie movie3 = new Movie(511, "The Promised Land", 1975, radu);
		
		this.movies.save(movie1);
		this.movies.save(movie2);
		this.movies.save(movie3);
//        radu.addMovies(movie1);
//        radu.addMovies(movie2);
//        radu.addMovies(movie3);
		SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken("james", "james",
				AuthorityUtils.createAuthorityList("ROLE_USER")));
		
		Movie movie4 = new Movie(100, "Mary mary", 1986, james);
		Movie movie5 = new Movie(200, "jones", 1990, james);
		Movie movie6 = new Movie(300, "jones", 1975, james);
		this.movies.save(movie4);
		this.movies.save(movie5);
		this.movies.save(movie6);
//		james.addMovies(movie4);
//		james.addMovies(movie5);
//		james.addMovies(movie6);
		
		SecurityContextHolder.clearContext();
	}

}
