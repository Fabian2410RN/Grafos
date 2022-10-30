<%-- 
    Document   : MensajeExitosoFalloRegistroCompras
    Created on : 29 oct. 2022, 22:21:52
    Author     : Estadm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <%
          String mensajeRegistro = "<script text/javascript>alert('Cliente registrado correctamente');</script>"; 
          out.println(mensajeRegistro);
         %>
    </body>
</html>
