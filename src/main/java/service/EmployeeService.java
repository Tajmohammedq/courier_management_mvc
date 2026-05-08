package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Dao.EmployeeDao;
import Exception.EmailAlreadyExist;
import Exception.Emailnotexist;
import dto.logindto;
import dto.tokendto;
import entity.EmployeeTable;
import entity.Role;
import entity.SignupTable;

public class EmployeeService {
	
	@Autowired
	private RestTemplate rest;
	private EmployeeDao employeedao;
	
	public String getencryptedpassword(String password) {
		ResponseEntity<String> s=rest.getForEntity("http://localhost:9090/both/encodepassword/"+password, String.class);
		return s.getBody();
	}

	private String getDefaultImage(String image) {
		return image == null ? "" : image.trim();
	}

	public void addemployee(EmployeeTable emp) {
		if (employeedao.get(emp.getEmail()) != null) {
			throw new EmailAlreadyExist("This email for the employee already exist please try another");
		}

		emp.setRole(Role.EMPLOYEE);
		emp.setImage(getDefaultImage(emp.getImage()));
		ResponseEntity<String> s=rest.getForEntity("http://localhost:9090/both/encodepassword/"+emp.getPassword(), String.class);
		emp.setPassword(s.getBody());
		employeedao.adduser(emp);
		
	}	
		
	public  tokendto getdetails(logindto login) throws JsonProcessingException {
		tokendto token=new tokendto();
		ObjectMapper objectMapper = new ObjectMapper();
	    String json = objectMapper.writeValueAsString(login);
	    HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
        ResponseEntity<tokendto> s=rest.postForEntity("http://localhost:9090/both/employeelogin",requestEntity, tokendto.class);
		token.setToken(s.getBody().getToken());
		token.setRefresh(s.getBody().getRefresh());
		token.setMessage(s.getBody().getMessage());
		System.out.println(token.toString());
		return token;
	}



	public void setEmployeedao(EmployeeDao employeedao) {
		this.employeedao = employeedao;
	}



	public void update(EmployeeTable employee, String email) {
		try {
			employee.setImage(getDefaultImage(employee.getImage()));
			try {
				employee.setPassword(getencryptedpassword(employee.getPassword()));
			}
			catch(Exception e) {
				System.out.println("got the exception");
			}
			employeedao.updateuser( employee, email);
		}
		catch(Exception e) {
			throw new Emailnotexist("Looks like this email is not present in our database");
		}
		
	}

	public EmployeeTable getprofile(String email) {
		try {
			return employeedao.getemployeeprofile(email);
		}
		catch(Exception e) {
			throw new Emailnotexist("Looks like this Employeeemail is not present in our database");
		}
	}




	
}


