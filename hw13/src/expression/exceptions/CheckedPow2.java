package expression.exceptions;

import expression.*;

public class CheckedPow2 extends UnaryOperator {
    public CheckedPow2(CommonExpression a) {
        super(a, "pow2");
    }

    @Override
    protected int apply(int a) throws OverflowException, EvaluateArgumentException {
        if (a < 0) {
            throw new EvaluateArgumentException("Cannot raise 2 to negative power");
        }
        int x = 1;
        for (int i = 0; i < a; i++) {
            x = CheckedMultiply.checkedMul(x, 2);
        }
        return x;
    }
}
