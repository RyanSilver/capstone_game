//package EYETeeCapstoneProject;

import java.util.*;

/**
 * class used for game features like attacking and setting up playernodes 
 * @author Ryan
 */
public class Game {

    private boolean DEBUG = false;
    private int speed = 2;

    /**
     * slows down the game to a human comprehensible level
     */
    public void setSpeedSlow() {
        speed = 1;
    }

    /**
     * UNUSED. 
     * returns the game number.
     * @return
     */
    public int getGameNumber() {
        return 1;
    }
    private Random generator = new Random(17);
    private RDB rdb;
    private int gameNumber = rdb.getGameNumber();
    private RiskMap map = null;

    private playerNode p0 = null;
    private playerNode p1 = null;

    /**
     * sets up a game 
     * @param d: debug mode 
     */
    public Game(boolean d) //, String oneType, String one, String twoType, String two)
    {
        rdb = null;
        rdb = new RDB();
        gameNumber = rdb.getGameNumber();
        DEBUG = d;
        map = new RiskMap(DEBUG);
        //p0 = new playerNode(oneType,one);
        //p1 = new playerNode(twoType,two);
    }

    /**
     * returns player0
     * @return
     */
    public playerNode getP0() {
        return p0;
    }

    /**
     * returns player2
     * @return
     */
    public playerNode getP1() {
        return p1;
    }

//  private CubbyHole toPa =   new CubbyHole();
//  private CubbyHole fromPa = new CubbyHole();
//  private CubbyHole toPb =   new CubbyHole();
//  private CubbyHole fromPb = new CubbyHole();
//  public playerNode p1 = new playerNode("java","playerRAN");//,new playerRAN(toPb,fromPb),toPb,fromPb, "BLUE");
//  public void setPlayerP0Python(String name)
//  {
//    p0 = new playerNode("python","playerA",new playerA(toPa,fromPa),toPa,fromPa, "RED");
//  }
//  public boolean isP0Set()
//  {
//    return p0 == null;
//  }
//  public void setPlayerP0Java(String name)
//  {
//    p0 = new playerNode("java","playerA",new playerA(toPa,fromPa),toPa,fromPa, "RED");
//  }

    /**
     * initialize a random player 
     * @param playerType: if in java use java playertype
     * @param playerFileName : name of player 
     * @param color : color of player
     */
    public void initPlayer(String playerType, String playerFileName, String color) {
        try {
            if (DEBUG) {
                U.pr("Game.initPlayer1>>> " + playerType + " " + playerFileName + "\n");
            }

            playerNode pp = new playerNode(DEBUG, playerType, playerFileName);
            if (DEBUG) {
                U.pr("Game.initPlayer2>>>" + "\n");
            }
            pp.setColor(color);
            if (DEBUG) {
                U.pr("Game.initPlayer3>>>" + "\n");
            }

            message("( MESSAGE " + playerType + " " + pp.name + " is " + pp.getColor() + " )");
            if (DEBUG) {
                U.pr("Game.initPlayer4>>>" + "\n");
            }

            pp.send("( GAME_NODE " + pp.name + " " + gameNumber + " " + pp.getColor() + " )");
            if (DEBUG) {
                U.pr("Game.initPlayer5>>>" + "\n");
            }
            U.rAssert((pp.receive()).equals("( ACK )"), "101-01");
            if (DEBUG) {
                U.pr("Game.initPlayer6>>>" + "\n");
            }

            for (String xx = rdb.getFirstTerritory();
                    xx != null;
                    xx = rdb.getSuccessorTerritory(xx)) {
                String ss = rdb.getTerritoryInfo(xx);
                pp.send(ss);
                U.rAssert((pp.receive()).equals("( ACK )"), "101-02");
            }
            String ss = null;
            for (int i = 0; (ss = mapConns.getConnectionShort(i)) != null; i++) {
                pp.send(ss);
                U.rAssert((pp.receive()).equals("( ACK )"), "101-03");
            }

            pp.send("( STATUS )");
            U.rAssert((pp.receive()).equals("( ACK )"), "101-04");
            if (color.equals("BLUE")) {
                p0 = pp;
            } else {
                p1 = pp;
            }
        } catch (Exception e) {

        }
    }

    /*
    version of init player to use with web application
     */

    /**
     * initialize a web based player
     * version of initPlayer to use with web application
     * @param playerType : always java
     * @param playerFileName : whatever you want to name the player 
     * @param color : player's color
     * @param recv: cubby holes that link up to a player web
     * @param send: cubby holes that link up to a player web
     * @return
     */

    public boolean initPlayerWeb(String playerType, String playerFileName, String color, CubbyHole recv, CubbyHole send) {
        try {
            if (DEBUG) {
                U.pr("Game.initPlayer1>>> " + playerType + " " + playerFileName + "\n");
            }

            playerNode pp = new playerNode(DEBUG, playerType, playerFileName, recv, send);
            if (DEBUG) {
                U.pr("Game.initPlayer2>>>" + "\n");
            }
            pp.setColor(color);
            if (DEBUG) {
                U.pr("Game.initPlayer3>>>" + "\n");
            }

            message("( MESSAGE " + playerType + " " + pp.name + " is " + pp.getColor() + " )");
            if (DEBUG) {
                U.pr("Game.initPlayer4>>>" + "\n");
            }

            pp.send("( GAME_NODE " + pp.name + " " + gameNumber + " " + pp.getColor() + " )");
            if (DEBUG) {
                U.pr("Game.initPlayer5>>>" + "\n");
            }
            U.rAssert((pp.receive()).equals("( ACK )"), "101-01");
            if (DEBUG) {
                U.pr("Game.initPlayer6>>>" + "\n");
            }

            for (String xx = rdb.getFirstTerritory();
                    xx != null;
                    xx = rdb.getSuccessorTerritory(xx)) {
                String ss = rdb.getTerritoryInfo(xx);
                pp.send(ss);
                U.rAssert((pp.receive()).equals("( ACK )"), "101-02");
            }
            String ss = null;
            for (int i = 0; (ss = mapConns.getConnectionShort(i)) != null; i++) {
                pp.send(ss);
                U.rAssert((pp.receive()).equals("( ACK )"), "101-03");
            }

            pp.send("( STATUS )");
            U.rAssert((pp.receive()).equals("( ACK )"), "101-04");
            if (color.equals("BLUE")) {
                p0 = pp;
            } else {
                p1 = pp;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * used during game. places armies 
     * @param mod : player to place 
     * @param inform : player to be informed 
     * @param init : mode initializeing the map
     * @throws Exception: thrown if web player disconnects 
     */
    public void addArmy(playerNode mod, playerNode inform, boolean init) throws Exception {
        try {
            if (DEBUG) {
                U.pr("Game.addArmy>>>\n");
            }
            String xx = null;
            if (init) {
                mod.send("( YOU_PLACE_ARMY GRAY )");
                xx = mod.receive();
                //System.out.println("xx:" + xx);
            } else {
                mod.send("( YOU_PLACE_ARMY " + mod.getColor() + " )");
                xx = mod.receive();
            }
            if (xx != null) {
                String[] tok = xx.split(" ");
                territoryNode tn = rdb.getTerritoryNode(tok[2]);
                U.rAssert(tn != null, "main-01");
                tn.armyCount++;
                if (init) {
                    U.rAssert(tn.armyCount == 1, "main-02");
                }
                String abc = null;
                String xyz = null;
                tn.armyColor = mod.getColor();
                abc = "( CIRCLE " + tn.xPos + " " + tn.yPos + " " + mod.getColor() + " " + tn.shortName + " " + tn.armyCount + " )";
                xyz = "( MAP_UPDATE " + tn.shortName + " " + mod.getColor() + " " + tn.armyCount + " )";
                message(abc);
                mod.send(xyz);
                U.rAssert((mod.receive()).equals("( ACK )"), "101-03");
                inform.send(xyz);
                U.rAssert((inform.receive()).equals("( ACK )"), "101-04");

            }
        } catch (Exception e) {
            throw new Exception();
        }
    }

    /**
     * resets the territory nodes and starts a game of risk allowing players to place armies
     * @return: true if successful 
     */
    public boolean initializeTerritories() {
        rdb.resetTn();
        try {
            if (DEBUG) {
                U.pr("Game.initializeTerritories>>>\n");
            }
            int WHO = (int) (1.0 + Math.random() * 10) % 2;
            boolean modeInitializingMap = false;
            for (int i = 0; i < 80; i++) {
                modeInitializingMap = !(i > 42);
                if (WHO == 1) {
                    addArmy(p1, p0, modeInitializingMap);
                } else if (WHO == 0) {
                    addArmy(p0, p1, modeInitializingMap);
                }
                WHO = (WHO + 1) % 2;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * sends a transaction and gets a string back from player
     * @param p: player node 
     * @param s: transaction
     * @return: reply from player 
     */
    public String sendTransGetString(playerNode p, String s) {
        if (DEBUG) {
            U.pr("Game.sendTransGetString>>>\n");
        }
        String ans = null;
        p.send(s);
        ans = p.receive();
        return ans;
    }

    /**
     * adds bonus armies according to game rules 
     * @param AttTerr : attacking player
     * @param DefTerr : defending player
     * @return : number of armies added 
     * @throws Exception : thrown if player disconnects 
     */
    public int addBonusArmies(playerNode AttTerr, playerNode DefTerr) throws Exception {
        try {
            if (DEBUG) {
                U.pr("Game.addBonusArmies>>>\n");
            }
            int addArmies = rdb.countTerritories(AttTerr.getColor()) / 3;  //add armies
            addArmies += rdb.haveContinent("NAM", AttTerr.getColor()) ? 5 : 0;//add continent bonus
            addArmies += rdb.haveContinent("SAM", AttTerr.getColor()) ? 2 : 0;
            addArmies += rdb.haveContinent("AFR", AttTerr.getColor()) ? 3 : 0;
            addArmies += rdb.haveContinent("EUR", AttTerr.getColor()) ? 5 : 0;
            addArmies += rdb.haveContinent("ASI", AttTerr.getColor()) ? 7 : 0;
            addArmies += rdb.haveContinent("AUS", AttTerr.getColor()) ? 2 : 0;
            for (int ii = addArmies; ii > 0; ii--) {
                //String xxx = AttTerr.send("( YOU_PLACE_ARMY " + AttTerr.getColor() + " )");// sendTransGetString(AttTerr,"( YOU_PLACE_ARMY " + AttTerr.getColor() + " )");
                //String [] tok = xxx.split(" ");
                //territoryNode tn = rdb.getTerritoryNode(tok[2]);
                //U.rAssert(tn != null,"main-01");
                //tn.armyCount++;
                addArmy(AttTerr, DefTerr, false);
            }
            return addArmies;
        } catch (Exception e) {
            throw new Exception();
        }
    }

    /**
     * communicates with seperate threads for attacks 
     * @param AttTerr: attacking player
     * @param DefTerr: defending player
     * @return
     */
    public boolean attack(playerNode AttTerr, playerNode DefTerr) {
        try {
            if (DEBUG) {
                U.pr("Game.attack>>>\n");
            }
            boolean doneAttack = false;
            for (; !doneAttack;) {

                String xxx = sendTransGetString(AttTerr, "( YOU_ATTACK )");

                if (xxx.equals("( ATTACK_DONE )")) {
                    doneAttack = true;
                } else {
                    int d1 = generator.nextInt(6) + 1;
                    int d2 = generator.nextInt(6) + 1;

                    String[] tok = xxx.split(" ");
                    territoryNode tnA = rdb.getTerritoryNode(tok[2]);
                    territoryNode tnB = rdb.getTerritoryNode(tok[3]);

                    if (d1 > d2) {
                        tnB.armyCount--;
                    } else {
                        tnA.armyCount--;
                    }

                    if (tnB.armyCount == 0) {
//            pr("CRAPPER>>> " + xxx + "\n");
                        tnA.armyCount -= Integer.parseInt(tok[4]);
                        tnB.armyCount = Integer.parseInt(tok[4]);
                        tnB.armyColor = tnA.armyColor;
                    }

                    String xyz1 = "( MAP_UPDATE " + tnA.shortName + " " + tnA.armyColor + " " + tnA.armyCount + " )";
                    AttTerr.send(xyz1);
                    U.rAssert((AttTerr.receive()).equals("( ACK )"), "101-03");
                    DefTerr.send(xyz1);
                    U.rAssert((DefTerr.receive()).equals("( ACK )"), "101-03");

                    String xyz2 = "( MAP_UPDATE " + tnB.shortName + " " + tnB.armyColor + " " + tnB.armyCount + " )";
                    AttTerr.send(xyz2);
                    U.rAssert((AttTerr.receive()).equals("( ACK )"), "101-03");
                    DefTerr.send(xyz2);
                    U.rAssert((DefTerr.receive()).equals("( ACK )"), "101-03");

                    String abcA = "( CIRCLE " + tnA.xPos + " " + tnA.yPos + " " + tnA.armyColor + " " + tnA.shortName + " " + tnA.armyCount + " )";
                    message(abcA);
                    String abcB = "( CIRCLE " + tnB.xPos + " " + tnB.yPos + " " + tnB.armyColor + " " + tnB.shortName + " " + tnB.armyCount + " )";
                    message(abcB);
                    if (speed == 1) {
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                        }
                    }

                }

            }
            return true;
            //toDraw.put("( MESSAGE " + AttTerr.color + " done attacking )");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * currently does not work. 
     * @param AttTerr
     * @param DefTerr
     * @return
     */
    public boolean movement(playerNode AttTerr, playerNode DefTerr) {
        try {
            if (DEBUG) {
                U.pr("Game.movement>>>\n");
            }
            String xxx = sendTransGetString(AttTerr, "( YOU_FORTIFY )");
            if (!xxx.equals("( ACK )")) {
                String[] tok = xxx.split(" ");
                territoryNode tn = rdb.getTerritoryNode(tok[2]);
                U.rAssert(tn != null, "movement-01");
                U.rAssert(tn.armyColor.equals(AttTerr.getColor()), "movement-02");
                U.rAssert(tn.armyCount > 1, "movement-03");
                tn.armyCount -= Integer.parseInt(tok[4]);
                tn = rdb.getTerritoryNode(tok[3]);
                U.rAssert(tn != null, "movement-04");
                U.rAssert(tn.armyColor.equals(AttTerr.getColor()), "movement-05");
                U.rAssert(tn.armyCount >= 1, "movement-06");
                tn.armyCount += Integer.parseInt(tok[4]);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * prints a message S to console 
     * @param s
     */
    public void message(String s) {
        U.pr("Game.message>>> " + s + "\n");
        map.send(s);
    }

    /**
     * counts territories belonging to P0
     * @return
     */
    public int countP0Territories() {
        return rdb.countTerritories("BLUE");
    }

    /**
     * counts armies belonging to P0
     * @return
     */
    public int countP0Armies() {
        return rdb.countArmies("BLUE");
    }

    /**
     * counts territories belonging to P1
     * @return
     */
    public int countP1Territories() {
        return rdb.countTerritories("RED");
    }

    /**
     * counts armies belonging to P1
     * @return
     */
    public int countP1Armies() {
        return rdb.countArmies("RED");
    }

    /**
     * p1 and p0 output strings and status 
     */
    public void report() {
        U.pr("game.report>>>\n");
        U.pr("\t" + p0.toString() + "\n");
        U.pr("\t" + p1.toString() + "\n");
    }
}
