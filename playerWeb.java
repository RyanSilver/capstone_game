//varient of player A that uses java sockets to communicate with a player over the web
import java.util.*;
import java.net.*;
import java.io.*;

public class playerWeb extends Thread{
  private static String myGameNumber = "***";
  private static String myColor      = "***";
  private static String myName       = "***";
  private static Random generator = new Random(17);
  private CubbyHole rec = null;
  private CubbyHole send = null;
  private Socket server;
  private DataInputStream in;
  private DataOutputStream out;
  private boolean mode=false;//server mode
  private DBConn dbconn;
  public playerWeb(CubbyHole a , CubbyHole b, Socket c)
  {
    super();
    rec = a;
    send = b;
    server=c;
    dbconn = new DBConn();
    dbconn.createNewGame("temp_user");
    start();
  }
  public playerWeb(CubbyHole a , CubbyHole b, Socket c, boolean d)
  {
    super();
    rec = a;
    send = b;
    server=c;
    mode=d;
    dbconn = new DBConn();
    dbconn.createNewGame("temp_user");
    start();
  }

//
//terrs
//


  public void run()
  {
    try{
    boolean gameContinues=true;
    in = new DataInputStream(server.getInputStream());
    out = new DataOutputStream(server.getOutputStream());
      while (gameContinues)
      {
        if (mode){
          String sss = in.readUTF();
          send.put(sss);
          String msg= rec.get();
          out.writeUTF(msg);

        }else{
          String sss = rec.get();
          String[] tok = sss.split(" ");
          if(tok[1].toUpperCase().equals("MAP_UPDATE")){
            //this is an update to the map so we log in in the games history
            dbconn.updateHistory(sss);
          }
          out.writeUTF(sss);
          String msg =in.readUTF();
          if (msg.equals(""))msg=null;
          if (sss.equals("( Game_Over )"))gameContinues=false;
          send.put(msg);
        }
      }
      server.close();
    }catch (Exception e ){
      //unexpected problem was caught
    }
  }


}
