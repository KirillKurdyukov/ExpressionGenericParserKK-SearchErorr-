package expression;

import expression.generic.AbstractAlgebra;

public class Min<T> extends MathOperations<T> {
    public Min(TripleExpression<T> first, TripleExpression <T> second, AbstractAlgebra<T> a) {
        super(first, second, a);
    }
    @Override
    public T operate(T x, T y) {
        return algebra.min(x, y);
    }
}
