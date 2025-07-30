package org.alilopez.service;

import org.alilopez.model.User;
import org.alilopez.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserRepository userRepo;

    // Constructor que recibe un UserRepository para interactuar con la base de datos
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // Método para obtener todos los usuarios desde la base de datos
    public List<User> getAllUsers() throws SQLException {
        return userRepo.findAll(); // Retorna una lista de todos los usuarios
    }

    // Método para obtener un usuario por su ID desde la base de datos
    public User getByIdUser(int idUser) throws SQLException {
        return userRepo.findByIdUser(idUser); // Retorna el usuario con el ID proporcionado
    }

    // Método para crear un nuevo usuario en la base de datos
    public void createUser(User user) throws SQLException {
        // Aquí podrías agregar validaciones antes de guardar el usuario, como verificar si el email ya existe
        userRepo.save(user); // Guarda el usuario en la base de datos
    }
}
