<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Course Detail</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex flex-column min-vh-100">
    <%@ include file="navbar.jsp" %>

    <h2>Course: ${course.title}</h2>
    <p>Description: <strong>${course.description}</strong></p>

    <h4 class="mt-4">Students Enrolled</h4>
    <ul class="list-group mb-4">
        <c:forEach var="s" items="${course.students}">
            <li class="list-group-item">${s.name} (${s.mail})</li>
        </c:forEach>
    </ul>

    <a href="courses" class="btn btn-link">← Back to course list</a>
    <%@ include file="footer.jsp" %>

</body>
</html>
