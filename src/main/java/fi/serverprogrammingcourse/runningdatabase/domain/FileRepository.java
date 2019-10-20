package fi.serverprogrammingcourse.runningdatabase.domain;

import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<FileDummy, Long> {

}
