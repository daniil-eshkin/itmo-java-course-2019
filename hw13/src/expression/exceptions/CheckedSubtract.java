package expression.exceptions;

import expression.CommonExpression;
import expression.BinOperator;

public class CheckedSubtract extends BinOperator {
    public CheckedSubtract(CommonExpression a, CommonExpression b) {
        super(a, b, 1, false, "-");
    }

    @Override
    public int apply(int a, int b) throws OverflowException {
        int c = a - b;
        if (a > 0 && b < 0 && c <= 0 || a < 0 && b > 0 && c >= 0 || a == 0 && b == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return a - b;
    }
}
