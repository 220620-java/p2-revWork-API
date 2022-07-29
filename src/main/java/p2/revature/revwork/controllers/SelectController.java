package p2.revature.revwork.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import p2.revature.revworkboot.api.SelectApplicationApi;
import p2.revature.revworkboot.models.Application;

@RestController
@RequestMapping(path="/select-application")
public class SelectController implements SelectApplicationApi{

	@Override
	@PostMapping
	public ResponseEntity<Application> selectApplicationPost(@Valid Integer body) {
		// TODO Auto-generated method stub
		
		System.out.println("post to select endpoint with input" + body);
		return null;
	}

}
