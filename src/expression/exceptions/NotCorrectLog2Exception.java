package expression.exceptions;

public class NotCorrectLog2Exception extends ParserException {
    public NotCorrectLog2Exception(int pos) {
        super("Excepted whitespace or negative: " + pos);
    }
}
