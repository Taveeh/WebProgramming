package Controller;

import domain.DatabaseManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddPostController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String postContent = req.getParameter("postContent");

        if (postContent.isEmpty()) {
            req.getSession().setAttribute("error", "Post content must not be null");
            req.getRequestDispatcher("main.jsp").include(req, resp);
        } else {
            DatabaseManager db = new DatabaseManager();
            db.addPost(postContent);
        }
    }
}
