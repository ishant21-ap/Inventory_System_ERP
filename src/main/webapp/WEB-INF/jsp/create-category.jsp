<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="navbar.jsp"/>

<h2>Create Category</h2>

<form method="post" action="/categories/create">
    Name :
    <input type="text" name="name" required />
    <br><br>

    Department :
    <select name="departmentId" required>
        <option value="">Select</option>
        <c:forEach var="dept" items="${departments}">
            <option value="${dept.id}">
                ${dept.name}
            </option>
        </c:forEach>
    </select>

    <br><br>

    Parent Category :
    <select name="parentId">
        <option value="">None</option>
        <c:forEach var="cat" items="${allCategories}">
            <option value="${cat.id}">
                ${cat.name}
            </option>
        </c:forEach>
    </select>

    <br><br>

    <button type="submit">Save</button>
</form>