package com.project.Inventory_System.controller;

import com.project.Inventory_System.dtos.CategoryRequestDTO;
import com.project.Inventory_System.dtos.CategoryResponseDTO;
import com.project.Inventory_System.service.CategoryService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {


    private final CategoryService categoryService;


    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody @Valid CategoryRequestDTO categoryRequestDTO) {
        categoryService.create(categoryRequestDTO);
        return ResponseEntity.ok("Category created");
    }

    @GetMapping
    public List<CategoryResponseDTO> getCategories() {
        return  categoryService.getAllCategories();
    }


    @PutMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivateCategory(@PathVariable Long id) {
        categoryService.deactivate(id);
        return ResponseEntity.ok("Category deactivated");
    }
}
