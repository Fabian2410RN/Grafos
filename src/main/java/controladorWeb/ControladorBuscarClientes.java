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
 * @author Agustin Arias
 */
@WebServlet(name = "ControladorBuscarClientes", urlPatterns = {"/vistaWeb/buscarCliente"})
public class ControladorBuscarClientes extends HttpServlet {
    private int cantidadDeIntentos = 0;
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("buscarCliente.jsp").forward(request, response);
    }
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        ConexionBaseDeDatosNeo4j verificarExisteNodo = new ConexionBaseDeDatosNeo4j();
        int cantPro = verificarExisteNodo.cantidadComprasDeCliente(nombre,apellidos);
        
        if (cantidadDeIntentos != 0){
            if(verificarExisteNodo.verificarSiExisteNodoCliente(nombre, apellidos)){
                
                //
                String [] arregloProductos = new String[cantPro];
                String [] arregloCantUnidades = new String[cantPro];
                for(int i = 0; i < cantPro; i++){
                    arregloProductos[i] = request.getParameter("producto"+i);
                    arregloCantUnidades[i] = request.getParameter("unidad"+i);
                    if(verificarExisteNodo.verificarSiExisteNodoProducto(arregloProductos[i])){
                        System.out.println("Funciona");
                    } else{
                        request.getRequestDispatcher("RegistroCompras.jsp").forward(request, response);
                    }
                    //String quitarNull = palabraEliminar(arregloProductos[i], "null");
                    //arregloProductosN[i] += 
                    System.out.println(arregloProductos[i]);
                    System.out.println(arregloCantUnidades[i]);
                }
                
            }else{
                //response.sendRedirect("MensajeFalloClienteNoExiste.jsp");
                System.out.println("El cliente no existe");
                request.getRequestDispatcher("buscarCliente.jsp").forward(request, response);
            }
            
        }
        
        cantidadDeIntentos++;
        
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("buscarCliente.jsp").forward(request, response);
       
    }

    
}
