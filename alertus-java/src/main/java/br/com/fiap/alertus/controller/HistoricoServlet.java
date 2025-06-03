package br.com.fiap.alertus.controller;

import br.com.fiap.alertus.dao.NotificationDAO;
import br.com.fiap.alertus.dao.EventDAO;
import br.com.fiap.alertus.model.Notification;
import br.com.fiap.alertus.model.Event;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HistoricoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            resp.sendRedirect("view/login.jsp");
            return;
        }
        
        EventDAO eventDAO = new EventDAO();
        NotificationDAO notificationDAO = new NotificationDAO();
        try {
            List<Event> events = eventDAO.findAll();
            List<Notification> notifications = notificationDAO.findAll();
            req.setAttribute("events", events);
            req.setAttribute("notifications", notifications);
            req.getRequestDispatcher("view/historico.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
