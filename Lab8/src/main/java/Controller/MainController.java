package Controller;

import domain.DatabaseManager;
import domain.Post;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MainController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        for (Cookie cookie: req.getCookies()) {
            System.out.println(cookie.getName() + " -> " + cookie.getValue());
            if (cookie.getName().equals("user")) {
                List<Post> posts = (new DatabaseManager()).getAllPosts();
                User user = (new DatabaseManager()).getUserWithName(cookie.getValue());
                req.getSession().setAttribute("posts", posts);
                req.getSession().setAttribute("user", user);
                req.getRequestDispatcher("main.jsp").include(req, resp);
                return;
            }
        }

        resp.getWriter().println("Invalid request");
    }
}
