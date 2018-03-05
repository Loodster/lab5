package exceptions;

public class NotInQueueException extends RuntimeException {
	public NotInQueueException() {
		super();
	}
	
	public NotInQueueException(String message) {
		super(message);
	}
	
}
