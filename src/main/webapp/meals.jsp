<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="ru.javawebinar.topjava.model.MealTo" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <style>
        <%@include file="style.css" %>
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>

<table cellpadding="5">
    <caption>
        <h2>
            Meals
        </h2>
        <a href="<%=request.getContextPath()%>/meals/new" class="btn btn-success">Add
            New Meal</a>
    </caption>
    <thead>
    <tr>
        <th align="left">Time</th>
        <th align="left">Description</th>
        <th align="left">Calories</th>
        <th align="left">Excess</th>
        <th align="center">Actions</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach var="meal" items="${meals}">

        <tr>
            <%
                MealTo mealTo = (MealTo) pageContext.getAttribute("meal");
                LocalDateTime dateTime = mealTo.getDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formatDateTime = dateTime.format(formatter);
                request.setAttribute("formatDateTime", formatDateTime);
            %>
            <c:choose>
            <c:when test="${meal.excess}">
                <td class="red">${formatDateTime}</td>
                <td class="red">${meal.description}</td>
                <td class="red">${meal.calories}</td>
                <td class="red">${meal.excess}</td>
                <td>
                    <a href="<%=request.getContextPath()%>/meals/edit?id=<c:out value='${meal.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp; <a
                        href="<%=request.getContextPath()%>/meals/delete?id=<c:out value='${meal.id}' />">Delete</a>
                </td>
            </c:when>
            <c:otherwise>
                <td class="green">${formatDateTime}</td>
                <td class="green">${meal.description}</td>
                <td class="green">${meal.calories}</td>
                <td class="green">${meal.excess}</td>
                <td>
                    <a href="<%=request.getContextPath()%>/meals/edit?id=<c:out value='${meal.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp; <a
                        href="<%=request.getContextPath()%>/meals/delete?id=<c:out value='${meal.id}' />">Delete</a>
                </td>
            </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>