package com.project.Inventory_System.service;

import com.project.Inventory_System.dtos.DepartmentRequestDTO;
import com.project.Inventory_System.dtos.DepartmentResponseDTO;
import com.project.Inventory_System.exceptions.BusinessException;
import com.project.Inventory_System.models.Department;
import com.project.Inventory_System.repository.DepartmentRepository;
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



}
