package com.project.Inventory_System.service;

import com.project.Inventory_System.dtos.DatatableRequestDTO;
import com.project.Inventory_System.dtos.DatatableResponseDTO;
import com.project.Inventory_System.dtos.DepartmentRequestDTO;
import com.project.Inventory_System.dtos.DepartmentResponseDTO;
import com.project.Inventory_System.exceptions.BusinessException;
import com.project.Inventory_System.models.Department;
import com.project.Inventory_System.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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



    public DatatableResponseDTO<DepartmentResponseDTO> getDatatable(
            DatatableRequestDTO request
    ) {


        int page = request.getStart() / request.getLength();


        Pageable pageable = PageRequest.of(
                page,
                request.getLength()
        );

        Page<Department> departmentPage;

        String searchValue =
                request.getSearch() != null
                        ? request.getSearch().getValue()
                        : null;

        if (searchValue != null && !searchValue.isBlank()) {


            departmentPage =
                    departmentRepository
                            .findByNameContainingIgnoreCase(searchValue, pageable);

        } else {

            departmentPage = departmentRepository.findAll(pageable);
        }

        List<DepartmentResponseDTO> dtoList =
                departmentPage.getContent()
                        .stream()
                        .map(dept -> modelMapper.map(dept, DepartmentResponseDTO.class))
                        .toList();


        DatatableResponseDTO<DepartmentResponseDTO> response =
                new DatatableResponseDTO<>();

        response.setDraw(request.getDraw());

        response.setRecordsTotal(departmentRepository.count());

        response.setRecordsFiltered(departmentPage.getTotalElements());

        response.setData(dtoList);

        return response;
    }

}
