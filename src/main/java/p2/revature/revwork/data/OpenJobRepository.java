package p2.revature.revwork.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import p2.revature.revwork.models.data.OpenJobs;
import p2.revature.revworkboot.models.Availablejob;

@Repository
public interface OpenJobRepository extends JpaRepository<OpenJobs, Integer>{
	
	public OpenJobs findById(int id);
}

