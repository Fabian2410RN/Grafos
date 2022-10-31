<%-- 
    Document   : buscarCliente
    Created on : Oct 30, 2022, 2:55:54 PM
    Author     : Agustin Arias
--%>
<%@ page import="accesoABaseDeDatos.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String mensaje = "<script text/javascript>alert('Esto se debe de mostrar en el msgbox');</script>"; 

%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de compra</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
    </head>
    
    <body>
    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card mt-8 text-center">
                <div class="card-header">
                    <h1>Buscar compras de cliente</h1>
                    <div>
                        <img src="../imagenesVista/Imagen5.png"/>
                    </div>
                </div>
                <br>
              
                    
                     <form method="post" action="<c:url value="/vistaWeb/buscarCliente"/>" >
                    <div class="form-group">
                        <label>Nombre</label>
                        <input type="text" class "form-control" name = "nombre" placeholder="nombreCliente" required="El campo esta vacío">
                    </div>
                   <div class="form-group">
                        <label>Apellido</label>
                        <input type="text" class "form-control" name = "apellido" placeholder="apellidoCliente" required="El campo esta vacío">
                    </div>
                    <div class="form-group">
                        <button type="submit" class= "btn btn-primary">
                            Ver compras del cliente
                        </button>
                        <br>
                        <br>
                        <%
                            
                            ConexionBaseDeDatosNeo4j db = new ConexionBaseDeDatosNeo4j();
                            //int contador = 0;
                            String nombreC = request.getParameter("nombre");
                            String apellidoC = request.getParameter("apellido");
                            System.out.println(nombreC);
                            System.out.println(apellidoC);
                            if(nombreC == null && apellidoC == null)
                            {
                                System.out.println("NO");
                                //contador++;
                            }
                            else
                            {
                                 int cant = db.cantidadComprasDeCliente(nombreC, apellidoC);
                                 String [] arregloNombre = new String[cant];
                                 String [] arregloCantidad = new String[cant];
                                 arregloNombre = db.getNombreProductosCliente(nombreC, apellidoC);
                                 arregloCantidad = db.getCantidadProductosCliente(nombreC, apellidoC);
                                 for(int i=0; i< cant; i++){
                                 out.println("<br><tbody>" + arregloNombre[i] + "<p>" + "Cantidad: " + arregloCantidad[i] + "</p>" + "</tbody><br>");
                            }
                            
                            
                            }
                            
                            
                            
                        %>
                    </div>
                    <br>
                    <br>
                </form>
            </div>
        </div>
    </div>
    </body>
</html>
