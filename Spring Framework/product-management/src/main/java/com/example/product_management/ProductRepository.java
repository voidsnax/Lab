package com.example.product_management;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Product> mapper = (rs, rowNum) ->
        new Product(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getDouble("price")
        );

    public List<Product> findAll() {
        return jdbcTemplate.query(
            "SELECT * FROM products", mapper);
    }

    public Product findById(int id) {
        List<Product> result = jdbcTemplate.query(
            "SELECT * FROM products WHERE id = ?", mapper, id);

        return result.isEmpty() ? null : result.get(0);
    }

    public int add(Product product) {
        return jdbcTemplate.update(
            "INSERT INTO products (id, name, price) VALUES (?, ?, ?)",
            product.getId(), product.getName(), product.getPrice());
    }

    public int update(int id, Product product) {
        return jdbcTemplate.update(
            "UPDATE products SET name = ?, price = ? WHERE id = ?",
            product.getName(), product.getPrice(), id);
    }

    public int delete(int id) {
        return jdbcTemplate.update(
            "DELETE FROM products WHERE id = ?", id);
    }
}