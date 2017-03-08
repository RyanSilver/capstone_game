package EYETeeCapstoneProject;

import java.sql.*;

public class DBConn {
	private Connection Conn;
	private PreparedStatement statement;
	private ResultSet rs;
	private int gameNo;

	public DBConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				String address = "jdbc:mysql://localhost:3306/games?";
				String user = "javaP";
				String password = "javaranpass";
				String path;
				Conn = DriverManager.getConnection(address + "user=" + user + "&password=" + password);
			} catch (Exception e) {
				System.out.println("there was a problem with the driver manager");
				//System.exit(2);
			}
		} catch (ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			//System.exit(1);
		}

	}

	public DBConn(String username) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				String address = "jdbc:mysql://localhost:3306/games?";
				String user = "javaP";
				String password = "javaranpass";
				String path;
				Conn = DriverManager.getConnection(address + "user=" + user + "&password=" + password);
			} catch (Exception e) {
				System.out.println("there was a problem with the driver manager");
			//	System.exit(2);
			}
		} catch (ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			//System.exit(1);
		}
		createNewGame(username);
	}

	public int createNewGame(String username) {
		// select usernumber from users where users.username=username;
		int gameNumber = 0;
		try {
			//here we get the usernumber for the user
			// statement =Conn.prepareStatement("select usernumber from users
			// where username=?"");
			// statement.setString(1, username);
			// rs = statement.executeUpdate();
			int id = 0;
			// while(rs.next()){
			// Retrieve by column name
			// id = rs.getInt(1);
			// }
			// insert into games.games_tracker (user_id) values (id)
			statement = Conn.prepareStatement("insert into games.games_tracker (user_id)  values (?)");
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
			//System.exit(3);
		}

		return 0;
	}
	public void updateHistory(String map_update){
		try{
			int lastUpdate = -1;
			//figure out what the last update was and inc it to no cause errors in data
			statement=Conn.prepareStatement("select max(update_number) from games_history where game_number = ?");
			statement.setInt(1, gameNo);
			rs = statement.executeQuery();
			while (rs.next()) {
				// Retrieve by column number
				lastUpdate = rs.getInt(1);
			}
			lastUpdate++;
			statement=Conn.prepareStatement("insert into games_history (game_number, update_number, update_contents) values(?,?,?)");
			statement.setInt(1, gameNo);
			statement.setInt(2, lastUpdate);
			statement.setString(3, map_update);
			statement.executeUpdate();
		}catch(SQLException e){
			System.out.println("Error: Problem in a SQL statement");
			//System.exit(3);
		}
	}

	public void closeAll() {
		try {
			statement.close();
			Conn.close();
		} catch (Exception e) {
			System.exit(4);

		}
	}
/*
	//testing main method:

	public static void main(String[] args) {
		DBConn C = new DBConn();
		System.out.println(C.createNewGame("user"));
		C.updateHistory("( Hello I am a fake game and this is my map update! )");
		C.updateHistory("( ASK BLUE 5 )");
	}
*/
}
