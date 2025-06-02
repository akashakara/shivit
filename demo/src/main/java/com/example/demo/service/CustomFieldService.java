package com.example.demo.service;

import com.example.demo.entity.CustomField;
import com.example.demo.repository.CustomFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomFieldService {

    @Autowired
    private CustomFieldRepository customFieldRepository;

    public List<CustomField> getFieldsByModuleId(Long moduleId) {
        System.out.println("Fetching custom fields for moduleId: " + moduleId);

        List<CustomField> fields = customFieldRepository.findByModuleId(moduleId);

        System.out.println("Fetched custom fields count: " + fields.size());
        return fields;
    }
}
