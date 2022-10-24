

package accesoABaseDeDatos;

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
public class ConexionBaseNeo4j {
    
    public Connection conexion(){
        Connection con = null;
        try{
            System.out.println("Estoy entrando en el try");
            Class.forName("org.neo4j.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:neo4j:bolt://localhost/?user=neo4j,password=1234,scheme=basic");
            //GraphDatabase.driver("neo4j://graph.example.com:7687", auth);

            System.out.println("Conexion establecida");
        } catch(ClassNotFoundException | SQLException e){
            System.out.println("No se pudo conectar");
            JOptionPane.showInputDialog(null, "ERROR DE: "+ e);
        }
        return con;
    }
    
    //public static void main(String[] args) throws Exception {
    //    ConexionBaseNeo4j cc = new ConexionBaseNeo4j();
    //}
}

