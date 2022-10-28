

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
    public void cargarCSVs(final String message) {
        try (Session session = driver.session()) {
<<<<<<< Updated upstream:src/main/java/accesoABaseDeDatos/ConexionBaseDeDatosNeo4j.java
        session.run("LOAD CSV WITH HEADERS FROM 'file:///C:/Users/Estadm/Downloads/CSV/Clientes.csv' AS clientes create (c1:Cliente {id: clientes.id, first_name: clientes.first_name, last_name: clientes.last_name})");
        session.run("LOAD CSV WITH HEADERS FROM 'file:///C:/Users/Estadm/Downloads/CSV/Compras.csv' AS compras create (c2:Compra {idCliente: compras.idCliente, idProducto: compras.idProducto, cantidad: compras.cantidad})");
        session.run("LOAD CSV WITH HEADERS FROM 'file:///C:/Users/Estadm/Downloads/CSV/Marcas.csv' AS marcas create (c3:Marca {id: marcas.id, nombre: marcas.nombre, pais: marcas.pais})");
        session.run("LOAD CSV WITH HEADERS FROM 'file:///C:/Users/Estadm/Downloads/CSV/Productos.csv' AS productos create (c4:Producto {id: productos.id, nombre: productos.nombre, marca: productos.marca, precio: productos.precio})");
  
=======
        session.run("LOAD CSV WITH HEADERS FROM 'file:///C:/Users/Agustin%20Arias/Desktop/Clientes.csv' AS clientes create (c1:Cliente {id: clientes.id, first_name: clientes.first_name, last_name: clientes.last_name})");
        session.run("LOAD CSV WITH HEADERS FROM 'file:///C:/Users/Agustin%20Arias/Desktop/Compras.csv' AS compras create (c2:Compra {idCliente: compras.idCliente, idProducto: compras.idProducto, cantidad: compras.cantidad})");
        session.run("LOAD CSV WITH HEADERS FROM 'file:///C:/Users/Agustin%20Arias/Desktop/Marcas.csv' AS marcas create (c3:Marca {id: marcas.id, nombre: marcas.nombre, pais: marcas.pais})");
        session.run("LOAD CSV WITH HEADERS FROM 'file:///C:/Users/Agustin%20Arias/Desktop/Productos.csv' AS productos create (c4:producto {id: productos.id, nombre: productos.nombre, marca: productos.marca, precio: productos.precio})");
        session.run("LOAD CSV WITH HEADERS FROM 'file:///C:/Users/Agustin%20Arias/Desktop/Productos.csv' AS row MATCH (a:producto {marca: row.marca})MATCH (b:Marca {nombre: row.marca})MERGE (a)-[:producido_por]->(b)RETURN *;");
        //System.out.println(cargarCSVClientes);
>>>>>>> Stashed changes:src/main/java/accesoABaseDeDatos/HelloWorldExample.java
        }
    }
    /*
    public static void main(String... args) throws Exception {
        try (HelloWorldExample greeter = new HelloWorldExample("bolt://localhost:7687", "neo4j", "1234")) {
            greeter.printGreeting("hello, world");
        }
    }*/
}


