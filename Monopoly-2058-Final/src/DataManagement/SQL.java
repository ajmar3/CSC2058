package DataManagement;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQL {
	
	public Connection connection;
	
	String host = "154.16.171.79";
	int port = 21055;
	String password = "password123";
	String username = "CommandLine";
	String database = "Monopoly";
	String table = "Scores";
	
	public void Storage() {
		try {
			synchronized(this) {
				if(getConnection() != null && !getConnection().isClosed()) {
					return;
				}
				
				//Class.forName("com.mysql.jdbc.Driver");
				setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database,this.username,this.password));
				System.out.println("Connection Made");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean Search(String name) {
		try {
			PreparedStatement s = getConnection().prepareStatement("SELECT * FROM Scores where Name=?");
			s.setString(1, name);
			
			ResultSet r = s.executeQuery();
			if(r.next()) {
				return true;
			}
			else {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void Write(String name, double value) {
		try {
			PreparedStatement s = getConnection().prepareStatement("INSERT INTO Scores (Name, Value, Wins) VALUES (?, ?, 1)");
			s.setString(1, name);
			s.setDouble(2,  value);
			
			s.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Update(String name, double value) {
		try {
			PreparedStatement s = getConnection().prepareStatement("UPDATE Scores SET Wins = Wins + 1 WHERE Name = ?");
			PreparedStatement s2 = getConnection().prepareStatement("UPDATE Scores SET Value = Value + ? WHERE Name = ?");
			s.setString(1, name);
			
			s2.setDouble(1, value);
			s2.setString(2,  name);

			s.executeUpdate();
			s2.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Retrieve() {
		try {
			PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM Scores ORDER BY Wins DESC LIMIT 5");
			ResultSet r = statement.executeQuery();
			for(int x = 0; x <= 5; x++) {
				if(r.next()) {
					System.out.println(x+1 + ". " + r.getString("Name") + " | " + r.getInt("Wins") + " Wins | £" + r.getDouble("Value"));
				}
			}

			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public Connection getConnection() {
		return connection;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}


}
