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

public class AddCommentController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = null;
        Post currentPost = null;
        DatabaseManager databaseManager = new DatabaseManager();
        int ok = 0;
        for (Cookie cookie: req.getCookies()) {
            System.out.println(cookie.getName());
            if (cookie.getName().equals("user")) {
                ok++;
                currentUser = databaseManager.getUserWithName(cookie.getValue());
            }
            if (cookie.getName().equals("postId")) {
                ok++;
                List<Post> posts = databaseManager.getAllPosts();
                currentPost = posts.stream().filter(post -> post.getId() == Integer.parseInt(cookie.getValue())).findFirst().get();
            }
            if (ok == 2) {
                String content = req.getParameter("commentText");
                System.out.println(currentPost);
                System.out.println(currentUser);
                System.out.println(content);
                databaseManager.addComment(content, currentPost.getId(), currentUser.getId());
                return;
            }
        }
        resp.getWriter().println("Invalid request");
    }
}
