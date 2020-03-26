package expression.generic;

import expression.exceptions.*;

public class IntegerAlgebra extends AbstractAlgebra<Integer> {
    public Integer add(Integer a, Integer b) {
        correctAddCheck(a, b);
        return a + b;
    }

    public Integer subtract(Integer a, Integer b) {
        correctSubtractCheck(a, b);
        return a - b;
    }

    public Integer multiply(Integer a, Integer b) {
        correctMultiplyCheck(a, b);
        return a * b;
    }

    public Integer divide(Integer a, Integer b) {
        correctDivideCheck(a, b);
        return a / b;
    }

    public Integer negate(Integer a) {
        correctCheck(a);
        return -a;
    }

    public Integer max(Integer a, Integer b) {
        return Math.max(a, b);
    }

    public Integer min(Integer a, Integer b) {
        return Math.min(a, b);
    }

    public Integer count(Integer a) {
        return a.bitCount(a);
    }
    public Integer parse(String a) throws ParserException {
        try {
            return Integer.parseInt(a);
        } catch (NumberFormatException e) {
            throw new ValueIsNot32BitNumber(a + " is't 32 bit number");
        }
    }

    private void correctAddCheck(int a, int b) {
        if (a > 0 && b > 0 && a > Integer.MAX_VALUE - b)
            throw new AddOverflowException("Happened overflow in moment: " + a + " + " + b);
        if (a < 0 && b < 0 && a < Integer.MIN_VALUE - b)
            throw new AddOverflowException("Happened overflow in moment: " + a + " + " + b);
    }

    private void correctDivideCheck(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new DivideOverflowException("Happened overflow in moment: " + a + " / " + b);
        }
        if (b == 0) {
            throw new DivisionOnZeroException("Happened division on zero in moment: " + a + " /  " + b);
        }
    }

    private void correctMultiplyCheck(int a, int b) {
        if (a != 0 && b != 0) {
            if ((a > 0 && b > 0 && a > Integer.MAX_VALUE / b) ||
                    (a < 0 && b < 0 && a < Integer.MAX_VALUE / b) ||
                    (a < 0 && b > 0 && a < Integer.MIN_VALUE / b) || (a > 0 && b < 0 && b < Integer.MIN_VALUE / a)) {
                throw new MultiplyOverflowException("Happened overflow in moment: " + a + " * " + b);
            }
        }
    }

    private void correctSubtractCheck(int a, int b) {
        if (b > 0 && Integer.MIN_VALUE + b > a)
            throw new SubtractOverflowException("Happened overflow in moment: " + a + " - " + b);
        if (b < 0 && Integer.MAX_VALUE + b < a)
            throw new SubtractOverflowException("Happened overflow in moment: " + a + " - " + b);
    }

    private void correctCheck(int a) {
        if (a == Integer.MIN_VALUE)
            throw new NegateOverflowException("Happened overflow in moment: " + "-" + a);
    }
}
