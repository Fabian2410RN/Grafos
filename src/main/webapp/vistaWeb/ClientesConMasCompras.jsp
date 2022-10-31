<%-- 
    Document   : clientesMasConMasCompras
    Created on : Oct 29, 2022, 1:04:03 AM
    Author     : Agustin Arias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="accesoABaseDeDatos.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de cliente</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
    </head>
    
    <body>
    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card mt-8 text-center">
                <div class="card-header">
                    <h1>Top 5 clientes con más compras</h1>
                </div>
                <br>
                    <%
                           ConexionBaseDeDatosNeo4j db = new ConexionBaseDeDatosNeo4j();
                           int cant = db.cantidadConsultaProductosMasVendidos();
                           String [] arregloNombres = new String[cant];
                           int [] arregloCantidad = new int[cant];
                           arregloNombres = db.getNombreClientesConMasCompras();
                           arregloCantidad = db.cantidadClientesMasCompras();
                           for(int i=0; i< cant; i++){
                                out.println("<br><tbody>" + "Nombre completo: " + arregloNombres[i] + "<p>" + "Cantidad de compras: " + arregloCantidad[i] + "</p>" + "</tbody><br>");
                            }
                           
                    %>
                    
            </div>
        </div>
    </div>
    </body>
</html>
</html>