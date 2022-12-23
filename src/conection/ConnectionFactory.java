package conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionFactory {
	private Connection connect = null;
	
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3307/hinode";
	private final String USER = "root";
	private final String PASS = "";
	
	
	//Estabelecer conexao
	public Connection getConnection() {
		try {
			Class.forName(DRIVER);
			connect = DriverManager.getConnection(URL, USER, PASS);
			return connect;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	
	//Fechar as conexoes com sobrecarga
	public static void closeConnection(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void closeConnection(Connection conn, PreparedStatement stmt) {
		closeConnection(conn);
		try {
			if(stmt != null) {
				stmt.close();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs) {
		closeConnection(conn, stmt);
		try {
			if(rs != null) {
				rs.close();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
}
