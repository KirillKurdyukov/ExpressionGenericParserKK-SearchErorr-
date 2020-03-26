package expression;


import expression.generic.AbstractAlgebra;

public class Multiply<T> extends MathOperations<T> {

    public Multiply(TripleExpression<T> firstA, TripleExpression<T> secondA, AbstractAlgebra<T> algebra) {
        super(firstA, secondA, algebra);
    }

    @Override
    public T operate(T a, T b) {
        return algebra.multiply(a, b);
    }
}