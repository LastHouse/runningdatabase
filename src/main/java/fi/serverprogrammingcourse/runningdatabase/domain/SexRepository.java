package fi.serverprogrammingcourse.runningdatabase.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SexRepository extends CrudRepository<Sex, Long>  {
	
	List<Sex> findByName(@Param ("name") String name);

}
