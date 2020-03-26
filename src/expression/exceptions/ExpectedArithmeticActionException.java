package expression.exceptions;

public class ExpectedArithmeticActionException extends ParserException {


    public ExpectedArithmeticActionException(int pos, String exp, StringBuilder error) {
        super("Expected arithmetic action on position: " + pos + "\n" + exp + "\n" + error.toString());
    }

}
