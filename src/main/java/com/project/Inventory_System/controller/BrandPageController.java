package com.project.Inventory_System.controller;

import com.project.Inventory_System.dtos.BrandRequestDTO;
import com.project.Inventory_System.repository.BrandRepository;
import com.project.Inventory_System.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandPageController {

    private final BrandRepository brandRepository;
    private final BrandService brandService;

    @GetMapping
    public String getBrandPage(){
        return "brand-list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {

        // Send brands for parent dropdown
        model.addAttribute("brands",
                brandRepository.findAll());

        return "brand-create";
    }

    @PostMapping("/create")
    public String createBrand(@ModelAttribute BrandRequestDTO dto) {

        brandService.createBrand(dto);

        return "redirect:/brands";
    }
}
