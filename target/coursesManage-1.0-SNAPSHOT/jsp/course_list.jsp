<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Course List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex flex-column min-vh-100">
    <%@ include file="navbar.jsp" %>

    <h2 class="mb-4">Course List</h2>

    <form method="post" action="courses" class="row g-3 mb-4">
        <div class="col-md-4">
            <input name="title" class="form-control" placeholder="Course Title"/>
        </div>
        <div class="col-md-6">
            <input name="description" class="form-control" placeholder="Description"/>
        </div>
        <div class="col-md-2">
            <button class="btn btn-primary w-100">Add Course</button>
        </div>
    </form>

    <table class="table table-bordered table-hover">
        <thead class="table-light">
            <tr>
                <th>Title</th><th>Description</th><th>Enrolled</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="c" items="${courses}">
            <tr>
                <td>${c.title}</td>
                <td>${c.description}</td>
                <td>
                    <a href="courses?action=detail&id=${c.id}" class="btn btn-sm btn-outline-secondary">
                        ${c.students.size()} student(s)
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%@ include file="footer.jsp" %>

</body>
</html>
