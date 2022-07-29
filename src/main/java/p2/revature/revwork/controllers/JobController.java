package p2.revature.revwork.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import p2.revature.revworkboot.api.JobApi;
import p2.revature.revworkboot.models.Availablejob;



@RestController
@RequestMapping(path="/job")
public class JobController implements JobApi{

	@Override
	@GetMapping()
	public ResponseEntity<List<Availablejob>> jobGet() {
		List<Availablejob> books = new ArrayList<>();
		
		Availablejob book1 = new Availablejob();
		book1.setTitle("Job Title1");
		book1.setSkills("skill 1, skill 2");
		book1.setJobdescription("MT");
		book1.setPayrate("25.12");
		
		books.add(book1);
		
		Availablejob book3 = new Availablejob();
		book3.setTitle("Job Title2");
		book3.setSkills("skill3");
		book3.setJobdescription("RE");
		book3.setPayrate("13.18");
		
		books.add(book3);
		
		return new ResponseEntity<>(books,HttpStatus.OK);
	}

	@Override
	@PostMapping
	public ResponseEntity<Void> jobPost(@Valid Availablejob body) {
		// TODO Auto-generated method stub
		
		System.out.println("Post made on job enppint");
		return null;
	}

//	@override
//    @ApiOperation(value = "", nickname = "jobGet", notes = "Get all the jobs that an employer has available.", response = Availablejob.class, responseContainer = "List", authorizations = {
//            @Authorization(value = "revwork_security_schema")    }, tags={  })
//        @ApiResponses(value = { 
//            @ApiResponse(code = 201, message = "The jobs were retrieved successfully.", response = Availablejob.class, responseContainer = "List") })
//        @RequestMapping(value = "/job",
//            produces = { "application/json" }, 
//            method = RequestMethod.GET)
//        ResponseEntity<List<Availablejob>> jobGet() {
//			List<AvailableJob> books = new AvailableJob<>();
//			
//			AvailableJob book1 = new AvailableJob();
//			book1.setBookAuthor("MT");
//			
//			books.add(book1);
//			
//			return new ResponseEntity<>(books,HttpStatus.OK);
//	    	
//	    }
        

	
	
}


//@RestController
//@RequestMapping(path="/job")
//public class JobController  {
//
//	// @ResponseBody // this method returns data directly in the body, rather than
//	// returning a view
//	@GetMapping()
//	public ResponseEntity<List<AvailableJob>> getUserById() {
//		
//		AvailableJob job = new AvailableJob();
//		job.setTitle("test");
//		
//		List<AvailableJob> jobs = new ArrayList<>();
//		jobs.add(job);
//		
//		
//		
//		return new ResponseEntity<>(jobs,HttpStatus.OK);
//
//	}
//	
//}



//public class UserController {
//	private UserService userServ;
//
//	public UserController(UserService userServ) {
//		this.userServ = userServ;
//	}
//
//	// @ResponseBody // this method returns data directly in the body, rather than
//	// returning a view
//	@GetMapping(path = "/{id}")
//	@Auth
//	public ResponseEntity<User> getUserById(@PathVariable("id") Integer userId) {
//		User user = userServ.getUser(userId);
//		if (user != null) {
//			// send a 200 status code with the user object as the response body
//			return ResponseEntity.ok(user);
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//
//	@PostMapping
//	public ResponseEntity<User> registerUser(@RequestBody User user) {
//		try {
//			user = userServ.registerUser(user);
//		} catch (UsernameAlreadyExistsException e) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).build();
//		}
//
//		return ResponseEntity.status(HttpStatus.CREATED).body(user);
//	}
//
//	@PutMapping(path = "/{id}")
//	@Auth
//	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Integer id) {
//		if (user.getId() == id) {
//			user = userServ.updateUser(user);
//			if (user != null) {
//				return ResponseEntity.ok(user);
//			} else {
//				// TODO this could probably be better
//				return ResponseEntity.badRequest().build();
//			}
//		} else {
//			return ResponseEntity.status(HttpStatus.CONFLICT).build();
//		}
//	}
//}
