package domain;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    String connectionString = "jdbc:postgresql://localhost:5432/WebProgramming";
    String username = "postgres";
    String password = "branza123";

    public DatabaseManager() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public User getUserWithName(String username) {
        User user = null;
        String statement = "SELECT * FROM Users U WHERE U.Username = '" + username + "'";
        try (var connection = DriverManager.getConnection(connectionString, this.username, this.password);
             var preparedStatement = connection.prepareStatement(statement);
             var rs = preparedStatement.executeQuery()) {
            if (rs.next()) {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return user;
    }

    public User authenticate(String username, String password) {
        User user = getUserWithName(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        String statement = "SELECT * FROM Posts P";
        try (var connection = DriverManager.getConnection(connectionString, this.username, this.password);
             var preparedStatement = connection.prepareStatement(statement);
             var rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("Id");
                posts.add(new Post(
                        id,
                        rs.getString("postContent"),
                        this.getPostComments(id)
                ));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return posts;
    }

    public User getUserWithId(int userId) {
        User user = null;
        String statement = "SELECT * FROM Users U WHERE U.id = " + userId;
        try (var connection = DriverManager.getConnection(connectionString, this.username, this.password);
             var preparedStatement = connection.prepareStatement(statement);
             var rs = preparedStatement.executeQuery()) {
            if (rs.next()) {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return user;
    }
    public List<Comment> getPostComments(int postId) {
        List<Comment> comments = new ArrayList<>();
        String statement = "SELECT * FROM Comments C WHERE C.postId = " + postId;
        try (var connection = DriverManager.getConnection(connectionString, this.username, this.password);
             var preparedStatement = connection.prepareStatement(statement);
             var rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                comments.add(new Comment(
                        rs.getInt("Id"),
                        rs.getString("Comment"),
                        getUserWithId(rs.getInt("userId"))
                ));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return comments;
    }


    public void addPost(String postContent) {
        String statement = "INSERT INTO posts(postcontent) VALUES ('" + postContent + "')";
        try (var connection = DriverManager.getConnection(connectionString, this.username, this.password);
             var preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.execute();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void deleteComment(int commentId) {
        System.out.println(commentId);
        String statement = "DELETE FROM comments where id = " + commentId;
        try (var connection = DriverManager.getConnection(connectionString, this.username, this.password);
             var preparedStatement = connection.prepareStatement(statement)) {
            System.out.println("Sunt si aici");
            preparedStatement.execute();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void addComment(String comment, int postID, int userId) {
        String statement = "INSERT INTO comments(comment, postid, userid) VALUES ('" + comment + "', " + postID +", " + userId + ")";
        try (var connection = DriverManager.getConnection(connectionString, this.username, this.password);
             var preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.execute();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
