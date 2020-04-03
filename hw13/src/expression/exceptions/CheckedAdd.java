package expression.exceptions;

import expression.CommonExpression;
import expression.BinOperator;

public class CheckedAdd extends BinOperator {
    public CheckedAdd(CommonExpression a, CommonExpression b) {
        super(a, b, 1, true, "+");
    }

    @Override
    public int apply(int a, int b) throws OverflowException {
        int c = a + b;
        if (a > 0 && b > 0 && c <= 0 || a < 0 && b < 0 && c >= 0) {
            throw new OverflowException();
        }
        return a + b;
    }
}
