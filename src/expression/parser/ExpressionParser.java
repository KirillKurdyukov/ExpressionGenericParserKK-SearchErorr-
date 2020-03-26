package expression.parser;

import expression.TripleExpression;
import expression.*;
import expression.Negate;
import expression.exceptions.*;
import expression.generic.AbstractAlgebra;

public class ExpressionParser<T> implements Parser {
    private String expression;
    private int len;
    private int pos;
    private AbstractAlgebra<T>  myAlgebra;
    public ExpressionParser(AbstractAlgebra<T> a) {
        myAlgebra = a;
    }

    enum Operator {
        COUNT("count", 1),
        MAX("max", 2),
        MIN("min", 2),
        ADD ("+", 2),
        POW ("**", 2),
        LOG ("//", 2),
        SUBTRACT("-", 2),
        MULTIPLY("*", 2),
        DIVIDE("/", 2),
        LEFTSHIFT("<<", 2),
        RIGHTSHIFT(">>", 2),
        VARIABLE(null, 0),
        DIGIT(null, 0),
        WHITESPACE(null, 0),
        ENDLINE("\0", 0),
        BRACKETLEFT("(", 1),
        BRACKETRIGHT(")", 2),
        LOG2("log2", 1),
        POW2("pow2", 1);
        private String operate;
        private int bin;
        Operator(String operator, int bin) {
            this.operate = operator;
            this.bin = bin;
        }
        public String getStringOperator() {
            return operate;
        }
        public int getBin() {
            return bin;
        }
        public int getLength() {
            if (operate != null) {
                return getStringOperator().length();
            }
            return 1;
        }
    }
    public TripleExpression<T> parse(String expression) throws ParserException{
        int bracket = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                bracket++;
            }
            if (expression.charAt(i) == ')') {
                bracket--;
            }
            if (bracket < 0)
                throw new WrongBracketsBalanceException(expression);
        }
        if (bracket != 0)
            throw new WrongBracketsBalanceException(expression);
        pos = 0;
        len = expression.length();
        this.expression = expression;
        if (getOperator() == Operator.WHITESPACE) {
            getNextOperator();
        }

        return getExpressionMaxAndMin();
    }

    private TripleExpression<T> getExpressionMaxAndMin() throws ParserException{
        TripleExpression<T> res = getExpressionSumAndSubtract();
        Operator cur = getOperator();
        while (cur != Operator.ENDLINE) {
            if (cur == Operator.MAX) {
                getNextOperator();
                res = getClassOperation(Operator.MAX.getStringOperator(), res, getExpressionSumAndSubtract());
            } else if (cur == Operator.MIN) {
                getNextOperator();
                res = getClassOperation(Operator.MIN.getStringOperator(), res, getExpressionSumAndSubtract());
            } else if (cur.getBin() == 2) {
                break;
            } else {
                throw new ExpectedArithmeticActionException(pos, expression, getError());
            }
            cur = getOperator();
        }
        return res;
    }
    private TripleExpression<T> getExpressionSumAndSubtract() throws ParserException{
        TripleExpression<T> res = getExpressionMultiplyAndDivide();
        Operator cur = getOperator();
        while (cur != Operator.ENDLINE) {
            if (cur == Operator.ADD) {
                getNextOperator();
                res = getClassOperation(Operator.ADD.getStringOperator(), res, getExpressionMultiplyAndDivide());
            } else if (cur == Operator.SUBTRACT) {
                getNextOperator();
                res = getClassOperation(Operator.SUBTRACT.getStringOperator(), res, getExpressionMultiplyAndDivide());
            } else if (cur.getBin() == 2) {
                break;
            } else {
                throw new ExpectedArithmeticActionException(pos, expression, getError());
            }
            cur = getOperator();
        }
        return res;
    }


    private TripleExpression<T> getExpressionMultiplyAndDivide() throws ParserException{
        TripleExpression<T> res = getExpressionVariableAndConst();
        Operator cur = getOperator();
        while (cur != Operator.ENDLINE) {
            if (cur == Operator.MULTIPLY) {
                getNextOperator();
                res = getClassOperation(Operator.MULTIPLY.getStringOperator(), res, getExpressionVariableAndConst());
            } else if (cur == Operator.DIVIDE) {
                getNextOperator();
                res = getClassOperation(Operator.DIVIDE.getStringOperator(), res, getExpressionVariableAndConst());
            } else if (cur.getBin() == 2) {
                break;
            } else {
                throw new ExpectedArithmeticActionException(pos, expression, getError());
            }
            cur = getOperator();
        }
        return res;
    }

    private StringBuilder getError(){
        StringBuilder error = new StringBuilder();
        error.append(" ".repeat(Math.max(0, pos)));
        error.append("^");
        return error;
    }
    private TripleExpression<T> getExpressionVariableAndConst() throws ParserException{
        Operator current = getOperator();
        if (current == Operator.DIGIT){
            return new Const<>(getNumber());
        }
        if (current == Operator.BRACKETLEFT){
            getNextOperator();
            TripleExpression<T> inBracket = getExpressionMaxAndMin();
            getNextOperator();
            return inBracket;
        }
        if (current == Operator.COUNT) {
            getNextOperator();
            return new Count<>(getExpressionVariableAndConst(), myAlgebra);
        }
        if (current == Operator.VARIABLE) {
            TripleExpression<T> var = new Variable<>(String.valueOf(expression.charAt(pos)));
            getNextOperator();
            return var;
        }
        if (current == Operator.SUBTRACT) {
            if (checkNextOperator(pos + getOperator().getLength()) == Operator.DIGIT){
                return new Const<>(getNumber());
            }
            getNextOperator();
            return new Negate<>(getExpressionVariableAndConst(), myAlgebra);
        }
        throw new NotValidSymbolException(pos, expression, getError());
    }

    private Operator checkNextOperator(int pos) throws ParserException {
        return getOperator(pos);
    }

    private Operator getOperator(int pos) throws NotValidSymbolException {
        if (pos >= len)
            return Operator.ENDLINE;
        for (Operator A : Operator.values()) {
            if (len - pos >= A.getLength() && expression.substring(pos, pos + A.getLength()).equals(A.getStringOperator())) {
                return A;
            }
        }
        if (Character.isDigit(expression.charAt(pos))) {
            return Operator.DIGIT;
        }
        if (Character.isWhitespace(expression.charAt(pos))) {
            return Operator.WHITESPACE;
        }
        if (expression.charAt(pos) == 'x' || expression.charAt(pos) == 'y' || expression.charAt(pos) == 'z') {
            return Operator.VARIABLE;
        }

        throw new NotValidSymbolException(pos, expression, getError());
    }

    private void getNextOperator() throws ParserException{
        pos += getOperator().getLength();
        while (getOperator() == Operator.WHITESPACE) {
            pos++;
        }
    }

    private T getNumber() throws ParserException {
        int begin = pos;
        int end = pos;
        if (getOperator() == Operator.SUBTRACT) {
            ++end;
            getNextOperator();
        }

        while (getOperator() == Operator.DIGIT) {
            ++end;
            getNextOperator();
        }

        String number = expression.substring(begin, end);

        try {
            return myAlgebra.parse(number);
        } catch (NumberFormatException e) {
            throw new ValueIsNot32BitNumber(number + " is't 32 bit number");
        }
    }
    private Operator getOperator() throws ParserException{
        return getOperator(pos);
    }

    private TripleExpression<T> getClassOperation(String operator, TripleExpression<T> first, TripleExpression<T> second) throws ParserException{
        if (operator.equals("min")) {
            return new Min<>(first, second, myAlgebra);
        }
        if (operator.equals("max")) {
            return new Max<>(first, second, myAlgebra);
        }
        if (operator.equals("+")) {
            return new Add<>(first, second, myAlgebra);
        }
        if (operator.equals("-")) {
            return new Subtract<>(first, second, myAlgebra);
        }
        if (operator.equals("*")) {
            return new Multiply<>(first, second, myAlgebra);
        }
        if (operator.equals("/")) {
            return new Divide<>(first, second, myAlgebra);
        }
        throw new ExpectedArithmeticActionException(pos, expression, getError());
    }
}
