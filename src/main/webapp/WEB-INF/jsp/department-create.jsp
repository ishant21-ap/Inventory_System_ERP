<%@ include file="layout/navbar.jsp" %>
<%@ include file="layout/sidebar.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">


<div class="content">

    <div class="form-container">

        <h2>Create Department</h2>

        <form action="/departments/create" method="post">

            <div class="form-group">
                <label>Name</label>
                <input type="text" name="name" required>
            </div>

            <div class="form-group">
                <label>Description</label>
                <input type="text" name="description">
            </div>

            <div class="form-actions">
                <button type="submit" class="btn-primary">
                    Save Department
                </button>
            </div>

        </form>

    </div>

</div>