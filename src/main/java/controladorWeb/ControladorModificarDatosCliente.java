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
@WebServlet(name = "ControladorModificarDatosCliente", urlPatterns = {"/vistaWeb/ModificarDatosCliente"})
public class ControladorModificarDatosCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getRequestDispatcher("ModificarDatosCliente.jsp").forward(request, response);
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        int idN = (Integer.parseInt(id));
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        
        ConexionBaseDeDatosNeo4j modificarNodo = new ConexionBaseDeDatosNeo4j();
        
        if("".equals(nombre) && "".equals(apellidos)){
            System.out.println("Escriba un campo");
        }
        else if("".equals(nombre)){
            modificarNodo.modificarApellidosCliente(idN, apellidos);
        }
        else if("".equals(apellidos)){
            modificarNodo.modificarNombreCliente(idN, nombre);
        }
        else{
            modificarNodo.modificarApellidosCliente(idN, apellidos);
            modificarNodo.modificarNombreCliente(idN, nombre);
        }
        
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("ModificarDatosCliente.jsp").forward(request, response);
        
    }
}
