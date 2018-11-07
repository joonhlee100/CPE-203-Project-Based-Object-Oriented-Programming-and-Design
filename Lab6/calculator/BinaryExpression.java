public abstract class BinaryExpression implements Expression
{
    private final Expression lft;
    private final Expression rht;
    private final String operator;

    public BinaryExpression(final Expression lft, final Expression rht, String operator)
    {
        this.lft = lft;
        this.rht = rht;
        this.operator = operator;
    }

    public String toString()
    {
        return "(" + lft + operator + rht + ")";
    }

    public double evaluate(final Bindings bindings)
    {
        double left = lft.evaluate(bindings);
        double right = rht.evaluate(bindings);
        double result = _applyOperator(l,r);
        return result;
    }

<<<<<<< HEAD
    protected abstract double _applyOperator(double lftt, double rht);
}
=======
    protected abstract double _applyOperator(double lftt, double rht) {}
}
>>>>>>> ebe284fc889f2f3961a5a09baaf392f23fb171f4
