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

import p2.revature.revwork.models.data.EmployerData;
import p2.revature.revwork.models.data.FreelancerData;
import p2.revature.revwork.models.data.OpenJobs;
import p2.revature.revwork.models.data.Profile;
import p2.revature.revwork.services.JobApplicationService;
import p2.revature.revwork.services.FreelancerService;
import p2.revature.revwork.services.OpenJobsService;
import p2.revature.revwork.services.ProfileService;
import p2.revature.revwork.utils.JwtUtil;
import p2.revature.revworkboot.api.RegisterApi;
import p2.revature.revworkboot.models.Application;
import p2.revature.revworkboot.models.Availablejob;
import p2.revature.revworkboot.models.Employerregister;
import p2.revature.revworkboot.models.Freelancerregister;
import p2.revature.revworkboot.models.Portfolio;
import p2.revature.revwork.models.data.JobApplication;

@RestController
@RequestMapping(path = "/freelancer")
public class FreelancerController implements RegisterApi {

	private FreelancerService fs;
	private ProfileService p;
	private OpenJobsService ojs;
	private JobApplicationService aps;

	public FreelancerController(FreelancerService fs, ProfileService p, OpenJobsService ojs,
			JobApplicationService aps) {
		this.fs = fs;
		this.p = p;
		this.ojs = ojs;
		this.aps = aps;
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

	@Override
	public ResponseEntity<Void> registerEmployerPost(@Valid Employerregister body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@PostMapping(path = "/register")
	public ResponseEntity<Void> registerFreelancerPost(@Valid Freelancerregister body) {
		boolean success = fs.verifyRigistration(body);

		if (success) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}

// Clean up API documentation get rid of unwated fields like skill

	@PostMapping(path = "/create_profile")
	public ResponseEntity<Portfolio> addJob(@RequestBody Portfolio aj,
			@RequestHeader(value = "Authorization", required = true) String authorization) {

		int id = JwtUtil.getId(authorization);

		if (id == aj.getFreelancerid().getId()) {

			Profile open = new Profile(aj.getId(), FreelancerData.fromFreelancer(aj.getFreelancerid()), aj.getCollege(),
					aj.getName(), aj.getEmail());
			p.addProfile(open);

			return ResponseEntity.status(HttpStatus.CREATED).body(aj);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

	@DeleteMapping(path = "/delete_profile")
	public ResponseEntity<Portfolio> deleteProfile(@RequestBody Portfolio aj,
			@RequestHeader(value = "Authorization", required = true) String authorization) {
		int id = JwtUtil.getId(authorization);

		if (id == aj.getFreelancerid().getId()) {

			Profile open = new Profile(aj.getId(), FreelancerData.fromFreelancer(aj.getFreelancerid()), aj.getCollege(),
					aj.getName(), aj.getEmail());
			if (p.deleteProfile(open) != null) {
				return ResponseEntity.status(HttpStatus.GONE).body(aj);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(aj);
			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}

	}

	@PutMapping(path = "/edit_profile")
	public ResponseEntity<Portfolio> editProfile(@RequestBody Portfolio aj,
			@RequestHeader(value = "Authorization", required = true) String authorization) {
		int id = JwtUtil.getId(authorization);

		if (id == aj.getFreelancerid().getId()) {
			Profile open = new Profile(aj.getId(), FreelancerData.fromFreelancer(aj.getFreelancerid()), aj.getCollege(),
					aj.getName(), aj.getEmail());
			if (p.editProfile(open) != null) {
				return ResponseEntity.status(HttpStatus.OK).body(aj);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(aj);
			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

	@PostMapping(path = "/submit_app")
	public ResponseEntity<JobApplication> submitApplication(@RequestBody Application app) {

		JobApplication a = new JobApplication(app.getId(), ojs.findById(OpenJobs.fromJob(app.getJobid()).getId()),
				p.findById(Profile.fromPortfolio(app.getPortfolioid()).getId()), app.getCoverletter(), app.getName());
		if (aps.addApplication(a) != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(a);
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}

	}
}
