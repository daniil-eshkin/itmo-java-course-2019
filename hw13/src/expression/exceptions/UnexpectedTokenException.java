package expression.exceptions;

public class UnexpectedTokenException extends ParseException {
    public UnexpectedTokenException(String message) {
        super(message);
    }
}
