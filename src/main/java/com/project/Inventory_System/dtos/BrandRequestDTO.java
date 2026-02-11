package com.project.Inventory_System.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandRequestDTO {

    @NotBlank
    private String name;

    private Long parentId;

}
