package fi.serverprogrammingcourse.runningdatabase;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.serverprogrammingcourse.runningdatabase.domain.Runner;
import fi.serverprogrammingcourse.runningdatabase.domain.RunnerRepository;
import fi.serverprogrammingcourse.runningdatabase.domain.Sex;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class RunnerRepositoryTest {

	    @Autowired
	    private RunnerRepository rrepository;

	    @Test
	    public void findByLastNameShouldReturnRunner() {
	        List<Runner> runners = rrepository.findByLastName("Wayne");
	        assertThat(runners).hasSize(1);
	        assertThat(runners.get(0).getFirstName()).isEqualTo("John");
	    }
	    
	    @Test
	    public void createNewRunner() {
	    	Runner runner = new Runner("Billy", "Ferguson", 34.2, 2, 12, 6, new Sex("2"));
	    	
	    	//Query is: insert into runner (firstname, hour, km, lastname, minute, second, sexid) values (?, ?, ?, ?, ?, ?, ?), parameters ['Billy',2,34.2,'Ferguson',12,6,<null>]
	    	
	    	rrepository.save(runner);
	    	assertThat(runner.getId()).isNotNull();
	    }
	   
}
