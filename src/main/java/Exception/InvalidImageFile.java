package Exception;

public class InvalidImageFile extends RuntimeException {
	public InvalidImageFile(String message) {
		super(message);
	}
}
