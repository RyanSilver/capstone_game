//package EYETeeCapstoneProject;

//import javax.swing.*;
import java.util.*;
import java.util.*;
import java.net.*;
import java.io.*;
//
//java tRisk -debug -jRandom.class -pRandom.py
//taskkill /F /IM python.exe
//

/**
 * the next level up from game.java: uses the game class to actually setup and play games of risk
 * @author Ryan
 */
public class tRiskR {

    private static boolean DEBUG = false;
    private static boolean goSlow = false;
    private static boolean UNIX = false;

    private static String playerOne = null;
    private static String playerOneType = null;
    private static String playerTwo = null;
    private static String playerTwoType = null;

    private Game game = null;
    private static int gameNumber = -1;
    private Socket webConnect;

    /**
     * 
     * @param gameNumber
     */
    public tRiskR(int gameNumber) {
        //maby set values here instead?
        this.gameNumber = gameNumber;
    }
 
    
    private static void usage(String[] args) {
        for (int i = 0; i < args.length; i++) {
            U.pr(args[i] + "\n");
            if (args[i].equals("-slow")) {
                goSlow = true;
            }
            if (args[i].equals("-unix")) {
                UNIX = true;
            } else if (args[i].equals("-debug")) {
                DEBUG = true;
            } else {
                boolean isJava = (args[i].substring(0, 2).equals("-j"));
                boolean isPython = (args[i].substring(0, 2).equals("-p"));
                if (isJava && playerOne == null) {
                    playerOne = args[i].substring(2);
                    playerOneType = "java";
                } else if (isJava) {
                    playerTwo = args[i].substring(2);
                    playerTwoType = "java";
                } else if (isPython && playerOne == null) {
                    playerOne = args[i].substring(2);
                    playerOneType = "python";
                } else if (isPython) {
                    playerTwo = args[i].substring(2);
                    playerTwoType = "python";
                }
                if (DEBUG) {
                    U.pr("tRisk.main>>>" + playerOne + " " + playerOneType + "\n");
                }
            }
        }
        if (System.getProperty("os.name").equals("Linux")) {
            UNIX = true;
        }
        if (DEBUG) {
            U.pr("tRisk.usage Completed\n");
        }
    }

    /*
    runnable varient of the main method.
     */

    /**
     *
     * @param args: arguments passed when started 
     * @param rec: cubby hole for player web 
     * @param send: cubby hole for player web 
     * @return: true if game was successful 
     */
    public boolean runGame(String[] args, CubbyHole rec, CubbyHole send) {
        usage(args);
        playerOneType = "java";
        if (DEBUG) {
            U.pr("tRisk.main\n");
        }

        game = new Game(DEBUG);
        playerOne = "player";
        if (!game.initPlayerWeb(playerOneType, playerOne, "BLUE", rec, send)) {
            return false;
        }

        game.getP0().send("( STATUS )");
        if (!game.getP0().receive().equals("( ACK )")) {
            return false;
        }

        U.pr("tRisk:main::>>>3\n");

        playerTwoType = "java";
        playerTwo = "random";
        game.initPlayer(playerTwoType, playerTwo, "RED");

        gameNumber = game.getGameNumber();

        playerNode AttTerr = null;
        playerNode DefTerr = null;

        game.message("( MESSAGE MAP Initialized 9 )");
        game.message("( MESSAGE GAME_NODE " + gameNumber + " )");
        U.pr("tRisk:main::>>>1\n");

//    if (DEBUG) game.report();
        U.pr("tRisk:main::>>>2\n");

        game.getP0().send("( STATUS )");
        try {
            U.rAssert((game.getP0().receive()).equals("( ACK )"), "101-06");
        } catch (Exception e) {
            return false;
        }
        if (DEBUG) {
            game.report();
        }

        U.pr("tRisk:main::>>>3\n");

        if (!game.initializeTerritories()) {
            return false;
        }

        int moveCount = 0;

        int WHO = (int) (1.0 + Math.random() * 10) % 2;
        boolean gameContinues = true;

        for (; gameContinues;) {
            if ((0 >= game.countP0Territories())
                    || (0 >= game.countP1Territories())) {
                gameContinues = false;
                break;
            }

            if (WHO == 1) {
                AttTerr = game.getP1();
                DefTerr = game.getP0();
            } else if (WHO == 0) {
                AttTerr = game.getP0();
                DefTerr = game.getP1();
            }
            try {
                game.addBonusArmies(AttTerr, DefTerr);
            } catch (Exception e) {
                gameContinues = false;
                return false;
            }
            try {
                game.message("( MESSAGE " + AttTerr.getColor() + " Adds " + game.addBonusArmies(AttTerr, DefTerr) + ", Attacking )");
            } catch (Exception e) {
                gameContinues = false;
                return false;
            }
//Attacking
            if (!game.attack(AttTerr, DefTerr)) {
                gameContinues = false;
                return false;
            }

//Movement currently does not function properly
/*
      if(!game.movement(AttTerr, DefTerr)){
        gameContinues=false;
      }
             */
            game.message("( MESSAGE STATUS RED("
                    + game.countP0Territories() + ":"
                    + game.countP0Armies() + ")  "
                    + "BLUE("
                    + game.countP1Territories() + ":"
                    + game.countP1Armies() + ")\n");

            WHO = (WHO + 1) % 2;
        }
        //p1.send("( STATUS )");

        //p0.send("( STATUS )");
        //rAssert((p0.receive()).equals("( ACK )"),"101-06");
        U.pr("main conclude" + "\n");
        return true;
    }
    /*
  public static void main(String [] args)
  {
    CubbyHole a,b;
    a=null;
    b=null;
    runGame(args,a,b);
  }
     */
}
