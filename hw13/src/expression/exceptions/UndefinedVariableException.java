package expression.exceptions;

public class UndefinedVariableException extends EvaluateException {
    public UndefinedVariableException(String message) {
        super(message);
    }
}
