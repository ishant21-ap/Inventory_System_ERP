package com.project.Inventory_System.controller;

import com.project.Inventory_System.dtos.DatatableRequestDTO;
import com.project.Inventory_System.dtos.DatatableResponseDTO;
import com.project.Inventory_System.dtos.DepartmentRequestDTO;
import com.project.Inventory_System.dtos.DepartmentResponseDTO;
import com.project.Inventory_System.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid DepartmentRequestDTO departmentRequestDTO) {
        departmentService.create(departmentRequestDTO);
        return ResponseEntity.ok("Department Created");
    }

    @GetMapping
    public List<DepartmentResponseDTO> getAllDepartments(){
        return  departmentService.getAll();
    }


    @PostMapping("/datatable")
    public DatatableResponseDTO<DepartmentResponseDTO> datatable(
            @RequestBody DatatableRequestDTO datatableRequestDTO
    ){
        return departmentService.getDatatable(datatableRequestDTO);
    }






}
