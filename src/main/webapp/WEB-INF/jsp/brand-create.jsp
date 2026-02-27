<%@ include file="layout/navbar.jsp" %>
<%@ include file="layout/sidebar.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="content">

    <div class="form-container">
        <h2>Create Brand</h2>

        <form action="/brands/create" method="post">

            <div class="form-group">
                <label>Name</label>
                <input type="text" name="name" required>
            </div>

            <div class="form-group">
                <label>Parent Brand (Optional)</label>
                <select name="parentId">
                    <option value="">None</option>
                    <c:forEach var="brand" items="${brands}">
                        <option value="${brand.id}">
                                ${brand.name}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn-primary">
                    Save Brand
                </button>
            </div>

        </form>
    </div>

</div>