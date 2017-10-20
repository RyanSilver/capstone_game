//package EYETeeCapstoneProject;

import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Ryan
 * Class used to setup and use connections to database. 
 */
public class DBConn {

    private Connection Conn;
    private PreparedStatement statement;
    private ResultSet rs;
    private int gameNo;
    private String dbName = "games.db";

    /**
     *reads from a file the name of the database and connects to it.
     * the default database is called games.db and should be located in the path of the application
     */
    public DBConn() {
        try {

            FileReader fr = new FileReader("gameDbLocation.CFG");
            BufferedReader br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader("gameDbLocation.CFG"));

            while ((sCurrentLine = br.readLine()) != null) {
                dbName = sCurrentLine;
            }

        } catch (IOException e) {

            dbName="games.db";

        }
        try {
            Class.forName("org.sqlite.JDBC");
            try {
                String address = "jdbc:sqlite:" + dbName;
                String user = "javaP";//left around for mysql
                String password = "javaranpass";//left around for mysql
                String path;
                Conn = DriverManager.getConnection(address);
            } catch (Exception e) {
                System.out.println("there was a problem with the driver manager");
                System.exit(2);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }

    }

    /**
     * sets up a game for the user and associates their username with games_tracker 
     * using games_tracker it is possible to tell what game belongs to who  
     * @param username: username of the user or empty string
     * @return: the game number.
     */
    public int createNewGame(String username) {
        // select usernumber from users where users.username=username;
        int gameNumber = 0;
        int id = 0;
        try {
            //here we get the usernumber for the user
            if (!username.equals("testUser")) {
                try {
                    statement = Conn.prepareStatement("select id from user where username = ?");
                    statement.setString(1, username);
                    rs = statement.executeQuery();
                    while (rs.next()) {
                        //Retrieve by column number
                        id = rs.getInt(1);
                    }
                } catch (Exception e) {
                    //
                    id = 0;
                }
            }

            // insert into games.games_tracker (user_id) values (id)
            statement = Conn.prepareStatement("insert into games_tracker (user_id)  values (?)");
            statement.setInt(1, id);
            statement.executeUpdate();

            // select max(gameNumber) from games_tracker where user_id = id;
            statement = Conn.prepareStatement("select max(game_number) from games_tracker where user_id = ?");
            // String sql="select max(game_number) from games_tracker where
            // user_id = ?";
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                // Retrieve by column number
                gameNumber = rs.getInt(1);
            }
            statement = Conn.prepareStatement("insert into games_history (game_number, update_number, update_contents) values (?,0,\"( GAME_STARTED )\")");
            statement.setInt(1, gameNumber);
            statement.executeUpdate();
            this.gameNo = gameNumber;
            return gameNumber;
        } catch (SQLException e) {
            System.out.println("Error: Problem in a SQL statement");
            System.exit(3);
        }

        return 0;
    }

    /**
     *  records actions of the players into the database. 
     * for optimal database size we only record the actual map updates as they are the result of the attacks 
     * @param map_update: the action to be recorded into the database 
     */
    public void updateHistory(String map_update) {
        try {
            int lastUpdate = -1;
            //figure out what the last update was and inc it to no cause errors in data
            statement = Conn.prepareStatement("select max(update_number) from games_history where game_number = ?");
            statement.setInt(1, gameNo);
            rs = statement.executeQuery();
            while (rs.next()) {
                // Retrieve by column number
                lastUpdate = rs.getInt(1);
            }
            lastUpdate++;
            statement = Conn.prepareStatement("insert into games_history (game_number, update_number, update_contents) values(?,?,?)");
            statement.setInt(1, gameNo);
            statement.setInt(2, lastUpdate);
            statement.setString(3, map_update);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: Problem in a SQL statement");
            System.exit(3);
        }
    }

    /**
     * closes the connection to the database.
     */
    public void closeAll() {
        try {
            statement.close();
            Conn.close();
        } catch (Exception e) {
            System.out.println("there was a problem closeing the database connection");
            System.exit(4);

        }
    }

    //testing main method:

    /**
     * testing method only to verify function of database
     * @param args
     */
    public static void main(String[] args) {
        DBConn C = new DBConn();
        System.out.println(C.createNewGame("user"));
        C.updateHistory("( Hello I am a fake game and this is my map update! )");
        C.updateHistory("( ASK BLUE 5 )");
    }

}
