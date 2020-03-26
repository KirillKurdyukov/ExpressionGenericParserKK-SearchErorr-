package expression;

import expression.generic.AbstractAlgebra;

public class Divide<T> extends MathOperations<T> {

    public Divide(TripleExpression<T> firstA, TripleExpression<T> secondA, AbstractAlgebra<T> algebra) {
        super(firstA, secondA, algebra);
    }

    @Override
    protected T operate(T a, T b) {
        return algebra.divide(a, b);
    }
}
