<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Forum</title>
    <script src="Scripts/jquery-2.0.3.js"></script>
    <link rel="stylesheet" href="Styles/login.css">
</head>
<body>
<div class="container">
    <form action="login" method="post">
        <div class="input-field">
            <label for="username">Username:</label>
            <input id="username" name="username" type="text">
        </div>
        <div class="input-field">
            <label for="password">Password:</label>
            <input id="password" name="password" type="password">
        </div>
        <%
            String error = (String) session.getAttribute("error");
            if (error != null) {
                out.println("<p>" + error + "</p>");
            }
        %>
        <div class="buttons">
            <button type="submit">Login</button>
        </div>
    </form>
</div>
</body>
</html>
