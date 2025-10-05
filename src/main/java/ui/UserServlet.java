package ui;

import bo.User;
import db.UserDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Servlet for handling user login and account creation.
 * <p>
 * Mapped to "/login" and "/createUser". Handles POST requests to either log in
 * an existing user or create a new user in the database.
 */
@WebServlet({"/login", "/createUser"})
public class UserServlet extends HttpServlet {

    /**
     * Handles POST requests for login or account creation.
     *
     * @param req  HttpServletRequest object containing client request data
     * @param resp HttpServletResponse object for sending response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String path = req.getServletPath();

        if ("/login".equals(path)) {
            handleLogin(req, resp);
        } else if ("/createUser".equals(path)) {
            handleCreateUser(req, resp);
        }
    }

    /**
     * Handles user login.
     * <p>
     * Checks that both username and password are provided, verifies the credentials
     * against the database, and sets a session attribute for the logged-in user.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException if a database error occurs or request cannot be forwarded
     * @throws IOException      if an I/O error occurs
     */
    private void handleLogin(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            req.setAttribute("error", "Please enter both username and password.");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        User user = new User(username, password);
        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        try {
            if (UserDB.searchUserInDB(username, password)) {
                req.getRequestDispatcher("/webshop.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException("Database error", e);
        }
    }

    /**
     * Handles creation of a new user account.
     * <p>
     * Validates input, creates a User object, stores it in the session, and
     * inserts the new user into the database.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException if a database error occurs or request cannot be forwarded
     * @throws IOException      if an I/O error occurs
     */
    private void handleCreateUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            req.setAttribute("error", "Please enter both username and password.");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        User user = new User(username, password);
        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        try {
            UserDB.writeUserToDB(username, password);
        } catch (Exception e) {
            throw new ServletException("Database error", e);
        }

        req.getRequestDispatcher("/webshop.jsp").forward(req, resp);
    }
}
