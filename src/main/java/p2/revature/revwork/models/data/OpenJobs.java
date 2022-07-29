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
public class OpenJobs {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int jobId;
	@OneToMany
	@JoinColumn(name = "employerid")
	private int employerId;
	private String jobName;
	private String description;
	private String skills;
	
	
	public OpenJobs() {
		super();
		this.jobId = 0;
		this.employerId = 0;
		this.jobName = "";
		this.description = "";
		this.skills = "";
		// TODO Auto-generated constructor stub
	}
	

	public OpenJobs(int jobId, int employerId, String jobName, String description, String skills) {
		super();
		this.jobId = jobId;
		this.employerId = employerId;
		this.jobName = jobName;
		this.description = description;
		this.skills = skills;
	}


	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public int getEmployerId() {
		return employerId;
	}
	public void setEmployerId(int employerId) {
		this.employerId = employerId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
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
	@Override
	public int hashCode() {
		return Objects.hash(description, employerId, jobId, jobName, skills);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpenJobs other = (OpenJobs) obj;
		return Objects.equals(description, other.description) && employerId == other.employerId && jobId == other.jobId
				&& Objects.equals(jobName, other.jobName) && Objects.equals(skills, other.skills);
	}
	@Override
	public String toString() {
		return "OpenJobs [jobId=" + jobId + ", employerId=" + employerId + ", jobName=" + jobName + ", description="
				+ description + ", skills=" + skills + "]";
	}
	
	
	

}
