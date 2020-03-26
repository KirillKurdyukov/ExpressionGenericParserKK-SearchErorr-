package expression.generic;

import expression.exceptions.ParserException;

public abstract class AbstractAlgebra<T> {
    public abstract T count(T a);

    public abstract T add(T a, T b);

    public abstract T subtract(T a, T b);

    public abstract T multiply(T a, T b);

    public abstract T divide(T a, T b);

    public abstract T negate(T a);

    public abstract T min(T a, T b);

    public abstract T max(T a, T b);

    public abstract T parse(String arg) throws ParserException;
}
