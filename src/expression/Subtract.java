package expression;

import expression.generic.AbstractAlgebra;

public class Subtract<T> extends MathOperations<T> {

    public Subtract(TripleExpression<T> firstA, TripleExpression<T> secondA, AbstractAlgebra<T> algebra) {
        super(firstA, secondA, algebra);
    }

    @Override
    public T operate(T a, T b) {
        return algebra.subtract(a, b);
    }

}
