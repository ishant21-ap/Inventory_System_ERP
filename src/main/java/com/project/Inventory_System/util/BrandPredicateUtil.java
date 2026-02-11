package com.project.Inventory_System.util;

import com.project.Inventory_System.models.Brand;

import java.util.function.Predicate;

public class BrandPredicateUtil {

    public static Predicate<Brand> searchByName(String search){
        return brand -> {
            if(search == null || search.isBlank()){
                return true;
            }

            return brand.getName()
                    .toLowerCase()
                    .contains(search.toLowerCase());
        };
    }


    public static Predicate<Brand> filterByActive(Boolean active){
        return brand -> {
            if(active == null){
                return true;
            }
            return brand.getIsActive().equals(active);
        };
    }
}
