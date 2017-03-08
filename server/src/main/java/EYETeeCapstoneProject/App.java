package EYETeeCapstoneProject;
import java.util.*;
import java.net.*;
import java.io.*;
public class App{
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
