package expression;

import expression.exceptions.EvaluateException;

public interface Expression extends ToMiniString {
    int evaluate(int x) throws EvaluateException;
}
