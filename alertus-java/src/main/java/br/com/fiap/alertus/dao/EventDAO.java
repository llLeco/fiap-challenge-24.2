package br.com.fiap.alertus.dao;

import br.com.fiap.alertus.model.Event;
import br.com.fiap.alertus.model.Region;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDAO {
    public void insert(Event event) throws SQLException {
        String sql = "INSERT INTO Event (type, intensity, datetime, region_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, event.getType());
            stmt.setString(2, event.getIntensity());
            stmt.setTimestamp(3, new Timestamp(event.getDatetime().getTime()));
            stmt.setInt(4, event.getRegion().getId());
            stmt.executeUpdate();
        }
    }

    public List<Event> findAll() throws SQLException {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT e.*, r.name as region_name, r.latitude, r.longitude " +
                     "FROM Event e JOIN Region r ON e.region_id = r.id " +
                     "ORDER BY e.datetime DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Event event = new Event();
                event.setId(rs.getInt("id"));
                event.setType(rs.getString("type"));
                event.setIntensity(rs.getString("intensity"));
                event.setDatetime(new Date(rs.getTimestamp("datetime").getTime()));
                
                Region region = new Region();
                region.setId(rs.getInt("region_id"));
                region.setName(rs.getString("region_name"));
                region.setLatitude(rs.getDouble("latitude"));
                region.setLongitude(rs.getDouble("longitude"));
                
                event.setRegion(region);
                events.add(event);
            }
        }
        return events;
    }

    public Event findById(int id) throws SQLException {
        String sql = "SELECT e.*, r.name as region_name, r.latitude, r.longitude " +
                     "FROM Event e JOIN Region r ON e.region_id = r.id " +
                     "WHERE e.id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Event event = new Event();
                    event.setId(rs.getInt("id"));
                    event.setType(rs.getString("type"));
                    event.setIntensity(rs.getString("intensity"));
                    event.setDatetime(new Date(rs.getTimestamp("datetime").getTime()));
                    
                    Region region = new Region();
                    region.setId(rs.getInt("region_id"));
                    region.setName(rs.getString("region_name"));
                    region.setLatitude(rs.getDouble("latitude"));
                    region.setLongitude(rs.getDouble("longitude"));
                    
                    event.setRegion(region);
                    return event;
                }
            }
        }
        return null;
    }

    public void update(Event event) throws SQLException {
        String sql = "UPDATE Event SET type = ?, intensity = ?, datetime = ?, region_id = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, event.getType());
            stmt.setString(2, event.getIntensity());
            stmt.setTimestamp(3, new Timestamp(event.getDatetime().getTime()));
            stmt.setInt(4, event.getRegion().getId());
            stmt.setInt(5, event.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Event WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
