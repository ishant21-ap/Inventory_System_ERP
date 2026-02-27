<%@ include file="layout/navbar.jsp" %>
<%@ include file="layout/sidebar.jsp" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="content">

    <div class="form-container">
        <h2>Create Category</h2>

        <form action="/categories/create" method="post">

            <div class="form-group">
                <label>Name</label>
                <input type="text" name="name" required>
            </div>

            <div class="form-group">
                <label>Department</label>
                <select name="departmentId" required>
                    <option value="">Select Department</option>
                    <c:forEach var="dept" items="${departments}">
                        <option value="${dept.id}">
                                ${dept.name}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label>Parent Category (Optional)</label>
                <select name="parentId">
                    <option value="">None</option>
                    <c:forEach var="cat" items="${categories}">
                        <option value="${cat.id}">
                                ${cat.name}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn-primary">
                    Save Category
                </button>
            </div>

        </form>
    </div>

</div>