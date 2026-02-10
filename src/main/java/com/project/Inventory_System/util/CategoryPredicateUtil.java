package com.project.Inventory_System.util;

import com.project.Inventory_System.models.Category;

import java.util.function.Predicate;

public class CategoryPredicateUtil {

    public static Predicate<Category> searchByName(String search){
        return category -> {
            if(search == null || search.isBlank()){
                return true;
            }
            return category.getName().toLowerCase().contains(search.toLowerCase());
        };
    }

    public static Predicate<Category> filterByDepartment(Long departmentId){
        return category -> {
            if(departmentId == null){
                return true;
            }

            return category.getDepartment().getId().equals(departmentId);
        };
    }


    public static Predicate<Category> filterByActive(Boolean active){
        return category -> {
            if(active == null){
                return true;
            }
            return category.getIsActive().equals(active);
        };
    }
}
