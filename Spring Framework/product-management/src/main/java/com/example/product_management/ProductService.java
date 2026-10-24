package com.example.product_management;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product getById(int id) {
        return repository.findById(id);
    }

    public int add(Product product) {
        return repository.add(product);
    }

    public int update(int id, Product product) {
        return repository.update(id, product);
    }

    public int delete(int id) {
        return repository.delete(id);
    }
}