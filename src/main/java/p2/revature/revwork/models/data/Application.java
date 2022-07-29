package p2.revature.revwork.models.data;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Application {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int applicationId;
	@OneToMany
	@JoinColumn(name = "jobId")
	private int jobId;
	@OneToMany
	@JoinColumn(name = "profileId")
	private int profileId;
	private String name;
	
	
	public Application() {
		super();
		this.applicationId = 0;
		this.jobId = 0;
		this.profileId = 0;
		this.name = "";
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	public Application(int applicationId, int jobId, int profileId, String name) {
		super();
		this.applicationId = applicationId;
		this.jobId = jobId;
		this.profileId = profileId;
		this.name = name;
	}





	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Application [applicationId=" + applicationId + ", jobId=" + jobId + ", profileId=" + profileId
				+ ", name=" + name + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(applicationId, jobId, name, profileId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Application other = (Application) obj;
		return applicationId == other.applicationId && jobId == other.jobId && Objects.equals(name, other.name)
				&& profileId == other.profileId;
	}
	
	
	
	

}
