package fi.serverprogrammingcourse.runningdatabase.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import fi.serverprogrammingcourse.runningdatabase.domain.Runner;
import fi.serverprogrammingcourse.runningdatabase.domain.RunnerRepository;
import fi.serverprogrammingcourse.runningdatabase.domain.Sex;
import fi.serverprogrammingcourse.runningdatabase.domain.SexRepository;


@RestController
public class RestRunnerController {

	@Autowired
	private RunnerRepository rrepository;
	@Autowired
	private SexRepository srepository;
	
	@RequestMapping(value="/sexes", method = RequestMethod.GET)
	public List<Sex> sexListRest() {
		return (List<Sex>) srepository.findAll();
	}

	@RequestMapping(value="/runners", method = RequestMethod.GET)
	public List<Runner> runnerListRest() {
		return (List<Runner>) rrepository.findAll();
	}
	
	@RequestMapping(value="/runner/{id}", method = RequestMethod.GET)
    public Optional<Runner> findRunnerRest(@PathVariable("id") Long runnertId) {	
    	return rrepository.findById(runnertId);
    	
	}
	
	@RequestMapping(value="/runners", method = RequestMethod.POST)
	public List<Runner> saveRunnerRest(@RequestBody Runner runner) {
		rrepository.save(runner);
		return (List<Runner>) rrepository.findAll();
	}
}
