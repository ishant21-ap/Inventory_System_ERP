package com.project.Inventory_System.controller;

import com.project.Inventory_System.dtos.CategoryRequestDTO;
import com.project.Inventory_System.repository.CategoryRepository;
import com.project.Inventory_System.repository.DepartmentRepository;
import com.project.Inventory_System.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryPageController {

    private final CategoryService categoryService;
    private final DepartmentRepository departmentRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public String viewCategoryPage()
    {
        return "category-list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {

        // Send departments for dropdown
        model.addAttribute("departments",
                departmentRepository.findAll());

        // Send categories for parent dropdown
        model.addAttribute("categories",
                categoryRepository.findAll());

        return "category-create";
    }

    // 🔹 SAVE CATEGORY
    @PostMapping("/create")
    public String createCategory(@ModelAttribute CategoryRequestDTO dto) {

        categoryService.create(dto);

        return "redirect:/categories";
    }
}
