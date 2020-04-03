package expression.exceptions;

import expression.CommonExpression;
import expression.UnaryOperator;

public class CheckedNegate extends UnaryOperator {
    public CheckedNegate(CommonExpression a) {
        super(a, "-");
    }

    @Override
    protected int apply(int a) throws OverflowException {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return -a;
    }
}
