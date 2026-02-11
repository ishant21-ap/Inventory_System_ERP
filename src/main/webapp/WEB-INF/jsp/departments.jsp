<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="navbar.jsp"/>


<html>
<head>
    <title>Department Datatable</title>
</head>
<body>
<h2>Department Datatable</h2>

<form method="get" action="/departments">
    Search:
    <input type="text" name="search" value="${search}" />
    Active:
    <select name="active">
        <option value="">All</option>
        <option value="true" ${active == true ? "selected" : ""}>Active</option>
        <option value="false" ${active == false ? "selected" : ""}>Inactive</option>
    </select>

    Sort:
    <select name="sort">
        <option value="">Default</option>
        <option value="name_asc" ${sort == 'name_asc' ? 'selected' : ''}>Name (Ascending)</option>
        <option value="name_desc" ${sort == 'name_desc' ? 'selected' : ''}>Name (Desceding)</option>
    </select>

    <button type="submit">Apply</button>
<br><br>
</form>
    <a href="/departments/create" class="btn">Create Department</a>


    <br><br>

    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Active</th>
        </tr>

        <c:forEach var="dept" items="${departments}">
            <tr>
                <td>${dept.id}</td>
                <td>${dept.name}</td>
                <td>${dept.description}</td>
                <td>${dept.isActive}</td>
            </tr>
        </c:forEach>
    </table>

    <br/>

    <a href="?page=${page - 1}&size=${size}&search=${search}&active=${active}&sort=${sort}">
        Previous
    </a>

    |
    <a href="?page=${page + 1}&size=${size}&search=${search}&active=${active}&sort=${sort}">
        Next
    </a>
