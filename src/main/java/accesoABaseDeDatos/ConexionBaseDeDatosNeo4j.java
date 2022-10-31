

package accesoABaseDeDatos;


import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;


public class ConexionBaseDeDatosNeo4j implements AutoCloseable {
    Driver driver;
    
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
    
    public void crearRelacionNodoClienteCompra(String nombreCliente, String nombreProducto) {
        try (Session session = driver.session()) {
            session.run("LOAD CSV WITH HEADERS FROM 'file:///C:/CSV/Clientes.csv' AS row MATCH (a:Cliente {id: row.id}) MATCH (b:Compra {idCliente: row.id}) MERGE (a)-[:realiza_una]->(b) RETURN *;");
        }
    }
    
    public void crearNodoCompra(int idCliente, int idProducto, int cantidad) {
        
        try (Session session = driver.session()) {
            session.run("Create (p:Compra {idCliente:'"+idCliente+"',idProducto:'"+idProducto+"',cantidad: '"+cantidad+"'})");
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
    
    public  int obtenerIDCliente(String nombre, String apellido) {
        //ConexionBaseDeDatosNeo4j csv = new ConexionBaseDeDatosNeo4j();
        
        //int contador = 0;
        //String [] arreglo = new String[1];
        try (Session session = driver.session()) {
            Result result = session.run("Match (c: Cliente {first_name: '"+nombre+"', last_name: '"+apellido+"'}) return c.id as id");
            org.neo4j.driver.Record record = result.next();
            String id = record.get("id").asString();
            int idConvertido = Integer.parseInt(id);
            System.out.println(id);
             /*
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                String nombreB = record.get("first_name").asString();
                //int cant = record.get("cantidadVendida").asInt();
                for(int i=0; i < 1 ; i++){
                    arreglo[contador] = nombreB;
                    System.out.println("Entrado al for");
                    System.out.println(arreglo[0]);
                    //contador++;
                }
                */
                //System.out.println(arreglo);
                //System.out.println(cant);
                //p.add(result.next().get(0).asString());
                return idConvertido;
            }
        
    }
    
    public  int obtenerIDProducto(String nombre) {
        try (Session session = driver.session()) {
            Result result = session.run("Match (c: Producto {nombre: '"+nombre+"'}) return c.id as id");
            org.neo4j.driver.Record record = result.next();
            String id = record.get("id").asString();
            int idConvertido = Integer.parseInt(id);
            System.out.println(id);
            return idConvertido;
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
    
    
    /*
    Métodos de la consulta 1
    */
     public  String[] getNombresProductosMasVendidos() {
        ConexionBaseDeDatosNeo4j csv = new ConexionBaseDeDatosNeo4j();
        
        int cantPro=csv.cantidadConsultaProductosMasVendidos();
        int contador = 0;
        String [] arreglo = new String[cantPro];
        try (Session session = driver.session()) {
        
            Result result = session.run("MATCH (a:Compra)-[r1:incluye_un]->(b:Producto) RETURN b.nombre as nombre,sum(toInteger(a.cantidad)) as  cantidadVendida ORDER BY cantidadVendida  DESC limit 5");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                String nombre = record.get("nombre").asString();
                int cant = record.get("cantidadVendida").asInt();
                for(int i=0; i < 1 ; i++){
                    arreglo[contador] = nombre;
                    System.out.println(arreglo[i]);
                    contador++;
                }
                
                //System.out.println(arreglo);
                //System.out.println(cant);
                //p.add(result.next().get(0).asString());
            }
            
        }
        return arreglo;
    }
     
     
       public  int[] getCantidadProductosMasVendidos() {
        ConexionBaseDeDatosNeo4j csv = new ConexionBaseDeDatosNeo4j();
        
        int cantPro=csv.cantidadConsultaProductosMasVendidos();
        int contador = 0;
        int [] arreglo = new int[cantPro];
        try (Session session = driver.session()) {
        
            Result result = session.run("MATCH (a:Compra)-[r1:incluye_un]->(b:Producto) RETURN b.nombre as nombre,sum(toInteger(a.cantidad)) as  cantidadVendida ORDER BY cantidadVendida  DESC limit 5");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                //String nombre = record.get("nombre").asString();
                int cant = record.get("cantidadVendida").asInt();
                for(int i=0; i < 1 ; i++){
                    arreglo[contador] = cant;
                    System.out.println(arreglo[i]);
                    contador++;
                }
                
                //System.out.println(arreglo);
                //System.out.println(cant);
                //p.add(result.next().get(0).asString());
            }
            
        }
        return arreglo;
    }
     
     
     public  int cantidadConsultaProductosMasVendidos() {
        int contador =0;
        try (Session session = driver.session()) {
        
            Result result = session.run("MATCH (a:Compra)-[r1:incluye_un]->(b:Producto) RETURN b.nombre as nombre,sum(toInteger(a.cantidad)) as  cantidadVendida ORDER BY cantidadVendida  DESC limit 5");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                contador++;
                //p.add(result.next().get(0).asString());
            }
        }
        return contador;
    }
    
     /*
    Métodos de la consulta 2
    */
     
     public  String[] getNombresMarcasMasVendidos() {
        ConexionBaseDeDatosNeo4j csv = new ConexionBaseDeDatosNeo4j();
        
        int cantPro=csv. cantidadConsultaMarcasMasVendidas();
        int contador = 0;
        String [] arreglo = new String[cantPro];
        try (Session session = driver.session()) {
        
            Result result = session.run("MATCH (a:Compra)-[r1:incluye_un]->(b:Producto)-[r2:producido_por]->(d:Marca) RETURN b.marca as Marca,d.pais as Pais,sum(toInteger(a.cantidad)) as  cantidadVendida ORDER BY cantidadVendida  DESC limit 5");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                String marca = record.get("Marca").asString();
                for(int i=0; i < 1 ; i++){
                    arreglo[contador] = marca;
                    System.out.println(arreglo[i]);
                    contador++;
                }
            }
            
        }
        return arreglo;
    }
    
     
    public  String[] getPaisMarcasMasVendidos() {
        ConexionBaseDeDatosNeo4j csv = new ConexionBaseDeDatosNeo4j();
        
        int cantPro=csv. cantidadConsultaMarcasMasVendidas();
        int contador = 0;
        String [] arreglo = new String[cantPro];
        try (Session session = driver.session()) {
        
            Result result = session.run("MATCH (a:Compra)-[r1:incluye_un]->(b:Producto)-[r2:producido_por]->(d:Marca) RETURN b.marca as Marca,d.pais as Pais,sum(toInteger(a.cantidad)) as  cantidadVendida ORDER BY cantidadVendida  DESC limit 5");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                String pais = record.get("Pais").asString();
                for(int i=0; i < 1 ; i++){
                    arreglo[contador] = pais;
                    System.out.println(arreglo[i]);
                    contador++;
                }
            }
            
        }
        return arreglo;
    }
     
     public  int cantidadConsultaMarcasMasVendidas() {
        int contador =0;
        try (Session session = driver.session()) {
        
            Result result = session.run("MATCH (a:Compra)-[r1:incluye_un]->(b:Producto)-[r2:producido_por]->(d:Marca) RETURN b.marca as Marca,d.pais as Pais,sum(toInteger(a.cantidad)) as  cantidadVendida ORDER BY cantidadVendida  DESC limit 5");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                contador++;
            }
        }
        return contador;
    }
     
     public  int[] getCantidadMarcasMasVendidos() {
        ConexionBaseDeDatosNeo4j csv = new ConexionBaseDeDatosNeo4j();
        
        int cantPro=csv. cantidadConsultaMarcasMasVendidas();
        int contador = 0;
        int [] arreglo = new int[cantPro];
        try (Session session = driver.session()) {
        
            Result result = session.run("MATCH (a:Compra)-[r1:incluye_un]->(b:Producto)-[r2:producido_por]->(d:Marca) RETURN b.marca as Marca,d.pais as Pais,sum(toInteger(a.cantidad)) as  cantidadVendida ORDER BY cantidadVendida  DESC limit 5");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                int cant = record.get("cantidadVendida").asInt();
                for(int i=0; i < 1 ; i++){
                    arreglo[contador] = cant;
                    System.out.println(arreglo[i]);
                    contador++;
                }
            }
            
        }
        return arreglo;
    }
     
      /*
    Métodos de la consulta 3
    */
     
      public  int cantidadConsultasClientesConMasCompras() {
        int contador =0;
        try (Session session = driver.session()) {
        
            Result result = session.run("MATCH (a:Cliente)-[r1:realiza_una]->(b:Compra) RETURN a.first_name +\" \" + a.last_name as nombreCompleto,sum(toInteger(b.cantidad)) as cantidadComprada ORDER BY cantidadComprada  DESC limit 5");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                contador++;
            }
        }
        return contador;
    }
      
      
      public  String[] getNombreClientesConMasCompras() {
        ConexionBaseDeDatosNeo4j csv = new ConexionBaseDeDatosNeo4j();
        
        int cantPro=csv. cantidadConsultasClientesConMasCompras();
        int contador = 0;
        String [] arreglo = new String[cantPro];
        try (Session session = driver.session()) {
        
            Result result = session.run("MATCH (a:Cliente)-[r1:realiza_una]->(b:Compra) RETURN a.first_name +\" \" + a.last_name as nombreCompleto,sum(toInteger(b.cantidad)) as cantidadComprada ORDER BY cantidadComprada  DESC limit 5");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                String nombre = record.get("nombreCompleto").asString();
                for(int i=0; i < 1 ; i++){
                    arreglo[contador] = nombre;
                    System.out.println(arreglo[i]);
                    contador++;
                }
            }
            
        }
        return arreglo;
    }
      
      
       public  int[] cantidadClientesMasCompras() {
        ConexionBaseDeDatosNeo4j csv = new ConexionBaseDeDatosNeo4j();
        
        int cantPro=csv. cantidadConsultasClientesConMasCompras();
        int contador = 0;
        int [] arreglo = new int[cantPro];
        try (Session session = driver.session()) {
        
            Result result = session.run("MATCH (a:Cliente)-[r1:realiza_una]->(b:Compra) RETURN a.first_name +\" \" + a.last_name as nombreCompleto,sum(toInteger(b.cantidad)) as cantidadComprada ORDER BY cantidadComprada  DESC limit 5");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                int cant = record.get("cantidadComprada").asInt();
                for(int i=0; i < 1 ; i++){
                    arreglo[contador] = cant;
                    System.out.println(arreglo[i]);
                    contador++;
                }
            }
            
        }
        return arreglo;
    }
     
       
    /*
    Métodos de la consulta 4
    */
    public  int cantidadComprasDeCliente(String pNombre, String pApellido) {
        int contador =0;
        try (Session session = driver.session()) {
        
            Result result = session.run("MATCH (a:Cliente)-[r1:realiza_una]->(b:Compra)-[r2:incluye_un]->(d:Producto) where a.first_name= '"+pNombre+"' and a.last_name= '"+pApellido+"' RETURN d.nombre as Nombre, b.cantidad as Cantidad");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                contador++;
            }
        }
        return contador;
    }
    
    public  String[] getNombreProductosCliente(String pNombre, String pApellido) {
        ConexionBaseDeDatosNeo4j csv = new ConexionBaseDeDatosNeo4j();
        
        int cantPro=csv. cantidadComprasDeCliente(pNombre, pApellido);
        int contador = 0;
        String [] arreglo = new String[cantPro];
        try (Session session = driver.session()) {
        
            Result result = session.run("MATCH (a:Cliente)-[r1:realiza_una]->(b:Compra)-[r2:incluye_un]->(d:Producto) where a.first_name= '"+pNombre+"' and a.last_name= '"+pApellido+"' RETURN d.nombre as Nombre, b.cantidad as Cantidad");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                String nombre = record.get("Nombre").asString();
                for(int i=0; i < 1 ; i++){
                    arreglo[contador] = nombre;
                    System.out.println(arreglo[i]);
                    contador++;
                }
            }
            
        }
        return arreglo;
    }
    
    public  String[] getCantidadProductosCliente(String pNombre, String pApellido) {
        ConexionBaseDeDatosNeo4j csv = new ConexionBaseDeDatosNeo4j();
        
        int cantPro=csv. cantidadComprasDeCliente(pNombre, pApellido);
        int contador = 0;
        String [] arreglo = new String[cantPro];
        try (Session session = driver.session()) {
        
            Result result = session.run("MATCH (a:Cliente)-[r1:realiza_una]->(b:Compra)-[r2:incluye_un]->(d:Producto) where a.first_name= '"+pNombre+"' and a.last_name= '"+pApellido+"' RETURN d.nombre as Nombre, b.cantidad as Cantidad");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                String cantidad = record.get("Cantidad").asString();
                for(int i=0; i < 1 ; i++){
                    arreglo[contador] = cantidad;
                    System.out.println(arreglo[i]);
                    contador++;
                }
                
            }
            
        }
        return arreglo;
    }
    public  String[] getNombreProductosComun(String pNombre, String pApellido) {
        ConexionBaseDeDatosNeo4j csv = new ConexionBaseDeDatosNeo4j();
        
        int cantPro=csv. cantidadComprasDeCliente(pNombre, pApellido);
        int contador = 0;
        String [] arreglo = new String[cantPro];
        try (Session session = driver.session()) {
        
            Result result = session.run("MATCH (a:Cliente)-[r1:realiza_una]->(b:Compra)-[r2:incluye_un]->(d:Producto) where a.first_name= '"+pNombre+"' and a.last_name= '"+pApellido+"' RETURN d.nombre as Nombre, b.cantidad as Cantidad");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                String nombre = record.get("NombreCompleto").asString();
                for(int i=0; i < 1 ; i++){
                    arreglo[contador] = nombre;
                    System.out.println(arreglo[i]);
                    contador++;
                }
            }
            
        }
        return arreglo;
    }
    
    
    /*
    Métodos de la consulta 5
    */
    
    public  int cantidadProductoComun(String pProducto) {
        int contador =0;
        try (Session session = driver.session()) {
        
            Result result = session.run("MATCH (a:Cliente)-[r1:realiza_una]->(b:Compra)-[r2:incluye_un]->(d:Producto)where d.nombre ='"+pProducto+"' RETURN a.first_name +  \" \"+ a.last_name as nombreCompleto");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                contador++;
            }
        }
        return contador;
    }
    
    public  String[] getNombreClientesComun(String pProducto) {
        ConexionBaseDeDatosNeo4j csv = new ConexionBaseDeDatosNeo4j();
        
        int cantPro=csv.cantidadProductoComun(pProducto);
        int contador = 0;
        String [] arreglo = new String[cantPro];
        try (Session session = driver.session()) {
        
            Result result = session.run("MATCH (a:Cliente)-[r1:realiza_una]->(b:Compra)-[r2:incluye_un]->(d:Producto)where d.nombre ='"+pProducto+"' RETURN a.first_name +  \" \"+ a.last_name as nombreCompleto");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                String nombre = record.get("nombreCompleto").asString();
                for(int i=0; i < 1 ; i++){
                    arreglo[contador] = nombre;
                    System.out.println(arreglo[i]);
                    contador++;
                }
            }
            
        }
        return arreglo;
    }
    
    
    /*
    Métodos de la consulta 6
    */
    public  int cantidadComprasEnComun(String pNombre, String pApellido) {
        int contador =0;
        try (Session session = driver.session()) {
        
            Result result = session.run("CALL {Match(c:Cliente{first_name:'"+pNombre+"', last_name:'"+pApellido+"'}) Match (c)-[r1:realiza_una]->(:Compra)-[incluye_un]->(p:Producto) Match(p:Producto)<-[:incluye_un]-(:Compra)<-[r2:realiza_una]-(u:Cliente) where u.first_name <> '"+pNombre+"' and u.last_name <> '"+pNombre+"' RETURN u.first_name + \" \" + u.last_name AS Nombre, COLLECT(p.nombre) AS Productos ORDER BY(Nombre)} WITH Nombre, Productos WHERE SIZE(Productos) >=2 RETURN Nombre, Productos");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                contador++;
            }
        }
        return contador;
    }
    public  String[] getNombreClientesComun(String pNombre, String pApellido) {
        ConexionBaseDeDatosNeo4j csv = new ConexionBaseDeDatosNeo4j();
        
        int cantPro=csv. cantidadComprasEnComun(pNombre, pApellido);
        int contador = 0;
        String [] arreglo = new String[cantPro];
        try (Session session = driver.session()) {
        
            Result result = session.run("CALL {Match(c:Cliente{first_name:'"+pNombre+"', last_name:'"+pApellido+"'}) Match (c)-[r1:realiza_una]->(:Compra)-[incluye_un]->(p:Producto) Match(p:Producto)<-[:incluye_un]-(:Compra)<-[r2:realiza_una]-(u:Cliente) where u.first_name <> '"+pNombre+"' and u.last_name <> '"+pNombre+"' RETURN u.first_name + \" \" + u.last_name AS Nombre, COLLECT(p.nombre) AS Productos ORDER BY(Nombre)} WITH Nombre, Productos WHERE SIZE(Productos) >=2 RETURN Nombre, Productos");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                String nombre = record.get("Nombre").asString();
                for(int i=0; i < 1 ; i++){
                    arreglo[contador] = nombre;
                    System.out.println(arreglo[i]);
                    contador++;
                }
            }
            
        }
        return arreglo;
    }
    
    
    public  String[] getNombreClientesProductoComun(String pNombre, String pApellido) {
        ConexionBaseDeDatosNeo4j csv = new ConexionBaseDeDatosNeo4j();
        
        int cantPro=csv. cantidadComprasEnComun(pNombre, pApellido);
        int contador = 0;
        String [] arreglo = new String[cantPro];
        try (Session session = driver.session()) {
        
            Result result = session.run("CALL {Match(c:Cliente{first_name:'"+pNombre+"', last_name:'"+pApellido+"'}) Match (c)-[r1:realiza_una]->(:Compra)-[incluye_un]->(p:Producto) Match(p:Producto)<-[:incluye_un]-(:Compra)<-[r2:realiza_una]-(u:Cliente) where u.first_name <> '"+pNombre+"' and u.last_name <> '"+pNombre+"' RETURN u.first_name + \" \" + u.last_name AS Nombre, COLLECT(p.nombre) AS Productos ORDER BY(Nombre)} WITH Nombre, Productos WHERE SIZE(Productos) >=2 RETURN Nombre, Productos");
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                String producto = record.get("Productos").toString();
                for(int i=0; i < 1 ; i++){
                    arreglo[contador] = producto;
                    System.out.println(arreglo[i]);
                    contador++;
                }
            }
            
        }
        return arreglo;
    }
       
    public static void main(String[] args) throws Exception {
       ConexionBaseDeDatosNeo4j csv = new ConexionBaseDeDatosNeo4j();
       String nombre = "Yoghurt Tubes";
       csv.obtenerIDProducto(nombre);
       //System.out.println(trueFalse);

      /* ConexionBaseDeDatosNeo4j csv = new ConexionBaseDeDatosNeo4j();
       csv.pruebaD();*/
    }
    
}



