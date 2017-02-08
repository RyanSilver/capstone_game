import java.io.*;
import java.util.*;

class connNode
{
  public String fromT = "***";
  public String fAttach = "***";
  public String toT = "***";
  public String tAttach = "***";
  public boolean toDisplay = false;
  public connNode(String a, String b, String c, String d, boolean e) 
  {
    fromT = a;
    fAttach = b;
    toT = c;
    tAttach = d;
    toDisplay = e;
  }
  public String toString() { return "( CONNECTION_NODE " + fromT + " " + fAttach + " " + toT + " " + tAttach + " " + toDisplay + " )"; }
}

  
public class mapConns
{ 
  public static connNode [] conns = 
  {
    new connNode (terrNames.ALA, dirs.e, terrNames.NWT, dirs.w, true),
    new connNode (terrNames.ALA, dirs.se, terrNames.ALB, dirs.nw, true),
    new connNode (terrNames.NWT, dirs.s, terrNames.ALB, dirs.n, true),
    new connNode (terrNames.NWT, dirs.se, terrNames.ONT, dirs.nw, true),
    new connNode (terrNames.NWT, dirs.e, terrNames.GRE, dirs.w, true),
    new connNode (terrNames.ONT, dirs.s, terrNames.EUS, dirs.n, true),
    new connNode (terrNames.ONT, dirs.ne, terrNames.GRE, dirs.sw, true),
    new connNode (terrNames.ALB, dirs.s, terrNames.WUS, dirs.n, true),
    new connNode (terrNames.ALB, dirs.e, terrNames.ONT, dirs.w, true),
    new connNode (terrNames.WUS, dirs.e, terrNames.EUS, dirs.w, true),
    new connNode (terrNames.ONT, dirs.e, terrNames.QUE, dirs.w, true),
    new connNode (terrNames.QUE, dirs.ne, terrNames.GRE, dirs.s, true),
    new connNode (terrNames.QUE, dirs.sw, terrNames.EUS, dirs.ne, true),
    new connNode (terrNames.EUS, dirs.sw, terrNames.CAM, dirs.ne, true),
    new connNode (terrNames.WUS, dirs.s, terrNames.CAM, dirs.n, true),
    new connNode (terrNames.GRE, dirs.e, terrNames.ICE, dirs.w, true),

    new connNode (terrNames.ICE, dirs.e, terrNames.SCA, dirs.w, true),
    new connNode (terrNames.ICE, dirs.se, terrNames.GBR, dirs.nw, true),
    new connNode (terrNames.SCA, dirs.s, terrNames.NEU, dirs.n, true),
    new connNode (terrNames.NEU, dirs.s, terrNames.SEU, dirs.n, true),
    new connNode (terrNames.WEU, dirs.ne, terrNames.NEU, dirs.sw, true),
    new connNode (terrNames.GBR, dirs.se, terrNames.NEU, dirs.nw, true),
    new connNode (terrNames.GBR, dirs.ne, terrNames.SCA, dirs.sw, true),
    new connNode (terrNames.GBR, dirs.s, terrNames.WEU, dirs.n, true),
    new connNode (terrNames.WEU, dirs.e, terrNames.SEU, dirs.w, true),
    new connNode (terrNames.SCA, dirs.se, terrNames.UKR, dirs.nw, true),
    new connNode (terrNames.NEU, dirs.e, terrNames.UKR, dirs.w, true),
    new connNode (terrNames.SEU, dirs.ne, terrNames.UKR, dirs.sw, true),
    new connNode (terrNames.CAM, dirs.se, terrNames.VEN, dirs.nw, true),

    new connNode (terrNames.VEN, dirs.s, terrNames.PER, dirs.n, true),
    new connNode (terrNames.VEN, dirs.se, terrNames.BRA, dirs.nw, true),
    new connNode (terrNames.PER, dirs.e, terrNames.BRA, dirs.w, true),
    new connNode (terrNames.PER, dirs.s, terrNames.ARG, dirs.n, true),
    new connNode (terrNames.ARG, dirs.ne, terrNames.BRA, dirs.sw, true),
    new connNode (terrNames.BRA, dirs.e,  terrNames.NAF, dirs.w, true),
    new connNode (terrNames.WEU, dirs.s, terrNames.NAF, dirs.n, true),
    new connNode (terrNames.SEU, dirs.sw, terrNames.NAF, dirs.ne, true),
    new connNode (terrNames.SEU, dirs.s, terrNames.EGY, dirs.n, true),
    new connNode (terrNames.SEU, dirs.se, terrNames.MEA, dirs.nw, true),

    new connNode (terrNames.NAF, dirs.e, terrNames.EGY, dirs.w, true),
    new connNode (terrNames.NAF, dirs.se, terrNames.EAF, dirs.nw, true),
    new connNode (terrNames.NAF, dirs.s, terrNames.CAF, dirs.n, true),
    new connNode (terrNames.CAF, dirs.e, terrNames.EAF, dirs.w, true),
    new connNode (terrNames.CAF, dirs.se, terrNames.SAF, dirs.nw, true),
    new connNode (terrNames.EAF, dirs.s, terrNames.SAF, dirs.n, true),
    new connNode (terrNames.EAF, dirs.se, terrNames.MAD, dirs.nw, true),
    new connNode (terrNames.SAF, dirs.e, terrNames.MAD, dirs.w, true),
    new connNode (terrNames.EGY, dirs.s, terrNames.EAF, dirs.n, true),
    new connNode (terrNames.EGY, dirs.s, terrNames.EAF, dirs.n, true),

    new connNode (terrNames.UKR, dirs.e, terrNames.URA, dirs.w, true),
    new connNode (terrNames.UKR, dirs.se, terrNames.AFG, dirs.nw, true),
    new connNode (terrNames.UKR, dirs.s, terrNames.MEA, dirs.n, true),
    new connNode (terrNames.EGY, dirs.ne, terrNames.MEA, dirs.sw, true),
    new connNode (terrNames.URA, dirs.s, terrNames.AFG, dirs.n, true),
    new connNode (terrNames.URA, dirs.e, terrNames.SIB, dirs.w, true),
    new connNode (terrNames.URA, dirs.se, terrNames.CHI, dirs.nw, true),
    new connNode (terrNames.AFG, dirs.sw, terrNames.MEA, dirs.ne, true),
    new connNode (terrNames.AFG, dirs.e, terrNames.CHI, dirs.w, true),
    new connNode (terrNames.AFG, dirs.s, terrNames.IND, dirs.n, true),
    new connNode (terrNames.MEA, dirs.se, terrNames.IND, dirs.nw, true),
    new connNode (terrNames.IND, dirs.e, terrNames.SIA, dirs.w, true),
    new connNode (terrNames.IND, dirs.ne, terrNames.CHI, dirs.sw, true),
    new connNode (terrNames.SIB, dirs.ne, terrNames.YAK, dirs.sw, true),
    new connNode (terrNames.SIB, dirs.e, terrNames.IRK, dirs.w, true),
    new connNode (terrNames.SIB, dirs.se, terrNames.MON, dirs.nw, true),
    new connNode (terrNames.SIB, dirs.s, terrNames.CHI, dirs.nw, true),
    new connNode (terrNames.YAK, dirs.s, terrNames.IRK, dirs.n, true),
    new connNode (terrNames.IRK, dirs.s, terrNames.MON, dirs.n, true),
    new connNode (terrNames.MON, dirs.s, terrNames.CHI, dirs.n, true),
    new connNode (terrNames.CHI, dirs.s, terrNames.SIA, dirs.n, true),
    new connNode (terrNames.CHI, dirs.e, terrNames.JAP, dirs.w, true),
    new connNode (terrNames.YAK, dirs.e, terrNames.KAM, dirs.w, true),
    new connNode (terrNames.IRK, dirs.ne, terrNames.KAM, dirs.w, true),
    new connNode (terrNames.MON, dirs.ne, terrNames.KAM, dirs.sw, true),
    new connNode (terrNames.KAM, dirs.s, terrNames.JAP, dirs.n, true),
    new connNode (terrNames.MON, dirs.se, terrNames.JAP, dirs.nw, true),
    new connNode (terrNames.SIA, dirs.s, terrNames.INE, dirs.n, true),

    new connNode (terrNames.INE, dirs.s, terrNames.WAU, dirs.n, true),
    new connNode (terrNames.INE, dirs.se, terrNames.EAU, dirs.nw, true),
    new connNode (terrNames.INE, dirs.e, terrNames.NGU, dirs.w, true),
    new connNode (terrNames.WAU, dirs.e, terrNames.EAU, dirs.w, true),
    new connNode (terrNames.WAU, dirs.ne, terrNames.NGU, dirs.sw, true),
    new connNode (terrNames.EAU, dirs.n, terrNames.NGU, dirs.s, true),
    new connNode (terrNames.EAU, dirs.se, terrNames.NZE, dirs.nw, true),

    new connNode (terrNames.KAM, dirs.n, terrNames.ALA, dirs.n, false),
    new connNode (terrNames.NZE, dirs.n, terrNames.ARG, dirs.n, false)
  };
  
  public static String getConnectionLong(int n)
  {
    String answer = null;
    if ((n >= 0) && (n < conns.length))
      answer = conns[n].toString();
    return answer;
  }

  public static String getConnectionShort(int n)
  {
    String ans = null;
    ans = getConnectionLong(n);
    if (ans != null)
    {
      String [] tok = ans.split(" ");
      ans = "( " + tok[1] + " " + tok[2] + " " + tok[4] + " )"; 
    }
    return ans;
  }
  }
