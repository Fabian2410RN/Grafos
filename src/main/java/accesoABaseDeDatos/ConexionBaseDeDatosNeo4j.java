

package accesoABaseDeDatos;


import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;

import static org.neo4j.driver.Values.parameters;
import static scala.concurrent.Await.result;

public class ConexionBaseDeDatosNeo4j implements AutoCloseable {
    private final Driver driver;
    
    public ConexionBaseDeDatosNeo4j(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    @Override
    public void close() throws RuntimeException {
        driver.close();
    }

    @SuppressWarnings("deprecation")
    public void cargarCSVs() {
        try (Session session = driver.session()) {
        session.run("LOAD CSV WITH HEADERS FROM 'file:///C:/CSV/Clientes.csv' AS clientes create (c1:Cliente {id: clientes.id, first_name: clientes.first_name, last_name: clientes.last_name})");
        session.run("LOAD CSV WITH HEADERS FROM 'file:///C:/CSV/Compras.csv' AS compras create (c2:Compra {idCliente: compras.idCliente, idProducto: compras.idProducto, cantidad: compras.cantidad})");
        session.run("LOAD CSV WITH HEADERS FROM 'file:///C:/CSV/Marcas.csv' AS marcas create (c3:Marca {id: marcas.id, nombre: marcas.nombre, pais: marcas.pais})");
        session.run("LOAD CSV WITH HEADERS FROM 'file:///C:/CSV/Productos.csv' AS productos create (c4:Producto {id: productos.id, nombre: productos.nombre, marca: productos.marca, precio: productos.precio})");
        
        //Relaciones
        session.run("LOAD CSV WITH HEADERS FROM 'file:///C:/CSV/Clientes.csv' AS row MATCH (a:Cliente {id: row.id}) MATCH (b:Compra {idCliente: row.id}) MERGE (a)-[:realiza_una]->(b) RETURN *;");
        session.run("LOAD CSV WITH HEADERS FROM 'file:///C:/CSV/Compras.csv' AS row MATCH (a:Compra {idProducto: row.idProducto}) MATCH (b:Producto {id: row.idProducto}) MERGE (a)-[:incluye_un]->(b) RETURN *;");
        session.run("LOAD CSV WITH HEADERS FROM 'file:///C:/CSV/Productos.csv' AS row MATCH (a:Producto {marca: row.marca}) MATCH (b:Marca {nombre: row.marca})MERGE (a)-[:producido_por]->(b)RETURN *;");
        
        }
    }
    
    public void crearNuevoNodoCliente(int id, String nombre, String apellidos) {
        try (Session session = driver.session()) {
            session.run("Create(c:Cliente{id: '"+id+"', first_name: '"+nombre+"', last_name: '"+apellidos+"'})");
        }
    }
    
    public int obtenerCantidadDeClientes() {
        int cantFinal = 0;
        try (Session session = driver.session()) {
            org.neo4j.driver.Record cantClientes = null;
            Result result = session.run("MATCH (n:Cliente) RETURN count(n) AS count");
            System.out.println("Imprimiendo numeros");
            cantClientes = result.next();
            int cant = cantClientes.get("count").asInt();
            System.out.println(cant);
            /*
            while (result.hasNext())
            {
                org.neo4j.driver.Record record = result.next();
                //System.out.println(record.get("id").asString());
                int cant = record.get("id").asInt();
                //cantFinal = (Integer.parseInt(cant));
            }
            */
            
            return cant+1;
            //System.out.println(result);
            //String dd = result.toString();
            //int cantFinal = (Integer.parseInt(dd));
            //return cantFinal+1;
        }
    }
    /*
    public static void main(String... args) throws Exception {
        try (HelloWorldExample greeter = new HelloWorldExample("bolt://localhost:7687", "neo4j", "1234")) {
            greeter.printGreeting("hello, world");
        }
    }*/
}


