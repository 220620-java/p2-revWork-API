package p2.revature.revwork.services;

import java.util.List;

import org.springframework.stereotype.Service;

import p2.revature.revwork.data.FreelancerRepository;
import p2.revature.revwork.models.data.FreelancerData;
import p2.revature.revworkboot.models.Freelancerregister;
import p2.revature.revworkboot.models.Usernameandpassword;

@Service
public class FreelancerService {
	
	
	private FreelancerRepository fr;
	
	public FreelancerService(FreelancerRepository fr) {
		this.fr = fr;
	}
	
	public List<FreelancerData> getAllFreelancers(){
		return fr.findAll();
	}
	
	
	public FreelancerData verifyLogin(Usernameandpassword login) {
		List<FreelancerData> free = fr.findByUsername(login.getUsername());
		if (free.size() == 0) {
			return null;
		} else {
			FreelancerData freelancer = free.get(0);
			
			if ( freelancer.getPassword().equals(login.getPassword()) ) {
				return freelancer;
			}
			else {
				return null;
			}
		}
	}
	
	public boolean verifyRigistration(Freelancerregister register) {
		List<FreelancerData> free = fr.findByName(register.getUsername());
		if (free.size() == 0) {
			FreelancerData f = new FreelancerData();
			f.setName(register.getName());
			f.setAbout(register.getAbout());
			f.setExperiencelevel(register.getExperiencelevel());
			f.setEmail(register.getEmail());
			f.setUsername(register.getUsername());
			f.setPassword(register.getPassword());
			fr.save(f);
			return true;
		} else {

			return false;
		}

	}
	
	

}

