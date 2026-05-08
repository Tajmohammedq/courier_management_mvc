package dto;
public class tokendto {
	String token;
	String refresh;
	@Override
	public String toString() {
		return "tokendto [token=" + token + ", refresh=" + refresh + ", message=" + message + "]";
	}
	String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRefresh() {
		return refresh;
	}
	public void setRefresh(String refresh) {
		this.refresh = refresh;
	}
	
	

}
