package movies;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends CrudRepository<Movie, Long> {
   
	List<Movie> findByTitle(String title);
	Movie findByMovieId(@Param(value = "movieId") int movieId);
	List<Movie> findByReviewNot(@Param(value = "review") String review);

}
