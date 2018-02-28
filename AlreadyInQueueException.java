package exceptions;

public class AlreadyInQueueException extends Exception {
	
	public AlreadyInQueueException(){
		super();
	}
	public AlreadyInQueueException(String message) {
		super(message);
	}
}
