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
@WebServlet(name = "ControladorRegistrarNuevoProducto", urlPatterns = {"/vistaWeb/RegistrarNuevoProducto"})
public class ControladorRegistrarNuevoProducto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("RegistrarNuevoProducto.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = 61;
        String nombre = request.getParameter("nombre");
        String marca = request.getParameter("marca");
        String precio = request.getParameter("precio");
        
        ConexionBaseDeDatosNeo4j nuevoNodo = new ConexionBaseDeDatosNeo4j();
        nuevoNodo.crearNuevoNodoProducto(id, nombre, marca, precio);
               
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("RegistrarNuevoProducto.jsp").forward(request, response);
        
        response.setContentType("text/html;charset=UTF-8");
    }

}
