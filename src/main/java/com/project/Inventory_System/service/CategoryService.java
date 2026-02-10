package com.project.Inventory_System.service;

import com.project.Inventory_System.dtos.CategoryRequestDTO;
import com.project.Inventory_System.dtos.CategoryResponseDTO;
import com.project.Inventory_System.exceptions.BusinessException;
import com.project.Inventory_System.models.Category;
import com.project.Inventory_System.models.Department;
import com.project.Inventory_System.repository.CategoryRepository;
import com.project.Inventory_System.repository.DepartmentRepository;
import com.project.Inventory_System.util.CategoryPredicateUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final DepartmentRepository departmentRepository;

    private final ModelMapper modelMapper;

    public void create(CategoryRequestDTO categoryRequestDTO){

        Department department = departmentRepository.findById(categoryRequestDTO.getDepartmentId())
                .orElseThrow(() -> new BusinessException("Department not found"));

        if(categoryRepository.existsByNameIgnoreCaseAndDepartmentId(categoryRequestDTO.getName(), categoryRequestDTO.getDepartmentId()))
            throw new BusinessException("Category already exists in this department");

        Category parent = null;
        if(categoryRequestDTO.getParentId() != null){
            parent = categoryRepository.findById(categoryRequestDTO.getParentId())
                    .orElseThrow(() -> new BusinessException("Parent category not found"));
        }

        if(parent != null && !parent.getDepartment().getId().equals(categoryRequestDTO.getDepartmentId()))
            throw new BusinessException("Parent Category must belong to same department");

        Category category = Category.builder()
                .name(categoryRequestDTO.getName())
                .department(department)
                .parent(parent)
                .build();

        categoryRepository.save(category);
    }



    public void deactivate(Long categoryId){
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new BusinessException("Category not found"));
        deactivateRecursively(category);
    }

    private void deactivateRecursively(Category category){
        category.setIsActive(false);

        List<Category> children = categoryRepository.findByParentId(category.getId());
        for(Category child : children){
            deactivateRecursively(child);
        }
    }


    public List<CategoryResponseDTO> getAllCategories(){


        return categoryRepository.findAll()
                .stream()
                .map(cat -> {
                    CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
                    categoryResponseDTO.setId(cat.getId());
                    categoryResponseDTO.setName(cat.getName());

                    categoryResponseDTO.setDepartmentId(cat.getDepartment().getId());
                    categoryResponseDTO.setDepartmentName(cat.getDepartment().getName());

                    if(cat.getParent() != null){
                        categoryResponseDTO.setParentId(cat.getParent().getId());
                        categoryResponseDTO.setParentName(cat.getParent().getName());
                    }
                    categoryResponseDTO.setIsActive(cat.getIsActive());
                    return categoryResponseDTO;
                })
                .toList();
    }



    public List<CategoryResponseDTO> getDataTable(
            String search,
            Long departmentId,
            Boolean isActive,
            int page,
            int size,
            String sortBy
    ){
        List<Category> categories = categoryRepository.findAll();

        Predicate<Category> predicate =
                CategoryPredicateUtil.searchByName(search)
                        .and(CategoryPredicateUtil.filterByDepartment(departmentId))
                        .and(CategoryPredicateUtil.filterByActive(isActive));

        Comparator<Category> comparator = getComparator(sortBy);

        int skip = page * size;

        return categories.stream()
                .filter(predicate)
                .sorted(comparator)
                .skip(skip)
                .limit(size)
                .map(category -> {
                    CategoryResponseDTO dto = new CategoryResponseDTO();
                    dto.setId(category.getId());
                    dto.setName(category.getName());

                    dto.setDepartmentId(category.getDepartment().getId());
                    dto.setDepartmentName(category.getDepartment().getName());

                    if (category.getParent() != null) {
                        dto.setParentId(category.getParent().getId());
                        dto.setParentName(category.getParent().getName());
                    }

                    dto.setIsActive(category.getIsActive());
                    return dto;
                })
                .toList();
    }



    private Comparator<Category> getComparator(String sortBy) {

        if(sortBy == null || sortBy.isBlank()){
            return Comparator.comparing(Category::getId);    // default sorting is by id
        }

        switch (sortBy) {
            case "name_asc":
                return Comparator.comparing(Category::getName);

            case "name_desc":
                return Comparator.comparing(Category::getName).reversed();

            default:
                return Comparator.comparing(Category::getId);
        }
    }
}
