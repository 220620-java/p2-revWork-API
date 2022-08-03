package p2.revature.revwork.models.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import p2.revature.revworkboot.models.Availablejob;
import p2.revature.revworkboot.models.Freelancer;

@Entity
@Table(name = "application")
public class JobApplication {

	@Id // specifies that this field is the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // specifies that the db generates this value
	private int id;
	@ManyToOne
	@JoinColumn(name = "jobid", referencedColumnName = "id")
	private OpenJobs openJob;
	@ManyToOne
	@JoinColumn(name = "profileid", referencedColumnName = "id")
	private Profile profile;
	private String coverletter;
	private String name;

	public JobApplication() {
		super();
	}

	public JobApplication(OpenJobs openJob, Profile profile, String coverletter, String name) {
		super();
		this.openJob = openJob;
		this.profile = profile;
		this.coverletter = coverletter;
		this.name = name;
	}

	public JobApplication(int id, OpenJobs openJob, Profile profile, String coverletter, String name) {
		super();
		this.id = id;
		this.openJob = openJob;
		this.profile = profile;
		this.coverletter = coverletter;
		this.name = name;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OpenJobs getOpenJob() {
		return openJob;
	}

	public void setOpenJob(OpenJobs openJob) {
		this.openJob = openJob;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getCoverletter() {
		return coverletter;
	}

	public void setCoverletter(String coverletter) {
		this.coverletter = coverletter;
	}

	@Override
	public String toString() {
		return "JobApplication [id=" + id + ", openJob=" + openJob + ", profile=" + profile + ", coverletter="
				+ coverletter + ", name=" + name + "]";
	}

	

}

