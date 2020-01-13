<%--
  Created by IntelliJ IDEA.
  User: andrey
  Date: 13.01.2020
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Edit your advert</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background-color: rgb(255, 235, 205)">
<div class="container">
    <h2>Edit your advert:</h2>
    <form action="${pageContext.servletContext.contextPath}/user" method="post">
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea class="form-control" id="description" placeholder="Enter description..." name="description">
                <c:out value="${advert.description}"></c:out>
            </textarea>
        </div>

        <div class="form-group">
            <label for="Status">Status:</label><br>
            <select name="status" class="form-control" id="status" placeholder="Choose status of your advert">
                <option value="false">
                    actively
                </option>
                <option value="true">
                    sales
                </option>
            </select>
        </div>

        <input type="hidden" name="id" value="${advert.id}"/>
        <input type="hidden" name="action" value="update"/>

        <button type="submit" class="btn btn-default">Edit</button>
    </form>
</div>

</body>
</html>
