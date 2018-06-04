<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <!-- Static content -->
    <link rel="stylesheet" href="/resources/css/style.css">
    <script type="text/javascript" src="/resources/js/app.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">

    <title>Spring Boot</title>
    <script>
        function getLoginPage() {
            window.location = '/login';
        }
    </script>
</head>
<body>
    <div class="jumbotron">
        <h1>Welcome to Spring Boot-MVC web Application</h1>
        <center><button onclick="getLoginPage()">PLease Login to Access Books and Cars in Store</button></center>
    </div>
</body>
</html>