package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
	
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BDFoto", "root", "admin");
		} catch (SQLException e) {
			//System.out.println("SQLException");
			Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);
			throw new RuntimeException("Erro SQLException ao abrir a conexão em connectionFactory", e);
		} catch (ClassNotFoundException ex) {
			//System.out.println("ClassNotFoundException");
			Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
			throw new RuntimeException("Erro ClassNotFoundException em connectionFactory", ex);
		}
	}

}
