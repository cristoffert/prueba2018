<%-- 
    Document   : index
    Created on : 14-06-2017, 21:06:52
    Author     : rober
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>  
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="estilos.css"/>
    </head>
    <body>
        <div class="box">
            <form action="register" method="POST"> 
                <h3>Register</h3>
                <input required type="text" placeholder="Username" name="username">
                <input required type="email" placeholder="Email address" name="email">

                <select name="rol">
                     <option selected value="0"> Rol </option>
                     <option value="Profesor">Profesor</option> 
                     <option value="Administrativo">Administrativo</option> 
                     <option value="Alumno">Alumno</option> 
                </select>
                <input required type="password" placeholder="Password" name="password">
                <input type="submit" class="btn btn-success btn-lg btn-block" value="Enviar">

                <a href="${pageContext.request.contextPath}/login">Ir a Login</a>
            </form>
        </div>
    </body>
</html>
