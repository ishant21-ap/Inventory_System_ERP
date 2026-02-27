package com.project.Inventory_System.dtos.projection;


public interface CategoryDatatableProjectionDTO {

    Long getId();
    String getName();
    String getDepartmentName();
    String getParentName();
    Boolean getIsActive();
}
