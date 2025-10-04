package ui;

import bo.Cart;
import bo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        if (cart != null && !cart.getItems().isEmpty()) {
            //  Här ska jag spara i DB senare
            try {
                db.CheckoutDB.addItemsToDB(cart.getItems(),user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            cart.getItems().clear();
        }


        req.setAttribute("message", "Tack! Din order har lagts.");
        req.getRequestDispatcher("checkout.jsp").forward(req, resp);
    }
}
