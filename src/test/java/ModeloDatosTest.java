import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.db.type.Source;
import org.assertj.db.type.Table;

public class ModeloDatosTest {
    public static  ModeloDatos instance;

     // Con variables de entorno
     String dbHost = System.getenv().get("DATABASE_HOST");
     String dbPort = System.getenv().get("DATABASE_PORT");
     String dbName = System.getenv().get("DATABASE_NAME");
     String dbUser = System.getenv().get("DATABASE_USER");
     String dbPass = System.getenv().get("DATABASE_PASS");

     String url = dbHost + ":" + dbPort + "/" + dbName;

    @BeforeAll
    public static void initModeloDatos() {
        instance = new ModeloDatos();
        instance.abrirConexion();
        System.out.println("initModeloDatos");
    }

    @AfterAll
    public static void finalizeModeloDatos() {
        instance.cerrarConexion();
    }

    @Test
    public void testExisteJugador() {
        System.out.println("Prueba de existeJugador");
        String nombre = "Carroll";        
        boolean expResult = true;
        boolean result = instance.existeJugador(nombre);        
        assertEquals(expResult, result);      
    }


    @Test
    public void testActualizarJugador() {        
        instance.actualizarJugador("Carroll");
        Source source = new Source(url, dbUser, dbPass);
        Table jugadores = new Table(source, "Jugadores");            
        assertThat(jugadores).row().hasValues("1","Carroll","1");
   
    }
}
