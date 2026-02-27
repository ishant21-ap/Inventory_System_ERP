package com.project.Inventory_System.repository;


import com.project.Inventory_System.dtos.projection.BrandDatatableProjectionDTO;
import com.project.Inventory_System.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    boolean existsByNameIgnoreCase(String name);

    List<Brand> findByParentId(Long parentId);


    @Query(value = """
select b.id as id, b.name as name,
p.name as parentName, b.is_active as isActive
from brands b 
left join brands p on b.parent_id = p.id
where(:search is null or lower(b.name) like lower(concat('%', :search, '%')))
order by b.id
limit :limit offset :offset
""", nativeQuery = true)
    List<BrandDatatableProjectionDTO> fetchBrandDatatable(
            @Param("search") String search,
            @Param("limit") int limit,
            @Param("offset") int offset
    );



    @Query(value = """
select count(*) from brands b
where(:search is null or lower(b.name) like lower(concat('%', :search, '%')))
""", nativeQuery = true)
    long countFiltered(@Param("search") String search);
}
