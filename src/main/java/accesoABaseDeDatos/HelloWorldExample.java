

package accesoABaseDeDatos;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;

import static org.neo4j.driver.Values.parameters;

public class HelloWorldExample implements AutoCloseable {
    private final Driver driver;
    
    public HelloWorldExample(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    @Override
    public void close() throws RuntimeException {
        driver.close();
    }

    @SuppressWarnings("deprecation")
    public void printGreeting(final String message) {
        try (Session session = driver.session()) {
        session.run("LOAD CSV WITH HEADERS FROM 'file:///C:/Users/Estadm/Downloads/CSV/Clientes.csv' AS clientes create (c1:Cliente {id: clientes.id, first_name: clientes.first_name, last_name: clientes.last_name})");
        session.run("LOAD CSV WITH HEADERS FROM 'file:///C:/Users/Estadm/Downloads/CSV/Compras.csv' AS compras create (c2:Compra {idCliente: compras.idCliente, idProducto: compras.idProducto, cantidad: compras.cantidad})");
        session.run("LOAD CSV WITH HEADERS FROM 'file:///C:/Users/Estadm/Downloads/CSV/Marcas.csv' AS marcas create (c3:Marca {id: marcas.id, nombre: marcas.nombre, pais: marcas.pais})");
        session.run("LOAD CSV WITH HEADERS FROM 'file:///C:/Users/Estadm/Downloads/CSV/Productos.csv' AS productos create (c4:producto {id: productos.id, nombre: productos.nombre, marca: productos.marca, precio: productos.precio})");
        //System.out.println(cargarCSVClientes);
        }
    }
    /*
    public static void main(String... args) throws Exception {
        try (HelloWorldExample greeter = new HelloWorldExample("bolt://localhost:7687", "neo4j", "1234")) {
            greeter.printGreeting("hello, world");
        }
    }*/
}

/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.neo4j.driver.GraphDatabase;
/**
 *
 * @author Estadm
 */
//fabian
//agdsgdsgdg
//dfdf
/*

public class ConexionBaseNeo4j {
    
    public Connection conexion(){
        Connection con = null;
        try{
            //System.out.println("Estoy entrando en el try");
            Class.forName("org.neo4j.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:neo4j:bolt://localhost:7687","neo4j","1234");
            //GraphDatabase.driver("neo4j://graph.example.com:7687", auth);
            System.out.println("Conexion establecida");
        } catch(ClassNotFoundException | SQLException e){
            System.out.println("No se pudo conectar nnn");
            JOptionPane.showInputDialog(null, "ERROR DE: "+ e);
        }
        return con;
    }
    
    //public static void main(String[] args) throws Exception {
    //    ConexionBaseNeo4j cc = new ConexionBaseNeo4j();
    //}
}
*/

