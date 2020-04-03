package expression;

import expression.exceptions.UndefinedVariableException;

import java.util.Map;

public class Variable extends CommonExpression {
    private static final Map<String, Variable> VARIABLES = Map.of(
            "x", new Variable("x"),
            "y", new Variable("y"),
            "z", new Variable("z")
    );
    private String var;

    public Variable(String var) {
        this.var = var;
    }

    private UndefinedVariableException error() {
        return new UndefinedVariableException("Cannot evaluate expression with undefined variable");
    }

    public static Variable valueOf(String var) {
        Variable v = VARIABLES.get(var);
        if (v == null) {
            throw new UndefinedVariableException("Undefined variable: " + var);
        }
        return v;
    }

    @Override
    public int evaluate(int x) throws UndefinedVariableException {
        if (!var.equals("x")) {
            throw error();
        }
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) throws UndefinedVariableException {
        switch (var) {
            case "x": return x;
            case "y": return y;
            case "z": return z;
            default: throw error();
        }
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Variable variable = (Variable) obj;
        return var.equals(variable.var);
    }

    @Override
    public int hashCode() {
        return var.hashCode();
    }
}
