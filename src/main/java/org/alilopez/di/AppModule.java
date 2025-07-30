package org.alilopez.di;

import org.alilopez.controller.ProductController;
import org.alilopez.controller.UserController;
import org.alilopez.repository.ProductRepository;
import org.alilopez.repository.UserRepository;
import org.alilopez.routes.ProductRoutes;
import org.alilopez.routes.UserRoutes;
import org.alilopez.service.ProductService;
import org.alilopez.service.UserService;

public class AppModule {

    // Método para inicializar y configurar el módulo de usuarios
    public static UserRoutes initUser() {
        // Crea una instancia del repositorio de usuarios, que maneja las operaciones con la base de datos
        UserRepository userRepo = new UserRepository();

        // Crea el servicio de usuarios, que contiene la lógica de negocio para gestionar los usuarios
        UserService userService = new UserService(userRepo);

        // Crea el controlador de usuarios, que maneja las solicitudes HTTP y las interactúa con el servicio
        UserController userController = new UserController(userService);

        // Crea las rutas de usuarios, donde se definen las rutas para las operaciones sobre los usuarios
        return new UserRoutes(userController);
    }

    public static ProductRoutes initProducts() {
        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);
        ProductController productController = new ProductController(productService);
        return new ProductRoutes(productController);
    }
}