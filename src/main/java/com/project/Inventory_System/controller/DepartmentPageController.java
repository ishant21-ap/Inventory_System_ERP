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

@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentPageController {

    private final DepartmentService departmentService;

    @GetMapping
    public String departmentPage(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Boolean active,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort,
            Model model
    ) {
        model.addAttribute("departments",
                departmentService.getDataTable(search, active, page, size, sort));

        model.addAttribute("search", search);
        model.addAttribute("active", active);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("sort", sort);

        return "departments"; // resolves to departments.jsp
    }


    @GetMapping("/create")
    public String showCreateDepartmentForm(Model model) {
        model.addAttribute("department", new DepartmentRequestDTO());
        return "create-department";
    }


    @PostMapping("/create")
    public String createDepartment(
            @ModelAttribute("department") DepartmentRequestDTO dto
    ){
        departmentService.create(dto);
        return "redirect:/departments";
    }
}

