package com.project.Inventory_System.controller;

import com.project.Inventory_System.dtos.BrandResponseDTO;
import com.project.Inventory_System.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/brand")
public class BrandPageController {

    private final BrandService brandService;


    @GetMapping
    public String getBrandDataTable(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Boolean active,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort,
            Model model
    ){
        List<BrandResponseDTO> brands = brandService.getDataTable(search, active, page, size, sort);

        model.addAttribute("brands", brands);
        model.addAttribute("active", active);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("sort", sort);
        model.addAttribute("search", search);

        return "brands";
    }
}
