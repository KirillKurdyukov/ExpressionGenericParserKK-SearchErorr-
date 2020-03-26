package expression;

import expression.generic.AbstractAlgebra;

public class Max<T> extends MathOperations<T> {
    public Max(TripleExpression<T> first, TripleExpression <T> second, AbstractAlgebra<T> a) {
        super(first, second, a);
    }
    @Override
    protected T operate(T a, T b) {
        return algebra.max(a, b);
    }
}
