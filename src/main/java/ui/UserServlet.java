package ui;

import bo.User; // din klass
import db.userDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet({"/login", "/createUser"}) // matchar action i login.jsp
public class UserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String path = req.getServletPath(); // ger /login eller /createUser

        if (path.equals("/login")) {
            handleLogin(req, resp);
        } else if (path.equals("/createUser")) {
            handleCreateUser(req, resp);
        }
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || username.isBlank() ||
                password == null || password.isBlank()) {
            req.setAttribute("error", "Please enter both username and password.");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        User user = new User(username, password);
        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        try {
            if (userDB.searchUserInDB(username, password)) {
                req.getRequestDispatcher("/webshop.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException("Database error", e);
        }
    }

    private void handleCreateUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || username.isBlank() ||
                password == null || password.isBlank()) {
            req.setAttribute("error", "Please enter both username and password.");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        User user = new User(username, password);
        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        try {
            userDB.writeUserToDB(username, password);
        } catch (Exception e) {
            throw new ServletException("Database error", e);
        }

        req.getRequestDispatcher("/webshop.jsp").forward(req, resp);
    }
}


