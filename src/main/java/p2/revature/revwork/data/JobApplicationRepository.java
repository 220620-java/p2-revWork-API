package p2.revature.revwork.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import p2.revature.revwork.models.data.JobApplication;


public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {

	public List<JobApplication> findByName(String name);
	
	public JobApplication findById(int id);
}
