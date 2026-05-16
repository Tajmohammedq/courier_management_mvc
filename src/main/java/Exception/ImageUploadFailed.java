package Exception;

public class ImageUploadFailed extends RuntimeException {
	public ImageUploadFailed(String message) {
		super(message);
	}
}
