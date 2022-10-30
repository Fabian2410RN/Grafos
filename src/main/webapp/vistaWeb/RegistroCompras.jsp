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
        <title>Registro de compra</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
    </head>
    
    <body>
    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card mt-8 text-center">
                <div class="card-header">
                    <h1>Registro de compra</h1>
                    <div>
                        <img src="../imagenesVista/Imagen5.png"/>
                    </div>
                </div>
                <br>
                <form method="post" action="<c:url value="/vistaWeb/RegistroCompras"/>" >
                    <div class="form-group">
                        <label>Nombre</label>
                        <input type="text" class "form-control" name = "nombre" placeholder="Nombre">
                    </div>
                    <div class="form-group">
                        <label>Apellidos</label>
                        <input type="text" class "form-control" name = "apellidos" placeholder="Primer apellido">
                    </div>
                    
                    <div class="form-group">
                        <label>Cantidad productos</label>
                        <input type="text" class "form-control" name = "cantProductos" placeholder="Cantidad productos" required="El campo esta vacío">
                    </div>
                    <div class="form-group">
                        <button type="submit" class= "btn btn-primary">
                            Agregar cantidad productos
                        </button>
                        <br>
                        <br>
                        <%
                            String cant = request.getParameter("cantProductos");
                            if( cant != null){
                                int cantProductosInt = Integer.parseInt(cant);
                                for(int j = 0; j < cantProductosInt; j++){
                                    int valor = j+1;
                                    out.println("<div class=form-group> <label>Producto"+valor+"</label> <input type=text class form-control name = producto"+j+" placeholder=Producto"+valor+"> </div>");
                                }
                            }
                        %>
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
