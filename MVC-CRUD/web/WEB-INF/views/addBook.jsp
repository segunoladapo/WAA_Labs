<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: segun
  Date: 5/31/2018
  Time: 11:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
    .error {
    color: #ff0000;
    font-style: italic;
    font-weight: bold;
    }
    </style>
    <title>Add A Book</title>
</head>
<body>
<springForm:form method="POST" modelAttribute="book" action="/books">
    <springForm:errors path="*" cssClass="error" element="div" />
    <table>
        <tr>
            <td>Title:</td>
            <td><input type="text" name="title" /> <springForm:errors path="title" cssClass="error" /></td>
        </tr>
        <tr>
            <td>ISBN:</td>
            <td><input type="text" name="ISBN" />  </td>
        </tr>
        <tr>
            <td>Author:</td>
            <td><input type="text" name="author" /> </td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><input type="text" name="price" /> </td>
        </tr>
    </table>
    <input type="submit"/>

</springForm:form>

</body>
</html>
