import java.sql.*;
import java.util.*;
import java.io.*;

public class RunJob
 {

 RunJob(String m, Boolean PrintOutPut, Boolean ReturnQuick)
  {
  try
  {
   if (System.getProperty("os.name").equals("Linux")){
     FileWriter fw = new FileWriter("ras");
     fw.write(m);
     fw.flush();
     fw.close();
   }else{
     FileWriter fw = new FileWriter("rbf.bat");
     fw.write(m);
     fw.flush();
     fw.close();
   }

   }
  catch (IOException e)
  {
    System.exit(-1);
  }
  try
  {
    if (System.getProperty("os.name").equals("Linux")){
      ProcessBuilder builder =new ProcessBuilder("ras");
      final Process process =builder.start();
    }else{
      ProcessBuilder builder = new ProcessBuilder("rbf.bat");
      final Process process = builder.start();
      if (!ReturnQuick)
      {
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String s = null;
        if (PrintOutPut) System.out.println("STDOUT::\n");
        while ((s = stdInput.readLine()) != null)
        {
          if (PrintOutPut) System.out.println(s);
        }
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        if (PrintOutPut) System.out.println("STDERR::\n");
        while ((s = stdError.readLine()) != null)
        {
          if (PrintOutPut) System.out.println(s);
        }
      }
    }
  }
  catch (Exception e1)
  {
    System.out.print(e1);
    System.exit(-1);
  }
 }
}
