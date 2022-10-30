<%-- 
    Document   : MensajeExitosoFalloRegistroCompras
    Created on : 29 oct. 2022, 22:21:52
    Author     : Estadm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <form method="post" action="<c:url value="/vistaWeb/MensajeFalloClienteNoExiste"/>" >
        <%
          String mensajeRegistro = "<script text/javascript>alert('El cliente no existe');</script>"; 
          out.println(mensajeRegistro);
         %>
         </form>
    </body>
</html>
