package expression.generic;

import expression.exceptions.DivisionOnZeroException;
import expression.exceptions.ParserException;

public class ShortAlgebra extends AbstractAlgebra<Short> {
    @Override
    public Short count(Short a) {
        return null;
    }

    @Override
    public Short add(Short a, Short b) {
        return (short) (a + b);
    }

    @Override
    public Short subtract(Short a, Short b) {
        return (short) (a - b);
    }

    @Override
    public Short multiply(Short a, Short b) {
        return (short) (a * b);
    }

    @Override
    public Short divide(Short a, Short b) {
        correctDivideCheck(a, b);
        return (short) (a / b);
    }

    @Override
    public Short negate(Short a) {
        String ans = String.valueOf(-a);
        return Short.parseShort(ans);
    }

    @Override
    public Short min(Short a, Short b) {
        return null;
    }

    @Override
    public Short max(Short a, Short b) {
        return null;
    }

    private void correctDivideCheck(Short a, Short b) {
        if (b == 0) {
            throw new DivisionOnZeroException("Happened division on zero in moment: " + a + " /  " + b);
        }
    }

    @Override
    public Short parse(String arg) throws ParserException {
        return Short.parseShort(arg);
    }
}
