package br.com.fiap.alertus.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    // Usando H2 Database em memória para facilitar execução
    private static final String URL = "jdbc:h2:mem:alertus;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASS = "";
    
    private static boolean initialized = false;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("H2 Driver not found", e);
        }
        
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        
        // Inicializar banco apenas uma vez
        if (!initialized) {
            initializeDatabase(conn);
            initialized = true;
        }
        
        return conn;
    }
    
    private static void initializeDatabase(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            // Criar tabela User
            stmt.execute("CREATE TABLE IF NOT EXISTS User (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "email VARCHAR(255) UNIQUE NOT NULL," +
                "password VARCHAR(255) NOT NULL," +
                "full_name VARCHAR(255) NOT NULL," +
                "role VARCHAR(50) NOT NULL" +
            ")");
            
            // Criar tabela Region
            stmt.execute("CREATE TABLE IF NOT EXISTS Region (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "latitude DECIMAL(10,8) NOT NULL," +
                "longitude DECIMAL(11,8) NOT NULL" +
            ")");
            
            // Criar tabela Event
            stmt.execute("CREATE TABLE IF NOT EXISTS Event (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "type VARCHAR(100) NOT NULL," +
                "intensity VARCHAR(50) NOT NULL," +
                "datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "region_id INT NOT NULL," +
                "FOREIGN KEY (region_id) REFERENCES Region(id)" +
            ")");
            
            // Criar tabela Notification
            stmt.execute("CREATE TABLE IF NOT EXISTS Notification (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "message VARCHAR(500) NOT NULL," +
                "level VARCHAR(50) NOT NULL," +
                "datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "event_id INT NOT NULL," +
                "FOREIGN KEY (event_id) REFERENCES Event(id)" +
            ")");
            
            // Inserir dados iniciais - Users
            stmt.execute("INSERT INTO User (email, password, full_name, role) VALUES " +
                "('admin@alertus.com', 'admin', 'Administrator', 'ADMIN')");
            stmt.execute("INSERT INTO User (email, password, full_name, role) VALUES " +
                "('operator@alertus.com', 'operator', 'System Operator', 'OPERATOR')");
            
            // Inserir dados iniciais - Regions
            stmt.execute("INSERT INTO Region (name, latitude, longitude) VALUES " +
                "('São Paulo', -23.5505, -46.6333)");
            stmt.execute("INSERT INTO Region (name, latitude, longitude) VALUES " +
                "('Rio de Janeiro', -22.9068, -43.1729)");
            stmt.execute("INSERT INTO Region (name, latitude, longitude) VALUES " +
                "('Brasília', -15.7942, -47.8822)");
            stmt.execute("INSERT INTO Region (name, latitude, longitude) VALUES " +
                "('Salvador', -12.9714, -38.5014)");
            
            // Inserir dados iniciais - Events
            stmt.execute("INSERT INTO Event (type, intensity, datetime, region_id) VALUES " +
                "('Flood', 'High', NOW(), 1)");
            stmt.execute("INSERT INTO Event (type, intensity, datetime, region_id) VALUES " +
                "('Heat Wave', 'Medium', NOW(), 2)");
            stmt.execute("INSERT INTO Event (type, intensity, datetime, region_id) VALUES " +
                "('Earthquake', 'Low', NOW(), 3)");
            stmt.execute("INSERT INTO Event (type, intensity, datetime, region_id) VALUES " +
                "('Storm', 'High', NOW(), 1)");
            stmt.execute("INSERT INTO Event (type, intensity, datetime, region_id) VALUES " +
                "('Fire', 'Low', NOW(), 4)");
            stmt.execute("INSERT INTO Event (type, intensity, datetime, region_id) VALUES " +
                "('Wildfire', 'High', NOW(), 4)");
            
            // Inserir dados iniciais - Notifications
            stmt.execute("INSERT INTO Notification (message, level, datetime, event_id) VALUES " +
                "('Heavy rainfall detected in São Paulo region. Risk of flooding in low areas.', 'High', NOW(), 1)");
            stmt.execute("INSERT INTO Notification (message, level, datetime, event_id) VALUES " +
                "('Temperature above 40°C expected in Rio de Janeiro. Stay hydrated.', 'Medium', NOW(), 2)");
            stmt.execute("INSERT INTO Notification (message, level, datetime, event_id) VALUES " +
                "('Minor seismic activity detected in Brasília region. No immediate danger.', 'Low', NOW(), 3)");
            stmt.execute("INSERT INTO Notification (message, level, datetime, event_id) VALUES " +
                "('Alert for Storm in São Paulo', 'High', NOW(), 4)");
            stmt.execute("INSERT INTO Notification (message, level, datetime, event_id) VALUES " +
                "('Alert for Wildfire in Salvador', 'High', NOW(), 6)");
            
            // Inserir dados adicionais para simular eventos cadastrados via interface
            stmt.execute("INSERT INTO Event (type, intensity, datetime, region_id) VALUES " +
                "('Enchente', 'Alta', NOW(), 1)");
            stmt.execute("INSERT INTO Event (type, intensity, datetime, region_id) VALUES " +
                "('Calor Extremo', 'Moderada', NOW(), 2)");
            stmt.execute("INSERT INTO Event (type, intensity, datetime, region_id) VALUES " +
                "('Terremoto', 'Moderada', NOW(), 1)");
            stmt.execute("INSERT INTO Event (type, intensity, datetime, region_id) VALUES " +
                "('Tempestade', 'Alta', NOW(), 1)");
            stmt.execute("INSERT INTO Event (type, intensity, datetime, region_id) VALUES " +
                "('Fogo', 'Baixa', NOW(), 4)");
            stmt.execute("INSERT INTO Event (type, intensity, datetime, region_id) VALUES " +
                "('Incendio', 'Alta', NOW(), 4)");
                
            // Notificações para os eventos em português
            stmt.execute("INSERT INTO Notification (message, level, datetime, event_id) VALUES " +
                "('Chuvas intensas na região', 'Alto', NOW(), 7)");
            stmt.execute("INSERT INTO Notification (message, level, datetime, event_id) VALUES " +
                "('Temperaturas acima de 40°C', 'Medio', NOW(), 8)");
            stmt.execute("INSERT INTO Notification (message, level, datetime, event_id) VALUES " +
                "('Alerta de Tempestade em São Paulo', 'Alto', NOW(), 10)");
            stmt.execute("INSERT INTO Notification (message, level, datetime, event_id) VALUES " +
                "('Alerta de Incendio em Belo Horizonte', 'Alto', NOW(), 12)");
        }
    }
}
