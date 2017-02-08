import java.io.*;
import java.util.Scanner;
public class SocketedFile implements Runnable{
  private int port;
  private static Comm comm=null;
  static playerA player;
  CubbyHole send;
  CubbyHole recv;
  //serverSocket server;

  public SocketedFile(){
    this.comm=new Comm();
  }
  public SocketedFile(int port){
    this.port= port;
    //server=new serverSocket(port);
    this.comm=new Comm();
    send=new CubbyHole();
    recv=new CubbyHole();
     this.player=new playerA(send,recv); //temp code

  }
  public void run(){
    //start
    //copy the server socket
    //start useing the copied server socket instead of the origional

    System.out.print("no-op");
  }

public static void main(String[] args) {
  SocketedFile p =new SocketedFile(1234);
  //temporarly takes keyboard input
  Scanner sc=new Scanner(System.in);
  String msg="HELLO";
  boolean done=false;
  while (true){ //game not over
    msg=comm.recvMsg("SERVER.TXT");
    //we have the message now we need to delete server.txt
    done=false;
    while (! done)
    try{
      if (System.getProperty("os.name").equals("Linux")){
        RunJob r = new RunJob("rm " + "CLIENT.TXT",false, false);
      }
      else{
        RunJob r = new RunJob("del " + "CLIENT.TXT",false, false);
      }
      File f=new File("CLIENT.TXT");
      try{
        if (f.exists()){
          Thread.sleep(10);
        }else{
          if (System.getProperty("os.name").equals("Linux")){
            RunJob r = new RunJob("rm " + "CLIENT.TXT",false, false);
          }
          else{
            RunJob r = new RunJob("del " + "CLIENT.TXT",false, false);
          }
        }
      }catch (Exception e){
        //whatever
      }
      System.out.println("Message from server: " + msg +" ... Waiting for reply");
      //msg= sc.nextLine();
      done=true;
      
  }catch (Exception e){
    //something went wrong deleteing files or something
    done=false;
  }

    msg= player.switchStmt(msg);
    System.out.println(msg);
    comm.sendMsg("CLIENT.TXT",msg);
    try{
        Thread.sleep(10);

    }catch(Exception e){
      //rest is for the weak anyway
    }

  }
}



}
