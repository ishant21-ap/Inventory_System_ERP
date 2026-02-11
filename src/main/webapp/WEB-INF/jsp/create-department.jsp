<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="navbar.jsp"/>

<h2>Create Department</h2>

<form method="post" action="/departments/create">
    Name:
    <input type="text" name="name" required>
    <br><br>

    Description:
    <input type="text" name="description">
    <br><br>

    <button type="submit">Save</button>
</form>