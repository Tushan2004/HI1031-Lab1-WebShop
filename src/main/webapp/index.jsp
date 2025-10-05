<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WebShop Login</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 50px; text-align: center; }
        h2, h3 { color: #333; }
        form { display: inline-block; margin-top: 20px; text-align: left; padding: 20px; border: 1px solid #ccc; border-radius: 10px; }
        label { display: block; margin-top: 10px; }
        input[type=text], input[type=password] { width: 200px; padding: 5px; margin-top: 5px; border-radius: 3px; border: 1px solid #ccc; }
        input[type=submit] { margin-top: 15px; padding: 8px 15px; border-radius: 5px; border: none; background-color: #007bff; color: white; cursor: pointer; }
        input[type=submit]:hover { background-color: #0056b3; }
        .message { margin-bottom: 20px; font-size: 18px; color: #555; }
        .error { color: red; margin-bottom: 15px; }
        .form-container { display: flex; justify-content: center; gap: 30px; flex-wrap: wrap; }
    </style>
</head>
<body>

<h2>Welcome to MT WebShop!</h2>
<p class="message">Please create an account or log in to continue.</p>

<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>

<div class="form-container">
    <form action="${pageContext.request.contextPath}/login" method="post">
        <h3>Login</h3>
        <label>Username:</label>
        <input type="text" name="username" required>
        <label>Password:</label>
        <input type="password" name="password" required>
        <input type="submit" value="Login">
    </form>

    <form action="${pageContext.request.contextPath}/createUser" method="post">
        <h3>Create Account</h3>
        <label>Username:</label>
        <input type="text" name="username" required>
        <label>Password:</label>
        <input type="password" name="password" required>
        <input type="submit" value="Create User">
    </form>
</div>

</body>
</html>
