package com.example.product_management;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id) {
        Product product = service.getById(id);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody Product product) {
        service.add(product);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Product added.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable int id, @RequestBody Product product) {

        if (service.update(id, product) == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found.");
        }

        return ResponseEntity.ok("Product updated.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        if (service.delete(id) == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found.");
        }

        return ResponseEntity.ok("Product deleted.");
    }
}