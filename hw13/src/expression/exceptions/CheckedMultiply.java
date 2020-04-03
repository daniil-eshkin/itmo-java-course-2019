package expression.exceptions;

import expression.CommonExpression;
import expression.BinOperator;

public class CheckedMultiply extends BinOperator {
    public CheckedMultiply(CommonExpression a, CommonExpression b) {
        super(a, b, 2, true, "*");
    }

    public static int checkedMul(int a, int b) throws OverflowException {
        if (b != 0 && a * b / b != a || a == -1 && b == Integer.MIN_VALUE || a == Integer.MIN_VALUE && b == -1) {
            throw new OverflowException();
        }
        return a * b;
    }

    @Override
    public int apply(int a, int b) throws OverflowException {
        return checkedMul(a, b);
    }
}