

package accesoABaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Estadm
 */
//fabian
//agdsgdsgdg
//dfdf
public class ConexionBaseNeo4j {
    Connection con = null;
    
    public Connection conexion(){
        Connection con = null;
        System.out.println("Entrando");
        try{
            Class.forName("org.neo4j.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:neo4j:bolt://localhost/?user=neo4j,password=1234,scheme=basic");
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

