package org.alilopez.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.alilopez.model.Product;
import org.alilopez.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public void getAll(Context ctx) {
        try {
            List<Product> products = productService.getAllProducts();
            ctx.json(products);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener productos: " + e.getMessage());
        }
    }

    public void getById(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Product product = productService.getProductById(id);

            if (product != null) {
                ctx.json(product);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("Producto no encontrado");
            }
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID inválido: debe ser un número");
        } catch (Exception e) {
            ctx.status(400).result("Error al obtener producto: " + e.getMessage());
        }
    }

    public void create(Context ctx) {
        try {
            Product product = ctx.bodyAsClass(Product.class);
            System.out.println(product);
            productService.createProduct(product);
            ctx.status(201).result("Producto creado exitosamente");
        } catch (IllegalArgumentException e) {
            ctx.status(400).result("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ctx.status(400).result("Error al crear producto: " + e.getMessage());
        }
    }
}
