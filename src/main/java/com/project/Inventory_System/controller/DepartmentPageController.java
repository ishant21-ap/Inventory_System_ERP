package com.project.Inventory_System.controller;

import com.project.Inventory_System.dtos.DepartmentRequestDTO;
import com.project.Inventory_System.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentPageController {

    private final DepartmentService departmentService;

    @GetMapping
    public String viewDepartmentPage()
    {
        return "department-list";
    }

    @GetMapping("/create")
    public String showCreateForm() {
        return "department-create"; // JSP name
    }

    @PostMapping("/create")
    public String createDepartment(
            @ModelAttribute DepartmentRequestDTO dto,
            RedirectAttributes redirectAttributes
    ){
        departmentService.create(dto);

        redirectAttributes.addFlashAttribute("toastMessage", "Department Created Successfully");

        return "redirect:/departments";
    }
}
