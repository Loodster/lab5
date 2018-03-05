package exceptions;
/**
 * Exception for when customer is already using a register
 * @author Michael
 *
 */
public class AlreadyUsingRegisterException extends RuntimeException {
	
	public AlreadyUsingRegisterException() {
		super();
	}
	
	public AlreadyUsingRegisterException(String message) {
		super(message);
	}
	
}
