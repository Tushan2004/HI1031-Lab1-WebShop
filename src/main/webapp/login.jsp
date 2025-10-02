<html>
<body>

<h2>Login</h2>

<form action="${pageContext.request.contextPath}/login" method="post">
    <label>Username:</label>
    <input type="text" name="username">
    <label>Password:</label>
    <input type="password" name="password">
    <input type="submit" value="Login">
</form>

</body>
</html>