package expression;

import expression.generic.AbstractAlgebra;

public class Count<T> implements TripleExpression<T> {

    private TripleExpression<T> count;
    private AbstractAlgebra<T> algebra;
    public Count(TripleExpression<T> count, AbstractAlgebra<T> algebra) {
        this.count = count;
        this.algebra = algebra;
    }
    @Override
    public T evaluate(T x, T y, T z) {
        return algebra.count(count.evaluate(x, y, z));
    }
}
