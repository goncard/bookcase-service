package infrastructure.exception;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(final String message) {
        super(message);
    }
}
