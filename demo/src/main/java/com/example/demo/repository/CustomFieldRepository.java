package com.example.demo.repository;

import com.example.demo.entity.CustomField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

@Repository
public interface CustomFieldRepository extends JpaRepository<CustomField, Long> {

    @EntityGraph(attributePaths = {"childFields", "fieldValidations", "fieldIndexing"})
    List<CustomField> findByModuleId(Long moduleId);
}
