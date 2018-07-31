<%-- 
    Document   : welcome
    Created on : 07-06-2017, 21:05:55
    Author     : rober
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <style>
            body {
                background-image: url("http://cdn.wallpapersafari.com/84/3/hgxewf.jpg");
                background-position: center;
                background-size: cover;
                background-repeat: no-repeat;
                height: 100vh;
                font-family: sans-serif;
                color:white;
            }

            .mensaje-exito {
                background: green;
                position: absolute;
                top: 50px;
                left: 20%;
                right: 20%;
                border-radius: 10px;
            }
            h3 {
                color:yellow;
            }
            .navbar-default  {
                background: rgba(0,0,0,.5);
                border-color: rgba(0,0,0,.5);
            }
        </style>
    </head>
    <body>
        
        <nav class="navbar navbar-default">
            <div class="container">
                <form class="navbar-form navbar-right" action="search" method="POST" >
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search" name="term">
                    </div>
                    <button type="submit" class="btn btn-default btn-success">Submit</button>
                </form>
            </div>
        </nav>
        <br>
        <br>
        <br>
        <br>
        
        <script>
          if( !localStorage.getItem('${pageContext.request.contextPath}/login') ){

   document.getElementById('mensaje-exito').style.display= 'block';
   // estableces el localstorage en 1 para que no se vuelva a cumplir la condicion
   localStorage.setItem('${pageContext.request.contextPath}/login',1);

} else {
   document.getElementById('mensaje-exito').style.display= 'none';
}
       </script>
       <%
       if (request.getParameter("action") == null){
       %>
        <div class="mensaje-exito">
            <p>Te has registrado correctamente ${user.username}</p>
            <p>Dirigete a tu email ${user.email} para activar tu cuenta</p>
        </div>
        <%
        }
        %>
       <%
       if (request.getParameter("action")!= null){
       %>
        <div class="mensaje-exito">
            <p>El usuario fue eliminado correctamente</p>
        </div>
        <%
        }
        %>
        <div class="item">
            <h1>Listado de Usuarios </h1>
            
            <table class="table">
                <thhead>
                    <tr>
                        <td>Username</td>
                        <td>Email</td>
                        <td>Rol</td>
                        <td>Editar</td>
                        <td>Eliminar</td>
                    </tr>
                </thhead>
                <tbody>
                    <c:forEach var="user" items="${Lista}">
                        <tr>
                            <td>${user.username}</td>
                            <td>${user.email}</td>
                            <td>${user.rol}</td>
                            <td><a href="#" class="btn btn-default">Editar</a></td>
                            <td><a href="${pageContext.request.contextPath}/home?action=eliminar&idUser=${user.id} " class="btn btn-danger">Eliminar</a></td>
                        </tr>  
                        
                    </c:forEach>   
                </tbody>
            </table>
            
             
                <h3>${user.username} ${user.email}</h3>             
            
        </div>
        
        
                <script
            src="https://code.jquery.com/jquery-3.2.1.js"
            integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
            crossorigin="anonymous"></script>
        <script>            
            //jquery para desaparecer el mensaje de error
            $(document).ready(function(){
               $('.mensaje-exito').delay(2000).fadeOut('slow');
            });
                      
        </script> 
    </body>
</html>
