<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student Detail</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex flex-column min-vh-100">
    <%@ include file="navbar.jsp" %>

    <h2>Student: ${student.name}</h2>
    <p>Mail: <strong>${student.mail}</strong></p>

    <h4 class="mt-4">Enrolled Courses</h4>
    <ul class="list-group mb-4">
        <c:forEach var="c" items="${student.courses}">
            <li class="list-group-item">${c.title} - ${c.description}</li>
        </c:forEach>
    </ul>

    <h5>Add More Courses</h5>
    <form method="post" action="students" class="d-flex gap-2">
        <input type="hidden" name="action" value="enroll"/>
        <input type="hidden" name="studentId" value="${student.id}"/>
        <select name="courseId" class="form-select w-50">
            <c:forEach var="c" items="${availableCourses}">
                <option value="${c.id}">${c.title}</option>
            </c:forEach>
        </select>
        <button class="btn btn-success">Enroll</button>
    </form>

    <a href="students" class="btn btn-link mt-4">← Back to list</a>
    <%@ include file="footer.jsp" %>

</body>
</html>
