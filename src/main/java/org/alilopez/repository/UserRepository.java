package org.alilopez.repository;

import org.alilopez.config.DatabaseConfig;
import org.alilopez.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    // Método para obtener todos los usuarios de la base de datos
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        // Consulta SQL para obtener todos los usuarios de la tabla 'users'
        String query = "SELECT * FROM users";
        // Establece la conexión con la base de datos, prepara la sentencia SQL y ejecuta la consulta
        try (
                Connection conn = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            // Itera sobre los resultados y crea objetos 'User' para cada fila en el resultado
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));  // Asigna el ID del usuario
                u.setName(rs.getString("name"));  // Asigna el nombre del usuario
                u.setEmail(rs.getString("email"));  // Asigna el correo electrónico del usuario
                users.add(u);  // Agrega el usuario a la lista
            }
        }
        return users;  // Retorna la lista de usuarios
    }

    // Método para obtener un usuario por su ID desde la base de datos
    public User findByIdUser(int idUser) throws SQLException {
        User user = null;
        // Consulta SQL para obtener un usuario específico por su ID
        String query = "SELECT * FROM users WHERE id = ?";

        // Establece la conexión con la base de datos, prepara la sentencia SQL y ejecuta la consulta
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Asigna el ID del usuario a la consulta SQL
            stmt.setInt(1, idUser);

            // Ejecuta la consulta y obtiene el resultado
            try (ResultSet rs = stmt.executeQuery()) {
                // Si se encuentra el usuario, lo asigna a la variable 'user'
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));  // Asigna el ID del usuario
                    user.setName(rs.getString("name"));  // Asigna el nombre del usuario
                    user.setEmail(rs.getString("email"));  // Asigna el correo electrónico del usuario
                }
            }
        }
        return user;  // Retorna el usuario encontrado (o null si no se encontró)
    }

    // Método para guardar un nuevo usuario en la base de datos
    public void save(User user) throws SQLException {
        // Consulta SQL para insertar un nuevo usuario
        String query = "INSERT INTO users (name, email, contrasenia) VALUES (?, ?, ?)";

        // Establece la conexión con la base de datos y prepara la sentencia SQL
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Asigna los valores del usuario a la consulta SQL
            stmt.setString(1, user.getName());  // Asigna el nombre del usuario
            stmt.setString(2, user.getEmail());  // Asigna el correo electrónico del usuario
            stmt.setString(3, user.getPassword());  // Asigna la contraseña del usuario (debería estar cifrada)

            // Ejecuta la consulta para insertar el nuevo usuario
            stmt.executeUpdate();
        }
    }
}

