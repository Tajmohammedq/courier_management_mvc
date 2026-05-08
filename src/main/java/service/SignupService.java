 package service;


import java.sql.SQLIntegrityConstraintViolationException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Dao.SignupDao;
import Exception.EmailAlreadyExist;
import Exception.Emailnotexist;
import Exception.InvalidCredentials;
import dto.logindto;
import dto.tokendto;
import entity.Role;
import entity.SignupTable;


public class SignupService {
	
	@Autowired
	private RestTemplate rest;
	private SignupDao signupDao;
	public String getencryptedpassword(String password) {
		ResponseEntity<String> s=rest.getForEntity("http://localhost:9090/both/encodepassword/"+password, String.class);
		return s.getBody();
	}

	private String getDefaultImage(String image) {
		return image == null ? "" : image.trim();
	}

	public RestTemplate restTemplate() {
        return new RestTemplate();
    }
	public void adduser(SignupTable signup) {
		if (signupDao.getuser(signup.getEmail()) != null) {
			throw new EmailAlreadyExist("This email already exist please try other one oplease");
		}

		signup.setRole(Role.USER);
		signup.setImage(getDefaultImage(signup.getImage()));
		signup.setPassword(getencryptedpassword(signup.getPassword()));
		signupDao.adduser(signup);
	}
	public tokendto getuser(logindto dto) throws JsonProcessingException {
		tokendto token=new tokendto();
		ObjectMapper objectMapper = new ObjectMapper();
	    String json = objectMapper.writeValueAsString(dto);
	    HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
        RestTemplate rest=restTemplate();
        ResponseEntity<tokendto> s=rest.postForEntity("http://localhost:9090/both/userlogin",requestEntity, tokendto.class);
		token.setToken(s.getBody().getToken());
		token.setRefresh(s.getBody().getRefresh());
		token.setMessage(s.getBody().getMessage());
		return token;
		
	}
	
	public SignupTable getuserdetails(String email) {
		return signupDao.getuser(email);
		
	}
	
	public SignupDao getSignupDao() {
		return signupDao;
	}

	public void setSignupDao(SignupDao signupDao) {
		this.signupDao = signupDao;
	}
	public void update(SignupTable signup,String mail) {
		try {
			signup.setImage(getDefaultImage(signup.getImage()));
			try {
				signup.setPassword(getencryptedpassword(signup.getPassword()));
			}
			catch(Exception e) {
				System.out.println("got the exception");
			}
			signupDao.updateuser( signup, mail);
		}
		catch(Exception e) {
			throw new Emailnotexist("Looks like this email is not present in our database");
		}
		
		
	}
	public SignupService(SignupDao dao) {
		this.signupDao=dao;
		
	}
	

		
	
	


	


}
