
package accesoABaseDeDatos;

import controladorWeb.ControladorCargarCSVs;

/**
 *
 * @author Estadm
 */
public class InicioEjecucion {
    public static void main(String[] args) throws Exception {
       ControladorCargarCSVs csv = new ControladorCargarCSVs();
       csv.agregarCSVClientes();
    }
}
