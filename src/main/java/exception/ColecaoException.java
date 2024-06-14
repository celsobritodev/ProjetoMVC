package exception;

public class ColecaoException extends Exception {
	private static final long serialVersionUID = 1L;


	public ColecaoException(String message) {
		super(message);
	}
	
	public ColecaoException(Throwable cause) {
		super(cause);
	}
	
	
	public ColecaoException(String message, Throwable cause) {
		super(message,cause );
	}
	
	
	
	
}
