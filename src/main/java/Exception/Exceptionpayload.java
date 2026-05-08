package Exception;

import org.springframework.http.HttpStatus;

public class Exceptionpayload {
	private String message;
	private Throwable throwable;
	private HttpStatus status;
	public String getMessage() {
		return message;
	}
	public Throwable getThrowable() {
		return throwable;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public Exceptionpayload(String message, Throwable throwable, HttpStatus status) {
		super();
		this.message = message;
		this.throwable = throwable;
		this.status = status;
	}

}
