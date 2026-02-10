package com.project.Inventory_System.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {

    private Long id;
    private String name;

    private Long departmentId;
    private String departmentName;

    private Long parentId;
    private String parentName;

    private Boolean isActive;
}
