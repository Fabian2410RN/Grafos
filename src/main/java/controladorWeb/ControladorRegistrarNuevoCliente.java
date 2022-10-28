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
@WebServlet(name = "ControladorRegistrarNuevoCliente", urlPatterns = {"/vistaWeb/RegistrarNuevoCliente"})
public class ControladorRegistrarNuevoCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getRequestDispatcher("RegistrarNuevoCliente.jsp").forward(request, response);
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        
        ConexionBaseDeDatosNeo4j nuevoNodo = new ConexionBaseDeDatosNeo4j("bolt://localhost:7687", "neo4j", "1234");
        id = nuevoNodo.obtenerCantidadDeClientes();
        nuevoNodo.crearNuevoNodoCliente(id, nombre, apellidos);
               
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("RegistrarNuevoCliente.jsp").forward(request, response);
    }
}
