package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Entity
@Table(name = "field_validations")
public class FieldValidation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custom_field_id")
    @JsonBackReference
    private CustomField customField;

    @Column(name = "validation_name", nullable = false)
    private String validationName;

    @Column(name = "error_message", nullable = false, columnDefinition = "mediumtext")
    private String errorMessage;

    @Column(nullable = false)
    private String status;

    @Column(name = "match_case", columnDefinition = "longtext")
    private String matchCase;

    @Column(name = "ref_custom_field_id")
    private Long refCustomFieldId;

    private String rule;

    @Column(name = "validation_rule")
    private String validationRule;

    @Column(name = "validation_type")
    private String validationType;

    private Integer position;
}
