package com.project.Inventory_System.service;

import com.project.Inventory_System.dtos.BrandRequestDTO;
import com.project.Inventory_System.dtos.BrandResponseDTO;
import com.project.Inventory_System.dtos.DatatableResponseDTO;
import com.project.Inventory_System.dtos.projection.BrandDatatableProjectionDTO;
import com.project.Inventory_System.exceptions.BusinessException;
import com.project.Inventory_System.models.Brand;
import com.project.Inventory_System.repository.BrandRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandService {

    private final BrandRepository brandRepository;

    public void createBrand(BrandRequestDTO brandRequestDTO) {
        if(brandRepository.existsByNameIgnoreCase(brandRequestDTO.getName())) {
            throw new BusinessException("Brand with name " + brandRequestDTO.getName() + " already exists");
        }

        Brand parent = null;

        if(brandRequestDTO.getParentId() != null) {
            parent = brandRepository.findById(brandRequestDTO.getParentId())
                    .orElseThrow(() -> new BusinessException("Brand with id " + brandRequestDTO.getParentId() + " does not exist"));
        }

        Brand brand = Brand.builder()
                .name(brandRequestDTO.getName())
                .parent(parent)
                .build();

        if (parent != null) {
            validateNoCircularRelation(parent, brandRequestDTO.getName());
        }


        brandRepository.save(brand);
    }

    private void validateNoCircularRelation(Brand parent, String newBrandName) {
        Brand current = parent;

        while(current != null){
            if(current.getName().equals(newBrandName)){
                throw new BusinessException("Brand with name " + newBrandName + " already exists");
            }
            current = current.getParent();
        }
    }






    public List<BrandResponseDTO> getAllBrands(){

        return brandRepository.findAll()
                .stream()
                .map(brand -> {
                    BrandResponseDTO dto = new BrandResponseDTO();
                    dto.setId(brand.getId());
                    dto.setName(brand.getName());

                    if(brand.getParent() != null) {
                        dto.setParentId(brand.getParent().getId());
                        dto.setParentName(brand.getParent().getName());
                    }
                    dto.setActive(brand.getIsActive());
                    return dto;
                }).toList();
    }



    public DatatableResponseDTO<BrandDatatableProjectionDTO> getDatatable(
            int draw, int length, int start, String search
    ){
        int offset = start;

        List<BrandDatatableProjectionDTO> data =
                brandRepository.fetchBrandDatatable(search, length, offset);

        long totalRecords = brandRepository.count();

        long filteredRecords = (search == null || search.isBlank()) ?
                totalRecords : brandRepository.countFiltered(search);

        DatatableResponseDTO<BrandDatatableProjectionDTO> result = new DatatableResponseDTO<>();
        result.setDraw(draw);
        result.setRecordsTotal(totalRecords);
        result.setRecordsFiltered(filteredRecords);
        result.setData(data);

        return result;
    }





}
