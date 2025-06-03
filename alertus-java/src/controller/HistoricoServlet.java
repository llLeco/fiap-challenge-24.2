package controller;

import dao.AlertaDAO;
import dao.EventoDAO;
import model.Alerta;
import model.Evento;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/historico")
public class HistoricoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EventoDAO eventoDAO = new EventoDAO();
        AlertaDAO alertaDAO = new AlertaDAO();
        try {
            List<Evento> eventos = eventoDAO.listar();
            List<Alerta> alertas = alertaDAO.listar();
            req.setAttribute("eventos", eventos);
            req.setAttribute("alertas", alertas);
            req.getRequestDispatcher("view/historico.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
