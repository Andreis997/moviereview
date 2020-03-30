package com.moviereview.moviereview.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.moviereview.moviereview.model.User;
import com.moviereview.moviereview.util.DBConnection;

public class UserDAO {
	
    private UserDAO() {};
    private static UserDAO instance;

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }
    
	private Connection connection;
    
    public boolean userExists(String email){
        connection = DBConnection.getConnection();
        
         try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
            	resultSet.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public User findUserByEmail(String email) {
    	connection = DBConnection.getConnection();
        
        try {
           PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
           preparedStatement.setString(1, email);
           ResultSet resultSet = preparedStatement.executeQuery();

           if (resultSet.next()) {
        	   User user = new User(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getNString("email"), resultSet.getNString("password"));
        	   resultSet.close();
               return user;
           }
       } catch (SQLException e) {	
           e.printStackTrace();
       }
       return null;
    }
    
    public boolean createUser(String username, String password, String email) {
        connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (email, username, password) VALUES (?, ?, ?)");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean isPasswordCorrect(String email, String password) {
        connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
            	resultSet.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
