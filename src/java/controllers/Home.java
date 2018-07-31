/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author rober
 */
public class Home extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Home</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Home at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                RequestDispatcher rd;
        //crear varibale de conexion a la bd
        DbConnection conn = new DbConnection();
        //creamos objeto de tipo userDao
        UserDao userDao = new UserDao(conn);
        //guardamos la llista de objetos User que vengan del metodo all()
        List<User> lista = userDao.all();
        //desconexion de la bD
        conn.disconnect();
        //creo el atributo lista para pasarlo a traves del  objeto request
        request.setAttribute("Lista", lista);
        //hacemos reenvio d ela solicitud
        rd = request.getRequestDispatcher("welcome.jsp");
        rd.forward(request, response);
        
        String action = request.getParameter("action");
        switch (action) {
            case "eliminar":
                this.deleteUser(request, response);
                break;
        }
        
    }

    //metodo delete en el Home servlet
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("idUser"));
        DbConnection conn = new DbConnection();
        UserDao userDao = new UserDao(conn);
        int result = userDao.delete(userId);
        String msg = "";
        if (result == 1) {
            msg = "El usuario fue eliminado del sistema";
        }
        else {
            msg = "Ocurrio un error al intentar borrar el usuario";
        }
        conn.disconnect();
        request.setAttribute("message", msg);
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/home");
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd;
        //crear varibale de conexion a la bd
        DbConnection conn = new DbConnection();
        //creamos objeto de tipo userDao
        UserDao userDao = new UserDao(conn);
        //guardamos la llista de objetos User que vengan del metodo all()
        List<User> lista = userDao.all();
        //desconexion de la bD
        conn.disconnect();
        //creo el atributo lista para pasarlo a traves del  objeto request
        request.setAttribute("Lista", lista);
        //hacemos reenvio d ela solicitud
        rd = request.getRequestDispatcher("welcome.jsp");
        rd.forward(request, response);
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
