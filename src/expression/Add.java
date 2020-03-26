package expression;

import expression.generic.AbstractAlgebra;

public class Add<T> extends MathOperations<T>{

    public Add(TripleExpression<T> firstA, TripleExpression<T> secondA, AbstractAlgebra<T> algebra) {
        super(firstA, secondA, algebra);
    }

    public T operate(T a, T b) {
        return algebra.add(a, b);
    }

}
