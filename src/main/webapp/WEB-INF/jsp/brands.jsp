<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="navbar.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Brand Datatable</title>
</head>
<body>

<h2>Brand Datatable</h2>
<form method="get" action="/brand">
    Search :
    <input type="text" name="search" value="${search}"/>

    Active :
    <select  name="active">
        <option value="">All</option>
        <option value="true" ${active == true ? "selected" : ""}>Active</option>
        <option value="false" ${active == false ? "selected" : ""}>Inactive</option>
    </select>


    Sort :
    <select name="sort">
        <option value="">Default</option>
        <option value="name_asc" ${sort == 'name_asc' ? 'selected' : ''}>Name (Ascending)</option>
        <option value="name_desc" ${sort == 'name_desc' ? 'selected' : ''}>Name (Descending)</option>
    </select>

    <button type="submit">Apply</button>

    <br><br>

</form>

    <a href="/brand/create">
        <button>Create Brand</button>
    </a>

    <br>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Brand</th>
            <th>Parent Brand</th>
            <th>Active</th>
        </tr>

        <c:forEach var="brand" items="${brands}">
            <tr>
                <td>${brand.id}</td>
                <td>${brand.name}</td>
                <td>
                    <c:choose>
                        <c:when test="${brand.parentName != null}">
                            ${brand.parentName}
                        </c:when>
                        <c:otherwise>
                            -
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${brand.active}</td>
            </tr>
        </c:forEach>
    </table>

    <br>
    <a href="?page=${page - 1}&size=${size}&search=${search}&departmentId=${departmentId}&active=${active}&sort=${sort}">
        Previous
    </a>
    |
    <a href="?page=${page + 1}&size=${size}&search=${search}&departmentId=${departmentId}&active=${active}&sort=${sort}">
        Next
    </a>
