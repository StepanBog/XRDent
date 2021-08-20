<%--
  Created by IntelliJ IDEA.
  User: stepa
  Date: 17.08.2021
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Login</h1>
    <form action="../../log" target="_blank">
        <p>
            Login:<input type="text" name="login">
        </p>
        <p>
            Password:<input type="password" name="password">
        </p>
        <p>
            <input type="radio" id="roleChoice1"
               name="role" value="Doctor">
            <label for="roleChoice1">Doctor</label>
            <input type="radio" id="roleChoice2"
                   name="role" value="Laboratory">
            <label for="roleChoice2">Laboratory</label>
        </p>
        <p>
            <button type="submit">Log In </button>
        </p>
    </form>
</body>
</html>
