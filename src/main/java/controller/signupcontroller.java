package controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import dto.logindto;
import dto.tokendto;
import entity.SignupTable;
import service.SignupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@CrossOrigin(origins="http://localhost:3333")
public class signupcontroller {

	private static final Logger logger =
			LoggerFactory.getLogger(signupcontroller.class);
	
	@Autowired
	private SignupService signup;
	
	@Autowired
	private RestTemplate rest;
	

//	@GetMapping("/check")
//	public void check() {
//		System.out.println("came into checking");
//		ResponseEntity<String> s=rest.getForEntity("http://localhost:9090/encrypt", String.class);
//		System.out.println("output="+s.getBody());
//	}


	@PostMapping(path ="/signup", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)

	public void signup(@RequestBody SignupTable request) {
		logger.info("Sign in request recieved for user");
//		throw new RuntimeException("CHECK IF CONTROLLER HIT");
		signup.adduser(request);
	}
	@PostMapping(path="/userlogin", produces=MediaType.APPLICATION_JSON_VALUE)
	public tokendto login(@RequestBody logindto dto) throws JsonProcessingException {
		return signup.getuser(dto);	
	}
	
	@GetMapping(path="/login/{email}",produces=MediaType.APPLICATION_JSON_VALUE)
	public SignupTable getuserdetails(@PathVariable String email) {
		return signup.getuserdetails(email);
		
	}
	
	@PostMapping(path ="/update/{mail}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody SignupTable signup1,@PathVariable String mail) {
		signup.update(signup1,mail);
	}
	
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
}
