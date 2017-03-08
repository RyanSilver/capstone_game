package EYETeeCapstoneProject;

import java.util.*;
public class Game
{
  private boolean DEBUG = false;
  private int speed = 2;
  public void setSpeedSlow() { speed = 1; }
  public int getGameNumber() { return 1; }
  private Random generator = new Random(17);
  private RDB rdb = new RDB();
  private int gameNumber = rdb.getGameNumber();
  private RiskMap map = null;

  private playerNode p0 = null;
  private playerNode p1 = null;

  public Game(boolean d) //, String oneType, String one, String twoType, String two)
  {
    DEBUG = d;
    map = new RiskMap(DEBUG);
    //p0 = new playerNode(oneType,one);
    //p1 = new playerNode(twoType,two);
  }

  public playerNode getP0() { return p0; }
  public playerNode getP1() { return p1; }

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

  public void initPlayer(String playerType, String playerFileName, String color)
  {
    if (DEBUG) U.pr("Game.initPlayer1>>> "+ playerType + " " + playerFileName + "\n");

    playerNode pp = new playerNode(DEBUG,playerType,playerFileName);
    if (DEBUG) U.pr("Game.initPlayer2>>>" + "\n");
    pp.setColor(color);
    if (DEBUG) U.pr("Game.initPlayer3>>>" + "\n");

    message("( MESSAGE " + playerType + " " + pp.name + " is " + pp.getColor() + " )");
    if (DEBUG) U.pr("Game.initPlayer4>>>" + "\n");

    pp.send("( GAME_NODE " + pp.name + " " + gameNumber + " " + pp.getColor() + " )");
    if (DEBUG) U.pr("Game.initPlayer5>>>" + "\n");
    U.rAssert((pp.receive()).equals("( ACK )"),"101-01");
    if (DEBUG) U.pr("Game.initPlayer6>>>" + "\n");

    for (
         String xx = rdb.getFirstTerritory();
         xx != null;
         xx = rdb.getSuccessorTerritory(xx)
        )
    {
      String ss = rdb.getTerritoryInfo(xx);
      pp.send(ss);
      U.rAssert((pp.receive()).equals("( ACK )"),"101-02");
    }
    String ss = null;
    for(int i=0; (ss = mapConns.getConnectionShort(i)) != null; i++)
    {
      pp.send(ss);
      U.rAssert((pp.receive()).equals("( ACK )"),"101-03");
    }

    pp.send("( STATUS )");
    U.rAssert((pp.receive()).equals("( ACK )"),"101-04");
    if (color.equals("BLUE")){
        p0=pp;
    }
    else{
        p1=pp;
    }
  }

  /*
    version of init player to use with web application
  */
  public void initPlayerWeb(String playerType, String playerFileName, String color, CubbyHole recv, CubbyHole send)
  {
    if (DEBUG) U.pr("Game.initPlayer1>>> "+ playerType + " " + playerFileName + "\n");

    playerNode pp = new playerNode(DEBUG,playerType,playerFileName, recv, send);
    if (DEBUG) U.pr("Game.initPlayer2>>>" + "\n");
    pp.setColor(color);
    if (DEBUG) U.pr("Game.initPlayer3>>>" + "\n");

    message("( MESSAGE " + playerType + " " + pp.name + " is " + pp.getColor() + " )");
    if (DEBUG) U.pr("Game.initPlayer4>>>" + "\n");

    pp.send("( GAME_NODE " + pp.name + " " + gameNumber + " " + pp.getColor() + " )");
    if (DEBUG) U.pr("Game.initPlayer5>>>" + "\n");
    U.rAssert((pp.receive()).equals("( ACK )"),"101-01");
    if (DEBUG) U.pr("Game.initPlayer6>>>" + "\n");

    for (
         String xx = rdb.getFirstTerritory();
         xx != null;
         xx = rdb.getSuccessorTerritory(xx)
        )
    {
      String ss = rdb.getTerritoryInfo(xx);
      pp.send(ss);
      U.rAssert((pp.receive()).equals("( ACK )"),"101-02");
    }
    String ss = null;
    for(int i=0; (ss = mapConns.getConnectionShort(i)) != null; i++)
    {
      pp.send(ss);
      U.rAssert((pp.receive()).equals("( ACK )"),"101-03");
    }

    pp.send("( STATUS )");
    U.rAssert((pp.receive()).equals("( ACK )"),"101-04");
    if (color.equals("BLUE")){
        p0=pp;
    }
    else{
        p1=pp;
    }
  }


  public void addArmy(playerNode mod, playerNode inform, boolean init)
  {
    try{
    if (DEBUG) U.pr("Game.addArmy>>>\n");
    String xx = null;
    if (init)
    {
      mod.send("( YOU_PLACE_ARMY GRAY )");
      xx = mod.receive();
      //System.out.println("xx:" + xx);
    }
    else
    {
      mod.send("( YOU_PLACE_ARMY " + mod.getColor() + " )");
      xx = mod.receive();
    }
    if(xx!=null){
      String [] tok = xx.split(" ");
      territoryNode tn = rdb.getTerritoryNode(tok[2]);
      U.rAssert(tn != null,"main-01");
      tn.armyCount++;
      if (init)
        U.rAssert(tn.armyCount == 1,"main-02");
      String abc = null;
      String xyz = null;
      tn.armyColor = mod.getColor();
      abc = "( CIRCLE " + tn.xPos + " " + tn.yPos + " " + mod.getColor() + " " + tn.shortName + " " + tn.armyCount + " )";
      xyz = "( MAP_UPDATE " + tn.shortName + " " + mod.getColor() + " " + tn.armyCount + " )";
      message(abc);
      mod.send(xyz);
      U.rAssert((mod.receive()).equals("( ACK )"),"101-03");
      inform.send(xyz);
      U.rAssert((inform.receive()).equals("( ACK )"),"101-04");
    }
  }catch(Exception e){
    System.out.println("got that null pointer error again "+init);
  }
  }

  public void initializeTerritories()
  {
    if (DEBUG) U.pr("Game.initializeTerritories>>>\n");
    int WHO = (int) (1.0 + Math.random() * 10) % 2;
    boolean modeInitializingMap =  false;
    for (int i=0; i<80; i++)
    {
      modeInitializingMap =  ! rdb.territoriesFull();
      if (WHO == 1)
        addArmy(p1, p0, modeInitializingMap);
      else if (WHO == 0)
        addArmy(p0, p1, modeInitializingMap);
      WHO = (WHO + 1) % 2;
    }
  }

  public String sendTransGetString(playerNode p, String s)
  {
    if (DEBUG) U.pr("Game.sendTransGetString>>>\n");
    String ans = null;
    p.send(s);
    ans = p.receive();
    return ans;
  }

  public int addBonusArmies(playerNode AttTerr, playerNode DefTerr)
  {
    if (DEBUG) U.pr("Game.addBonusArmies>>>\n");
    int addArmies = rdb.countTerritories(AttTerr.getColor()) / 3;  //add armies
    addArmies += rdb.haveContinent("NAM", AttTerr.getColor()) ? 5: 0 ;//add continent bonus
    addArmies += rdb.haveContinent("SAM", AttTerr.getColor()) ? 2: 0 ;
    addArmies += rdb.haveContinent("AFR", AttTerr.getColor()) ? 3: 0 ;
    addArmies += rdb.haveContinent("EUR", AttTerr.getColor()) ? 5: 0 ;
    addArmies += rdb.haveContinent("ASI", AttTerr.getColor()) ? 7: 0 ;
    addArmies += rdb.haveContinent("AUS", AttTerr.getColor()) ? 2: 0 ;
    for (int ii = addArmies;ii >0; ii-- )
    {
      //String xxx = AttTerr.send("( YOU_PLACE_ARMY " + AttTerr.getColor() + " )");// sendTransGetString(AttTerr,"( YOU_PLACE_ARMY " + AttTerr.getColor() + " )");
      //String [] tok = xxx.split(" ");
      //territoryNode tn = rdb.getTerritoryNode(tok[2]);
      //U.rAssert(tn != null,"main-01");
      //tn.armyCount++;
      addArmy(AttTerr, DefTerr, false);
    }
    return addArmies;
  }

  public void attack(playerNode AttTerr, playerNode DefTerr)
  {
    if (DEBUG) U.pr("Game.attack>>>\n");
    boolean doneAttack = false;
    for (;!doneAttack;)
    {

      String xxx = sendTransGetString(AttTerr,"( YOU_ATTACK )");

      if (xxx.equals("( ATTACK_DONE )"))
        doneAttack = true;
      else
      {
        int d1 = generator.nextInt(6) + 1;
        int d2 = generator.nextInt(6) + 1;

        String [] tok = xxx.split(" ");
        territoryNode tnA = rdb.getTerritoryNode(tok[2]);
        territoryNode tnB = rdb.getTerritoryNode(tok[3]);

        if (d1 > d2)
          tnB.armyCount--;
        else
          tnA.armyCount--;

        if (tnB.armyCount == 0)
        {
//            pr("CRAPPER>>> " + xxx + "\n");
          tnA.armyCount-=Integer.parseInt(tok[4]);
          tnB.armyCount=Integer.parseInt(tok[4]);
          tnB.armyColor = tnA.armyColor;
        }

        String xyz1 = "( MAP_UPDATE " + tnA.shortName + " " + tnA.armyColor + " " + tnA.armyCount + " )";
        AttTerr.send(xyz1);
        U.rAssert((AttTerr.receive()).equals("( ACK )"),"101-03");
        DefTerr.send(xyz1);
        U.rAssert((DefTerr.receive()).equals("( ACK )"),"101-03");

        String xyz2 = "( MAP_UPDATE " + tnB.shortName + " " + tnB.armyColor + " " + tnB.armyCount + " )";
        AttTerr.send(xyz2);
        U.rAssert((AttTerr.receive()).equals("( ACK )"),"101-03");
        DefTerr.send(xyz2);
        U.rAssert((DefTerr.receive()).equals("( ACK )"),"101-03");

        String abcA = "( CIRCLE " + tnA.xPos + " " + tnA.yPos + " " + tnA.armyColor + " " + tnA.shortName + " " + tnA.armyCount + " )";
        message(abcA);
        String abcB = "( CIRCLE " + tnB.xPos + " " + tnB.yPos + " " + tnB.armyColor + " " + tnB.shortName + " " + tnB.armyCount + " )";
        message(abcB);
        if (speed == 1) {try {Thread.sleep(1000);} catch(Exception e) {}}

      }

    }
    //toDraw.put("( MESSAGE " + AttTerr.color + " done attacking )");
  }

  public void movement(playerNode AttTerr, playerNode DefTerr)
  {
    if (DEBUG) U.pr("Game.movement>>>\n");
    String xxx = sendTransGetString(AttTerr,"( YOU_FORTIFY )");
    if (!xxx.equals("( ACK )"))
    {
      String [] tok = xxx.split(" ");
      territoryNode tn = rdb.getTerritoryNode(tok[2]);
      U.rAssert(tn != null,"movement-01");
      U.rAssert(tn.armyColor.equals(AttTerr.getColor()),"movement-02");
      U.rAssert(tn.armyCount > 1,"movement-03");
      tn.armyCount -= Integer.parseInt(tok[4]);
      tn = rdb.getTerritoryNode(tok[3]);
      U.rAssert(tn != null,"movement-04");
      U.rAssert(tn.armyColor.equals(AttTerr.getColor()),"movement-05");
      U.rAssert(tn.armyCount >= 1,"movement-06");
      tn.armyCount += Integer.parseInt(tok[4]);
    }
  }

  public void initPlayers()
  {
  }

  public void message(String s)
  {
    U.pr("Game.message>>> " + s + "\n");
    map.send(s);
  }

  public int countP0Territories()
  {
    return rdb.countTerritories("BLUE");
  }

  public int countP0Armies()
  {
    return rdb.countArmies("BLUE");
  }

  public int countP1Territories()
  {
    return rdb.countTerritories("RED");
  }

  public int countP1Armies()
  {
    return rdb.countArmies("RED");
  }

  public void report()
  {
    U.pr("game.report>>>\n");
    U.pr("\t" + p0.toString() + "\n");
    U.pr("\t" + p1.toString() + "\n");
  }
}
