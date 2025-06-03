package br.com.fiap.alertus.dao;

import br.com.fiap.alertus.model.Region;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegionDAO {
    public void insert(Region region) throws SQLException {
        String sql = "INSERT INTO Region (name, latitude, longitude) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, region.getName());
            stmt.setDouble(2, region.getLatitude());
            stmt.setDouble(3, region.getLongitude());
            stmt.executeUpdate();
        }
    }

    public List<Region> findAll() throws SQLException {
        List<Region> regions = new ArrayList<>();
        String sql = "SELECT * FROM Region ORDER BY name";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Region region = new Region();
                region.setId(rs.getInt("id"));
                region.setName(rs.getString("name"));
                region.setLatitude(rs.getDouble("latitude"));
                region.setLongitude(rs.getDouble("longitude"));
                regions.add(region);
            }
        }
        return regions;
    }

    public Region findById(int id) throws SQLException {
        String sql = "SELECT * FROM Region WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Region region = new Region();
                    region.setId(rs.getInt("id"));
                    region.setName(rs.getString("name"));
                    region.setLatitude(rs.getDouble("latitude"));
                    region.setLongitude(rs.getDouble("longitude"));
                    return region;
                }
            }
        }
        return null;
    }

    public void update(Region region) throws SQLException {
        String sql = "UPDATE Region SET name = ?, latitude = ?, longitude = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, region.getName());
            stmt.setDouble(2, region.getLatitude());
            stmt.setDouble(3, region.getLongitude());
            stmt.setInt(4, region.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Region WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
