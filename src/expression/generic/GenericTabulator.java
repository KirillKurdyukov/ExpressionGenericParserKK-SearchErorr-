package expression.generic;

import expression.TripleExpression;
import expression.exceptions.ComputationalException;
import expression.exceptions.ParserException;
import expression.exceptions.UnsupportedTypeException;
import expression.parser.ExpressionParser;

import java.math.BigInteger;
import java.util.function.Function;

public class GenericTabulator implements Tabulator {
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws ParserException {
        switch (mode) {
            case "i":
                return giveTable(this::applyInteger, new ExpressionParser<Integer>(new IntegerAlgebra()).parse(expression), x1, x2, y1, y2, z1, z2);
            case "d":
                return giveTable(this::applyDouble, new ExpressionParser<Double>(new DoubleAlgebra()).parse(expression), x1, x2, y1, y2, z1, z2);
            case "bi":
                return giveTable(this::applyBigInteger, new ExpressionParser<BigInteger>(new BigIntegerAlgebra()).parse(expression), x1, x2, y1, y2, z1, z2);
            case "s":
                return giveTable(this::applyShort, new ExpressionParser<Short>(new ShortAlgebra()).parse(expression), x1, x2, y1, y2, z1, z2);
            case "l":
                return giveTable(this::applyLong, new ExpressionParser<Long>(new LongAlgebra()).parse(expression), x1, x2, y1, y2, z1, z2);
            default:
                throw new UnsupportedTypeException(mode);
        }
    }

    private <T> Object[][][] giveTable(Function<Integer, T> cast, TripleExpression<T> expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        Object[][][] result = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int x = 0; x <= x2 - x1; x++) {
            for (int y = 0; y <= y2 - y1; y++) {
                for (int z = 0; z <= z2 - z1; z++) {
                    try {
                        result[x][y][z] = expression.evaluate(cast.apply(x + x1), cast.apply(y + y1), cast.apply(z + z1));
                    } catch (ComputationalException e) {
                        result[x][y][z] = null;
                    }
                }
            }
        }
        return result;
    }

    private Integer applyInteger(int a) {
        return a;
    }

    private Double applyDouble(int a) {
        return (double) a;
    }

    private Short applyShort(int a) {
        return (short) a;
    }

    private Long applyLong(int a) {
        return (long) a;
    }

    private BigInteger applyBigInteger(int a) {
        return BigInteger.valueOf(a);
    }
}
