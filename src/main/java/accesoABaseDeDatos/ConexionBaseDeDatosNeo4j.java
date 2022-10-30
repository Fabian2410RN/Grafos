

package accesoABaseDeDatos;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;

import static org.neo4j.driver.Values.parameters;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import static scala.concurrent.Await.result;

public class ConexionBaseDeDatosNeo4j implements AutoCloseable {
    final private Driver driver;
    
    public ConexionBaseDeDatosNeo4j() {
        String uri = "bolt://localhost:7687";
        String user = "neo4j";
        String password = "12345";
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
    
    //CREAR NODOS CLIENTES Y PRODUCTOS
    public void crearNuevoNodoCliente(int id, String nombre, String apellidos) {
        try (Session session = driver.session()) {
            session.run("Create(c:Cliente{id: '"+id+"', first_name: '"+nombre+"', last_name: '"+apellidos+"'})");
        }
    }
    
    public void crearNuevoNodoProducto(int id, String nombre, String marca, String precio) {
        try (Session session = driver.session()) {
            session.run("Create(c4:Producto{id: '"+id+"', nombre: '"+nombre+"', marca: '"+marca+"', precio: '"+precio+"'})");
        }
    }
    
    //MODIFICAR CLIENTES Y PRODUCTOS
    public void modificarNombreCliente(int id, String nombre) {
        try (Session session = driver.session()) {
            session.run("match (n:Cliente) where n.id = '"+id+"' set n.first_name = '"+nombre+"'");
        }
    }
    
    public void modificarApellidosCliente(int id, String apellidos) {
        //id = (Integer.parseInt(id));
        try (Session session = driver.session()) {
            session.run("match (n:Cliente) where n.id = '"+id+"' set n.last_name = '"+apellidos+"'");
        }
    }
    
    public void modificarNombreProducto(int id, String nombre) {
        try (Session session = driver.session()) {
            session.run("match (n:Producto) where n.id = '"+id+"' set n.nombre = '"+nombre+"'");
        }
    }
    
    public void modificarMarcaProducto(int id, String marca) {
        try (Session session = driver.session()) {
            session.run("match (n:Producto) where n.id = '"+id+"' set n.marca = '"+marca+"'");
        }
    }
    
    public void modificarPrecioProducto(int id, String precio) {
        try (Session session = driver.session()) {
            session.run("match (n:Producto) where n.id = '"+id+"' set n.precio = '"+precio+"'");
        }
    }
    
    //MÉTODOS DE VERIFICACIÓN
    public int verificarRelacionNodoCliente(int id) {
        try (Session session = driver.session()) {
            //org.neo4j.driver.Record valor = null;
            int contador = 0;
            Result result = session.run("match (c:Cliente {id:'"+id+"'})-->(n) return n;");
            while (result.hasNext())
            {
                org.neo4j.driver.Record record = result.next();
                //System.out.println(record.get("id").asString());
                contador += 1;
                //cantFinal = (Integer.parseInt(cant));
            }
            if(contador != 0){
             return 0;
            }
        }
        return 1;
    }
    
    public int verificarRelacionNodoProducto(int id) {
        try (Session session = driver.session()) {
            //org.neo4j.driver.Record valor = null;
            int contador = 0;
            Result result = session.run("match (c:Producto {id:'"+id+"'})-->(n) return n;");
            while (result.hasNext())
            {
                org.neo4j.driver.Record record = result.next();
                //System.out.println(record.get("id").asString());
                contador += 1;
                //cantFinal = (Integer.parseInt(cant));
            }
            if(contador != 0){
             return 0;
            }
        }
        return 1;
    }
    
    public Boolean verificarSiExisteNodoCliente(String nombre, String apellido) {
        int contador = 0;
        
        try (Session session = driver.session()){
            Result rs = session.run("match (c:Cliente {first_name: '"+nombre+"', last_name:'"+apellido+"'}) return c");
            while(rs.hasNext()){
                org.neo4j.driver.Record record = rs.next();
                //System.out.println(rs.("c.first_name"));
                contador ++;
            }    
            return contador != 0;
        }
        
    }
    
    public Boolean verificarSiExisteNodoProducto(String nombre) {
        int contador = 0;
        try (Session session = driver.session()){
            Result rs = session.run("match (c:Producto {nombre: '"+nombre+"'}) return c");
            while(rs.hasNext()){
                org.neo4j.driver.Record record = rs.next();
                //System.out.println(rs.("c.first_name"));
                contador ++;
            }    
            return contador != 0;
        }
    }
    
    //ELIMINAR RELACIONES O NODOS
    public void eliminarRelacionNodoClienteConCompras(int id) {
        try (Session session = driver.session()) {
            
            session.run("match (n:Cliente {id: '"+id+"'})-[r:realiza_una]-(co:Compra) delete r");
            
            session.run("match (n:Cliente) where n.id = '"+id+"' delete n");
            
        }
        System.out.println("Nodo eliminado");
    }
    
    public void eliminarRelacionNodoProductoConMarcaYCompra(int id) {
        try (Session session = driver.session()) {
            session.run("match (c:Producto {id: '"+id+"'})-[r:producido_por]-(co:Marca) delete r");
            session.run("match (c:Producto {id: '"+id+"'})-[r:incluye_un]-(co:Compra) delete r");
            session.close();
        }
        System.out.println("Relaciones eliminada");
    }
    
    public void eliminarNodoProducto(int id) {
        try (Session session = driver.session()) {
           session.run("match (n:Producto) where n.id = '"+id+"' delete n");
            
        }
        System.out.println("Nodo eliminado");
    }
    
    
    
    //
    public int obtenerCantidadDeClientes() {
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
    /*
    public List<String> getProductosMasVendidos() {
    try (Session session = driver.session()) {
        return session.readTransaction(tx -> {
            List<String> productos = new ArrayList<>();
            Result result = tx.run("MATCH (a:Compra)-[r1:incluye_un]->(b:producto)RETURN b.nombre,sum(toInteger(a.cantidad)) as  cantidadVendida ORDER BY cantidadVendida  DESC limit 5");
            while (result.hasNext()) {
                productos.add(result.next().get(0).asString());
            }
            return productos;
        });
    }*/
    
    public static void main(String[] args) throws Exception {
       ConexionBaseDeDatosNeo4j csv = new ConexionBaseDeDatosNeo4j();
       String nombre = "Crush";
       String apellido = "gdgd";
       boolean trueFalse = csv.verificarSiExisteNodoProducto(nombre);
       System.out.println(trueFalse);
    }
    
}



