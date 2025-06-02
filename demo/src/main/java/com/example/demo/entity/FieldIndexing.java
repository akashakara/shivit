package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "field_indexing")
public class FieldIndexing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "custom_field_id")
    private CustomField customField;

    private String indexingType;

    @Column(name = "data_field_name")
    private String dataFieldName;

    private Boolean display;

    @Column(name = "display_order")
    private Integer displayOrder;

    @Column(name = "field_width")
    private Integer fieldWidth;

    private Boolean filter;

    @Column(name = "filter_type")
    private String filterType;

    private Boolean hiddable;

    @Column(name = "index_field_header")
    private String indexFieldHeader;

    @Column(name = "index_field_name")
    private String indexFieldName;

    private Boolean sortable;

    @Column(name = "indexing_details")
    private String indexingDetails;

    @Column(name = "index_order")
    private Integer indexOrder;

    @Column(name = "index_type")
    private String indexType;
}
