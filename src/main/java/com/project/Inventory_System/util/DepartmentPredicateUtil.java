package com.project.Inventory_System.util;

import com.project.Inventory_System.models.Department;

import java.util.Comparator;
import java.util.function.Predicate;

public class DepartmentPredicateUtil {

    public static Predicate<Department> searchByName(String search) {
        return dept -> {
            if (search == null || search.isBlank()) {
                return true;       // return all data
            }
            return dept.getName().toLowerCase().contains(search.toLowerCase());
        };
    }


    public static Predicate<Department> filterByActive(Boolean active) {
        return department -> {
            if(active == null){
                return true;
            }
            return department.getIsActive().equals(active);
        };
    }


}
