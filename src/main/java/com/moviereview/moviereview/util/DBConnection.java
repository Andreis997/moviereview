package com.moviereview.moviereview.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
	            String url = "jdbc:mysql://localhost:3306/movie_review?useSSL=false";
	            String user = "root";
	            String password = "root";
	            String driver="com.mysql.cj.jdbc.Driver";
	            Class.forName(driver);
	            connection = DriverManager.getConnection(url, user, password);
	            connection.setAutoCommit(false);
	        }  catch (ClassNotFoundException e) {
	            System.err.println("Could not load db driver");
	            e.printStackTrace();
	        } catch (SQLException e) {
	            System.err.println("Could not open db connction");
	            e.printStackTrace();
	        }
        }
        return connection;
    }

}