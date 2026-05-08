package service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Dao.SignupDao;
import dto.logindto;
import dto.tokendto;
import entity.SignupTable;

@RunWith(MockitoJUnitRunner.class)
public class SignupServiceTest {
	@InjectMocks
	private SignupService service;
	@Mock
	private SignupDao dao;
	@Mock
	private RestTemplate rest;
	@Test
	public void adduser() {
		try {
		SignupTable signup=new SignupTable();
		service.adduser(signup);
	    verify(dao).adduser(signup);
		}
		catch(Exception e) {
			assertEquals("This email already exist please try other one", e.getMessage());
		}
	}

	@Test
	public void update() {
		String email="new@gmail.com";
		SignupTable signup=new SignupTable();
		service.update(signup,email);
	    verify(dao).updateuser(signup, email); 
	}

}
