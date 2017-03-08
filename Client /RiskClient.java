

import java.util.*;
import java.net.*;
import java.io.*;
public class RiskClient{
  public static void main(String[] args) {
    try{
      String serverName = args[0];
      int port = Integer.parseInt(args[1]);
      Socket client = new Socket(serverName,port);
      playerClient player= new playerClient(client);

    }catch (Exception e ){
      //client.close();
    }
  }
}
