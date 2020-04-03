package expression.exceptions;

import expression.*;

public class CheckedPow extends BinOperator {
    public CheckedPow(CommonExpression a, CommonExpression b) {
        super(a, b, 3, false, "**");
    }

    @Override
    public int apply(int a, int b) throws OverflowException, EvaluateArgumentException {
        if (b < 0) {
            throw new EvaluateArgumentException("Cannot raise " + a + " to negative power");
        } else if (a == 0) {
            if (b <= 0) {
                throw new EvaluateArgumentException("Cannot raise 0 to non-positive power");
            } else {
                return 0;
            }
        } else if (a == 1) {
            return a;
        } else if (a == -1) {
            return (b % 2 == 0 ? 1 : -1);
        } else {
            int x = 1;
            for (int i = 0; i < b; i++) {
                x = CheckedMultiply.checkedMul(x, a);
            }
            return x;
        }
    }
}
