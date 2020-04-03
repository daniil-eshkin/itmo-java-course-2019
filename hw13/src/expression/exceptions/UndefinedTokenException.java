package expression.exceptions;

public class UndefinedTokenException extends ParseException {
    public UndefinedTokenException(String message) {
        super(message);
    }
}
