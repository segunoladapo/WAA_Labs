<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <!-- Static content -->
    <link rel="stylesheet" href="/resources/css/style.css">
    <script type="text/javascript" src="/resources/js/app.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">

    <title>Login Page</title>
</head>
<body>

<security:authorize access="hasRole('UsdsSER')">
    This zone will be visible to Supervisor only.<br/>
    You have Supervisor role.<br/>
</security:authorize>
<div class="jumbotron">
    <div class="row align-items-center justify-content-center">
        <h1>Login Here</h1>
    </div>
</div>
<div class="row align-items-center justify-content-center">
    <form action="/authenticate" method="POST">

        <div class="form-group">
            <label >Email address</label>
            <input  class="form-control"  name="username"
                   placeholder="Enter email">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="password">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>