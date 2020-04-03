package expression.exceptions;

import expression.CommonExpression;
import expression.BinOperator;

public class CheckedDivide extends BinOperator {
    public CheckedDivide(CommonExpression a, CommonExpression b) {
        super(a, b, 2, false, "/");
    }

    @Override
    public int apply(int a, int b) throws OverflowException, DivisionByZeroException {
        if (b == -1 && a == Integer.MIN_VALUE) {
            throw new OverflowException();
        } else if (b == 0) {
            throw new DivisionByZeroException();
        }
        return a / b;
    }
}
