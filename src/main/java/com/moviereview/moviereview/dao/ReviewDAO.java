package com.moviereview.moviereview.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.moviereview.moviereview.model.Review;
import com.moviereview.moviereview.model.User;
import com.moviereview.moviereview.util.DBConnection;

public class ReviewDAO {
	private ReviewDAO() {
	};

	private static ReviewDAO instance;

	public static ReviewDAO getInstance() {
		if (instance == null) {
			instance = new ReviewDAO();
			connection = DBConnection.getConnection();
		}
		return instance;
	}

	private static Connection connection;
	
	public List<Review> getAllReviewsOfMovie(int movieId){
        List<Review> reviews = new ArrayList<>(); 
		try {
            
        	PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM reviews WHERE movie_id_api = ?");
            preparedStatement.setInt(1, movieId);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
            	reviews.add(new Review(rs.getInt("id"), rs.getInt("movie_id_api"), UserDAO.getInstance().findUserById(rs.getInt("user_id")),
            			rs.getString("content"), rs.getDate("created_at"), rs.getString("rating")));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }
	
	public boolean createReview(int movieIdApi, User user, String content, String rating) {
		try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO reviews (movie_id_api, user_id, content, rating) VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, movieIdApi);
            preparedStatement.setInt(2, user.getId());
            preparedStatement.setString(3, content);
            preparedStatement.setString(4, rating);
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
	}
}
