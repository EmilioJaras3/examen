package org.alilopez.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.alilopez.model.User;
import org.alilopez.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserController {
    private final UserService userService;

    // Constructor del controlador que recibe un UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Método para obtener todos los usuarios
    public void getAll(Context ctx) {
        try {
            // Llama al servicio para obtener la lista de usuarios
            List<User> users = userService.getAllUsers();
            // Responde con la lista de usuarios en formato JSON
            ctx.json(users);
        } catch (SQLException e) {
            // En caso de error en la base de datos, responde con un error 500 (fallo interno del servidor)
            ctx.status(500).result("Error al obtener usuarios");
        }
    }

    // Método para obtener un usuario por su ID
    public void getById(Context ctx) {
        try {
            // Obtiene el ID de la ruta y lo convierte en un número
            int id = Integer.parseInt(ctx.pathParam("id"));
            // Llama al servicio para obtener el usuario por su ID
            User user = userService.getByIdUser(id);
            // Si el usuario existe, lo responde en formato JSON
            if (user != null) {
                ctx.json(user);
            } else {
                // Si no se encuentra, responde con un error 404 (no encontrado)
                ctx.status(HttpStatus.NOT_FOUND).result("Usuario no encontrado");
            }
        } catch (Exception e) {
            // Responde con un error 404 si ocurre algún problema
            ctx.status(404).result("Error al obtener usuarios");
        }
    }

    // Método para crear un nuevo usuario
    public void create(Context ctx) {
        try {
            // Convierte el cuerpo de la solicitud en un objeto de tipo User
            User user = ctx.bodyAsClass(User.class);
            // Llama al servicio para crear el usuario en la base de datos
            userService.createUser(user);
            // Responde con un código 201 (creado) si la operación es exitosa
            ctx.status(201).result("Usuario creado");
        } catch (Exception e) {
            // Responde con un error 400 (solicitud incorrecta) si ocurre algún problema
            ctx.status(400).result("Error al crear usuario");
        }
    }
}
