package controladorWeb;


import accesoABaseDeDatos.ConexionBaseNeo4j;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Estadm
 */
@WebServlet(name = "CControladorCargaCSVs", urlPatterns = {"/vistaWeb/CargarCSVs"})
public class CControladorCargaCSVs extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entrando Do get");
        ConexionBaseNeo4j conexion1 = new ConexionBaseNeo4j();
        Connection bd = conexion1.conexion();
        
        String cargarCSVClientes;
        String cargarCSVCompras;
        String cargarCSVMarcas;
        String cargarCSVProductos;
        //nuevoNodo = "Create(c:Cliente{id: '33', first_name: 'Carlos', last_name: '"+nombre+"'})";
        cargarCSVClientes = "LOAD CSV WITH HEADERS FROM 'file:///C:/Users/Estadm/Downloads/CSV/Clientes.csv' AS clientes create (c1:Cliente {id: clientes.id, first_name: clientes.first_name, last_name: clientes.last_name})";
        cargarCSVCompras = "LOAD CSV WITH HEADERS FROM 'file:///C:/Users/Estadm/Downloads/CSV/Compras.csv' AS compras create (c2:Compra {idCliente: compras.idCliente, idProducto: compras.idProducto, cantidad: compras.cantidad})";
        cargarCSVMarcas = "LOAD CSV WITH HEADERS FROM 'file:///C:/Users/Estadm/Downloads/CSV/Marcas.csv' AS marcas create (c3:Marca {id: marcas.id, nombre: marcas.nombre, pais: marcas.pais})";
        cargarCSVProductos = "LOAD CSV WITH HEADERS FROM 'file:///C:/Users/Estadm/Downloads/CSV/Productos.csv' AS productos create (c4:producto {id: productos.id, nombre: productos.nombre, marca: productos.marca, precio: productos.precio})";

        try{
            PreparedStatement csvClientes = bd.prepareStatement(cargarCSVClientes);
            PreparedStatement csvCompras = bd.prepareStatement(cargarCSVCompras);
            PreparedStatement csvMarcas = bd.prepareStatement(cargarCSVMarcas);
            PreparedStatement csvProductos = bd.prepareStatement(cargarCSVProductos);
            
            csvClientes.executeUpdate();
            csvCompras.executeUpdate();
            csvMarcas.executeUpdate();
            csvProductos.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println("Error");
        }
        request.getRequestDispatcher("CargarCSVs.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
        }   

}
