package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import java.util.Set;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "custom_fields")
@ToString(onlyExplicitlyIncluded = true)
public class CustomField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Long id;

    @Column(name = "module_id", nullable = false)
    private Long moduleId;

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "field_type")
    private String fieldType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_field_id")
    @JsonBackReference
    private CustomField parentField;

    @OneToMany(mappedBy = "parentField", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<CustomField> childFields = new HashSet<>();

    @OneToMany(mappedBy = "customField", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<FieldValidation> fieldValidations = new HashSet<>();

    @OneToOne(mappedBy = "customField", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FieldIndexing fieldIndexing;

    @Column(name = "field_header", nullable = false)
    private String fieldHeader;

    @Column(name = "is_mandatory")
    private String isMandatory;

    @Column(name = "on_change")
    private String onChange;

    @Column(name = "on_click")
    private String onClick;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomField)) return false;
        CustomField that = (CustomField) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
