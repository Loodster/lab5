package exceptions;

public class AlreadyInQueueException extends RuntimeException {
	
	public AlreadyInQueueException(){
		super();
	}
	public AlreadyInQueueException(String message) {
		super(message);
	}
}
