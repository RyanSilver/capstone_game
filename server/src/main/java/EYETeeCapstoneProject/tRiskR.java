package EYETeeCapstoneProject;

//import javax.swing.*;
import java.util.*;
import java.util.*;
import java.net.*;
import java.io.*;
//
//java tRisk -debug -jRandom.class -pRandom.py
//taskkill /F /IM python.exe
//
public class tRiskR
  {
  private static boolean DEBUG = false;
  private static boolean goSlow = false;
  private static boolean UNIX=false;

  private static String playerOne=null;
  private static String playerOneType=null;
  private static String playerTwo=null;
  private static String playerTwoType=null;

  private static Game game = null;
  private static int gameNumber = -1;
  private Socket webConnect;

  public tRiskR(int gameNumber){
    //maby set values here instead?
    this.gameNumber=gameNumber;
  }

  private static void usage(String [] args)
  {
    for (int i=0; i < args.length; i++)
    {
      U.pr(args[i] + "\n");
      if (args[i].equals("-slow"))
        goSlow = true;
      if (args[i].equals("-unix"))
        UNIX = true;
      else if (args[i].equals("-debug"))
        DEBUG = true;
      else
      {
        boolean isJava   = (args[i].substring(0,2).equals("-j"));
        boolean isPython = (args[i].substring(0,2).equals("-p"));
        if (isJava && playerOne == null)
        {
          playerOne = args[i].substring(2);
          playerOneType = "java";
        }
        else if (isJava)
        {
          playerTwo = args[i].substring(2);
          playerTwoType = "java";
        }
        else if (isPython && playerOne == null)
        {
          playerOne = args[i].substring(2);
          playerOneType = "python";
        }
        else if (isPython)
        {
          playerTwo = args[i].substring(2);
          playerTwoType = "python";
        }
       if (DEBUG) U.pr("tRisk.main>>>" + playerOne + " " + playerOneType + "\n");
      }
    }
    if (System.getProperty("os.name").equals("Linux")) UNIX=true;
    if (DEBUG) U.pr("tRisk.usage Completed\n");
  }

  /*
    runnable varient of the main method.
  */
  public static void runGame(String [] args, CubbyHole rec, CubbyHole send){
    usage(args);
    if (DEBUG) U.pr("tRisk.main\n");

    game = new Game(DEBUG);
    playerOne="player";
    game.initPlayerWeb(playerOneType,playerOne, "BLUE", rec, send);

    game.getP0().send("( STATUS )");
    U.rAssert((game.getP0().receive()).equals("( ACK )"),"101-06");

    U.pr("tRisk:main::>>>3\n");
    //System.exit(0);           commented out by ryan

    playerTwoType="java";
    playerTwo="random";
    game.initPlayer(playerTwoType,playerTwo, "RED");

    gameNumber = game.getGameNumber();

    playerNode AttTerr = null;
    playerNode DefTerr = null;

    game.message("( MESSAGE MAP Initialized 9 )");
    game.message("( MESSAGE GAME_NODE " + gameNumber + " )");
    U.pr("tRisk:main::>>>1\n");

//    if (DEBUG) game.report();

    U.pr("tRisk:main::>>>2\n");

    game.getP0().send("( STATUS )");
    U.rAssert((game.getP0().receive()).equals("( ACK )"),"101-06");

    if (DEBUG) game.report();

    U.pr("tRisk:main::>>>3\n");

    //System.exit(0); commented out by ryan

    game.initializeTerritories();

    int moveCount = 0;

    int WHO = (int) (1.0 + Math.random() * 10) % 2;
    boolean gameContinues = true;

    for (;gameContinues;)
    {
      if ((0 >= game.countP0Territories()) ||
          (0 >= game.countP1Territories()))
      {
        gameContinues = false;
        break;
      }

      if (WHO == 1)      { AttTerr = game.getP1(); DefTerr = game.getP0(); }
      else if (WHO == 0) { AttTerr = game.getP0(); DefTerr = game.getP1(); }

      game.addBonusArmies(AttTerr, DefTerr);

      game.message("( MESSAGE " + AttTerr.getColor() + " Adds " + game.addBonusArmies(AttTerr,DefTerr) + ", Attacking )");
//Attacking
      game.attack(AttTerr, DefTerr);

//Movement
      //game.movement(AttTerr, DefTerr);

      game.message("( MESSAGE STATUS RED(" +
                 game.countP0Territories()  + ":" +
                 game.countP0Armies() + ")  " +
                 "BLUE(" +
                 game.countP1Territories() + ":" +
                 game.countP1Armies()+ ")\n");

      WHO = (WHO + 1) % 2;
    }
      //p1.send("( STATUS )");

      //p0.send("( STATUS )");
      //rAssert((p0.receive()).equals("( ACK )"),"101-06");

    U.pr("main conclude" + "\n");

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
