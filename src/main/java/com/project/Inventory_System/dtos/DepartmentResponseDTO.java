package com.project.Inventory_System.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Boolean isActive;
}
