package com.project.Inventory_System.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandResponseDTO {

    private Long id;
    private String name;

    private Long parentId;
    private String parentName;

    private Boolean active;

}
