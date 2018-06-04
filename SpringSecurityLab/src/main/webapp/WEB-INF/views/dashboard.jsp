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

    <title>DashBoard Page</title>
</head>
<body>
<div class="jumbotron">
    <div class="row align-items-center justify-content-center">
        <h1>Welcome to Spring-Boot MVC web Application</h1>
    </div>
</div>
<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SALES')">

    <div class="container">
        <h2>Books In Store</h2>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Title</th>
                <th>ISBN</th>
                <th>Author</th>
                <th>Price</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.title}</td>
                    <td>${book.ISBN}</td>
                    <td>${book.author}</td>
                    <td>${book.price}</td>
                    <td><a href="books/${book.id}">Edit</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="/books/add"> Add a New Book to Store</a>
    </div>
</security:authorize>

<div>
    <p></p>
</div>
<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNT')">

    <div class="container">
        <h2>Cars Available for Sale</h2>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Make</th>
                <th>Model</th>
                <th>Year</th>
                <th>Color</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="car" items="${cars}">
                <tr>
                    <td>${car.make}</td>
                    <td>${car.model}</td>
                    <td>${car.year}</td>
                    <td>${car.color}</td>
                    <td><a href="cars/${car.id}">Edit</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="addCar.html"> Add a Car</a>
    </div>

</security:authorize>


</body>
</html>