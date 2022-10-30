/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Agustin Arias
 */
        /*
@WebServlet(name = "ControladorProductosMasVendidos", urlPatterns = {"/vistaWeb/ProductosMasVendidos"})
public class ControladorProductosMasVendidos extends HttpServlet {
private int cantidadDeIntentos;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entrando Do get");
        
        cantidadDeIntentos++;
        
            if(cantidadDeIntentos == 1){
                ConexionBaseDeDatosNeo4j greeter = new ConexionBaseDeDatosNeo4j();
                greeter.getProductosMasVendidos();
                greeter.close();

            }else{
                System.out.println("Nada que hacer");
            }
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("ProductosMasVendidos.jsp").forward(request, response);
    }
    }

}*/
