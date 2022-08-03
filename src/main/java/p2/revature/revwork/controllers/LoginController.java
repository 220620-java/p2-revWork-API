package p2.revature.revwork.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import p2.revature.revwork.models.data.EmployerData;
import p2.revature.revwork.models.data.FreelancerData;
import p2.revature.revwork.services.EmployerService;
import p2.revature.revwork.services.FreelancerService;
import p2.revature.revwork.utils.JwtUtil;
import p2.revature.revworkboot.api.LoginApi;
import p2.revature.revworkboot.models.Usernameandpassword;

@RestController
@RequestMapping(path = "/login")
public class LoginController implements LoginApi {

	private JwtUtil jwtUtil;
	private FreelancerService freeService;
	private EmployerService empService;

	public LoginController(JwtUtil jwtUtil, FreelancerService freeService, EmployerService empService) {
		this.jwtUtil = jwtUtil;
		this.freeService = freeService;
		this.empService = empService;
	}

	

	@Override
	@PostMapping()
//	@CrossOrigin(origins = "http://127.0.0.1:5555/")
	public ResponseEntity<String> loginPost(@Valid Usernameandpassword body) {

		String username = body.getUsername();
		String role = body.getRole();
		String token = "";

		if (role.equals("freelancer")) {

			FreelancerData freelancer = freeService.verifyLogin(body);
			if (freelancer == null) {
				return new ResponseEntity<>("Wrong username or passowrd", HttpStatus.BAD_REQUEST);
			}

			try {
				JWTCreator.Builder builder = jwtUtil.getJwtBuilder();
				Algorithm algorithm = Algorithm.HMAC256("michael");
				String[] sa = { "freelancer" };
				token = builder.withIssuer("auth0").withArrayClaim("roles", sa).withClaim("username", username)
						.withClaim("fullname", freelancer.getName()).withClaim("id", freelancer.getId())
						.sign(algorithm);
			} catch (JWTCreationException exception) {

			}
		} else if (role.equals("employer")) {
			EmployerData employer = empService.verifyLogin(body);
			if (employer == null) {
				return new ResponseEntity<>("Wrong username or password", HttpStatus.BAD_REQUEST);
			}

			try {
				JWTCreator.Builder builder = jwtUtil.getJwtBuilder();
				Algorithm algorithm = Algorithm.HMAC256("michael");
				String[] sa = { "employer" };
				token = builder.withIssuer("auth0").withArrayClaim("roles", sa).withClaim("username", username)
						.withClaim("fullname", employer.getName()).withClaim("id", employer.getId()).sign(algorithm);
			} catch (JWTCreationException exception) {

			}

		}
		  
		return new ResponseEntity<>(JwtUtil.pullFromHeader(token) + "Token:"+ token , HttpStatus.OK);
	}

}
