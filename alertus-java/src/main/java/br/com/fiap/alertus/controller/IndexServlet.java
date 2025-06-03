package br.com.fiap.alertus.controller;

import br.com.fiap.alertus.dao.NotificationDAO;
import br.com.fiap.alertus.model.Notification;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            NotificationDAO dao = new NotificationDAO();
            
            List<Notification> notifications = dao.findAll();
            req.setAttribute("notifications", notifications);
            
            req.getRequestDispatcher("view/index.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
