package br.com.fiap.alertus.dao;

import br.com.fiap.alertus.model.Notification;
import br.com.fiap.alertus.model.Event;
import br.com.fiap.alertus.model.Region;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotificationDAO {
    
    public void insert(Notification notification) throws SQLException {
        String sql = "INSERT INTO Notification (message, level, datetime, event_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, notification.getMessage());
            stmt.setString(2, notification.getLevel());
            stmt.setTimestamp(3, new Timestamp(notification.getDatetime().getTime()));
            stmt.setInt(4, notification.getEvent().getId());
            stmt.executeUpdate();
        }
    }

    public List<Notification> findAll() throws SQLException {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT n.*, e.type, e.intensity, e.datetime as event_datetime, e.region_id, " +
                     "r.name as region_name, r.latitude, r.longitude " +
                     "FROM Notification n JOIN Event e ON n.event_id = e.id JOIN Region r ON e.region_id = r.id " +
                     "ORDER BY n.datetime DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Notification notification = new Notification();
                notification.setId(rs.getInt("id"));
                notification.setMessage(rs.getString("message"));
                notification.setLevel(rs.getString("level"));
                notification.setDatetime(new Date(rs.getTimestamp("datetime").getTime()));
                
                Event event = new Event();
                event.setId(rs.getInt("event_id"));
                event.setType(rs.getString("type"));
                event.setIntensity(rs.getString("intensity"));
                event.setDatetime(new Date(rs.getTimestamp("event_datetime").getTime()));
                
                Region region = new Region();
                region.setId(rs.getInt("region_id"));
                region.setName(rs.getString("region_name"));
                region.setLatitude(rs.getDouble("latitude"));
                region.setLongitude(rs.getDouble("longitude"));
                
                event.setRegion(region);
                notification.setEvent(event);
                notifications.add(notification);
            }
        }
        return notifications;
    }
    
    public Notification findById(int id) throws SQLException {
        String sql = "SELECT n.*, e.type, e.intensity, e.datetime as event_datetime, e.region_id, " +
                     "r.name as region_name, r.latitude, r.longitude " +
                     "FROM Notification n JOIN Event e ON n.event_id = e.id JOIN Region r ON e.region_id = r.id " +
                     "WHERE n.id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Notification notification = new Notification();
                    notification.setId(rs.getInt("id"));
                    notification.setMessage(rs.getString("message"));
                    notification.setLevel(rs.getString("level"));
                    notification.setDatetime(new Date(rs.getTimestamp("datetime").getTime()));
                    
                    Event event = new Event();
                    event.setId(rs.getInt("event_id"));
                    event.setType(rs.getString("type"));
                    event.setIntensity(rs.getString("intensity"));
                    event.setDatetime(new Date(rs.getTimestamp("event_datetime").getTime()));
                    
                    Region region = new Region();
                    region.setId(rs.getInt("region_id"));
                    region.setName(rs.getString("region_name"));
                    region.setLatitude(rs.getDouble("latitude"));
                    region.setLongitude(rs.getDouble("longitude"));
                    
                    event.setRegion(region);
                    notification.setEvent(event);
                    return notification;
                }
            }
        }
        return null;
    }
    
    public void update(Notification notification) throws SQLException {
        String sql = "UPDATE Notification SET message = ?, level = ?, datetime = ?, event_id = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, notification.getMessage());
            stmt.setString(2, notification.getLevel());
            stmt.setTimestamp(3, new Timestamp(notification.getDatetime().getTime()));
            stmt.setInt(4, notification.getEvent().getId());
            stmt.setInt(5, notification.getId());
            stmt.executeUpdate();
        }
    }
    
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Notification WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
