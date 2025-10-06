package ui;

import bo.Cart;
import bo.User;
import db.CheckoutDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet that handles the checkout process.
 * It saves the items in the user's cart to the database
 * and clears the cart after successful checkout.
 */
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    /**
     * Handles POST requests to process checkout.
     * It retrieves the user's cart and user info from the session,
     * saves all cart items to the database, and then clears the cart.
     *
     * @param req  the HttpServletRequest object
     * @param resp the HttpServletResponse object
     * @throws ServletException if a servlet error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            // User not logged in, redirect to login page
            resp.sendRedirect("index.jsp");
            return;
        }

        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        if (cart != null && !cart.getItems().isEmpty()) {
            try {
                // Save items in DB
                CheckoutDB.addItemsToDB(cart.getItems(), user);
                // Clear the cart after checkout
                cart.getItems().clear();
            } catch (SQLException e) {
                throw new ServletException("Could not save order in database.", e);
            }
        }

        req.setAttribute("message", "Thank you! Your order has been placed.");
        req.getRequestDispatcher("checkout.jsp").forward(req, resp);
    }
}
