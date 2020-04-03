package expression;

import expression.exceptions.EvaluateException;

import java.util.Objects;

public abstract class BinOperator extends CommonExpression {
    private CommonExpression a, b;
    private int prior;
    private boolean commutative;
    private String operator;

    public BinOperator(CommonExpression a, CommonExpression b, int prior, boolean commutative, String operator) {
        this.a = a;
        this.b = b;
        this.prior = prior;
        this.commutative = commutative;
        this.operator = operator;
    }

    protected abstract int apply(int a, int b) throws EvaluateException;

    protected int getPrior() {
        return prior;
    }

    protected boolean isCommutative() {
        return commutative;
    }

    protected String operator() {
        return operator;
    }

    private String firstOperand() {
        if (!(a instanceof BinOperator)) {
            return a.toMiniString();
        }
        BinOperator bin = (BinOperator) a;
        return bin.getPrior() < getPrior()
                ? a.toBracedString()
                : a.toMiniString();
    }

    private String secondOperand() {
        if (!(b instanceof BinOperator)) {
            return b.toMiniString();
        }
        BinOperator bin = (BinOperator) b;
        return bin.getPrior() < getPrior() || (bin.getPrior() == getPrior() && (!bin.isCommutative() || !isCommutative()))
                ? b.toBracedString()
                : b.toMiniString();
    }

    @Override
    public int evaluate(int x) throws EvaluateException {
        return apply(a.evaluate(x), b.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) throws EvaluateException {
        return apply(a.evaluate(x, y, z), b.evaluate(x, y, z));
    }

    @Override
    public String toMiniString() {
        return firstOperand() + " " + operator() + " " + secondOperand();
    }

    @Override
    public String toString() {
        return "(" + a.toString() + " " + operator() + " " + b.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BinOperator bop = (BinOperator) obj;
        return a.equals(bop.a) && b.equals(bop.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, getClass());
    }
}
