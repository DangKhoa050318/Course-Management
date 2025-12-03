<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex flex-column min-vh-100">
    <%@ include file="navbar.jsp" %>

    <h2 class="mb-4">Student List</h2>

    <form method="post" action="students" class="row g-3 mb-4">
        <input type="hidden" name="action" value="add"/>
        <div class="col-md-4">
            <input name="name" class="form-control" placeholder="Name"/>
        </div>
        <div class="col-md-4">
            <input name="mail" class="form-control" placeholder="Mail"/>
        </div>
        <div class="col-md-4">
            <button type="submit" class="btn btn-primary">Add Student</button>
        </div>
    </form>

    <table class="table table-bordered table-hover">
        <thead class="table-light">
            <tr>
                <th>Name</th><th>Mail</th><th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="s" items="${students}">
            <tr>
                <td>${s.name}</td>
                <td>${s.mail}</td>
                <td><a href="students?action=detail&id=${s.id}" class="btn btn-sm btn-outline-info">Details</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%@ include file="footer.jsp" %>

</body>
</html>
