package expression;

import expression.exceptions.EvaluateException;

public interface TripleExpression extends ToMiniString {
    int evaluate(int x, int y, int z) throws EvaluateException;
}