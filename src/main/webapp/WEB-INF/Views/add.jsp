<%--
  Created by IntelliJ IDEA.
  User: andrey
  Date: 10.01.2020
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding new adverts</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function validate() {
            var res = true;
            if ($('#brand').val() === "") {
                alert('You must enter ' + $('#brand').attr('name'));
                res = false;
            }
            if ($('#engine').val() === "") {
                alert('You must enter ' + $('#engine').attr('name'));
                res = false;
            }
            if ($('#transmission').val() === "") {
                alert('You must enter ' + $('#transmission').attr('name'));
                res = false;
            }
            if ($('#carcass').val() === "") {
                alert('You must enter ' + $('#carcass').attr('name'));
                res = false;
            }
            return res;
        }
    </script>
</head>
<body style="background-color: rgb(255, 235, 205)">
<div class="container">
    <h2>Add new advert:</h2>
    <form action="${pageContext.servletContext.contextPath}/add" method="post" enctype="multipart/form-data" onsubmit="return validate();">
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea class="form-control" id="description" placeholder="Enter description..." name="description">
            </textarea>
        </div>

        <div class="form-group">
            <h3>Characteristics of car:</h3>
        </div>

        <div class="form-group">
            <label for="category">Category:</label><br>
            <select name="category" class="form-control" id="category" placeholder="Choose category">
                <option value="passenger_car">
                    Passenger car
                </option>
                <option value="truck">
                    Truck
                </option>
            </select>
        </div>

        <div class="form-group">
            <label for="brand">Brand:</label>
            <input type="text" class="form-control" id="brand" placeholder="Enter brand of car" name="brand">
        </div>

        <div class="form-group">
            <label for="engine">Engine:</label>
            <input type="text" class="form-control" id="engine" placeholder="Enter kind of engine" name="engine">
        </div>

        <div class="form-group">
            <label for="transmission">Transmission:</label>
            <input type="text" class="form-control" id="transmission" placeholder="Enter kind of transmission" name="transmission">
        </div>

        <div class="form-group">
            <label for="carcass">Carcass:</label>
            <input type="text" class="form-control" id="carcass" placeholder="Enter kind of carcass" name="carcass">
        </div>

        <div class="form-group">
            <label for="file">Photo of your car:</label>
            <input type="file" id="file" value="Choose photo of your car" name="file">
        </div>

        <button type="submit" class="btn btn-default">Add</button>
    </form>
</div>

</body>
</html>
