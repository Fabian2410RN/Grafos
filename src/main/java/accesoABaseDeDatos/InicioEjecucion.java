
package accesoABaseDeDatos;

import controladorWeb.CargarCSVs;

/**
 *
 * @author Estadm
 */
public class InicioEjecucion {
    public static void main(String[] args) throws Exception {
       CargarCSVs csv = new CargarCSVs();
       csv.agregarCSVClientes();
    }
}
