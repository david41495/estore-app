package com.simplilearn.estore.controller;

import com.simplilearn.estore.admin.dao.ShipmentDao;
import com.simplilearn.estore.admin.model.Shipment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/admin/shipments")
public class ShipmentServlet extends HttpServlet {

    private final ShipmentDao dao = new ShipmentDao();
    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = param(req, "action", "list");
        try {
            switch (action) {
                case "new":
                    req.setAttribute("shipment", new Shipment());
                    req.getRequestDispatcher("/WEB-INF/shipments/shipment-form.jsp").forward(req, resp);
                    break;
                case "edit":
                    int id = Integer.parseInt(req.getParameter("id"));
                    req.setAttribute("shipment", dao.getById(id));
                    req.getRequestDispatcher("/WEB-INF/shipments/shipment-form.jsp").forward(req, resp);
                    break;
                case "delete":
                	dao.delete(Integer.parseInt(req.getParameter("id")));
                	req.getSession().setAttribute("flash", "Shipment deleted.");
                	resp.sendRedirect(req.getContextPath() + "/admin/shipments?action=list");

                    break;
                default: // list
                    req.setAttribute("shipments", dao.listAll());
                    req.getRequestDispatcher("/WEB-INF/shipments/shipment-list.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Shipment s = new Shipment();

        String idStr = req.getParameter("shipmentId");
        if (idStr != null && !idStr.isBlank()) s.setShipmentId(Integer.parseInt(idStr));

        s.setShipmentStatus(parseInt(req.getParameter("shipmentStatus")));
        s.setShipmentTitle(req.getParameter("shipmentTitle"));
        s.setShipmentMethod(req.getParameter("shipmentMethod"));
        s.setShipmentCompany(req.getParameter("shipmentCompany"));

        String dateStr = req.getParameter("shipmentDate");
        try {
            if (dateStr != null && !dateStr.isBlank()) {
                Date d = df.parse(dateStr);
                s.setShipmentDate(d);
            }
        } catch (Exception ignored) {}

        try {
        	if (s.getShipmentId() == null) {
        	    dao.create(s);
        	    req.getSession().setAttribute("flash", "Shipment created.");
        	} else {
        	    dao.update(s);
        	    req.getSession().setAttribute("flash", "Shipment updated.");
        	}
        	resp.sendRedirect(req.getContextPath() + "/admin/shipments?action=list");

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    // helpers
    private static String param(HttpServletRequest req, String name, String def) {
        String v = req.getParameter(name);
        return (v == null || v.isBlank()) ? def : v;
    }

    private static int parseInt(String v) {
        try { return Integer.parseInt(v); } catch (Exception e) { return 0; }
    }
}
