import java.util.*;
import java.net.*;
import java.io.*;
public class RiskClient{
  private static final boolean UImode=true; //TODO: change to true if you want to see your game on your computer as it plays
  private static final int port=5551; //it is possible that the server port will change in the future, check the tutorials page if you have connection issues.
  private static final String username="testUser2"; //TODO: change to your username to use playback feature on the website
  public static void main(String[] args) {
    try{
      String serverName = args[0];
      Socket client = new Socket(serverName,port);
      playerClient player= new playerClient(client,UImode,username);
    }catch (Exception e ){
      System.exit(1);
    }
  }
}
