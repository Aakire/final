package com.example.springSecurityApplication.services;

import com.example.springSecurityApplication.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CategoryService {

    private final ProductRepository productRepository;

    @Autowired
    public CategoryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
