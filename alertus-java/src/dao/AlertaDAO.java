package dao;

import model.Alerta;
import model.Evento;
import model.Regiao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlertaDAO {
    public void inserir(Alerta alerta) throws SQLException {
        String sql = "INSERT INTO ALERTA (evento_id, mensagem, nivel, data) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, alerta.getEvento().getId());
            stmt.setString(2, alerta.getMensagem());
            stmt.setString(3, alerta.getNivel());
            stmt.setTimestamp(4, new Timestamp(alerta.getData().getTime()));
            stmt.executeUpdate();
        }
    }

    public List<Alerta> listar() throws SQLException {
        List<Alerta> lista = new ArrayList<>();
        String sql = "SELECT a.*, e.tipo, r.nome as regiao_nome FROM ALERTA a JOIN EVENTO e ON a.evento_id=e.id JOIN REGIAO r ON e.regiao_id=r.id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Alerta a = new Alerta();
                a.setId(rs.getInt("id"));
                Evento e = new Evento();
                e.setId(rs.getInt("evento_id"));
                e.setTipo(rs.getString("tipo"));
                Regiao r = new Regiao();
                r.setNome(rs.getString("regiao_nome"));
                e.setRegiao(r);
                a.setEvento(e);
                a.setMensagem(rs.getString("mensagem"));
                a.setNivel(rs.getString("nivel"));
                a.setData(new Date(rs.getTimestamp("data").getTime()));
                lista.add(a);
            }
        }
        return lista;
    }
}
