package expression.generic;

import expression.exceptions.DivideOverflowException;
import expression.exceptions.DivisionOnZeroException;
import expression.exceptions.ParserException;

public class LongAlgebra extends AbstractAlgebra<Long> {
    @Override
    public Long count(Long a) {
        return null;
    }

    @Override
    public Long add(Long a, Long b) {
        return a + b;
    }

    @Override
    public Long subtract(Long a, Long b) {
        return a - b;
    }

    @Override
    public Long multiply(Long a, Long b) {
        return a * b;
    }

    @Override
    public Long divide(Long a, Long b) {
        correctDivideCheck(a, b);
        return a / b;
    }

    private void correctDivideCheck(Long a, Long b) {
        if (b == 0) {
            throw new DivisionOnZeroException("Happened division on zero in moment: " + a + " /  " + b);
        }
    }

    @Override
    public Long negate(Long a) {
        return -a;
    }

    @Override
    public Long min(Long a, Long b) {
        return null;
    }

    @Override
    public Long max(Long a, Long b) {
        return null;
    }

    @Override
    public Long parse(String arg) throws ParserException {
        return Long.parseLong(arg);
    }
}
