package p2.revature.revwork.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import p2.revature.revwork.models.data.JobApplication;
import p2.revature.revwork.models.data.EmployerData;
import p2.revature.revwork.models.data.OpenJobs;
import p2.revature.revwork.services.JobApplicationService;
import p2.revature.revwork.services.EmployerService;
import p2.revature.revwork.services.OpenJobsService;
import p2.revature.revwork.utils.JwtUtil;
import p2.revature.revworkboot.api.RegisterApi;
import p2.revature.revworkboot.models.Availablejob;
import p2.revature.revworkboot.models.Employerregister;
import p2.revature.revworkboot.models.Freelancerregister;

@RestController
@RequestMapping(path = "/employer")
public class EmployerController implements RegisterApi {

	private EmployerService empServ;
	private OpenJobsService ojs;
	private JobApplicationService aps;
	private JwtUtil jwt;

	// Did = GetJobs + FindJobbyID+ AddJob + Delete Job + edit Job + Register
	// Need to do = Login/Logout + Select Application + ID Validation with JWT for
	// editing and deleting jobs

	public EmployerController(EmployerService empServ, OpenJobsService ojs, JobApplicationService aps, JwtUtil jwt) {
		this.ojs = ojs;
		this.empServ = empServ;
		this.aps = aps;
		this.jwt = jwt;
	}

	@GetMapping(path = "/get_jobs")
	public ResponseEntity<List<Availablejob>> jobGet() {
		List<OpenJobs> open = ojs.getAllJobs();
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

	@GetMapping(path = "/{id}")
	public ResponseEntity<List<Availablejob>> jobGetById(@PathVariable Integer id) {
		OpenJobs open = ojs.findById(id);
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

	@GetMapping(path = "/get_applicants/{name}")
	public ResponseEntity<List<JobApplication>> getApplicantsByName(@PathVariable String name) {
		List<JobApplication> list = aps.selectApplicants(name);
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
		}
	}

	@GetMapping(path = "/applicant/{id}")
	public ResponseEntity<JobApplication> getApplicantsById(@PathVariable int id) {
		JobApplication list = aps.selectApplicant(id);
		if (list.getId() < 1 || list.getClass().equals(null)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
		}
	}

	@Override
	@PostMapping(path = "/register")
	public ResponseEntity<Void> registerEmployerPost(@Valid Employerregister body) {
		boolean success = empServ.verifyRigistration(body);

		if (success) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}

	@PostMapping(path = "/add_job")
	public ResponseEntity<Availablejob> addJob(@RequestBody Availablejob aj, @RequestHeader(value = "Authorization", required = true) String authorization) {
		int id = JwtUtil.getId(authorization);
		
		if(id == aj.getEmployerid().getId()) {
		OpenJobs open = new OpenJobs(aj.getId(), empServ.findById(aj.getEmployerid().getId()), aj.getName(), aj.getDescription(), aj.getSkills(),
				aj.getPayrate());
		ojs.addJob(open);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(aj);
		
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

	@DeleteMapping(path = "/delete_job")
	public ResponseEntity<Availablejob> deleteJob(@RequestBody Availablejob openJob, @RequestHeader(value = "Authorization", required = true) String authorization) {
		
		int id = JwtUtil.getId(authorization);

		if(id == openJob.getEmployerid().getId()) {

		OpenJobs open = new OpenJobs(openJob.getId(), EmployerData.fromEmployer(openJob.getEmployerid()),
				openJob.getName(), openJob.getDescription(), openJob.getSkills(), openJob.getPayrate());
		ojs.deleteJob(open);
		return ResponseEntity.status(HttpStatus.GONE).body(openJob);
		
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

	@PutMapping(path = "/edit_job")
	public ResponseEntity<Availablejob> editJob(@RequestBody Availablejob aj, @RequestHeader(value = "Authorization", required = true) String authorization) {
		
		int id = JwtUtil.getId(authorization);

		if(id == aj.getEmployerid().getId()) {

		OpenJobs open = new OpenJobs(aj.getId(), EmployerData.fromEmployer(aj.getEmployerid()), aj.getName(),
				aj.getDescription(), aj.getSkills(), aj.getPayrate());
		if (ojs.editJob(open) != null) {
			return ResponseEntity.status(HttpStatus.OK).body(aj);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(aj);
		}
	} else {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
	}

	@Override
	public ResponseEntity<Void> registerFreelancerPost(@Valid Freelancerregister body) {
		// TODO Auto-generated method stub
		return null;
	}

}
