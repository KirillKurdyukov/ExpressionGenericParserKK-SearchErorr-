package expression;

import java.util.Objects;

public class Variable<T> implements TripleExpression<T> {
    private String argName;
    public Variable (String argName) {
        this.argName = argName;
    }

    public T evaluate(T x, T y, T z) {
        if (argName.equals("x")){
            return x;
        }
        if (argName.equals("y")){
            return y;
        }
        if (argName.equals("z")){
            return z;
        }
        throw new IllegalArgumentException("Unexpected variable " + argName);
    }
}
