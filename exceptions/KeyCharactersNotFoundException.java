package exceptions;

public class KeyCharactersNotFoundException extends RuntimeException{
    public KeyCharactersNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
