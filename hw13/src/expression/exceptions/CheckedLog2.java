package expression.exceptions;

import expression.*;

public class CheckedLog2 extends UnaryOperator {
    public CheckedLog2(CommonExpression a) {
        super(a, "log2");
    }

    @Override
    protected int apply(int a) throws EvaluateArgumentException {
        if (a <= 0) {
            throw new EvaluateArgumentException("Log2 argument must be greater than 0");
        } else {
            int x = -1;
            while (a > 0) {
                a /= 2;
                x++;
            }
            return x;
        }
    }
}
