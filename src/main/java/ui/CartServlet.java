package ui;

import bo.Cart;
import bo.CartItem;
import bo.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String action = req.getParameter("action");

        if ("add".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String descr = req.getParameter("dresc");
            double price = Double.parseDouble(req.getParameter("price")); // endast för add

            Item item = new Item(id, name, descr, price);
            cart.addItem(new CartItem(item, 1));


        } else if ("remove".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            cart.removeItem(id);
        }

        resp.sendRedirect("webshop.jsp");
    }
}
