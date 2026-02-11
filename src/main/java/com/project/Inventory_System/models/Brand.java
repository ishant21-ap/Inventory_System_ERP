package com.project.Inventory_System.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "brands")
public class Brand extends BaseEntity{

    @Column(unique = true, nullable = false, length = 100)
    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Brand parent;
}
