package Exception;

import org.springframework.http.HttpStatus;

public class Exceptionpayload {
	private String message;
	private HttpStatus status;
	public String getMessage() {
		return message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public Exceptionpayload(String message, HttpStatus status) {
		super();
		this.message = message;
		this.status = status;
	}

}
