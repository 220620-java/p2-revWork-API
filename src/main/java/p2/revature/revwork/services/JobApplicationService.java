package p2.revature.revwork.services;

import org.springframework.stereotype.Service;

import java.util.*;
import p2.revature.revwork.data.JobApplicationRepository;
import p2.revature.revwork.models.data.JobApplication;


@Service
public class JobApplicationService {
	
	private JobApplicationRepository ar;
	
	public JobApplicationService(JobApplicationRepository ar) {
		this.ar = ar;
	}
	
	public JobApplication addApplication(JobApplication app) {
		app.setId(0);
		ar.save(app);
		if (app.getId() != 0) {
			return app;
		}
		return null;
	}
	
	public List<JobApplication> selectApplicants(String name) {
		List<JobApplication> apps = ar.findByName(name);
		if(apps.get(0) == null) {
			return null;
		}
		else {
			return apps;
		}
	}
	
	public JobApplication selectApplicant(int id) {
		boolean pass = ar.existsById(id);
		if(pass != false) {
			return ar.findById(id);
		} else {
		return null;
		}
	}

}

