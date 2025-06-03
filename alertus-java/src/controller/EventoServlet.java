package controller;

import dao.AlertaDAO;
import dao.EventoDAO;
import dao.RegiaoDAO;
import model.Alerta;
import model.Evento;
import model.Regiao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/evento")
public class EventoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegiaoDAO regiaoDAO = new RegiaoDAO();
        EventoDAO eventoDAO = new EventoDAO();
        try {
            List<Regiao> regioes = regiaoDAO.listar();
            List<Evento> eventos = eventoDAO.listar();
            req.setAttribute("regioes", regioes);
            req.setAttribute("eventos", eventos);
            req.getRequestDispatcher("view/formEvento.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getParameter("tipo");
        double intensidade = Double.parseDouble(req.getParameter("intensidade"));
        int regiaoId = Integer.parseInt(req.getParameter("regiao"));
        String dataStr = req.getParameter("data");
        Date data;
        try {
            data = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dataStr);
        } catch (ParseException e) {
            throw new ServletException("Data inválida");
        }
        RegiaoDAO regiaoDAO = new RegiaoDAO();
        EventoDAO eventoDAO = new EventoDAO();
        AlertaDAO alertaDAO = new AlertaDAO();
        try {
            Regiao regiao = regiaoDAO.buscarPorId(regiaoId);
            Evento evento = new Evento();
            evento.setTipo(tipo);
            evento.setIntensidade(intensidade);
            evento.setData(data);
            evento.setRegiao(regiao);
            eventoDAO.inserir(evento);

            // regra simples de alerta
            String nivel = null;
            if (("enchente".equalsIgnoreCase(tipo) && intensidade > 100) ||
                ("terremoto".equalsIgnoreCase(tipo) && intensidade > 5) ||
                ("calor".equalsIgnoreCase(tipo) && intensidade > 40)) {
                nivel = "vermelho";
            }

            if (nivel != null) {
                Alerta alerta = new Alerta();
                alerta.setEvento(evento);
                alerta.setMensagem("Alerta de " + tipo + " na região " + regiao.getNome());
                alerta.setNivel(nivel);
                alerta.setData(new Date());
                alertaDAO.inserir(alerta);
            }
            resp.sendRedirect("evento");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
