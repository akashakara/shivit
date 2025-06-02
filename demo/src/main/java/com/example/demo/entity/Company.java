package com.example.demo.entity;
import lombok.Data;
import jakarta.persistence.*;
import java.util.Map;

@Data
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @ElementCollection
    @MapKeyColumn(name = "field_name")
    @Column(name = "field_value")
    @CollectionTable(name = "company_fields", joinColumns = @JoinColumn(name = "company_id"))
    private Map<String, String> customFields;
}