<%--
  Created by IntelliJ IDEA.
  User: andrey
  Date: 11.01.2020
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Registration</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function validate() {
            var res = true;
            if ($('#login').val() === "") {
                alert('You should enter ' + $('#login').attr('name'));
                res = false;
            }
            if (!(/^8\d{10}$/.test($('#phone').val()))) {
                alert('Number of phone should have the next format 8dddddddddd, where d is any numeral');
                res = false;
            }
            if ($('#password').val() === "") {
                alert('You should enter ' + $('#password').attr('name'));
                res = false;
            }
            if (!($('#password').val() === $('#rpassword').val())) {
                alert('Passwords in field \"Password\" and \"Repeat your password\" are not equals');
                res = false;
            }
            return res;
        }
    </script>
</head>
<body style="background-color: rgb(255, 235, 205)">
<div class="container">
    <h2>Registration:</h2>
    <form action="${pageContext.servletContext.contextPath}/signup" method="post" onsubmit="return validate();">
        <c:if test="${error != ''}">
            <div style="background-color: red">
                <c:out value="${error}"></c:out>
            </div>
        </c:if>

        <div class="form-group">
            <label for="login">Login:</label>
            <input type="text" class="form-control" id="login" placeholder="Enter your login" name="login">
        </div>

        <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="text" class="form-control" id="phone" placeholder="Enter your phone" name="phone">
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" placeholder="Enter your password" name="password">
        </div>

        <div class="form-group">
            <label for="rpassword">Repeat your password:</label>
            <input type="password" class="form-control" id="rpassword" placeholder="Repeat your password" name="rpassword">
        </div>

        <button type="submit" class="btn btn-default">SignUp</button>
    </form>

</div>
</body>
</html>
