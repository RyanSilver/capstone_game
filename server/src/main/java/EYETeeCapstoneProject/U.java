package EYETeeCapstoneProject;

public class U
{
  public static final int  circleSize = 45;
  public static void pr(String s)  { System.out.print(s); }
  public static void rAssert(boolean b, String err)
  {
    if (!b) { pr("\n\n***ERROR*** " + err + "\n");
    //System.exit(0);
  }
  }
  public static void delay(int secs)
  {
    try {Thread.sleep(secs * 1000); } catch(Exception e) {}
  }
}
