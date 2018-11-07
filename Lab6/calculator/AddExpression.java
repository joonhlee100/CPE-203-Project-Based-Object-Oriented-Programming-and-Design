abstract class AddExpression
   implements Expression
{
   private final Expression lft;
   private final Expression rht;

   public AddExpression(final Expression lft, final Expression rht)
   {
      this.lft = lft;
      this.rht = rht;
   }

    public double _applyOperator(Double left, Double right)
   {
      return  left + right;
   }
}
