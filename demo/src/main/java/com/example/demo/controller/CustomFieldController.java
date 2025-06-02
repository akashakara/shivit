package com.example.demo.controller;

import com.example.demo.entity.CustomField;
import com.example.demo.service.CustomFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/custom-fields")
public class CustomFieldController {
    @Autowired
    private CustomFieldService service;

    @GetMapping
    public ResponseEntity<List<CustomField>> getByModuleId(@RequestParam Long moduleId) {
        return ResponseEntity.ok(service.getFieldsByModuleId(moduleId));
    }
}