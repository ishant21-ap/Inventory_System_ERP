<%@ include file="layout/navbar.jsp" %>
<%@ include file="layout/sidebar.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

<div class="content">
    <h2>Dashboard</h2>
    <h3>Welcome to Inventory Management System</h3>
    <div class="card-container">
        <div class="card">
            <h3>Total Departments</h3>
            <p>${totalDepartments}</p>
        </div>
        <div class="card">
            <h3>Total Categories</h3>
            <p>${totalCategories}</p>
        </div>
        <div class="card">
            <h3>Total Brands</h3>
            <p>${totalBrands}</p>
        </div>
    </div>
</div>