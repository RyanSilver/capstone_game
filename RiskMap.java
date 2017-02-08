public class RiskMap
{
  private boolean DEBUG = false;
  private CubbyHole toDraw = new CubbyHole();
  private rMap map = null;
  public RiskMap(boolean d)
  {
    DEBUG = d;
    map = new rMap(toDraw);
    initMap();
  }

  public void send(String s)
  {
    toDraw.put(s);
  }
  
  private void initMap()
  {
    map = new rMap(toDraw);
    String sss = null;
    for (int i=0; (sss = RDB.getCircle(i)) != null; i++)
    {
      toDraw.put(sss);
    }
    for (int i=0; (sss = RDB.getConnection(i)) != null; i++)
    {
      String [] tok = sss.split(" " );
      if (tok[6].equals("true"))
      {
        int [] fromP = RDB.getCoord(tok[2]);
        int [] toP = RDB.getCoord(tok[4]);
        int [] newFrom = RDB.correctCoord(fromP,tok[3]);
        int [] newTo   = RDB.correctCoord(toP,tok[5]);
        String ttt = "( LINE " + newFrom[0] + " " + newFrom[1] + " " + newTo[0] + " " + newTo[1] + " " +" )";
        toDraw.put(ttt);
      }
      //else pr("888>>>" + sss + "\n");
    }
//FOUR hack lines that are not connections, wraparound, E -> W
//ALA -> W
    {
      int [] a1 = RDB.getCoord("ALA");
      int [] a2 = RDB.correctCoord(a1,"w");
      String ttt = "( LINE " + (a2[0]-15) + " " + (a2[1]+3) + " " + a2[0] + " " + a2[1] + " " +" )";
      toDraw.put(ttt);
    }
//ARG -> W
    {
      int [] a1 = RDB.getCoord("ARG");
      int [] a2 = RDB.correctCoord(a1,"w");
      String ttt = "( LINE " + (a2[0]-140) + " " + (a2[1]+15) + " " + a2[0] + " " + a2[1] + " " +" )";
      toDraw.put(ttt);
    }
//KAM -> E
    {
      int [] a1 = RDB.getCoord("KAM");
      int [] a2 = RDB.correctCoord(a1,"e");
      String ttt = "( LINE " + a2[0] + " " + a2[1] + " " + (a2[0]+100) + " " + (a2[1]-5) + " " +" )";
      toDraw.put(ttt);
    }
//NZE -> E
    {
      int [] a1 = RDB.getCoord("NZE");
      int [] a2 = RDB.correctCoord(a1,"e");
      String ttt = "( LINE " + a2[0] + " " + a2[1] + " " + (a2[0]+30) + " " + (a2[1]-5) + " " +" )";
      toDraw.put(ttt);
    }
  }
}