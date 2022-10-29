/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import accesoABaseDeDatos.ConexionBaseDeDatosNeo4j;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Estadm
 */
@WebServlet(name = "ControladorModificarDatosProducto", urlPatterns = {"/vistaWeb/ModificarDatosProducto"})
public class ControladorModificarDatosProducto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("ModificarDatosProducto.jsp").forward(request, response);
       
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        int idN = (Integer.parseInt(id));
        String nombre = request.getParameter("nombre");
        String marca = request.getParameter("marca");
        String precio = request.getParameter("precio");
        
        ConexionBaseDeDatosNeo4j modificarNodo = new ConexionBaseDeDatosNeo4j();
        
        if("".equals(nombre) && "".equals(marca) && "".equals(precio)){
            System.out.println("Escriba un campo");
        }
        else if("".equals(nombre) && "".equals(marca)){
            modificarNodo.modificarPrecioProducto(idN, precio);
        }
        else if("".equals(marca) && "".equals(precio)){
            modificarNodo.modificarNombreProducto(idN, nombre);
        }
        else if("".equals(precio) && "".equals(nombre)){
            modificarNodo.modificarMarcaProducto(idN, marca);
        }
        else if("".equals(nombre)){
            modificarNodo.modificarMarcaProducto(idN, marca);
            modificarNodo.modificarPrecioProducto(idN, precio);
        }
        else if("".equals(marca)){
            modificarNodo.modificarNombreProducto(idN, nombre);
            modificarNodo.modificarPrecioProducto(idN, precio);
        }
        else if("".equals(precio)){
            modificarNodo.modificarNombreProducto(idN, nombre);
            modificarNodo.modificarMarcaProducto(idN, marca);
        }
        else{
            modificarNodo.modificarNombreProducto(idN, nombre);
            modificarNodo.modificarMarcaProducto(idN, marca);
            modificarNodo.modificarPrecioProducto(idN, precio);
        }
        
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("ModificarDatosProducto.jsp").forward(request, response);
       
    }
}
