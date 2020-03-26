package expression;

import expression.generic.AbstractAlgebra;

import java.util.Objects;

public abstract class MathOperations<T> implements TripleExpression<T> {

    protected TripleExpression<T> firstA;
    protected TripleExpression<T> secondA;
    protected AbstractAlgebra<T> algebra;
    protected abstract T operate(T a, T b);

    public MathOperations(TripleExpression<T> firstA, TripleExpression<T> secondA, AbstractAlgebra <T> algebra) {
        this.firstA = firstA;
        this.secondA = secondA;
        this.algebra = algebra;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return operate(firstA.evaluate(x, y, z), secondA.evaluate(x, y, z));
    }

    @Override
    public int hashCode() {
         return Objects.hash(firstA, secondA);
    }

    public boolean equals(Object expression) {
        if (expression == this)
            return true;
        if (expression == null || expression.getClass() != this.getClass())
            return false;
        MathOperations exp = (MathOperations) expression;
        return this.firstA.equals(exp.firstA) && secondA.equals(exp.secondA);
    }
}
