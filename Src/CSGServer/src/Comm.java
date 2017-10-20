//package EYETeeCapstoneProject;

import java.io.*;

public class Comm {

    /**
     *  unused class used to implement code through file manipulation
     */
    public Comm() {
    }

    /**
     * used to send a message to another program or thread via a file
     * @param fileName:the name of the file 
     * @param msg: message to be delivered 
     */
    public static void sendMsg(String fileName, String msg) {
        boolean done = false;
        for (int i = 0; (!done && i < 5); i++) {
            try {
                FileWriter fstream = new FileWriter("temp.txt");
                BufferedWriter out = new BufferedWriter(fstream);
                out.write(msg);
                out.close();
                if (System.getProperty("os.name").equals("Linux")) {
                    RunJob r = new RunJob("mv temp.txt " + fileName, false, false);
                } else {
                    RunJob r = new RunJob("ren temp.txt " + fileName, false, false);
                }
                done = true;
            } catch (Exception e) {
                continue;
            }
        }
        if (!done) {
            System.err.println("Error with file 5 times");
        }
    }

    /**
     * waits for a file to appear and reads it 
     * @param fileName: file to wait for 
     * @return: message contained in filename 
     */
    public static String recvMsg(String fileName) {
        String answer = "";
        boolean done = false;
        for (int i = 0; (!done && i < 5); i++) {
            try {
                File f = new File(fileName);
                while (!f.exists()) {
                    Thread.sleep(10);
                }
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                answer = br.readLine();
                br.close();
                done = true;
            } catch (Exception e) {
                continue;
            }
        }
        if (!done) {
            System.err.println("Error with file 5 times");
        }
        return answer;
    }
}
