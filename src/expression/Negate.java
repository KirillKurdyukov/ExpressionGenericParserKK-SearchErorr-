package expression;

import expression.generic.AbstractAlgebra;

public class Negate<T> implements TripleExpression<T> {
    private TripleExpression<T> negative;
    private AbstractAlgebra<T> algebra;
    public Negate(TripleExpression<T> negative, AbstractAlgebra<T> algebra) {
        this.negative = negative;
        this.algebra = algebra;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return algebra.negate(negative.evaluate(x, y, z));
    }

}
