import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class territoryNode
{
  public String   armyColor   = "GRAY";
  public int      armyCount   = 0; 
  public String   shortName   = null;
  public int      terrNr      = -1;
  public String   continent   = null;
  public int      xPos        = 0; 
  public int      yPos        = 0; 
  public territoryNode(int a, String b, String c, int x, int y)
  {
    this.terrNr    = a;
    this.shortName = b;
    this.continent = c;
    this.xPos      = x;
    this.yPos      = y;
    this.armyColor ="GRAY";
  }
  public territoryNode(int a, String b, String c, String ref, String dir)
  {
    this(a,b,c, 50, 50);
  }
}
public class RDB
{
  public static final int  gutterSize = 60;
  private static ArrayList<territoryNode> tn = new ArrayList<territoryNode>();
  
  private static void addContinent(int a, String b, String c, int x, int y)
  {
    tn.add ( new territoryNode(a,b,c,x,y));
  }
  public static territoryNode getTerritoryNode(String s)
  {
    territoryNode ans = null;
    for (int i=0; (ans == null && i<tn.size()); i++)
    {
      territoryNode t = tn.get(i);
      if (t.shortName.equals(s))
        ans = t;
    }
    return ans;
  }
  
  public static int[] getCoord(String s)
  {
    int [] ans = {0,0};
    for (int i=0; i<tn.size(); i++)
    {
      territoryNode t = tn.get(i);
      if (t.shortName.equals(s))
      {
        ans[0] = t.xPos;
        ans[1] = t.yPos;
      }
    }
    return ans;  
  }

  public static boolean haveContinent(String cont, String color)
  {
    boolean ans = true;
    for (int i=0; i<tn.size(); i++)
    {
      territoryNode t = tn.get(i);
      if (t.continent.equals(cont) && !t.armyColor.equals(color))
      {
        ans = false;
        break;
      }
    }
    return ans;  
  }

  public static int[] correctCoord(int [] p, String d)
  {
    int [] ans = {0,0};
    int newx = p[0];
    int newy = p[1];
    if (d.equals("n"))
    {
      newx += U.circleSize/2;
    }
    else if (d.equals("ne"))
    {
      newx += U.circleSize*0.75+2;
      newy += U.circleSize*0.25+2;
    }
    else if (d.equals("e"))
    {
      newx += U.circleSize;
      newy += U.circleSize*0.5;
    }
    else if (d.equals("se"))
    {
      newx += U.circleSize*0.75+2;
      newy += U.circleSize*0.75+2;
    }
    else if (d.equals("s"))
    {
      newx += U.circleSize*0.5;
      newy += U.circleSize;
    }
    else if (d.equals("sw"))
    {
      newx += U.circleSize*0.25+2;
      newy += U.circleSize*0.75+2;
    }
    else if (d.equals("w"))
    {
      newy += U.circleSize*0.5;
    }
    else if (d.equals("nw"))
    {
      newx += U.circleSize*0.25-4;
      newy += U.circleSize*0.25-4;
    }
    ans[0] = newx;
    ans[1] = newy;
    return ans;
  }
  
  private static void addTerritory(int a, String b, String c, String ref, String dir)
  {
    int newx = -1;
    int newy = -1;
    for (int i=0; i<tn.size(); i++)
    {
      if (tn.get(i).shortName.equals(ref))
      {
        newx = tn.get(i).xPos;
        newy = tn.get(i).yPos;
        if      (dir.equals("N"))                       newy -= gutterSize;
        else if (dir.equals("NE")) {newx += gutterSize; newy -= gutterSize;}
        else if (dir.equals("E"))   newx += gutterSize;
        else if (dir.equals("SE")) {newx += gutterSize; newy += gutterSize;}
        else if (dir.equals("S"))                       newy += gutterSize;
        else if (dir.equals("SW")) {newx -= gutterSize; newy += gutterSize;}
        break;
      }
    }
    tn.add ( new territoryNode(a,b,c,newx,newy));

  }
  
  public static String getCircle(int n)
  {
    String ans = null;
    if ((n > -1) && (n < tn.size()))
    {
      territoryNode t = tn.get(n);
      ans = "( CIRCLE " + t.xPos + " " + t.yPos + " ";
      ans = ans + t.armyColor + " " + t.shortName + " " + t.armyCount +" )";
    }
    return ans;
  }

  public RDB() 
  {
    addContinent(terrNames.iALA, "ALA", "NAM", 20, 30);
    addTerritory(terrNames.iNWT, "NWT", "NAM", "ALA", "E");
    addTerritory(terrNames.iALB, "ALB", "NAM", "NWT", "S");
    addTerritory(terrNames.iWUS, "WUS", "NAM", "ALB", "S");
    addTerritory(terrNames.iCAM, "CAM", "NAM", "WUS", "S");
    addTerritory(terrNames.iONT, "ONT", "NAM", "ALB", "E");
    addTerritory(terrNames.iEUS, "EUS", "NAM", "WUS", "E");
    addTerritory(terrNames.iQUE, "QUE", "NAM", "ONT", "E");
    addTerritory(terrNames.iGRE, "GRE", "NAM", "QUE", "NE");

    addContinent(terrNames.iVEN, "VEN", "SAM", 150, 280);
    addTerritory(terrNames.iPER, "PER", "SAM", "VEN", "S");
    addTerritory(terrNames.iBRA, "BRA", "SAM", "PER", "E");
    addTerritory(terrNames.iARG, "ARG", "SAM", "PER", "S");

    addContinent(terrNames.iNAF, "NAF", "AFR", 370, 350);
    addTerritory(terrNames.iCAF, "CAF", "AFR", "NAF", "S");
    addTerritory(terrNames.iSAF, "SAF", "AFR", "CAF", "SE");
    addTerritory(terrNames.iEGY, "EGY", "AFR", "NAF", "E");
    addTerritory(terrNames.iEAF, "EAF", "AFR", "CAF", "E");
    addTerritory(terrNames.iMAD, "MAD", "AFR", "EAF", "SE");

    addContinent(terrNames.iICE, "ICE", "EUR", 350, 30);
    addTerritory(terrNames.iGBR, "GBR", "EUR", "ICE", "SE");
    addTerritory(terrNames.iSCA, "SCA", "EUR", "GBR", "NE");
    addTerritory(terrNames.iNEU, "NEU", "EUR", "GBR", "SE");
    addTerritory(terrNames.iWEU, "WEU", "EUR", "NEU", "SW");
    addTerritory(terrNames.iSEU, "SEU", "EUR", "WEU", "E");
    addTerritory(terrNames.iUKR, "UKR", "EUR", "NEU", "E");
    
    addContinent(terrNames.iURA, "URA", "ASI", 600, 125);
    addTerritory(terrNames.iMEA, "MEA", "ASI", "SEU", "SE");
    addTerritory(terrNames.iAFG, "AFG", "ASI", "URA", "S");
    addTerritory(terrNames.iSIB, "SIB", "ASI", "URA", "E");
    addTerritory(terrNames.iYAK, "YAK", "ASI", "SIB", "NE");
    addTerritory(terrNames.iIRK, "IRK", "ASI", "YAK", "S");
    addTerritory(terrNames.iMON, "MON", "ASI", "IRK", "S");
    addTerritory(terrNames.iCHI, "CHI", "ASI", "MON", "S");
    addTerritory(terrNames.iIND, "IND", "ASI", "MEA", "SE");
    addTerritory(terrNames.iSIA, "SIA", "ASI", "CHI", "S");
    addTerritory(terrNames.iKAM, "KAM", "ASI", "YAK", "E");
    addTerritory(terrNames.iJAP, "JAP", "ASI", "CHI", "E");

    addContinent(terrNames.iINE, "INE", "AUS", 740, 375);
    addTerritory(terrNames.iNGU, "NGU", "AUS", "INE", "E");
    addTerritory(terrNames.iWAU, "WAU", "AUS", "INE", "S");
    addTerritory(terrNames.iEAU, "EAU", "AUS", "WAU", "E");
    addTerritory(terrNames.iNZE, "NZE", "AUS", "EAU", "SE");

  }
  
  public static String getFirstTerritory()
  {
    return terrNames.getFirstTerritory();
  }
  
  public static String getSuccessorTerritory(String a)
  {
    return terrNames.getSuccessorTerritory(a);    
  }

  public static String getTerritoryInfo(String a)
  {
    return terrNames.getTerritoryInfo(a);    
  }
  
  public static int getGameNumber()
  {
    return 1;
  }

  public static String getConnection(int n)
  {
    return mapConns.getConnectionLong(n);
  }
  
  public boolean territoriesFull()
  {
    boolean ans = true;
    for (int i=0; i<tn.size(); i++)
    {
      territoryNode t = tn.get(i);
      if (t.armyColor.equals("GRAY"))
      {
        ans = false;
        break;
      }
    }
    return ans;
  }
  public int countTerritories(String color)
  {
    int ans = 0;
    for (int i=0; i<tn.size(); i++)
    {
      territoryNode t = tn.get(i);
      if (t.armyColor.equals(color))
      {
        ans++;
      }
    }
    return ans;    
  }

  public int countArmies(String cc)
  {
    int count = 0;
    for (int i=0; i<tn.size(); i++)
    {
      territoryNode t = tn.get(i);
      if (t.armyColor.equals(cc))
        count += t.armyCount;
    }
    return count;
  }
}