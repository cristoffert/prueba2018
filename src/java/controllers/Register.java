package controllers;

import dao.DbConnection;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;

public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.- recibir parametros de formulario y guardarlos en variables
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String rol = request.getParameter("rol");
        String password = request.getParameter("password");
        //2.- Imprimir hacia consola de servidor(Output) para asegurarme de que capture los parametros
        System.out.println(email);
        System.out.println(password);
        System.out.println(rol);
        System.out.println(username);
        
        //3.Instnaciar el objeto user de la clase User.java
        User user1 = new User(0);
        user1.setUsername(username);
        user1.setEmail(email);
        user1.setRol(rol);
        user1.setPassword(password);
        System.out.println("objeto user1 creado: " + user1.getEmail());
        
        //4.-Hacemos el Insert. Guardar en Base de Datos
        DbConnection conn = new DbConnection();
        UserDao userDao = new UserDao(conn);
        boolean insert = userDao.insert(user1);
        System.out.println("resultado del insert: " + insert);
        
        //5 capturar todos los users en mi BD y enviarlos a jsp
        List<User> lista = userDao.all();
        conn.disconnect();
        request.setAttribute("Lista", lista);
        
        //4.- LLamar al  metodo validate del modelo User.java
//        boolean status = user1.validate();
//        System.out.println("resultado de validacion: " + status);
        
        
        //5.- Establecer dentro del objeto request, el objeto
        //que enviaré hacia la pagina jsp
        request.setAttribute("User", user1);
        //6.- Validar el booleano status (validacion en el modelo)
        //si el contenido de las variables coinciden con lo que yo defino manualmente entro al if
        //y soy redirigido a pagina de bienvenido welcome.jsp
        if (insert) {
            RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
            rd.forward(request, response);
        }
        //de lo contrario es redireccionado a error.jsp
        else {
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
        }
    }
}
