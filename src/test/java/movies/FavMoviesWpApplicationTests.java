package movies;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FavMoviesWpApplication.class})
@WebAppConfiguration(value="src/main/js")
public class FavMoviesWpApplicationTests {

	protected HttpMessageConverter mappingJackson2HttpMessageConverter;
	protected MockMvc mockMvc;
	protected MediaType contentType = new MediaType(org.springframework.hateoas.MediaTypes.HAL_JSON.getType(),
			org.springframework.hateoas.MediaTypes.HAL_JSON.getSubtype(),
            Charset.forName("utf8"));
	@Autowired
	protected WebApplicationContext webApplicationContext;
	@Autowired
	protected MovieRepository movieRepository;
	@Autowired
	protected UserRepository userRepository;
	protected User user1;
	protected User user2;
	protected List<Movie> movies;
	protected String userName1 = "obediah";
	protected String userName2 = "sdfsggg";
	static boolean isInitialized = false;
	
	@Test
	public void contextLoads() {
	}
	

	@Autowired
    public void setConvertersTest(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);
    }
	
    @Before
    public void setup() throws Exception {
//    	if (isInitialized) {
//    		return;
//    	}
    	
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
        
		this.movieRepository.deleteAll();
        //this.userRepository.deleteAll();
        this.movies = new ArrayList<Movie>();

        this.user1 = userRepository.save(new User(userName1, "password"));
        this.user2 = userRepository.save(new User(userName2, "password"));
        SecurityContextHolder.getContext().setAuthentication(
    			new UsernamePasswordAuthenticationToken(userName1, "password",
    				AuthorityUtils.createAuthorityList("ROLE_USER")));
        
        this.movies.add(movieRepository.save(new Movie(123,"End of days", 1999, this.user1)));
        
        SecurityContextHolder.getContext().setAuthentication(
    			new UsernamePasswordAuthenticationToken(userName2, "password",
    				AuthorityUtils.createAuthorityList("ROLE_USER")));
        this.movies.add(movieRepository.save(new Movie(456,"Lion King", 1980, this.user2)));
        this.movies.add(movieRepository.save(new Movie(123,"End of days", 1999, this.user2)));
        isInitialized = true;
    }
	
	@Test
    public void convertersTest() {

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
	
    @After
    public void finish() throws Exception {
    	SecurityContextHolder.clearContext();
    }
    
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
