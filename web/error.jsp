<%-- 
    Document   : error
    Created on : 14-06-2017, 20:58:29
    Author     : rober
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .mensaje-error {
                background: red;
                text-align: center;
                margin: 10px 20%;
                border-radius: 5px;
                color:white;
                padding: 5px 0;
            }
        </style>
    </head>
    <body>
        <div class="mensaje-error">
            <h3>Tus credenciales son incorrectas</h3>
            <h3>Intentalo de nuevo por favor</h3>
        </div>
        
        <jsp:include page="index.jsp" />
        
        
        <script
            src="https://code.jquery.com/jquery-3.2.1.js"
            integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
            crossorigin="anonymous"></script>
        <script>
            // javascript nativo para cambiar background del body
            function changeBackground(color) {
                document.body.style.background = color;
            } 
            //changeBackground("yellow");
                     
            //jquery para desaparecer el mensaje de error
            $(document).ready(function(){
               $('body').css("background", "black");
               $('.mensaje-error').delay(2000).fadeOut('slow');
            });
                      
        </script> 
    </body>
</html>
