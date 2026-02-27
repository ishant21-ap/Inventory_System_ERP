package com.project.Inventory_System.dtos;

import lombok.Data;

import java.util.List;

@Data
public class DatatableResponseDTO<T> {


    private int draw;
    private long recordsTotal;
    private long recordsFiltered;
    private List<T> data;
}
