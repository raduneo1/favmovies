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
							"ROLE_MANAGER"));
		User james = this.users.save(new User("james", "james",
							"ROLE_MANAGER"));

		SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken("radu", "doesn't matter",
				AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

		this.movies.save(new Movie(192, "Batman Begins", 2006, radu));
		this.movies.save(new Movie(242, "Spiderman", 2001, radu));
		this.movies.save(new Movie(511, "Untouchables", 1997, radu));

		SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken("james", "doesn't matter",
				AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

		this.movies.save(new Movie(192, "Batman Begins", 2006, james));
		this.movies.save(new Movie(242, "Spiderman", 2001, james));
		this.movies.save(new Movie(511, "Untouchables", 1997, james));
		
		SecurityContextHolder.clearContext();
	}

}
