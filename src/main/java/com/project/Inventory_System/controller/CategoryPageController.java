package com.project.Inventory_System.controller;

import com.project.Inventory_System.service.DepartmentService;
import org.springframework.ui.Model;
import com.project.Inventory_System.dtos.CategoryResponseDTO;
import com.project.Inventory_System.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryPageController {

    private final CategoryService categoryService;

    private final DepartmentService departmentService;


    @GetMapping("/page")
    public String categoryPage(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) Boolean active,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort,
            Model model) {
        List<CategoryResponseDTO> categories =
                categoryService.getDataTable(search, departmentId, active, page, size, sort);

        model.addAttribute("categories", categories);
        model.addAttribute("search", search);
        model.addAttribute("departmentId", departmentId);
        model.addAttribute("active", active);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("sort", sort);

        model.addAttribute("departments", departmentService.getAll());

        return "categories";

    }
}
