package com.project.Inventory_System.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonUtils {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
