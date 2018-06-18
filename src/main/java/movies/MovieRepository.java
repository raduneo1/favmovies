package movies;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends CrudRepository<Movie, Long> {
	
	Movie findByMovieId(@Param(value = "movieId") int movieId);
    @Query("select m from Movie m where m.review<>'' order by m.modifiedTime desc")
    List<Movie> findByReviewNotEmptySortRevChrono();

}
