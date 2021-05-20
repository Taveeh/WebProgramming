package Controller;

import domain.DatabaseManager;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {
    public LoginController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username.isEmpty()) {
            req.getSession().setAttribute("error", "Username must not be empty");
            req.getRequestDispatcher("login.jsp").include(req, resp);
        } else if (password.isEmpty()) {
            req.getSession().setAttribute("error", "Password must not be empty");
            req.getRequestDispatcher("login.jsp").include(req, resp);
        } else {
            DatabaseManager dbManager = new DatabaseManager();
            User user = dbManager.authenticate(username, password);
            if (user != null) {
                resp.addCookie(new Cookie("user", user.getUsername()));
                resp.sendRedirect("main");
            } else {
                req.getSession().setAttribute("error", "Username or password invalid");
                req.getRequestDispatcher("login.jsp").include(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("login.jsp").include(req, resp);
    }
}
