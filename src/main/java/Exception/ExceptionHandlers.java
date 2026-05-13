package Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler(value= {EmailAlreadyExist.class})
	public ResponseEntity<Exceptionpayload> emailexist(EmailAlreadyExist exist){
		System.out.println("camre to handler");
		Exceptionpayload payload=new Exceptionpayload(exist.getMessage(),HttpStatus.CONFLICT);
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(payload);		
		
	}

	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Exceptionpayload> generalError(Exception exception) {
		Exceptionpayload payload = new Exceptionpayload(
				exception.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR);
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(payload);
	}
}
