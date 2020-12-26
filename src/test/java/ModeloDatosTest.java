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
     String dbHost = "jdbc:mysql://localhost";
     String dbPort = "3306";
     String dbName = "baloncesto";
     String dbUser = "usuario";
     String dbPass = "clave";
     String url = dbHost + ":" + dbPort + "/" + dbName;


    @Test
    public void testExisteJugador() {
        System.out.println("Prueba de existeJugador");
        String nombre = "Carroll";        
        boolean expResult = true;   
        instance = new ModeloDatos();
        instance.abrirConexion();       
        boolean result = instance.existeJugador(nombre);        
        assertEquals(expResult, result);  
        instance.cerrarConexion();
          
    }


    @Test
    public void testActualizarJugador() {   
        instance = new ModeloDatos();
        instance.abrirConexion();  
        instance.actualizarJugador("Carroll");
        Source source = new Source(url, dbUser, dbPass);
        Table jugadores = new Table(source, "Jugadores");            
        assertThat(jugadores).row().hasValues("1","Carroll","1");
        instance.cerrarConexion();
          
    }
}
