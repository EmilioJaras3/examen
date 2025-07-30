package org.alilopez.service;

import org.alilopez.model.Product;
import org.alilopez.repository.ProductRepository;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() throws SQLException {
        return productRepo.findAll();
    }

    public Product getProductById(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID debe ser mayor a 0");
        }
        return productRepo.findById(id);
    }

    public void createProduct(Product product) throws SQLException {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }

        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }

        if (product.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }

        productRepo.save(product);
    }
}
