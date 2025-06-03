package controller;

import dao.RegiaoDAO;
import model.Regiao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/regiao")
public class RegiaoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegiaoDAO dao = new RegiaoDAO();
        try {
            List<Regiao> regioes = dao.listar();
            req.setAttribute("regioes", regioes);
            req.getRequestDispatcher("view/formRegiao.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        double latitude = Double.parseDouble(req.getParameter("latitude"));
        double longitude = Double.parseDouble(req.getParameter("longitude"));
        Regiao r = new Regiao();
        r.setNome(nome);
        r.setLatitude(latitude);
        r.setLongitude(longitude);
        RegiaoDAO dao = new RegiaoDAO();
        try {
            dao.inserir(r);
            resp.sendRedirect("regiao");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
