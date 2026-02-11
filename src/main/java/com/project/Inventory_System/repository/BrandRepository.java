package com.project.Inventory_System.repository;


import com.project.Inventory_System.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    boolean existsByNameIgnoreCase(String name);

    List<Brand> findByParentId(Long parentId);
}
