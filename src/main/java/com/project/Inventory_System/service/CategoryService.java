package com.project.Inventory_System.service;

import com.project.Inventory_System.dtos.CategoryRequestDTO;
import com.project.Inventory_System.dtos.CategoryResponseDTO;
import com.project.Inventory_System.dtos.DatatableResponseDTO;
import com.project.Inventory_System.dtos.projection.CategoryDatatableProjectionDTO;
import com.project.Inventory_System.exceptions.BusinessException;
import com.project.Inventory_System.models.Category;
import com.project.Inventory_System.models.Department;
import com.project.Inventory_System.repository.CategoryRepository;
import com.project.Inventory_System.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

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



    public DatatableResponseDTO<CategoryDatatableProjectionDTO> getDatatable(
            int draw, int length, int start, String search
    ){
        int offset = start;


        List<CategoryDatatableProjectionDTO> data =
                categoryRepository.fetchCategoryDatatable(search, length, offset);

        long totalRecords = categoryRepository.count();

        long filteredRecords = (search == null || search.isBlank()) ?
                totalRecords : categoryRepository.countFiltered(search);


        DatatableResponseDTO<CategoryDatatableProjectionDTO> responseDTO = new DatatableResponseDTO<>();
        responseDTO.setDraw(draw);
        responseDTO.setRecordsTotal(totalRecords);
        responseDTO.setRecordsFiltered(filteredRecords);
        responseDTO.setData(data);


        return responseDTO;
    }


}
