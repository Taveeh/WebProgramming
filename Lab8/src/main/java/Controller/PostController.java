package Controller;

import domain.Comment;
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

public class PostController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        int ok = 0;
        for (Cookie cookie: req.getCookies()) {
            if (cookie.getName().equals("postId")) {
                DatabaseManager db = new DatabaseManager();
                List<Post> posts = db.getAllPosts();
                Post currentPost = posts.stream().filter(post -> post.getId() == Integer.parseInt(cookie.getValue())).findFirst().get();
                req.getSession().setAttribute("currentPost", currentPost);
                List<Comment> comments = db.getPostComments(Integer.parseInt(cookie.getValue()));
                req.getSession().setAttribute("comments", comments);
                ok++;
            }
            if (cookie.getName().equals("user")) {
                User user = (new DatabaseManager()).getUserWithName(cookie.getValue());
                req.getSession().setAttribute("user", user);
                ok++;
            }
            if (ok == 2) {
                req.getRequestDispatcher("post.jsp").include(req, resp);
                return;
            }
        }
        resp.getWriter().println("Invalid request");
    }
}
