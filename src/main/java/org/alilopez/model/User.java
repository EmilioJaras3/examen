package org.alilopez.model;

public class User {

    private int id;  // ID del usuario
    private String name;  // Nombre del usuario
    private String email;  // Correo electrónico del usuario
    private String password;  // Contraseña del usuario (debería estar cifrada)

    // Getter para el atributo 'id'
    public int getId() {
        return id;
    }

    // Setter para el atributo 'id'
    public void setId(int id) {
        this.id = id;
    }

    // Getter para el atributo 'name'
    public String getName() {
        return name;
    }

    // Setter para el atributo 'name'
    public void setName(String name) {
        this.name = name;
    }

    // Getter para el atributo 'email'
    public String getEmail() {
        return email;
    }

    // Setter para el atributo 'email'
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter para el atributo 'password'
    public String getPassword() {
        return password;
    }

    // Setter para el atributo 'password'
    public void setPassword(String password) {
        this.password = password;
    }
}
