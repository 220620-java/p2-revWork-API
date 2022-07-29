package p2.revature.revwork.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import p2.revature.revworkboot.api.ApplyApi;
import p2.revature.revworkboot.models.Application;

@RestController
@RequestMapping(path="/apply")
public class ApplyController implements ApplyApi {

	@Override
	@PostMapping()
	public ResponseEntity<Void> applyPost(@Valid Application body) {
		// TODO Auto-generated method stub
		
		System.out.println("post to apply endpoint");
		return null;
	}

	@Override
	public ResponseEntity<Void> applyPut(@Valid Application body) {
		// TODO Auto-generated method stub
		return null;
	}

}
