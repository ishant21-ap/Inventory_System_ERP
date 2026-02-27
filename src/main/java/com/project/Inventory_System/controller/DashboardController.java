package com.project.Inventory_System.controller;

import com.project.Inventory_System.repository.BrandRepository;
import com.project.Inventory_System.repository.CategoryRepository;
import com.project.Inventory_System.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {


    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final DepartmentRepository departmentRepository;


    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        long totalDepartments = departmentRepository.count();
        long totalCategories = categoryRepository.count();
        long totalBrands = brandRepository.count();

        model.addAttribute("totalDepartments", totalDepartments);
        model.addAttribute("totalCategories", totalCategories);
        model.addAttribute("totalBrands", totalBrands);

        return "dashboard";
    }
}
