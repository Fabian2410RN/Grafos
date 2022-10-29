<%-- 
    Document   : RegistrarNuevoCliente
    Created on : 28 oct. 2022, 12:42:43
    Author     : Estadm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String mensaje = "<script text/javascript>alert('Esto se debe de mostrar en el msgbox');</script>"; 

%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de producto</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
    </head>
    
    <body>
    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card mt-8 text-center">
                <div class="card-header">
                    <h1>Registro de producto</h1>
                    <div>
                        <img src="../imagenesVista/Imagen5.png"/>
                    </div>
                </div>
                <br>
                <form method="post" action="<c:url value="/vistaWeb/RegistrarNuevoProducto"/>" >
                    <div class="form-group">
                        <label>Nombre</label>
                        <input type="text" class "form-control" name = "nombre" placeholder="Nombre" required>
                    </div>
                    <div class="form-group">
                        <label>Marca</label>
                        <input type="text" class "form-control" name = "marca" placeholder="Marca" required>
                    </div>
                    <div class="form-group">
                        <label>Precio</label>
                        <input type="text" class "form-control" name = "precio" placeholder="Precio" required>
                    </div>
                    <div class="form-group">
                        <button type="submit" class= "btn btn-primary">
                            Registrar
                        </button>
                        <a href="../index.html">Cancelar</a>
                        
                    </div>
                </form>
            </div>
        </div>
    </div>
    </body>
</html>
