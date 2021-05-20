<%@ page import="domain.User" %>
<%@ page import="domain.Post" %>
<%@ page import="domain.Comment" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Forum post</title>
    <script src="Scripts/jquery-2.0.3.js"></script>
    <link rel="stylesheet" href="Styles/post.css">
    <script src="Scripts/post.js"></script>
</head>
<body>
<div class="container">
    <%
        User user = (User) request.getSession().getAttribute("user");
        Post currentPost = (Post) request.getSession().getAttribute("currentPost");
        out.println("<h4> Hello there! General " + user.getUsername() + "! Welcome to the forum post: <b>" + currentPost.getPostContent() + "</b></h4>");
    %>

    <div class="comments">
        <h3>Here are the comments:</h3>

        <%
            @SuppressWarnings("unchecked") List<Comment> commentList = (List<Comment>) request.getSession().getAttribute("comments");
            for (Comment comment: commentList) {
                String toPrint = "<div class=\"comment\">" + "<p>" + comment.getComment() + "</p>";
                if (comment.getUser().getId() == user.getId()) {
                    toPrint += "<button onclick=\"deleteComment(" + comment.getId() + ")\">" + "DELETE" + "</button> </div>";
                } else {
                    toPrint += "</div>";
                }
                out.println(toPrint);
            }
        %>
    </div>
    <div class="add-div">
    <div class="input-field">
        <label for="commentInput">Comment: </label>
        <input id="commentInput" type="text">
    </div>
        <button onclick="addComment()">Add Comment</button>
    </div>


</div>
</body>
</html>
