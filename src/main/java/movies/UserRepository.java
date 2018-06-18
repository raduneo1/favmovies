package movies;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(exported = true)
public interface UserRepository extends CrudRepository<User, Long> {

	//User save(User user);

	User findByName(@Param(value = "name") String name);

}