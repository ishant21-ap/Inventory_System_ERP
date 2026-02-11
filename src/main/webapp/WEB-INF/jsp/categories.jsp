<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="navbar.jsp"/>


<html>
<head>
    <title>Category Datatable</title>
</head>
<body>

<h2>Category Datatable</h2>
<form method="get" action="/category">
    Search :
    <input type="text" name="search" value="${search}" />

    Department :
    <select name="departmentId">
        <option value="">All</option>
        <c:forEach var="dept" items="${departments}">
            <option value="${dept.id}"
                    ${dept.id == departmentId ? "selected" : ""}>
                ${dept.name}
            </option>
        </c:forEach>
    </select>

    Active:
    <select name="active">
        <option value="">All</option>
        <option value="true" ${active == true ? "selected" : ""}>Active</option>
        <option value="false" ${active == false ? "selected" : ""}>Inactive</option>
    </select>

    Sort:
    <select name="sort">
        <option value="">Default</option>
        <option value="name_asc" ${sort == 'name_asc' ? 'selected' : ''}>Name Asc</option>
        <option value="name_desc" ${sort == 'name_desc' ? 'selected' : ''}>Name Desc</option>
    </select>

    <button type="submit">Apply</button>

</form>

<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Category</th>
        <th>Department</th>
        <th>Parent Category</th>
        <th>Active</th>
    </tr>

    <c:forEach var="cat" items="${categories}">
        <tr>
            <td>${cat.id}</td>
            <td>${cat.name}</td>
            <td>${cat.departmentName}</td>
            <td>
                <c:choose>
                    <c:when test="${cat.parentName != null}">
                        ${cat.parentName}
                    </c:when>
                    <c:otherwise>
                        -
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${cat.isActive}</td>
        </tr>
    </c:forEach>
</table>

<br/>

<a href="?page=${page - 1}&size=${size}&search=${search}&departmentId=${departmentId}&active=${active}&sort=${sort}">
    Previous
</a>
|
<a href="?page=${page + 1}&size=${size}&search=${search}&departmentId=${departmentId}&active=${active}&sort=${sort}">
    Next
</a>
