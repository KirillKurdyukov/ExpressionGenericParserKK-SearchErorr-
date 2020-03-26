package expression;

import expression.generic.AbstractAlgebra;

public class Const<T> implements TripleExpression<T>{
    private T arg;

    public Const(T cnt) {
        this.arg = cnt;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return arg;
    }

}
