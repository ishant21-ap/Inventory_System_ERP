package com.project.Inventory_System.repository;

import com.project.Inventory_System.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // prevents duplicate categories
    boolean existsByNameIgnoreCaseAndDepartmentId(String name, Long departmentId);

    List<Category> findByParentId(Long parentId);

    List<Category> findByDepartmentId(Long departmentId);
}
