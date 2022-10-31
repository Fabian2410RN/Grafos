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
                    <h1>Clientes y producto común</h1>
                    <div>
                        <img src="../imagenesVista/Imagen5.png"/>
                    </div>
                </div>
                <br>
              
                    
                     <form method="post" action="<c:url value="/vistaWeb/ClienteProductoComun"/>" >
                    <div class="form-group">
                        <label>Producto</label>
                        <input type="text" class "form-control" name = "producto" placeholder="nombre del producto" required="El campo esta vacío">
                    </div>
                    <div class="form-group">
                        <button type="submit" class= "btn btn-primary">
                            Ver quienes más han comprado el producto
                        </button>
                        <br>
                        <br>
                        <%
                            
                            ConexionBaseDeDatosNeo4j db = new ConexionBaseDeDatosNeo4j();
                            //int contador = 0;
                            String producto = request.getParameter("producto");
                       
                            if(producto == null)
                            {
                                System.out.println("NO");
                                //contador++;
                            }
                            else
                            {
                                 int cant = db.cantidadProductoComun(producto);
                                 String [] arregloNombre = new String[cant];
                                 arregloNombre = db.getNombreClientesComun(producto);
                                 for(int i=0; i< cant; i++){
                                 out.println("<br><tbody>" +"Nombre: " + arregloNombre[i] + "<p>"+ "</p>" + "</tbody><br>");
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
