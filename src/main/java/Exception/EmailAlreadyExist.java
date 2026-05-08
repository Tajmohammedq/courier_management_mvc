package Exception;

public class EmailAlreadyExist extends RuntimeException{
	
	public EmailAlreadyExist(String message) {
		super(message);
	}
	public EmailAlreadyExist(){
		super("This emial already exist");
	}
	public EmailAlreadyExist(String message,Throwable cause) {
		super(message);
	}
}
