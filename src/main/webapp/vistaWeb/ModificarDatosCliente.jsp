<%-- 
    Document   : ModificarDatosCliente
    Created on : 28 oct. 2022, 16:31:37
    Author     : Estadm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar datos cliente</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
    </head>
    
    <body>
    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card mt-8 text-center">
                <div class="card-header">
                    <h1>Ingrese el dato o datos a modificar</h1>
                    <div>
                        <img src="../imagenesVista/Imagen5.png"/>
                    </div>
                </div>
                <br>
                <form method="post" action="<c:url value="/vistaWeb/ModificarDatosCliente"/>" >
                    <div class="form-group">
                        <label>ID</label>
                        <input type="text" class "form-control" name = "id" placeholder="ID" required="Debe ingresar el ID">
                    </div>
                    <div class="form-group">
                        <label>Nombre</label>
                        <input type="text" class "form-control" name = "nombre" placeholder="Nombre">
                    </div>
                    <div class="form-group">
                        <label>Apellidos</label>
                        <input type="text" class "form-control" name = "apellidos" placeholder="Primer apellido">
                    </div>
                    <div class="form-group">
                        <button type="submit" class= "btn btn-primary">
                            Modificar
                        </button>
                        <a href="../index.html">Cancelar</a>
                        
                    </div>
                </form>
            </div>
        </div>
    </div>
    </body>
</html>
