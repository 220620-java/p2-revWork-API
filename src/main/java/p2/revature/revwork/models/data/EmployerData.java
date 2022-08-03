package p2.revature.revwork.models.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import p2.revature.revworkboot.models.Employer;

@Entity
@Table(name="employer")
public class EmployerData {
	
	

	@Id // specifies that this field is the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // specifies that the db generates this value
	private int id;
	private String name;
	private String email;
	private String username;
	private String password;

	public EmployerData() {
	}
	
	public EmployerData(int id) {
		super();
		this.id = id;
	}

	public EmployerData(int id, String name, String email, String username, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	public static Employer toEmployer(EmployerData data) {
		Employer emp = new Employer();
		
		
		emp.setEmail(data.getEmail());
		emp.setId(emp.getId());
		emp.setName(data.getName());
		emp.setPassword(data.getPassword());
		emp.setUsername(data.getUsername());
		
		return emp;
	}
	
	public static EmployerData fromEmployer(Employer emp) {
		EmployerData data = new EmployerData();
		
		
		data.setEmail(emp.getEmail());
		data.setId(emp.getId());
		data.setName(emp.getName());
		data.setPassword(emp.getPassword());
		data.setUsername(emp.getUsername());
		
		return data;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Employer [id=" + id + ", name=" + name + ", email=" + email + ", username=" + username + ", password="
				+ password + "]";
	}

	
}


