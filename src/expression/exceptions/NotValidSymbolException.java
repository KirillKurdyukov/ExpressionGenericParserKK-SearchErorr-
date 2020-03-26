package expression.exceptions;

public class NotValidSymbolException extends ParserException {
    public NotValidSymbolException(int pos, String exp, StringBuilder error) {
        super("Symbol is strange on position: " + pos + "\n" + exp + "\n" + error.toString());
    }
}
