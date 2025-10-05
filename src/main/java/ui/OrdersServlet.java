package ui;

import bo.Order;
import bo.User;
import db.OrdersDB;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Servlet that handles displaying the orders for the currently logged-in user.
 * <p>
 * When a GET request is made to "/orders", this servlet retrieves the User object
 * from the session, fetches all orders associated with that user from the database,
 * and forwards them to the "orders.jsp" page for display.
 */
@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {

    /**
     * Handles GET requests for "/orders".
     *
     * @param req  the HttpServletRequest object that contains the request the client made
     * @param resp the HttpServletResponse object that contains the response the servlet returns
     * @throws ServletException if an input or output error occurs while handling the request
     * @throws IOException      if the request for the GET could not be handled
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Get the session; do not create a new one if it doesn't exist
        HttpSession session = req.getSession(false);

        // If session or user is not present, redirect to login page
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("index.jsp");
            return;
        }

        // Retrieve the logged-in user from the session
        User user = (User) session.getAttribute("user");

        // Fetch the orders for this user from the database
        List<Order> orders = OrdersDB.getOrdersByUser(user);

        // Set orders as a request attribute and forward to JSP for display
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("orders.jsp").forward(req, resp);
    }
}
