package com.simplilearn.estore.controller;

import com.simplilearn.estore.admin.dao.OrderItemDao;
import com.simplilearn.estore.admin.model.OrderItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderItemServlet",
           urlPatterns = { "/admin/orderitems", "/admin/orderitems/" })
public class OrderItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final OrderItemDao dao = new OrderItemDao();




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = param(req, "action", "list");
        try {
            switch (action) {
                case "new":
                    req.setAttribute("item", new OrderItem());
                    req.getRequestDispatcher("/WEB-INF/orderitems/orderitem-form.jsp").forward(req, resp);
                    break;
                case "edit":
                    int id = Integer.parseInt(req.getParameter("id"));
                    req.setAttribute("item", dao.getById(id));
                    req.getRequestDispatcher("/WEB-INF/orderitems/orderitem-form.jsp").forward(req, resp);
                    break;
                case "delete":
                    dao.delete(Integer.parseInt(req.getParameter("id")));
                    req.getSession().setAttribute("flash", "Order item deleted.");
                    resp.sendRedirect(req.getContextPath() + "/admin/orderitems?action=list");
                    break;

                default:
                    List<OrderItem> items = dao.listAll();
                    req.setAttribute("items", items);
                    req.getRequestDispatcher("/WEB-INF/orderitems/orderitem-list.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderItem oi = new OrderItem();

        String idStr = req.getParameter("orderItemId");
        if (idStr != null && !idStr.isBlank()) oi.setOrderItemId(Integer.parseInt(idStr));

        oi.setOrderId(parseInt(req.getParameter("orderId")));
        oi.setProductId(parseInt(req.getParameter("productId")));
        oi.setProductTitle(req.getParameter("productTitle"));
        oi.setProductDescription(req.getParameter("productDescription"));
        oi.setProductCode(req.getParameter("productCode"));
        oi.setProductImg(req.getParameter("productImg"));
        oi.setProductCategory(req.getParameter("productCategory"));
        oi.setPrice(parseInt(req.getParameter("price")));
        oi.setQuantity(parseInt(req.getParameter("quantity")));
        oi.setTotalPrice(parseInt(req.getParameter("totalPrice")));

        List<String> errors = new ArrayList<>();

        if (oi.getProductTitle() == null || oi.getProductTitle().isBlank())
        	errors.add("Title is required.");

        if (oi.getPrice() == null || oi.getPrice() <= 0)
        	errors.add("Price must be a positive number.");

        if (oi.getQuantity() == null || oi.getQuantity() <= 0)
        	errors.add("Quantity must be at least 1.");

        if (!errors.isEmpty()) {
        	req.setAttribute("errors", errors);
        	req.setAttribute("item", oi);
        	req.getRequestDispatcher("/WEB-INF/orderitems/orderitem-form.jsp").forward(req,resp);
        	return;
        }

        try {
            if (oi.getOrderItemId() == null) {
                dao.create(oi);
                req.getSession().setAttribute("flash", "Order item created.");
            } else {
                dao.update(oi);
                req.getSession().setAttribute("flash", "Order item updated.");
            }
            resp.sendRedirect(req.getContextPath() + "/admin/orderitems?action=list");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }


    private static String param(HttpServletRequest r, String n, String d){ String v=r.getParameter(n); return (v==null||v.isBlank())?d:v; }
    private static int parseInt(String v){ try { return Integer.parseInt(v); } catch(Exception e){ return 0; } }
}
