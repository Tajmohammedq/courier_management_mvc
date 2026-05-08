package Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler(value= {EmailAlreadyExist.class})
	public ResponseEntity<Object> emailexist(EmailAlreadyExist exist){
		System.out.println("camre to handler");
		Exceptionpayload payload=new Exceptionpayload(exist.getMessage(),exist.getCause(),HttpStatus.CONFLICT);
		return new ResponseEntity<>(payload,HttpStatus.CONFLICT);		
		
	}

	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> generalError(Exception exception) {
		Exceptionpayload payload = new Exceptionpayload(
				exception.getMessage(),
				exception.getCause(),
				HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
