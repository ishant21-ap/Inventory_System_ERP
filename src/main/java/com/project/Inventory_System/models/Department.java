package com.project.Inventory_System.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "departments")
public class Department extends BaseEntity{


    @Column(unique = true, nullable = false, length = 100)
    private String name;

    @Column(length = 250)
    private String description;


}
