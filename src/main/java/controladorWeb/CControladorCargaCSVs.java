package controladorWeb;


import accesoABaseDeDatos.ConexionBaseDeDatosNeo4j;
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
<<<<<<< Updated upstream
        ConexionBaseDeDatosNeo4j greeter = new ConexionBaseDeDatosNeo4j("bolt://localhost:7687", "neo4j", "1234");
        greeter.cargarCSVs("Hola");
=======
        //ConexionBaseNeo4j conexion1 = new ConexionBaseNeo4j();
        //Connection bd = conexion1.conexion();
        HelloWorldExample greeter = new HelloWorldExample("bolt://localhost:7687", "neo4j", "12345");
        greeter.printGreeting("Hola");
>>>>>>> Stashed changes
        System.out.println("Hecho");
        
        request.getRequestDispatcher("CargarCSVs.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
        }   

}
