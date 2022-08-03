package p2.revature.revwork.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import p2.revature.revwork.models.data.EmployerData;
import p2.revature.revwork.models.data.OpenJobs;
import p2.revature.revwork.services.EmployerService;
import p2.revature.revwork.services.OpenJobsService;
import p2.revature.revworkboot.api.JobApi;
import p2.revature.revworkboot.models.Availablejob;

@RestController
@RequestMapping(path = "/job")
public class JobController implements JobApi {

	private OpenJobsService oj;
	private EmployerService es;

	public JobController(OpenJobsService oj, EmployerService es) {
		this.oj = oj;
		this.es = es;
	}

	//works
	@Override
	@GetMapping
	public ResponseEntity<List<Availablejob>> jobGet() {
		List<OpenJobs> open = oj.getAllJobs();
		List<Availablejob> aj = new ArrayList<>();
		for (OpenJobs o : open) {
			Availablejob a = new Availablejob();
			a.setId(o.getId());
			a.setEmployerid(EmployerData.toEmployer(o.getEmployer()));
			a.setName(o.getName());
			a.setDescription(o.getDescription());
			a.setSkills(o.getSkills());
			a.setPayrate(o.getPayrate());			
			aj.add(a);
		}
		return ResponseEntity.ok(aj);
	}
	
	// employerID is null
	@GetMapping(path = "/{id}")
	public ResponseEntity<List<Availablejob>> jobGetById(@Valid Availablejob body) {
		OpenJobs open = oj.findById(body.getId());
		List<Availablejob> aj = new ArrayList<>();
			Availablejob a = new Availablejob();
			a.setId(open.getId());
			a.setEmployerid(EmployerData.toEmployer(open.getEmployer()));
			a.setName(open.getName());
			a.setDescription(open.getDescription());
			a.setSkills(open.getSkills());
			a.setPayrate(open.getPayrate());
			aj.add(a);
		
		return ResponseEntity.ok(aj);
	}

	@PostMapping
	public ResponseEntity<Availablejob> postJob(@RequestBody Availablejob openJob) {
		OpenJobs open = new OpenJobs(openJob.getId(), EmployerData.fromEmployer(openJob.getEmployerid()),openJob.getName(), openJob.getDescription(), openJob.getSkills(), openJob.getPayrate());
		oj.addJob(open);
		return ResponseEntity.status(HttpStatus.CREATED).body(openJob);
	}

	@Override
	public ResponseEntity<Void> jobPost(@Valid Availablejob body) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@DeleteMapping
	public ResponseEntity<Availablejob> deleteJob(@RequestBody Availablejob openJob){
		OpenJobs open = new OpenJobs( openJob.getId(), EmployerData.fromEmployer(openJob.getEmployerid()),openJob.getName(), openJob.getDescription(), openJob.getSkills(), openJob.getPayrate());
		oj.deleteJob(open);
		return ResponseEntity.status(HttpStatus.GONE).body(openJob);		
	}


}

