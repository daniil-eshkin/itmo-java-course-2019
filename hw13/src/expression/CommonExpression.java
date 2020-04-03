package expression;

public abstract class CommonExpression implements Expression, TripleExpression {
    public String toBracedString() {
        return "(" + toMiniString() + ")";
    }
}
