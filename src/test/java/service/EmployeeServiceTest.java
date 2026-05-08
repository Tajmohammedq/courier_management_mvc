package service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import Dao.EmployeeDao;
import Dao.SignupDao;
import controller.signupcontroller;
import entity.EmployeeTable;
import entity.SignupTable;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
	private EmployeeTable employee;
	
	@Before
	public  void before() {
		this.employee=new EmployeeTable("new3@employee.com", "Taj@1234", "mohammed", "taj","9948897414","pic");
	}
	
	@Mock
	private EmployeeDao mockeddao;
	@InjectMocks
	private EmployeeService service;
	@Mock
	private RestTemplate rest;
	
	
	

	
//	@Test
//	public  void getdetails() {
//		String email = "new3@employee.com";
//		when(mockeddao.get(email)).thenReturn(this.employee);
//		assertEquals(service.getdetails(email),this.employee);	
//		
//		
//	}
	@Test
	public void addemployee() {
		try {
			ResponseEntity<String> s=null;
		 	doNothing().when(mockeddao).adduser(this.employee);
		 	when(rest.getForEntity("http://localhost:9090/both/encodepassword/"+this.employee.getPassword(), String.class))
		 	.thenReturn(ResponseEntity.ok("encodedPassword"));
		 	service.addemployee(this.employee);
		 	verify(mockeddao).adduser(this.employee);
		}
		catch(Exception e) {
			assertEquals("This email for the employee already exist please try another",e.getMessage());
		}
	}


	

}
