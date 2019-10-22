package fi.serverprogrammingcourse.runningdatabase.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Sex {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long sexid;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sex")
	private List<Runner> runners;

	public Sex() {
		
	}
	public Sex(String name) {
		super();
		this.name = name;
	}
	public Long getSexid() {
		return sexid;
	}
	
	public void setSexid(Long sexid) {
		this.sexid = sexid;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Runner> getRunners() {
		return runners;
	}

	public void setRunners(List<Runner> runners) {
		this.runners = runners;
	}

	@Override
	public String toString() {
		return "Sex [sexid=" + sexid + ", name=" + name + "]";
	}

}
