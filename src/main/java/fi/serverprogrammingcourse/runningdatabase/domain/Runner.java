package fi.serverprogrammingcourse.runningdatabase.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.DecimalFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Runner {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name = "firstname")
	@NotNull
    @Size(min=2, max=30, message = "{name.not.empty}")
	private String firstName;
	
	@Column(name = "lastname")
	@NotNull
    @Size(min=2, max=30)
	private String lastName;
	
	@Column(name = "km")
	private double km;
	
	@Column(name = "hour")
	@NotNull
	private long hour;
	
	@Column(name = "minute")
	@NotNull
	@Max(59)
	private long minute;
	
	@Column(name = "second")
	@NotNull
	@Max(59)
	private long second;
	
	@Transient
	private String minkm;

	@PostLoad
	private void onLoad() {
		DecimalFormat mf = new DecimalFormat("#0.");
		DecimalFormat sf = new DecimalFormat("00");
		long milliseconds = (hour * 3600000) + (minute * 60000) + (second * 1000);
        
		double pace = milliseconds / km;
		
		double minutes = (pace / 1000) / 60;
        double seconds = (pace / 1000) % 60;
		
		
		this.minkm = mf.format(Math.floor(minutes))+ sf.format(Math.round(seconds));
		
	}
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "sexid")
	private Sex sex;
	
	public Runner(String firstName, String lastName, double km, long hour, long minute, long second, Sex sex) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.km = km;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.sex = sex;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "Runner [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", km=" + km + ", hour="
				+ hour + ", minute=" + minute + ", second=" + second + ", minkm=" + minkm + ", sex=" + sex + "]";
	}
	public Runner() {

	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public double getKm() {
		return km;
	}
	public void setKm(double km) {
		this.km = km;
	}
	public long getHour() {
		return hour;
	}
	public void setHour(long hour) {
		this.hour = hour;
	}
	public long getMinute() {
		return minute;
	}
	public void setMinute(long minute) {
		this.minute = minute;
	}
	public long getSecond() {
		return second;
	}
	public void setSecond(long second) {
		this.second = second;
	}
	public String getMinkm() {
		return minkm;
	}
	public void setMinkm(String minkm) {
		this.minkm = minkm;
	}
}
