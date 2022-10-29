<%-- 
    Document   : menuConsultas
    Created on : Oct 29, 2022, 12:50:43 AM
    Author     : Agustin Arias
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
            <h1>Bienvenido al apartado de consultas</h1>
            <br>
            <div class="row center-text">

                <div class="col">
                    <br>
                    <br>
                    <div>
                    </div>
                    <br>
                    <br>
                    <a class="btn btn-secondary" href="ProductosMasVendidos.jsp">Top 5 productos más vendidos</a>
                </div>
                <div class="col">
                    <br>
                    <br>
                    <div>
                    </div>
                    <br>
                    <br>
                    <a class="btn btn-secondary" href="MarcasMasVendidas.jsp">Top 5 marcas más vendidos</a>
                </div>
                <div class="col">
                    <br>
                    <br>
                    <div>
                    </div>
                    <br>
                    <br>
                    <a class="btn btn-secondary" href="ClientesConMasCompras.jsp">Top 5 clientes con mas compras</a>
                </div>
                <div class="col">
                    <br>
                    <br>
                    <div>
                    </div>
                    <br>
                    <br>
                    <a class="btn btn-secondary" href="ComprasDeUnCliente.jsp">Consultar compras de un cliente</a>
                </div>
            </div>
            <br>
        </div>
    </head>
</html>

