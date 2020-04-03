package expression;

import expression.exceptions.EvaluateException;

import java.util.Objects;

public abstract class UnaryOperator extends CommonExpression {
    private CommonExpression a;
    private String operator;

    public UnaryOperator(CommonExpression a, String operator) {
        this.a = a;
        this.operator = operator;
    }

    protected abstract int apply(int a) throws EvaluateException;

    protected String operator() {
        return operator;
    }

    @Override
    public int evaluate(int x) throws EvaluateException {
        return apply(a.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) throws EvaluateException {
        return apply(a.evaluate(x, y, z));
    }

    @Override
    public String toMiniString() {
        return operator() + " " + (a instanceof BinOperator ? a.toBracedString() : a.toMiniString());
    }

    @Override
    public String toString() {
        return " (" + operator() + a.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UnaryOperator op = (UnaryOperator) obj;
        return a.equals(op.a);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, getClass());
    }
}
