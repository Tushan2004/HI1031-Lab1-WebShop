package ui;

import bo.Cart;
import bo.CartItem;
import bo.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Servlet that handles adding and removing items from the shopping cart.
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    /**
     * Handles POST requests for adding or removing items from the cart.
     *
     * @param req  the HttpServletRequest object
     * @param resp the HttpServletResponse object
     * @throws ServletException if a servlet error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        // Create a new cart if it doesn't exist in session
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String action = req.getParameter("action");

        if ("add".equals(action)) {
            // Add a product to the cart
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String descr = req.getParameter("dresc");
            double price = Double.parseDouble(req.getParameter("price"));

            Item item = new Item(id, name, descr, price);
            cart.addItem(new CartItem(item, 1));

        } else if ("remove".equals(action)) {
            // Remove a product from the cart
            int id = Integer.parseInt(req.getParameter("id"));
            cart.removeItem(id);
        }

        // Redirect back to the webshop page
        resp.sendRedirect("webshop.jsp");
    }
}
