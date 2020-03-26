package expression.generic;

public class DoubleAlgebra extends AbstractAlgebra<Double> {
    public Double parse(String a) {
        return Double.parseDouble(a);
    }

    public Double max(Double a, Double b) {
        return Math.max(a, b);
    }

    public Double min(Double a, Double b) {
        return Math.min(a, b);
    }

    @Override
    public Double count(Double a) {
        String new_a = Long.toBinaryString(Double.doubleToRawLongBits(a));
        double answer = 0;
        for (int i = 0; i < new_a.length(); i++) {
            if (new_a.charAt(i) == '1')
                answer++;
        }
        return answer;
    }

    public Double add(Double a, Double b) {
        return a + b;
    }

    public Double subtract(Double a, Double b) {
        return a - b;
    }

    public Double multiply(Double a, Double b) {
        return a * b;
    }

    public Double divide(Double a, Double b) {
        return a / b;
    }

    public Double negate(Double a) {
        return -a;
    }
}
