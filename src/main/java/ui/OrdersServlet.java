package ui;

import bo.Order;
import bo.User;
import db.OrdersDB;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("index.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        List<Order> orders = OrdersDB.getOrdersByUser(user);

        req.setAttribute("orders", orders);
        req.getRequestDispatcher("orders.jsp").forward(req, resp);
    }
}