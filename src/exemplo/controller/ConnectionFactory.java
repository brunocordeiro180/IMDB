package exemplo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Realiza conexao com banco de dados postgresql atraves da porta 5432
public class ConnectionFactory {
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/IMDB", "postgres", "postgres");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

