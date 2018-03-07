package exceptions;

public class NotAtRegisterException extends RuntimeException {
    public NotAtRegisterException() {
    }

    public NotAtRegisterException(String message) {
        super(message);
    }
}
