package movies;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

public class MovieControllerTests extends FavMoviesWpApplicationTests {
    @Test
    public void readSingleMovie() throws Exception {
        mockMvc.perform(get("/api/movies/0"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.movieId", is(this.movies.get(0).getMovieId())))
                .andExpect(jsonPath("$.tmdbId", is(123)))
                .andExpect(jsonPath("$.title", is("End of days")))
                .andExpect(jsonPath("$.year", is(1999)));
    }
    
    @Test
    public void readAllMovie() throws Exception {
        mockMvc.perform(get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.movies", hasSize(3)))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$._embedded.movies[0].tmdbId", is(123)))
                .andExpect(jsonPath("$._embedded.movies[0].title", is("End of days")))
                .andExpect(jsonPath("$._embedded.movies[0].year", is(1999)))
		        .andExpect(jsonPath("$._embedded.movies[1].tmdbId", is(456)))
		        .andExpect(jsonPath("$._embedded.movies[1].title", is("Lion King")))
		        .andExpect(jsonPath("$._embedded.movies[1].year", is(1980)))
	            .andExpect(jsonPath("$._embedded.movies[2].tmdbId", is(123)))
	            .andExpect(jsonPath("$._embedded.movies[2].title", is("End of days")))
	            .andExpect(jsonPath("$._embedded.movies[2].year", is(1999)));
    }

    @Test
    public void createMovie() throws Exception {
        String movie = json(new Movie(789, "Batman", 2000, user2));

        this.mockMvc.perform(post("/movies/")
                .contentType(contentType)
                .content(movie))
                .andExpect(status().isOk());
    }
}
