package br.com.fiap.alertus.controller;

import br.com.fiap.alertus.dao.NotificationDAO;
import br.com.fiap.alertus.dao.EventDAO;
import br.com.fiap.alertus.dao.RegionDAO;
import br.com.fiap.alertus.model.Notification;
import br.com.fiap.alertus.model.Event;
import br.com.fiap.alertus.model.Region;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EventoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            resp.sendRedirect("view/login.jsp");
            return;
        }
        
        RegionDAO regionDAO = new RegionDAO();
        EventDAO eventDAO = new EventDAO();
        try {
            List<Region> regions = regionDAO.findAll();
            List<Event> events = eventDAO.findAll();
            req.setAttribute("regions", regions);
            req.setAttribute("events", events);
            req.getRequestDispatcher("view/formEvento.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            resp.sendRedirect("view/login.jsp");
            return;
        }
        
        RegionDAO regionDAO = new RegionDAO();
        EventDAO eventDAO = new EventDAO();
        try {
            int regionId = Integer.parseInt(req.getParameter("regiao_id"));
            Region region = regionDAO.findById(regionId);
            Event event = new Event();
            event.setType(req.getParameter("tipo"));
            event.setIntensity(req.getParameter("intensidade"));
            
            String dataStr = req.getParameter("data");
            if (dataStr != null && !dataStr.isEmpty()) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    event.setDatetime(sdf.parse(dataStr));
                } catch (ParseException e) {
                    event.setDatetime(new Date());
                }
            } else {
                event.setDatetime(new Date());
            }
            
            event.setRegion(region);
            eventDAO.insert(event);
            
            // Criar notificação automática para todos os eventos
            Notification notification = new Notification();
            notification.setEvent(event);
            
            // Definir nível e mensagem baseado na intensidade
            String level = "Medium"; // padrão
            String message = "Event alert for " + event.getType() + " in " + region.getName();
            
            if ("High".equals(event.getIntensity()) || "Alta".equals(event.getIntensity())) {
                level = "High";
                message = "HIGH ALERT: " + event.getType() + " detected in " + region.getName() + ". Immediate action required.";
            } else if ("Medium".equals(event.getIntensity()) || "Moderada".equals(event.getIntensity())) {
                level = "Medium";
                message = "Medium alert for " + event.getType() + " in " + region.getName() + ". Monitor situation.";
            } else if ("Low".equals(event.getIntensity()) || "Baixa".equals(event.getIntensity())) {
                level = "Low";
                message = "Low level alert for " + event.getType() + " in " + region.getName() + ". Stay informed.";
            }
            
            notification.setMessage(message);
            notification.setLevel(level);
            notification.setDatetime(new Date());
            
            NotificationDAO notificationDAO = new NotificationDAO();
            notificationDAO.insert(notification);
            
            resp.sendRedirect("evento");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
