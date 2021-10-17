<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <title>Meal Form</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meal Form</h2>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${meal != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${meal == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${meal != null}">
                                Edit Meal
                            </c:if>
                            <c:if test="${meal == null}">
                                Add New Meal
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${meal != null}">
                        <input type="hidden" name="id" value="<c:out value='${meal.id}' />"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label>Meal date and Time</label> <input type="datetime-local"
                                                                 value="<c:out value='${meal.dateTime}' />"
                                                                 class="form-control"
                                                                 name="dateTime" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Meal Description</label> <input type="text"
                                                               value="<c:out value='${meal.description}' />"
                                                               class="form-control"
                                                               name="description" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Meal Calories</label> <input type="number" min="1" step="1"
                                                               value="<c:out value='${meal.calories}' />"
                                                               class="form-control"
                                                               name="calories" required="required">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>