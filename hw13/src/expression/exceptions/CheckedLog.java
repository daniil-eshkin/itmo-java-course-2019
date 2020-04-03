package expression.exceptions;

import expression.*;

public class CheckedLog extends BinOperator {
    public CheckedLog(CommonExpression a, CommonExpression b) {
        super(a, b, 3, false, "//");
    }

    @Override
    public int apply(int a, int b) throws EvaluateArgumentException {
        if (b <= 1) {
            throw new EvaluateArgumentException("Log base must be greater than 1");
        } else if (a <= 0) {
            throw new EvaluateArgumentException("Log argument must be greater than 0");
        } else {
            int x = 0;
            while (a >= b) {
                a /= b;
                x++;
            }
            return x;
        }
    }
}