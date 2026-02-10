package com.project.Inventory_System.controller;

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

@Controller
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


    @GetMapping("/datatable")
    public List<DepartmentResponseDTO> datatable(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Boolean active,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort
    ){
        return departmentService.getDataTable(search, active, page, size, sort);
    }






}
