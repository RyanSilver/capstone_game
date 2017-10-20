//package EYETeeCapstoneProject;

import java.util.*;
import java.net.*;
import java.io.*;

/**
 * loops and sets up games of risk. 
 * @author Ryan
 */
public class riskServer extends Thread {

    private ServerSocket serverSocket;
    private static int port = 55555;
    static String[] args;

    /**
     * sets timeout and ports for sockets 
     * @param args
     * @param port
     * @throws IOException
     */
    public riskServer(String[] args, int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(this.port);
        //serverSocket.setSoTimeout(10000);
        this.args = args;
        try {
            serverSocket.setSoTimeout(5000);
        } catch (Exception e) {
            System.out.println("there was a problem setting timeout");
            System.exit(400);
        }
    }

    public void run() {

        while (true) {
            try {
                Socket server = serverSocket.accept();
                System.out.println(server.getRemoteSocketAddress() + " Just connected, starting game");
                tRiskR game = new tRiskR(0);
                CubbyHole playerSend = new CubbyHole();//send to player
                CubbyHole playerRecv = new CubbyHole();//receive from player
                playerWeb player = new playerWeb(playerSend, playerRecv, server);
                game.runGame(args, playerSend, playerRecv);
                server.close();
            } catch (IOException e) {

            }
        }
    }

    /**
     * the main method of the server application. starts 5 threads for games of risk 
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            try {
                int pt = 5551 + i;
                Thread t = new riskServer(args, pt);
                t.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
