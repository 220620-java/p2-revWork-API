package p2.revature.revwork.controllers.test;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import p2.revature.revwork.controllers.EmployerController;
import p2.revature.revwork.models.data.EmployerData;
import p2.revature.revwork.models.data.FreelancerData;
import p2.revature.revwork.models.data.JobApplication;
import p2.revature.revwork.models.data.OpenJobs;
import p2.revature.revwork.models.data.Profile;
import p2.revature.revwork.services.EmployerService;
import p2.revature.revwork.services.JobApplicationService;
import p2.revature.revwork.services.OpenJobsService;
import p2.revature.revwork.utils.JwtUtil;
import p2.revature.revworkboot.models.Application;
import p2.revature.revworkboot.models.Availablejob;
import p2.revature.revworkboot.models.Employer;
import p2.revature.revworkboot.models.Employerregister;
import p2.revature.revworkboot.models.Freelancer;
import p2.revature.revworkboot.models.Portfolio;

@WebMvcTest(controllers = EmployerController.class)
public class EmployerControllerTest {
	
	
	@MockBean
	private EmployerService es;
	
	@MockBean
	Employerregister er;
	
	@MockBean
	private EmployerData eda;
	
	@MockBean
	private OpenJobsService ojs;
	
	@MockBean 
	private JwtUtil jwt;
		
	@MockBean
	private JobApplicationService jas;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper om = new ObjectMapper();
	
	
	@Test
	public void getJobs() throws Exception {
		List<Availablejob> list = new ArrayList<>();
		List<OpenJobs> oj = new ArrayList<>();
		Availablejob aj = new Availablejob();
		aj.setId(1);
		Employer e = new Employer();
		EmployerData ed = new EmployerData(1);
		OpenJobs oj1 = new OpenJobs(1, ed, "", " ", "", "");
		oj.add(oj1);
		e.setId(1);
		aj.setEmployerid(e);
		String value = om.writeValueAsString(list);
		String token = "Bearer " +"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJlbXBsb3llciJdLCJpc3MiOiJhdXRoMCIsImZ1bGxuYW1lIjoiQmVlJ3MgSG9uZXkiLCJpZCI6MSwidXNlcm5hbWUiOiJIb25leUluYyJ9.Yz7-bEYUfHPcBrwGPU-cJiZ2bvsLLJMOOunfcFFy1r4";		
		
		Mockito.when(jwt.getId(Mockito.anyString())).thenReturn(1);
		Mockito.when(es.findById(Mockito.anyInt())).thenReturn(ed);
		Mockito.when(eda.getJobs()).thenReturn(oj);
		
		mockMvc.perform(get("/employer/1/get_jobs").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  token)
				)
		.andExpect(status().isOk())
		.andExpect(content().json(value));
	}
	
	@Test
	public void forbidGetJobs() throws Exception {
		List<Availablejob> list = new ArrayList<>();	
		Availablejob aj = new Availablejob();
		aj.setId(1);
		Employer e = new Employer();
		EmployerData ed = new EmployerData(2);
		e.setId(2);
		aj.setEmployerid(e);
		String value = om.writeValueAsString(list);
		String token = "Bearer " +"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJlbXBsb3llciJdLCJpc3MiOiJhdXRoMCIsImZ1bGxuYW1lIjoiQmVlJ3MgSG9uZXkiLCJpZCI6MSwidXNlcm5hbWUiOiJIb25leUluYyJ9.Yz7-bEYUfHPcBrwGPU-cJiZ2bvsLLJMOOunfcFFy1r4";		
		
		Mockito.when(jwt.getId(Mockito.anyString())).thenReturn(1);
		Mockito.when(es.findById(Mockito.anyInt())).thenReturn(ed);
		
		mockMvc.perform(get("/employer/2/get_jobs").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  token)
				)
		.andExpect(status().isForbidden());
		
	}
	
	
	@Test
	public void getJobById() throws Exception {
		OpenJobs job = new OpenJobs(1);
		String value = om.writeValueAsString(job);
		Mockito.when(ojs.findById(job.getId())).thenReturn(job);
		
		mockMvc.perform(get("/employer/1"))
		.andExpect(status().isOk())
		.andExpect(content().json(value));
	}
	
	
	@Test
	public void doNotGetApplicantsByName() throws Exception {
		List<JobApplication> list = new ArrayList<>();
		
		Mockito.when(jas.selectApplicants(Mockito.anyString())).thenReturn(list);
		
		mockMvc.perform(get("/get_applicants/franklyn"))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void doNotGetApplicantById() throws Exception {
		JobApplication job = new JobApplication(2);
		
		Mockito.when(jas.selectApplicant(Mockito.anyInt())).thenReturn(job);
		
		mockMvc.perform(get("/applicant/1"))
		.andExpect(status().isNotFound());
		
	}
	
	@Test
	public void Register() throws Exception {
		Employerregister input = new Employerregister();
		
		Mockito.when(es.verifyRigistration(input)).thenReturn(true);
		
		mockMvc.perform(post("/employer/register").contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(input)))
		.andExpect(status().isCreated());
		
	}
	
	@Test
	public void doNotRegister() throws Exception {
		Employerregister input = new Employerregister();
		
		Mockito.when(es.verifyRigistration(input)).thenReturn(false);
		
		mockMvc.perform(post("/employer/register").contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(input)))
		.andExpect(status().isNotAcceptable());
		
	}
	
	@Test
	public void addJob() throws JsonProcessingException, Exception {
		Availablejob aj = new Availablejob();
		Employer emp = new Employer();
		OpenJobs job = new OpenJobs(1);
		emp.setId(1);
		aj.setId(1);
		aj.setEmployerid(emp);
		String value = om.writeValueAsString(aj);
		String token = "Bearer " +"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJlbXBsb3llciJdLCJpc3MiOiJhdXRoMCIsImZ1bGxuYW1lIjoiQmVlJ3MgSG9uZXkiLCJpZCI6MSwidXNlcm5hbWUiOiJIb25leUluYyJ9.Yz7-bEYUfHPcBrwGPU-cJiZ2bvsLLJMOOunfcFFy1r4";		
		
		
		Mockito.when(jwt.getId(Mockito.anyString())).thenReturn(1);
		Mockito.when(ojs.addJob(job)).thenReturn(job);
		
		mockMvc.perform(post("/employer/add_job").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  token)
				.content(om.writeValueAsString(aj)))
		.andExpect(status().isCreated())
		.andExpect(content().json(value));		
	}

	
	@Test
	public void doNotAddJob() throws JsonProcessingException, Exception {
		Availablejob aj = new Availablejob();
		Employer emp = new Employer();
		OpenJobs job = new OpenJobs(1);
		emp.setId(2);
		aj.setId(2);
		aj.setEmployerid(emp);
		String value = om.writeValueAsString(aj);
		String token = "Bearer " +"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJlbXBsb3llciJdLCJpc3MiOiJhdXRoMCIsImZ1bGxuYW1lIjoiQmVlJ3MgSG9uZXkiLCJpZCI6MSwidXNlcm5hbWUiOiJIb25leUluYyJ9.Yz7-bEYUfHPcBrwGPU-cJiZ2bvsLLJMOOunfcFFy1r4";		
		
		
		Mockito.when(jwt.getId(Mockito.anyString())).thenReturn(1);
		Mockito.when(ojs.addJob(job)).thenReturn(job);
		
		mockMvc.perform(post("/employer/add_job").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  token)
				.content(om.writeValueAsString(aj)))
		.andExpect(status().isForbidden());		
	}
	
	@Test
	public void getApplicants() throws Exception {
		Availablejob aj = new Availablejob();
		Employer e = new Employer();
		aj.setId(1);
		e.setId(2);
		aj.setEmployerid(e);
		String value = om.writeValueAsString(aj);
		String token = "Bearer " +"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJlbXBsb3llciJdLCJpc3MiOiJhdXRoMCIsImZ1bGxuYW1lIjoiQmVlJ3MgSG9uZXkiLCJpZCI6MSwidXNlcm5hbWUiOiJIb25leUluYyJ9.Yz7-bEYUfHPcBrwGPU-cJiZ2bvsLLJMOOunfcFFy1r4";		
		
		Mockito.when(jwt.getId(Mockito.anyString())).thenReturn(1);
		
		mockMvc.perform(post("/employer/get_applicants").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  token)
				.content(om.writeValueAsString(aj)))
		.andExpect(status().isForbidden());		
	}
	
	@Test
	public void doNotGetApplicants() throws Exception {
		List<JobApplication> list = new ArrayList<>();
		Availablejob aj = new Availablejob();
		Employer emp = new Employer();
		JobApplication app = new JobApplication(null);
		emp.setId(2);
		aj.setId(2);
		aj.setEmployerid(emp);
		list.add(app);
		list.set(0, null);
		String token = "Bearer " +"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJlbXBsb3llciJdLCJpc3MiOiJhdXRoMCIsImZ1bGxuYW1lIjoiQmVlJ3MgSG9uZXkiLCJpZCI6MSwidXNlcm5hbWUiOiJIb25leUluYyJ9.Yz7-bEYUfHPcBrwGPU-cJiZ2bvsLLJMOOunfcFFy1r4";		
		
		Mockito.when(jas.selectApplicants(Mockito.anyString())).thenReturn(list);
		
		mockMvc.perform(get("/employer/get_applicants/frankyln").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  token)
				.content("franklyn"))
		.andExpect(status().isNotFound());
		
	}
	
	@Test
	public void getApplicant() throws Exception {
		JobApplication app = new JobApplication(1);
		String value = om.writeValueAsString(app);
		String token = "Bearer " +"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJlbXBsb3llciJdLCJpc3MiOiJhdXRoMCIsImZ1bGxuYW1lIjoiQmVlJ3MgSG9uZXkiLCJpZCI6MSwidXNlcm5hbWUiOiJIb25leUluYyJ9.Yz7-bEYUfHPcBrwGPU-cJiZ2bvsLLJMOOunfcFFy1r4";		

		Mockito.when(jas.selectApplicant(Mockito.anyInt())).thenReturn(app);
		
		mockMvc.perform(get("/employer/applicant/1").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  token)
				)
		.andExpect(status().isAccepted())
		.andExpect(content().json(value));
	}
	
	
	@Test
	public void deleteJob() throws JsonProcessingException, Exception {
		Availablejob aj = new Availablejob();
		Employer emp = new Employer();
		OpenJobs job = new OpenJobs(1);
		emp.setId(1);
		aj.setId(1);
		aj.setEmployerid(emp);
		String value = om.writeValueAsString(aj);
		String token = "Bearer " +"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJlbXBsb3llciJdLCJpc3MiOiJhdXRoMCIsImZ1bGxuYW1lIjoiQmVlJ3MgSG9uZXkiLCJpZCI6MSwidXNlcm5hbWUiOiJIb25leUluYyJ9.Yz7-bEYUfHPcBrwGPU-cJiZ2bvsLLJMOOunfcFFy1r4";		
		
		Mockito.when(jwt.getId(Mockito.anyString())).thenReturn(1);
		Mockito.when(ojs.deleteJob(job)).thenReturn(job);
		
		mockMvc.perform(delete("/employer/delete_job").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  token)
				.content(om.writeValueAsString(aj)))
		.andExpect(status().isGone())
		.andExpect(content().json(value));
	}
	
	@Test
	public void doNotDeleteJob() throws JsonProcessingException, Exception {
		Availablejob aj = new Availablejob();
		Employer emp = new Employer();
		OpenJobs job = new OpenJobs(1);
		emp.setId(2);
		aj.setId(2);
		aj.setEmployerid(emp);
		String value = om.writeValueAsString(aj);
		String token = "Bearer " +"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJlbXBsb3llciJdLCJpc3MiOiJhdXRoMCIsImZ1bGxuYW1lIjoiQmVlJ3MgSG9uZXkiLCJpZCI6MSwidXNlcm5hbWUiOiJIb25leUluYyJ9.Yz7-bEYUfHPcBrwGPU-cJiZ2bvsLLJMOOunfcFFy1r4";		
		
		Mockito.when(jwt.getId(Mockito.anyString())).thenReturn(1);
		Mockito.when(ojs.deleteJob(job)).thenReturn(job);
		
		mockMvc.perform(delete("/employer/delete_job").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  token)
				.content(om.writeValueAsString(aj)))
		.andExpect(status().isForbidden());
	}
	
	@Test
	public void forbiddenEditJob() throws JsonProcessingException, Exception {
		Availablejob aj = new Availablejob();
		Employer emp = new Employer();
		EmployerData emp1 = new EmployerData(4);
		OpenJobs job = new OpenJobs(1);
		emp.setId(8);
		aj.setId(1);
		aj.setEmployerid(emp);
		job.setEmployer(emp1);
		String value = om.writeValueAsString(aj);
		String token = "Bearer " +"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJlbXBsb3llciJdLCJpc3MiOiJhdXRoMCIsImZ1bGxuYW1lIjoiQmVlJ3MgSG9uZXkiLCJpZCI6MSwidXNlcm5hbWUiOiJIb25leUluYyJ9.Yz7-bEYUfHPcBrwGPU-cJiZ2bvsLLJMOOunfcFFy1r4";		
		
		Mockito.when(jwt.getId(Mockito.anyString())).thenReturn(1);
		Mockito.when(ojs.editJob(job)).thenReturn(Mockito.any());
		
		mockMvc.perform(put("/employer/edit_job").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  token)
				.content(om.writeValueAsString(aj)))
		.andExpect(status().isForbidden());	
	}
	
	@Test
	public void jobToEditNotFound() throws JsonProcessingException, Exception {
		Availablejob aj = new Availablejob();
		Employer emp = new Employer();
		EmployerData emp1 = new EmployerData(1);
		OpenJobs job = null;
		emp.setId(1);
		aj.setId(1);
		aj.setEmployerid(emp);
		String value = om.writeValueAsString(aj);
		String token = "Bearer " +"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJlbXBsb3llciJdLCJpc3MiOiJhdXRoMCIsImZ1bGxuYW1lIjoiQmVlJ3MgSG9uZXkiLCJpZCI6MSwidXNlcm5hbWUiOiJIb25leUluYyJ9.Yz7-bEYUfHPcBrwGPU-cJiZ2bvsLLJMOOunfcFFy1r4";		
		
		Mockito.when(jwt.getId(Mockito.anyString())).thenReturn(1);
		Mockito.when(ojs.editJob(job)).thenReturn(job);
		
		mockMvc.perform(put("/employer/edit_job").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  token)
				.content(om.writeValueAsString(aj)))
		.andExpect(status().isNotFound());
	}
	
	

}
