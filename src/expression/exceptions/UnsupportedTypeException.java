package expression.exceptions;

public class UnsupportedTypeException extends ParserException {
    public UnsupportedTypeException(String mode) {
        super("Unsupported type: " + mode);
    }
}
