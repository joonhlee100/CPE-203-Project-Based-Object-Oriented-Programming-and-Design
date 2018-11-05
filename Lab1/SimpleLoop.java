class SimpleLoop
{
   public static int sum(int low, int high)
   {
      /* TO DO:  Return the sum of the integers between
         low and high (inclusive).  Yes, this can be
         done without a loop, but the point is to
         practice the syntax for a loop.
      */
       int tot = 0;

       for (int i = 0; i <=high; i++){
           tot += tot + i;
       }

      return tot;
   }
}
