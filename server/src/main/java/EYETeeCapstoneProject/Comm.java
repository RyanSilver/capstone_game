package EYETeeCapstoneProject;

import java.io.*;
public class Comm
{
  public Comm() { }

  public static void sendMsg(String fileName,String msg)
  {
    boolean done = false;
    for (int i=0; (!done && i<5); i++)
    {
      try
      {
        FileWriter fstream = new FileWriter("temp.txt");
        BufferedWriter out = new BufferedWriter(fstream);
        out.write(msg);
        out.close();
        if (System.getProperty("os.name").equals("Linux")){
          RunJob r = new RunJob("mv temp.txt " + fileName,false,false);
        }else{
          RunJob r = new RunJob("ren temp.txt " + fileName,false,false);
        }
        done = true;
      }
      catch (Exception e)
      {
        continue;
      }
    }
    if (!done) System.err.println("Error with file 5 times");
  }

  public static String recvMsg(String fileName)
  {
    String answer = "";
    boolean done = false;
    for (int i=0; (!done && i<5); i++)
    {
      try
      {
        File f = new File(fileName);
        while (! f.exists()) { Thread.sleep(10); }
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        answer = br.readLine();
        br.close();
        done=true;
      }
      catch (Exception e)
      {
        continue;
      }
    }
    if (!done) System.err.println("Error with file 5 times");
    return answer;
  }
}
