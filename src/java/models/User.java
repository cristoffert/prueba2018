package models;

public class User {
    //atributos privados de la clase User
    private int id;
    private String username, email, rol, password;

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    //metodo constructor
    public User(int id) {
        this.id = id;
    }
    //metodos getter y setter de cada atributo
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
        public boolean validate() {
        if (username.equals("jose") && email.equals("jose@gmail.com") &&password.equals("1234")){
            return true;
        }
        else {
            return false;
        }
    }
    
}
