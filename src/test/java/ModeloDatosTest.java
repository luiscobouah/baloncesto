import org.junit.jupiter.api.Test;
import static org.assertj.db.api.Assertions.assertThat;
import org.assertj.db.type.Source;
import org.assertj.db.type.Table;

public class ModeloDatosTest {
     // Con variables de entorno
     String dbHost = System.getenv().get("DATABASE_HOST");
     String dbPort = System.getenv().get("DATABASE_PORT");
     String dbName = System.getenv().get("DATABASE_NAME");
     String dbUser = System.getenv().get("DATABASE_USER");
     String dbPass = System.getenv().get("DATABASE_PASS");

     String url = dbHost + ":" + dbPort + "/" + dbName;


    @Test
    public void testExisteJugador() {
        Source source = new Source(url, dbUser, dbPass);
        Table jugadores = new Table(source, "jugadores");  
        assertThat(jugadores).column("nombre").hasValues("Carroll");     
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
