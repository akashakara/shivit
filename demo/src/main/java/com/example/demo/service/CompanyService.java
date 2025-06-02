package com.example.demo.service;

import com.example.demo.entity.Company;
import com.example.demo.repository.CompanyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository repository;

    public Page<Company> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Company save(Company company) {
        return repository.save(company);
    }

    public Company update(Long id, Company updatedCompany) {
        Company existing = repository.findById(id).orElseThrow();
        BeanUtils.copyProperties(updatedCompany, existing, "id");
        return repository.save(existing);
    }
}