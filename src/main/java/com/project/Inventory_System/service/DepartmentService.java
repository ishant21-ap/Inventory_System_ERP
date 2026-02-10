package com.project.Inventory_System.service;

import com.project.Inventory_System.dtos.DepartmentRequestDTO;
import com.project.Inventory_System.dtos.DepartmentResponseDTO;
import com.project.Inventory_System.exceptions.BusinessException;
import com.project.Inventory_System.models.Department;
import com.project.Inventory_System.repository.DepartmentRepository;
import com.project.Inventory_System.util.DepartmentPredicateUtil;
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
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final ModelMapper modelMapper;

    public void create(DepartmentRequestDTO departmentRequestDTO) {
        if(departmentRepository.existsByNameIgnoreCase(departmentRequestDTO.getName())) {
            throw new BusinessException("Department Already Exists");
        }

        Department department = Department.builder()
                .name(departmentRequestDTO.getName())
                .description(departmentRequestDTO.getDescription())
                .build();
        departmentRepository.save(department);
    }


    public List<DepartmentResponseDTO> getAll(){
        return departmentRepository.findAll().
                stream().
                map(department -> modelMapper.map(department, DepartmentResponseDTO.class))
                .collect(Collectors.toList());
    }


    public List<DepartmentResponseDTO> getDataTable(
            String search,
            Boolean isActive,
            int page,
            int size,
            String sortBy
            ){
        List<Department> departments = departmentRepository.findAll();

        // Predicate (Filtering)
        Predicate<Department> predicate = DepartmentPredicateUtil
                .searchByName(search)
                .and(DepartmentPredicateUtil.filterByActive(isActive));

        // Sorting
        Comparator<Department> comparator = getComparator(sortBy);

        // Pagination
        int skip = page * size;

        return departments
                .stream()
                .filter(predicate)
                .sorted(comparator)
                .skip(skip)
                .limit(size)
                .map(department -> modelMapper.map(department, DepartmentResponseDTO.class))
                .collect(Collectors.toList());
    }



    private Comparator<Department> getComparator(String sortBy) {

        if(sortBy == null || sortBy.isBlank()){
            return Comparator.comparing(Department::getId);    // default sorting is by id
        }

        switch (sortBy) {
            case "name_asc":
                return Comparator.comparing(Department::getName);

            case "name_desc":
                return Comparator.comparing(Department::getName).reversed();

            default:
                return Comparator.comparing(Department::getId);
        }
    }

}
