package com.project.Inventory_System.dtos;

import lombok.Data;

import java.util.List;


// This class is the JSON that Datatable Sends when serverSide is true

@Data
public class DatatableRequestDTO {

    private int draw;

    private int start;

    private int length;

    private Search search;


    @Data
    public static class Search {
        private String value;
    }
}



