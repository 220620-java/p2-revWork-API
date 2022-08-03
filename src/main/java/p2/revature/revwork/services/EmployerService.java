package p2.revature.revwork.services;

import java.util.List;

import org.springframework.stereotype.Service;

import p2.revature.revwork.data.EmployerRepository;
import p2.revature.revwork.data.OpenJobRepository;
import p2.revature.revwork.models.data.EmployerData;
import p2.revature.revwork.models.data.FreelancerData;
import p2.revature.revwork.models.data.OpenJobs;
import p2.revature.revworkboot.models.Availablejob;
import p2.revature.revworkboot.models.Employer;
import p2.revature.revworkboot.models.Employerregister;
import p2.revature.revworkboot.models.Usernameandpassword;

@Service
public class EmployerService {

	private EmployerRepository er;
	private OpenJobRepository oj;

	public EmployerService(EmployerRepository er, OpenJobRepository oj) {
		this.oj = oj;
		this.er = er;
	}

	public List<EmployerData> getAllEmployers() {
		return er.findAll();
	}

	public EmployerData findById(int id) {
		return er.findById(id);
	}
	
	
	public EmployerData verifyLogin(Usernameandpassword login) {
		List<EmployerData> emp = er.findByUsername(login.getUsername());
		if (emp.size() == 0) {
			return null;
		} else {
			EmployerData employer = emp.get(0);
			
			if ( employer.getPassword().equals(login.getPassword()) ) {
				return employer;
			}
			else {
				return null;
			}
		}
	}

	public boolean verifyRigistration(Employerregister register) {
		List<EmployerData> employers = er.findByName(register.getUsername());

		if (employers.size() == 0) {
			EmployerData emp = new EmployerData();
			emp.setName(register.getName());
			emp.setEmail(register.getEmail());
			emp.setUsername(register.getUsername());
			emp.setPassword(register.getPassword());
			er.save(emp);
			return true;
		} else {

			return false;
		}

	}
	
	public Availablejob addJob(Availablejob openJob) {
		// the employer ID isnt auto generating (producing null in the db) and
		// I honestly dont know if its a problem
		// Get employerID should actually be getEmployer( ) in the AvaliableJob API i think
		// Ill try this later 
		//OpenJobs open = new OpenJobs(openJob.getEmployerid(),openJob.getName(), openJob.getDescription(), openJob.getSkills(), openJob.getPayrate());
		OpenJobs open = new OpenJobs(openJob.getId(), EmployerData.fromEmployer(openJob.getEmployerid()),openJob.getName(), openJob.getDescription(),openJob.getSkills(), openJob.getPayrate());
		oj.save(open);
		return openJob;
	}


	public OpenJobs deleteJob(OpenJobs open) {
		
		if (open == null) {
			return null;
		} else {
			oj.delete(open);
			return open;

		}

	}
}

