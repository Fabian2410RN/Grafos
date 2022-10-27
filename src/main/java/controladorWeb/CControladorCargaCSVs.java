package controladorWeb;


import accesoABaseDeDatos.HelloWorldExample;
import accesoABaseDeDatos.HelloWorldExample;
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
        //ConexionBaseNeo4j conexion1 = new ConexionBaseNeo4j();
        //Connection bd = conexion1.conexion();
        HelloWorldExample greeter = new HelloWorldExample("bolt://localhost:7687", "neo4j", "1234");
        greeter.printGreeting("Hola");
        System.out.println("Hecho");
        /*
        String cargarCSVClientes;
        String cargarCSVCompras;
        String cargarCSVMarcas;
        String cargarCSVProductos;
        //nuevoNodo = "Create(c:Cliente{id: '33', first_name: 'Carlos', last_name: '"+nombre+"'})";
        
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
        }*/
        request.getRequestDispatcher("CargarCSVs.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
        }   

}
