package expression;

public class Const extends CommonExpression {
    private int val;

    public Const(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    @Override
    public int evaluate(int x) {
        return val;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Const con = (Const) obj;
        return val == con.getVal();
    }

    @Override
    public int hashCode() {
        return val;
    }
}
