

package accesoABaseDeDatos;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;

import static org.neo4j.driver.Values.parameters;

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
    /*
    public static void main(String... args) throws Exception {
        try (HelloWorldExample greeter = new HelloWorldExample("bolt://localhost:7687", "neo4j", "1234")) {
            greeter.printGreeting("hello, world");
        }
    }*/
}

