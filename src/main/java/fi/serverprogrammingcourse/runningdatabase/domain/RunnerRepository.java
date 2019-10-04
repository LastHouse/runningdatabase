package fi.serverprogrammingcourse.runningdatabase.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RunnerRepository extends CrudRepository<Runner, Long> {
	
	List<Runner> findByLastName(String lastName);
	
	Optional<Runner> findById(@Param ("id") Long id);

}
