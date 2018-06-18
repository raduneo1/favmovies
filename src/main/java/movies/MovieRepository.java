package movies;

import java.util.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface MovieRepository extends CrudRepository<Movie, Long> {

	@Override
	@PreAuthorize("#movie?.user == null or #movie?.user?.name == authentication?.name")
	Movie save(@Param("movie") Movie movie);

	@Override
	@PreAuthorize("@movieRepository.findOne(#id)?.user?.name == authentication?.name")
	void deleteById(@Param("id") Long id);

	@Override
	@PreAuthorize("#movie?.user?.name == authentication?.name")
	void delete(@Param("movie") Movie movie);
	
	//findByAddress_ZipCode
	
	List<Movie> findByMovieId(@Param(value = "movieId") int movieId);
	
	List<Movie> findByUser_Name(@Param(value = "name") String name);
	
	//@Query("select m, u from Movie, User where m.review<>'' order by m.modifiedTime desc")
	//Movie findByMovieIdAndUser_Name(@Param(value = "name") String name);
	
    @Query("select m from Movie m where m.review<>'' order by m.modifiedTime desc")
    List<Movie> findByReviewNotEmptySortRevChrono();

}
