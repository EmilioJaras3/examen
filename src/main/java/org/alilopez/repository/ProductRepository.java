package org.alilopez.repository;

import org.alilopez.config.DatabaseConfig;
import org.alilopez.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setStock(rs.getInt("stock"));
                product.setPrice(rs.getDouble("price"));
                products.add(product);
            }
        }
        return products;
    }

    public Product findById(int id) throws SQLException {
        Product product = null;
        String query = "SELECT * FROM product WHERE id = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setStock(rs.getInt("stock"));
                    product.setPrice(rs.getDouble("price"));
                }
            }
        }
        return product;
    }

    public void save(Product product) throws SQLException {
        String query = "INSERT INTO product (name, description, stock, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setInt(3, product.getStock());
            stmt.setDouble(4, product.getPrice());

            stmt.executeUpdate();
        }
    }
}