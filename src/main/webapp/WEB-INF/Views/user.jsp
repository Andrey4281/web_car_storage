<%--
  Created by IntelliJ IDEA.
  User: andrey
  Date: 13.01.2020
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>You personal page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function getAdverts() {
            $.ajax({
                url: '${pageContext.servletContext.contextPath}/data',
                type: 'GET',
                dataType: 'json',
                complete: function (data) {
                    var adverts = JSON.parse(data.responseText);
                    addAdverts(adverts);
                }
            })
        }

        function addAdverts(adverts) {
            var result = '';
            for (var i = 0; i < adverts.length; i++) {
                var flag = 'actively';
                if (adverts[i].status) {
                    flag = 'sales';
                }
                result += '<tr><td>' + adverts[i].id + '</td>'
                    + '<td>' + adverts[i].user.login +'</td>'
                    + '<td>' + adverts[i].user.phone +'</td>'
                    + '<td>' + adverts[i].created +'</td>'
                    + '<td>' + '<img src="${pageContext.servletContext.contextPath}/download?name=' + adverts[i].photo + '"' + ' width="100px" height="100px"/>' + '</td>'
                    + '<td>' + adverts[i].description +'</td>'
                    + '<td>' + flag +'</td>'
                    + '<td>' + adverts[i].car.category +'</td>'
                    + '<td>' + adverts[i].car.brand +'</td>'
                    + '<td>' + adverts[i].car.engine +'</td>'
                    + '<td>' + adverts[i].car.transmission +'</td>'
                    + '<td>' + adverts[i].car.carcass +'</td></tr>';
            }
            var tbody = document.getElementById("adverts");
            tbody.innerHTML = result;
        }
    </script>
</head>
<body style="background-color: rgb(255, 235, 205)">
<script>
    getAdverts();
    setInterval("getAdverts();", 30000);
</script>

<div class="container">
    <div align="center">
        <script>
            document.write('<h2>Dear ' + sessionStorage.getItem("user").toString() + '!This your personal page!'
            +'You can edit, delete your advert and add new advert. Also you can browse '
            +'all adverts in system.</h2>');
        </script>
    </div>

    <div>
        <h2>
        <form action="${pageContext.servletContext.contextPath}/signout" method="post">
            <input type="submit" value="Sign Out"/>
        </form>
        </h2>
    </div>

    <div>
        <h2>You own adverts:
        </h2>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>phone</th>
                <th>created</th>
                <th>photo</th>
                <th>description</th>
                <th>status</th>
                <th>category</th>
                <th>brand</th>
                <th>engine</th>
                <th>transmission</th>
                <th>carcass</th>
                <th>edit</th>
                <th>delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${adverts}" var="advert">
                <tr>
                    <td><c:out value="${advert.id}"></c:out></td>
                    <td><c:out value="${advert.user.login}"></c:out></td>
                    <td><c:out value="${advert.user.phone}"></c:out></td>
                    <td><c:out value="${advert.created}"></c:out></td>
                    <td>
                        <img src="${pageContext.servletContext.contextPath}/download?name=${advert.photo}" width="100px" height="100px"/>
                    </td>
                    <td><c:out value="${advert.description}"></c:out></td>
                    <td>
                        <c:if test="${advert.status == true}">
                            <c:out value="sales"></c:out>
                        </c:if>
                        <c:if test="${advert.status == false}">
                            <c:out value="actively"></c:out>
                        </c:if>
                    </td>
                    <td><c:out value="${advert.car.category}"></c:out></td>
                    <td><c:out value="${advert.car.brand}"></c:out></td>
                    <td><c:out value="${advert.car.engine}"></c:out></td>
                    <td><c:out value="${advert.car.transmission}"></c:out></td>
                    <td><c:out value="${advert.car.carcass}"></c:out></td>
                    <td>
                        <form action="${pageContext.servletContext.contextPath}/user" method="get">
                            <input type="hidden" name="id" value="${advert.id}"/>
                            <input type="hidden" name="action" value="update"/>
                            <input type="submit" value="edit"/>
                        </form>
                    </td>
                    <td>
                        <form action="${pageContext.servletContext.contextPath}/user" method="post">
                            <input type="hidden" name="id" value="${advert.id}"/>
                            <input type="hidden" name="action" value="delete"/>
                            <input type="submit" value="delete"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div>
        <h2>Add new advert:</h2>
        <h2>
        <form action="${pageContext.servletContext.contextPath}/add" method="get">
            <input type="submit" value="add"/>
        </form>
        </h2>
    </div>

    <div>
        <h2>All adverts in system:</h2>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>phone</th>
                <th>created</th>
                <th>photo</th>
                <th>description</th>
                <th>status</th>
                <th>category</th>
                <th>brand</th>
                <th>engine</th>
                <th>transmission</th>
                <th>carcass</th>
            </tr>
            </thead>
            <tbody id="adverts">
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
