package com.project.Inventory_System.repository;

import com.project.Inventory_System.dtos.projection.CategoryDatatableProjectionDTO;
import com.project.Inventory_System.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // prevents duplicate categories
    boolean existsByNameIgnoreCaseAndDepartmentId(String name, Long departmentId);

    List<Category> findByParentId(Long parentId);

    List<Category> findByDepartmentId(Long departmentId);



    // for pagination and datatable
    @Query(value = """
select c.id as id, c.name as name, d.name as departmentName, p.name as parentName, c.is_active as isActive
from categories as c 
left join departments as d on c.department_id = d.id
left join categories as p on c.parent_id = p.id
where (:search is null or lower(c.name) like lower(concat('%', :search, '%')))
order by c.id
limit :limit offset :offset
""", nativeQuery = true)
    List<CategoryDatatableProjectionDTO> fetchCategoryDatatable(
            @Param("search") String search,
            @Param("limit") int limit,
            @Param("offset") int offset
    );



    @Query(value = """
select count(*) from categories c where (:search is null or lower(c.name) like lower(concat('%', :search, '%')))
""", nativeQuery = true)
    long countFiltered(@Param("search") String search);
}
