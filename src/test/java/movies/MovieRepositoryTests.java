package movies;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

//@DataJpaTest
public class MovieRepositoryTests extends FavMoviesWpApplicationTests {
	@Test
	public void testFindByTmdbId() throws Exception {
		List<Movie> foundMovies = this.movieRepository.findByTmdbId(123);
		assertEquals(foundMovies.size(), 2);
		assertEquals(foundMovies.get(0).getTitle(), "End of days");
		assertEquals(foundMovies.get(0).getYear(), 1999);
		assertThat(foundMovies.get(0).getUser().getName(), anyOf((is(userName1)), is(userName2)));
		assertEquals(foundMovies.get(1).getTitle(), "End of days");
		assertEquals(foundMovies.get(1).getYear(), 1999);
		assertThat(foundMovies.get(1).getUser().getName(), anyOf((is(userName1)), is(userName2)));
	}
	
	@Test
	public void testFindByTmdbIdAndUserName() throws Exception {
		List<Movie> foundMovies = this.movieRepository.findByTmdbIdAndUser_Name(123, userName1);
		assertEquals(foundMovies.size(), 1);
		assertEquals(foundMovies.get(0).getTitle(), "End of days");
		assertEquals(foundMovies.get(0).getYear(), 1999);
		assertEquals(foundMovies.get(0).getUser().getName(), userName1);
	}
}
