<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="navbar.jsp"/>

<h2>Create Brand</h2>

<form method="post" action="/brand/create">
    Name :
    <input type="text" name="name" required>

    Parent Brand :
    <select name="parentId">
        <option value="">None</option>
        <c:forEach var="brand" items="${allBrands}">
            <option value="${brand.id}">
                ${brand.name}
            </option>
        </c:forEach>
    </select>
    <br>
    <button type="submit">Save</button>
</form>