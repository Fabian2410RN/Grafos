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
 * @author Estadm
 */
@WebServlet(name = "ControladorRegistroCompras", urlPatterns = {"/vistaWeb/RegistroCompras"})
public class ControladorRegistroCompras extends HttpServlet {
    private int cantidadDeIntentos = 0;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getRequestDispatcher("RegistroCompras.jsp").forward(request, response);
       
    }

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String cantProductos = request.getParameter("cantProductos");
        int cantPro = Integer.parseInt(cantProductos);
        
        
        System.out.println("Cantidad de entradas "+cantidadDeIntentos);
        
        if (cantidadDeIntentos != 0){
            String [] arregloProductos = new String[cantPro];
            for(int i = 0; i < cantPro; i++){
                String quitarNull = palabraEliminar(request.getParameter("producto"+i), "null");
                
                arregloProductos[i] += quitarNull;
                System.out.println(arregloProductos[i]);
            }
        }
        cantidadDeIntentos++;
        
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("RegistroCompras.jsp").forward(request, response);
       
    }
    public static String  palabraEliminar(String oracion,String palabra) {
    String nuevaPalabra;
    if(oracion.contains(palabra)){
        nuevaPalabra = oracion.replace(palabra, "");
        return nuevaPalabra;
        }
    return oracion;
}
}
