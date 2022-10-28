<%-- 
    Document   : MenuMantenimientoClientes
    Created on : 28 oct. 2022, 12:17:09
    Author     : Estadm
--%>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>

        <div class="box" style="text-align: center">
            <br>
            <br>
            <h1>Bienvenido al mantenimiento de clientes</h1>
            <br>
            <div class="row center-text">

                <div class="col">
                    <br>
                    <br>
                    <div>
                        <img src="imagenesVista/1.png"/>
                    </div>
                    <br>
                    <br>
                    <a class="btn btn-secondary" href="RegistrarNuevoCliente.jsp"> Registrar nuevo cliente</a>
                </div>
                <div class="col">
                    <br>
                    <br>
                    <div>
                        <img src="imagenesVista/2.png"/>
                    </div>
                    <br>
                    <br>
                    <a class="btn btn-secondary" href="vistaWeb/RegistroClientes.jsp"> Modificar datos de cliente</a>
                </div>
                <div class="col">
                    <br>
                    <br>
                    <div>
                        <img src="imagenesVista/3.png"/>
                    </div>
                    <br>
                    <br>
                    <a class="btn btn-secondary" href="vistaWeb/SeleccionDeDeposito.jsp"> Eliminar cliente</a>
                </div>
            </div>
            <br>
        </div>
    </head>
</html>
