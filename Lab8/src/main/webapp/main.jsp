<%@ page import="domain.Post" %>
<%@ page import="domain.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Forum</title>
    <link rel="stylesheet" href="Styles/main.css">
    <script src="Scripts/jquery-2.0.3.js"></script>
</head>
<body>
<div class="container">
    <%
        out.println("<h4>Hello there! General " + ((User) request.getSession().getAttribute("user")).getUsername() + "!</h4>");
    %>
    <div>
        <%
            @SuppressWarnings("unchecked") List<Post> posts = (List<Post>) request.getSession().getAttribute("posts");
            out.println("<ul class=\"elements\">");
            for (Post post : posts) {
                out.println("<li><button onClick=goToPost(" + post.getId() + ")>" + post.getPostContent() + "</button></li>");
            }
            out.println("</ul>");
        %>
    </div>

    <div class="add">
        <div class="input-field">
            <label for="topic">Post content: </label>
            <input id="topic" name="topic" type="text">
        </div>
        <button onclick="addPost()">Add Post</button>
    </div>

    <button onclick="logout()">LOGOUT</button>

</div>
</body>
<script src="Scripts/main.js"></script>

</html>
