//package EYETeeCapstoneProject;

/**
 * used to throw exceptions and errors for disconnecting, and misbehaved players
 * @author Ryan
 */

public class U {

    /**
     *
     */
    public static final int circleSize = 45;

    /**
     * print something to screen 
     * @param s
     */
    public static void pr(String s) {
        System.out.print(s);
    }

    /**
     * used to throw exceptions and errors for disconnecting, and misbehaved players
     * @param b
     * @param err
     * @throws Exception
     */
    public static void rAssert(boolean b, String err) throws Exception {
        if (!b) {
            pr("\n\n***ERROR*** " + err + "\n");
            throw new Exception();
        }
    }

    /**
     * adds delay for slow play. waits 1 second 
     * @param secs
     */
    public static void delay(int secs) {
        try {
            Thread.sleep(secs * 1000);
        } catch (Exception e) {
        }
    }
}
