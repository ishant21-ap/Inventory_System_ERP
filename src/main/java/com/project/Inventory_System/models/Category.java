package com.project.Inventory_System.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Reference;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "categories",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "department_id"})
    }
)
public class Category extends BaseEntity{

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @Reference
    private Category parent;

}
