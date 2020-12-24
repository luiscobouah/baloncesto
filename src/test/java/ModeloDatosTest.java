import org.junit.jupiter.api.Test;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.db.type.Source;
import org.assertj.db.type.Table;

public class ModeloDatosTest {
     // Con variables de entorno
    /* String dbHost = System.getenv().get("DATABASE_HOST");
     String dbPort = System.getenv().get("DATABASE_PORT");
     String dbName = System.getenv().get("DATABASE_NAME");
     String dbUser = System.getenv().get("DATABASE_USER");
     String dbPass = System.getenv().get("DATABASE_PASS");

     String url = dbHost + ":" + dbPort + "/" + dbName;
*/
    String dbHost = "jdbc:mysql://localhost";
    String dbPort = "3306";
    String dbName = "baloncesto";
    String dbUser = "usuario";
    String dbPass = "clave";
    String url = dbHost + ":" + dbPort + "/" + dbName;


    @Test
    public void testExisteJugador() {
        System.out.println("Prueba de existeJugador");
        String nombre = "";
        ModeloDatos instance = new ModeloDatos();
        boolean expResult = true;
        boolean result = instance.existeJugador(nombre);
        assertEquals(expResult, result);
       // fail("Fallo forzado.");
    }


    @Test
    public void testActualizarJugador() {
        ModeloDatos instance = new ModeloDatos();
        instance.actualizarJugador("Carroll");
        Source source = new Source(url, dbUser, dbPass);
        Table jugadores = new Table(source, "Jugadores");        
        assertThat(jugadores).row().hasValues("1","Carroll","1");
   
    }
}
