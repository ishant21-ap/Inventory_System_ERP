<%@ include file="layout/navbar.jsp" %>
<%@ include file="layout/sidebar.jsp" %>

<link rel="stylesheet"
      href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">


<div class="content">

    <div class="page-header">
        <h2>Categories</h2>

        <a href="/categories/create" class="btn-primary">
            Create Category
        </a>
    </div>

    <table id="categoryTable" class="display">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Department</th>
            <th>Parent</th>
            <th>Active</th>
        </tr>
        </thead>
    </table>

</div>


<script>
    $(document).ready(function (){
        $('#categoryTable').DataTable({
            processing: true,
            serverSide: true,
            ordering: false,
            ajax: {
                url: "/api/categories/datatable",
                type: "POST",
                contentType: "application/json",
                data: function (d){
                    return JSON.stringify(d);
                }
            },
            columns: [
                {data: "id"},
                {data: "name"},
                {data: "departmentName"},
                {data: "parentName"},
                {data: "isActive"}
            ]
        });
    });
</script>