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
@WebServlet(name = "ControladorEliminarNodoProductoConSinRelacion", urlPatterns = {"/vistaWeb/EliminarNodoProductoConSinRelacion"})
public class ControladorEliminarNodoProductoConSinRelacion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("EliminarNodoProductoConSinRelacion.jsp").forward(request, response);
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        int idEntero = (Integer.parseInt(id));
        //int obtenerValorDeVerdad;
        
        ConexionBaseDeDatosNeo4j eliminarNodoYRelacion = new ConexionBaseDeDatosNeo4j();
        eliminarNodoYRelacion.eliminarRelacionNodoProductoConMarcaYCompra(idEntero);
        eliminarNodoYRelacion.eliminarNodoProducto(idEntero);

        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("EliminarNodoProductoConSinRelacion.jsp").forward(request, response);
       
    }
}
