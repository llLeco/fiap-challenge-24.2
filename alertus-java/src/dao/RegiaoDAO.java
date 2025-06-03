package dao;

import model.Regiao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegiaoDAO {
    public void inserir(Regiao regiao) throws SQLException {
        String sql = "INSERT INTO REGIAO (nome, latitude, longitude) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, regiao.getNome());
            stmt.setDouble(2, regiao.getLatitude());
            stmt.setDouble(3, regiao.getLongitude());
            stmt.executeUpdate();
        }
    }

    public List<Regiao> listar() throws SQLException {
        List<Regiao> lista = new ArrayList<>();
        String sql = "SELECT * FROM REGIAO";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Regiao r = new Regiao();
                r.setId(rs.getInt("id"));
                r.setNome(rs.getString("nome"));
                r.setLatitude(rs.getDouble("latitude"));
                r.setLongitude(rs.getDouble("longitude"));
                lista.add(r);
            }
        }
        return lista;
    }

    public Regiao buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM REGIAO WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Regiao r = new Regiao();
                    r.setId(rs.getInt("id"));
                    r.setNome(rs.getString("nome"));
                    r.setLatitude(rs.getDouble("latitude"));
                    r.setLongitude(rs.getDouble("longitude"));
                    return r;
                }
            }
        }
        return null;
    }
}
