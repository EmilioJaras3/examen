package org.alilopez.routes;

import io.javalin.Javalin;
import org.alilopez.controller.ProductController;

public class ProductRoutes {
    private final ProductController productController;

    public ProductRoutes(ProductController productController) {
        this.productController = productController;
    }

    public void register(Javalin app) {
        app.get("/products", productController::getAll);
        app.get("/products/{id}", productController::getById);
        app.post("/products", productController::create);
    }
}