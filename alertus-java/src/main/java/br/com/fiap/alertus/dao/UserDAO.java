package br.com.fiap.alertus.dao;

import br.com.fiap.alertus.model.User;
import java.sql.*;

public class UserDAO {
    
    public User findByEmailAndPassword(String email, String password) throws SQLException {
        String sql = "SELECT * FROM User WHERE email = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setFullName(rs.getString("full_name"));
                    user.setRole(rs.getString("role"));
                    return user;
                }
            }
        }
        return null;
    }
    
    public void insert(User user) throws SQLException {
        String sql = "INSERT INTO User (email, password, full_name, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFullName());
            stmt.setString(4, user.getRole());
            stmt.executeUpdate();
        }
    }
    
    public User findById(int id) throws SQLException {
        String sql = "SELECT * FROM User WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setFullName(rs.getString("full_name"));
                    user.setRole(rs.getString("role"));
                    return user;
                }
            }
        }
        return null;
    }
}
