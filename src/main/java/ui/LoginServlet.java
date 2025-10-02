package ui;

import bo.User; // din klass
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login") // matchar action i login.jsp
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || username.isBlank() ||
                password == null || password.isBlank()) {
            req.setAttribute("error", "Please enter both username and password.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        User user = new User(username, password);

        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
