package dao;

import model.Evento;
import model.Regiao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventoDAO {
    public void inserir(Evento evento) throws SQLException {
        String sql = "INSERT INTO EVENTO (tipo, intensidade, data, regiao_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, evento.getTipo());
            stmt.setDouble(2, evento.getIntensidade());
            stmt.setTimestamp(3, new Timestamp(evento.getData().getTime()));
            stmt.setInt(4, evento.getRegiao().getId());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    evento.setId(rs.getInt(1));
                }
            }
        }
    }

    public List<Evento> listar() throws SQLException {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT e.*, r.nome as regiao_nome FROM EVENTO e JOIN REGIAO r ON e.regiao_id=r.id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Evento e = new Evento();
                e.setId(rs.getInt("id"));
                e.setTipo(rs.getString("tipo"));
                e.setIntensidade(rs.getDouble("intensidade"));
                e.setData(new Date(rs.getTimestamp("data").getTime()));
                Regiao r = new Regiao();
                r.setId(rs.getInt("regiao_id"));
                r.setNome(rs.getString("regiao_nome"));
                e.setRegiao(r);
                lista.add(e);
            }
        }
        return lista;
    }
}
