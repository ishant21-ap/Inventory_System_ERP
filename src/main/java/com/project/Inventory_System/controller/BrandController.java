package com.project.Inventory_System.controller;

import com.project.Inventory_System.dtos.BrandRequestDTO;
import com.project.Inventory_System.dtos.BrandResponseDTO;
import com.project.Inventory_System.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;


    @PostMapping
    public ResponseEntity<?> createBrand(@RequestBody @Valid BrandRequestDTO brandRequestDTO) {
        brandService.createBrand(brandRequestDTO);
        return ResponseEntity.ok("Brand Created");
    }


}
