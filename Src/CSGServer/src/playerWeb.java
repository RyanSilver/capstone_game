//package EYETeeCapstoneProject;

//varient of player A that uses java sockets to communicate with a player over the web
import java.util.*;
import java.net.*;
import java.io.*;

/**
 * varient of player A that uses java sockets to communicate with a player over the web
 * @author Ryan
 */
public class playerWeb extends Thread {

    private static String myGameNumber = "***";
    private static String myColor = "***";
    private static String myName = "***";
    private static Random generator = new Random(17);
    private CubbyHole rec = null;
    private CubbyHole send = null;
    private Socket server;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean mode = false;//server mode
    private DBConn dbconn;

    /**
     * sets up a cubby hole that can be used to communicate over the web 
     * @param a
     * @param b
     * @param c
     */
    public playerWeb(CubbyHole a, CubbyHole b, Socket c) {
        super();
        rec = a;
        send = b;
        server = c;
        try {
            in = new DataInputStream(server.getInputStream());
            out = new DataOutputStream(server.getOutputStream());
            myName = in.readUTF();
            dbconn = new DBConn();
            dbconn.createNewGame(myName);
        } catch (Exception e) {

        }
        start();
    }

    /**
     * unused version
     * @param a
     * @param b
     * @param c
     * @param d
     */
    public playerWeb(CubbyHole a, CubbyHole b, Socket c, boolean d) {
        super();
        rec = a;
        send = b;
        server = c;
        mode = d;
    }

//
//terrs
//
    public void run() {
        try {
            boolean gameContinues = true;
            while (gameContinues) {
                if (mode) {
                    String sss = in.readUTF();
                    send.put(sss);
                    String msg = rec.get();
                    out.writeUTF(msg);

                } else {
                    String sss = rec.get();
                    String[] tok = sss.split(" ");
                    if (tok[1].toUpperCase().equals("MAP_UPDATE")) {
                        //this is an update to the map so we log in in the games history
                        dbconn.updateHistory(sss);
                    }
                    if (tok[1].toUpperCase().equals("GAME_OVER")) {
                        gameContinues = false;

                    }
                    String msg;
                    try {
                        out.writeUTF(sss);
                        msg = in.readUTF();
                    } catch (Exception e) {
                        gameContinues = false;
                        msg = "TIMEOUT";
                        try {
                            server.close();
                        } catch (Exception x) {

                        }

                    }
                    if (msg.equals("")) {
                        msg = null;
                    }
                    if (sss.equals("( Game_Over )")) {
                        gameContinues = false;
                    }

                    send.put(msg);
                }
            }
            server.close();
        } catch (Exception e) {
            //unexpected problem was caught
            try {
                server.close();
            } catch (Exception x) {

            }
        }
    }

}
