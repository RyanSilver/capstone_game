public class playerNode
{
  public boolean DEBUG = false;
  public boolean inJava = false;
  public boolean inPython = false;
  public String name = null;
  public Object threadPtr = null;
  private String color = "GRAY";
  public CubbyHole toPlayer = null;
  public CubbyHole fromPlayer = null;
  private static Comm comm = null;

  public void setColor(String s) { color = s; }
  public String getColor() { return color; }

//  public playerNode(String lang, String fileName)
//  {
//  }
//  public playerNode(String lang, String a, Object b, CubbyHole c, CubbyHole d, String e)
  public playerNode(boolean d, String lang, String b, CubbyHole to, CubbyHole from)
  {
    DEBUG = d;
    if (System.getProperty("os.name").equals("Linux")){
      RunJob r1 = new RunJob("rm "+ "SERVER.TXT" ,false, false);
      RunJob r2 = new RunJob("rm " + "CLIENT.TXT",false, false);
    }else{
      RunJob r1 = new RunJob("del " + "SERVER.TXT",false, false);
      RunJob r2 = new RunJob("del " + "CLIENT.TXT",false, false);
    }
    name = b;
    if (lang.equals("java"))
    {
      inJava = true;
      toPlayer =   to;
      fromPlayer = from;
//    this.name = a;
//    this.threadPtr = b;
//    this.toPlayer = c;
//    this.fromPlayer = d;
//    this.color = e;
      //playerRAN z =new playerRAN(toPlayer,fromPlayer);
    }
    if (lang.equals("python"))
    {
      if (DEBUG) U.pr("playerNode1>>>" + name + "\n");
      inPython = true;
      comm = new Comm();
      startedPython = true;
      RunJob r = new RunJob("python " + name, false, false);
      if (DEBUG) U.pr("playerNode2>>>" + "\n");
    }
  }

  public playerNode(boolean d, String lang, String b)
  {
    DEBUG = d;
    if (System.getProperty("os.name").equals("Linux")){
      RunJob r1 = new RunJob("rm "+ "SERVER.TXT" ,false, false);
      RunJob r2 = new RunJob("rm " + "CLIENT.TXT",false, false);
    }else{
      RunJob r1 = new RunJob("del " + "SERVER.TXT",false, false);
      RunJob r2 = new RunJob("del " + "CLIENT.TXT",false, false);
    }
    name = b;
    if (lang.equals("java"))
    {
      inJava = true;
      toPlayer =   new CubbyHole();
      fromPlayer = new CubbyHole();
//    this.name = a;
//    this.threadPtr = b;
//    this.toPlayer = c;
//    this.fromPlayer = d;
//    this.color = e;
      playerRAN z =new playerRAN(toPlayer,fromPlayer);
    }
    if (lang.equals("python"))
    {
      if (DEBUG) U.pr("playerNode1>>>" + name + "\n");
      inPython = true;
      comm = new Comm();
      startedPython = true;
      RunJob r = new RunJob("python " + name, false, false);
      if (DEBUG) U.pr("playerNode2>>>" + "\n");
    }
  }

  public boolean startedPython = false;
  public void send (String s)
  {
    if (inJava) this.toPlayer.put(s);
    else if (inPython)
    {
      U.pr("playerNode.send>>>\n");
      comm.sendMsg("SERVER.TXT" ,s);
    }
  }

  public String receive()
  {
    String answer = "";
    if (inJava) answer = this.fromPlayer.get();
    else if (inPython)
    {
      U.pr("game.receive>>>TRYING TO RECEIVE FROM PYTHON>>>\n");
      answer = comm.recvMsg("CLIENT.TXT");
      U.pr("game.receive>>>"  +  answer + "\n");
      if (System.getProperty("os.name").equals("Linux")){
        RunJob r = new RunJob("rm " + "CLIENT.TXT",false, false);
      }
      else{
        RunJob r = new RunJob("del " + "CLIENT.TXT",false, false);
      }
    }
    return answer;
  }

  public String toString()
  {
    String lang = "java";
    if (inPython) lang = "python";
    return "( PlayerNode " + lang + " " + name + " )";
  }
}
