package movies;

import javax.persistence.*;

import java.io.Serializable;
import java.util.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	@Id 
	@GeneratedValue
	private Long userId;
    //fetch = FetchType.LAZY, 
	@OneToMany(mappedBy = "user")
	private Set<Movie> movies = new HashSet<>();
	 
	private String name;
    
	private String[] roles;
	
	private @JsonIgnore String password;

	protected User() {}

	public User(String name, String password, String... roles) {
		this.setName(name);
		this.setPassword(password);
		this.setRoles(roles);
	}

    public Set<Movie> getMovies() {
        return this.movies;
    }
    
    public void addMovies(Movie movie) {
    	this.movies.add(movie);
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

}