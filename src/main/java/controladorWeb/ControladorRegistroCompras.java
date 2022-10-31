/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import accesoABaseDeDatos.ConexionBaseDeDatosNeo4j;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        ConexionBaseDeDatosNeo4j verificarExisteNodo = new ConexionBaseDeDatosNeo4j();
        
        if (cantidadDeIntentos != 0){
            if(verificarExisteNodo.verificarSiExisteNodoCliente(nombre, apellidos)){
                //
                String [] arregloProductos = new String[cantPro];
                String [] arregloCantUnidades = new String[cantPro];
                for(int i = 0; i < cantPro; i++){
                    arregloProductos[i] = request.getParameter("producto"+i);
                    arregloCantUnidades[i] = request.getParameter("unidad"+i);
                    if(verificarExisteNodo.verificarSiExisteNodoProducto(arregloProductos[i])){
                        int idCliente = verificarExisteNodo.obtenerIDCliente(nombre, apellidos);
                        int idProducto = verificarExisteNodo.obtenerIDProducto(arregloProductos[i]);
                        int cantProducto = Integer.parseInt(arregloCantUnidades[i]);
                        int ultimaCompra = verificarExisteNodo.obtenerCantidadDeComprasConIdCompra();
                        verificarExisteNodo.crearNodoCompra(ultimaCompra,idCliente, idProducto, cantProducto);
                        verificarExisteNodo.crearRelacionClienteCompra(idCliente, ultimaCompra);
                        verificarExisteNodo.crearRelacionCompraProducto(ultimaCompra, idProducto);
                        System.out.println("Ultima compra: "+ ultimaCompra +" Id cliente: "+ idCliente +" Id producto: "+ idProducto +" Cantidad: "+ cantProducto);
                    } else{
                        request.getRequestDispatcher("RegistroCompras.jsp").forward(request, response);
                    }
                    //String quitarNull = palabraEliminar(arregloProductos[i], "null");
                    //arregloProductosN[i] += 
                    //System.out.println(arregloProductos[i]);
                    //System.out.println(arregloCantUnidades[i]);
                }
                
            }else{
                //response.sendRedirect("MensajeFalloClienteNoExiste.jsp");
                System.out.println("El cliente no existe");
                request.getRequestDispatcher("RegistroCompras.jsp").forward(request, response);
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
    public static void fff(){
        String mensajeRegistro = "<script text/javascript>alert('El cliente no existe');</script>"; 
          out.println(mensajeRegistro);
    }
            
    
}
