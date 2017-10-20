
import java.util.*;
import java.net.*;
import java.io.*;

public class playerClient extends Thread
{
  private static String myGameNumber = "***";
  private static String myColor      = "***";
  private static String myName       = "***";
  private static boolean UIon=false;
  private static clientMap cMap;
  private static Random generator = new Random(17);
  private Socket server;
  private DataInputStream in;
  private DataOutputStream out;
  public playerClient(Socket c, boolean mode,String userName) throws Exception
  {
    super();
    server = c;
    in = new DataInputStream(server.getInputStream());
    out = new DataOutputStream(server.getOutputStream());
    UIon=mode;
    out.writeUTF(userName);
    start();
    if (mode){
      cMap=new clientMap();
      cMap.showUI();
    }
  }

//
//terrs
//
  private class terr
  {
    public String name;
    public String color;
    public int count;
    public terr(String a, String b, int c)
    {
      name = a;
      color = b;
      count = c;
    }
    public String toString()
    {
      return "( terr: name=" + name + ", color=" + color + ", count=" + count + " )";
    }
  }
  private ArrayList<terr> terrs = new ArrayList<terr>();
  private void printterrs(){
    for (int i=0;i<terrs.size() ;i++ ) {
      System.out.println(terrs.get(i).toString());
    }

  }

  private terr getTerr(String cc)
  {
    terr ans = null;
    for (int i=0; i<terrs.size(); i++)
    {
      terr t = terrs.get(i);
      if (t.name.equals(cc))
      {
        ans = t;
        break;
      }
    }
    return ans;
  }

  private int countTerrs(String cc)
  {
    int count = 0;
    for (int i=0; i<terrs.size(); i++)
    {
      terr t = terrs.get(i);
      if (t.color.equals(cc))
        count ++;
    }
    return count;
  }

  private int countArmies(String cc)
  {
    int count = 0;
    for (int i=0; i<terrs.size(); i++)
    {
      terr t = terrs.get(i);
      if (t.color.equals(cc))
        count += t.count;
    }
    return count;
  }

  private String getNthColorTerr(int n, String cc)
  {
    int count = 0;
    String ans = null;
    for (int i=0; i<terrs.size(); i++)
    {
      terr t = terrs.get(i);
      if (t.color.equals(cc))
      {
        count++;
        if (n == count)
        {
          ans = t.name;
          break;
        }
      }
    }
    return ans;
  }

//
//conns
//
  private class conn
  {
    public String from;
    public String to;
    public conn(String a, String b)
    {
      from = a;
      to = b;
    }
  }
  private ArrayList<conn> conns = new ArrayList<conn>();

  private ArrayList<String> adjAll(String cc)
  {
    ArrayList<String> adj = new ArrayList<String>();
    for (int i=0; i<conns.size(); i++)
    {
      conn t = conns.get(i);
      if (t.from.equals(cc) || t.to.equals(cc))
      {
        if (t.from.equals(cc))    adj.add(t.to);
        else if (t.to.equals(cc)) adj.add(t.from);
      }
    }
    return adj;
  }

  private ArrayList<String> adjByColor(String cc, boolean eqColor)
  {
    ArrayList<String> ans = new ArrayList<String>();
    ArrayList<String> all = adjAll(cc);
    //printAL(all);
    terr s = getTerr(cc);

    for (int i=0; i<all.size(); i++)
    {
      terr t = getTerr(all.get(i));
      if (eqColor)
      {
        if (t.color.equals(myColor))
          ans.add(t.name);
      } else if (!eqColor)
      {
        if (!t.color.equals(myColor))
          ans.add(t.name);
      }
    }
    return ans;
  }

  private ArrayList<String> adjAllies(String c)
  {
    return adjByColor(c,true);
  }

  private ArrayList<String> adjEnemies(String c)
  {
    return adjByColor(c,false);
  }

  public void printAL(ArrayList<String> t)
  {
    System.out.print(" [ ");
    for (int p=0; p<t.size(); p++)
    {
      System.out.print(t.get(p) + " ");
    }
    System.out.println("]");
  }

  public void run()
  {
    try {
    while (true)
    {
      String sss = in.readUTF();
//      System.out.print(myName + " " + myColor + "\n");
      String[] tok = sss.split(" ");
      String msg = null;

      transactions tran = transactions.valueOf(tok[1].toUpperCase());
      switch (tran)
      {
        case GAME_NODE:
          myName = tok[2];
          myGameNumber = tok[3];
          myColor = tok[4];
          msg = "( ACK )";
          break;
        case STATUS:
          System.out.print("\n\nplayer: \n");
          System.out.print("\tgameNumber : " + myGameNumber+ "\n");
          System.out.print("\tmyColor    : " + myColor + "\n");
          System.out.print("\tTotalTerrs : " + terrs.size() + "\n");
          System.out.print("\tMyTerrs    : " + countTerrs(myColor) + "\n");
          System.out.print("\tconnections: " + conns.size() + "\n");
          System.out.print("\tmyArmies   : " + countArmies(myColor) + "\n");
          System.out.print("terrs");
          printterrs();
          msg = "( ACK )";
          break;
        case TERRITORY_NODE:
          terr t = new terr(tok[2],"GRAY",0);
          terrs.add(t);
          msg = "( ACK )";
          break;
        case CONNECTION_NODE:
          conn c = new conn(tok[2],tok[3]);
          conns.add(c);
          msg = "( ACK )";
          break;
        case YOU_PLACE_ARMY:
          if (tok[2].equals("GRAY"))
          {
            int cc = countTerrs("GRAY");
            if(cc==0){
              System.out.println("no grey terrs");
              System.out.print("\n\nplayer: \n");
              System.out.print("\tgameNumber : " + myGameNumber+ "\n");
              System.out.print("\tmyColor    : " + myColor + "\n");
              System.out.print("\tTotalTerrs : " + terrs.size() + "\n");
              System.out.print("\tMyTerrs    : " + countTerrs(myColor) + "\n");
              System.out.print("\tconnections: " + conns.size() + "\n");
              System.out.print("\tmyArmies   : " + countArmies(myColor) + "\n");
              System.out.print("terrs");
              printterrs();
            }
            int rr = (int) (1.0 + Math.random() * cc);
            String ss = getNthColorTerr(rr, "GRAY");
            //System.out.println(myColor);
            //System.out.println(ss);
            //if(ss.equals("")){

            //}
            msg = "( PLACE_ARMY " + ss + " )";
            break;
          } else if (tok[2].equals(myColor))
          {
            int cc = countTerrs(myColor);
            int rr = (int) (1.0 + Math.random() * cc);
            String ss = getNthColorTerr(rr, myColor);
            //System.out.println("token is my color"+ ss);
            msg = "( PLACE_ARMY " + ss + " )";
            break;
          } else {
              break;
          }
        case MAP_UPDATE:
          if(UIon){
            cMap.mapUpdate(tok[2],tok[3],tok[4]);
          }
          for (int i=0; i<terrs.size(); i++)
            {
              terr t2 = terrs.get(i);
              if (t2.name.equals(tok[2]))
              {
                t2.color = tok[3];
                t2.count = Integer.parseInt(tok[4]);
                break;
              }
            }
          msg = "( ACK )";
          break;
//          System.out.println("" + myName + ":" + myColor + ">>>" + sss);
//            System.out.println("gh2>>>");
//          System.out.println("gh1>>>");
        case YOU_ATTACK:
          msg = "( ATTACK_DONE )";
          for (int i=0; i<terrs.size(); i++)
          {
            if ((terrs.get(i).color).equals(myColor) && (terrs.get(i).count > 1))
            {
              ArrayList<String> ttt = new ArrayList<String>();
              ttt = adjEnemies(terrs.get(i).name);
              //System.out.print("ALLterrs " + terrs.get(i).name + " connects to ");
              //printAL(ttt);
              if (ttt.size() > 0)
              {
                int moveNr = terrs.get(i).count - 1;//Math.max(1,generator.nextInt(terrs.get(i).count));
                ///System.out.println("AAA>>>" + terrs.get(i).count + ":" + moveNr);
                String target = ttt.get(generator.nextInt(ttt.size()));
                msg = "( ATTACK " + terrs.get(i).name + " " + target + " " + moveNr + " )";
                break;
              }
            }
          }
          break;
        case YOU_FORTIFY:
          int maxValue = -1;
          String saveTerr = "***";
          for (int i=0; i<terrs.size(); i++)
          {
             if ((terrs.get(i).color.equals(myColor)) && (terrs.get(i).count > maxValue))
             {
               maxValue = terrs.get(i).count;
               saveTerr = terrs.get(i).name;
             }
          }
          msg = "( ACK )";
          if (maxValue != -1)
          {
            maxValue = Math.max(1,generator.nextInt(maxValue));
            if (maxValue >1)
            {
              ArrayList<String> ttt = new ArrayList<String>();
              ttt = adjAllies(saveTerr);
              String target = ttt.get(generator.nextInt(ttt.size()));
              msg = "( FORTIFY " + saveTerr + " " + target + " " + (maxValue-1) + " )";
            }
          }
          break;
        default:
          System.out.print("PLAYER::: BAD TRANSACTION:\n");
          server.close();
          System.exit(0);
      }
      out.writeUTF(msg);
    }
  }catch(Exception e){

  }
  }
}
