import java.util.*;
import java.net.*;
import java.io.*;
public class riskServer extends Thread{
  private ServerSocket serverSocket;
  private static int port = 55555;
  static String[] args;
  private int gameNumber;
  public riskServer(String[] args, int port) throws IOException {
      this.port = port;
      serverSocket = new ServerSocket(this.port);
      //serverSocket.setSoTimeout(10000);
      this.args=args;

   }
   public void run(){
     while(true){
       try{
         Socket server = serverSocket.accept();
         int userNumber = 0;//recive username from the user's first transmission select the usernumber
         gameNumber=0;//select max(game_number) from games +1
         //add the game to the db
         System.out.println(server.getRemoteSocketAddress() +" Just connected, starting game");
         tRiskR game= new tRiskR(gameNumber);
         CubbyHole playerSend=new CubbyHole();//send to player
         CubbyHole playerRecv=new CubbyHole();//receive from player
         playerWeb player=new playerWeb( playerSend,  playerRecv, server);
         game.runGame(args,playerSend,playerRecv);
         server.close();

       }catch(IOException e){
         e.printStackTrace();
         break;
       }
     }
   }

  public static void main(String[] args) {
    for (int i =0;i<5 ;i++ ) {
      try{
        int pt= 5551+i;
        Thread t = new riskServer(args,pt);
        t.start();
      }catch (IOException e){
        e.printStackTrace();
      }
    }
  }
}
