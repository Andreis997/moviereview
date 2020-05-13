package com.moviereview.moviereview.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	public boolean setUserRole(int id, String role) {
		connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET role = ? WHERE id = ?");
            preparedStatement.setString(1, role);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
	}
	
	public List<User> getUsers() {
		connection = DBConnection.getConnection();
        List<User> users = new ArrayList<User>();
		
        try {
           PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
           ResultSet rs = preparedStatement.executeQuery();
           while (rs.next()) {
        	   users.add(new User(rs.getString("email"), rs.getString("username"), rs.getString("role"), rs.getInt("id")));
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return users;
	}
    
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
        	   User user = new User(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getNString("email"), resultSet.getNString("password"), resultSet.getString("role"));
        	   resultSet.close();
               return user;
           }
       } catch (SQLException e) {	
           e.printStackTrace();
       }
       return null;
    }
    
    public User findUserByUsername(String username) {
    	connection = DBConnection.getConnection();
        
        try {
           PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
           preparedStatement.setString(1, username);
           ResultSet resultSet = preparedStatement.executeQuery();

           if (resultSet.next()) {
        	   User user = new User(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getNString("email"), resultSet.getNString("password"), resultSet.getString("role"));
        	   resultSet.close();
               return user;
           }
       } catch (SQLException e) {	
           e.printStackTrace();
       }
       return null;
    }
    
    public User findUserById(int id) {
    	connection = DBConnection.getConnection();
        
        try {
           PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
           preparedStatement.setInt(1, id);
           ResultSet resultSet = preparedStatement.executeQuery();

           if (resultSet.next()) {
        	   User user = new User(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getNString("email"), resultSet.getNString("password"), resultSet.getString("role"));
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
