package p2.revature.revwork.models.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import p2.revature.revworkboot.models.Availablejob;
import p2.revature.revworkboot.models.Employer;



@Entity
@Table(name="openjobs")
public class OpenJobs {

	@Id // specifies that this field is the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // specifies that the db generates this value
	private int id;
	@ManyToOne
	@JoinColumn(name = "employerid", referencedColumnName = "id")
	private EmployerData employer;
	private String name;
	private String description;
	private String skills;
	private String payrate;

	public OpenJobs() {}
	
	public OpenJobs(int id, EmployerData employer, String name, String description, String skills, String payrate) {
		super();
		this.id = id;
		this.employer = employer;
		this.name = name;
		this.description = description;
		this.skills = skills;
		this.payrate = payrate;
	}
	
	public static OpenJobs fromJob(Availablejob aj) {
		EmployerData ed = new EmployerData();
		ed.setId( aj.getId());
		OpenJobs oj = new OpenJobs();		
		oj.setId(aj.getId());
		oj.setEmployer(ed);
		oj.setName(aj.getName());
		oj.setDescription(aj.getDescription());
		oj.setPayrate(aj.getPayrate());
		oj.setSkills(aj.getSkills());		
		return oj;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EmployerData getEmployer() {
		return employer;
	}

	public void setEmployer(EmployerData employer) {
		this.employer = employer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}
	
	public String getPayrate() {
		return payrate;
	}
	public void setPayrate(String payrate) {
		this.payrate = payrate;
	}

	@Override
	public String toString() {
		return "OpenJobs [id=" + id + ", employer=" + employer + ", name=" + name + ", description=" + description
				+ ", skills=" + skills + ", payrate=" + payrate + "]";
	}

}

