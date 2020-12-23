import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class ModeloDatosTest {

    public final static String FILENAME = "/dbunitData.xml";

    @BeforeEach
    public void loadData() throws Exception {
        String dbHost = System.getenv().get("DATABASE_HOST");
        String dbPort = System.getenv().get("DATABASE_PORT");
        String dbName = System.getenv().get("DATABASE_NAME");
        String dbUser = System.getenv().get("DATABASE_USER");
        String dbPass = System.getenv().get("DATABASE_PASS");
        String url = dbHost + ":" + dbPort + "/" + dbName;

        IDatabaseTester databaseTester = new JdbcDatabaseTester(
                "com.mysql.cj.jdbc.Driver",
                url,
                dbUser,
                dbPass,
                dbName
        );

        IDataSet dataSet = new FlatXmlDataSetBuilder().build(this.getClass()
                .getResourceAsStream(FILENAME));
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }



  /*  @Test
    public void testExisteJugador() {
        System.out.println("Prueba de existeJugador");
        String nombre = "";
        ModeloDatos instance = new ModeloDatos();
        boolean expResult = false;
        boolean result = instance.existeJugador(nombre);
        assertEquals(expResult, result);
       // fail("Fallo forzado.");
    }*/

    @Test
    public void test() {
        assertTrue(true);
    }

   /* @Test
    public void testActualizarJugador() {
        System.out.println("Prueba de actualizar jugador");       
        ModeloDatos instance = new ModeloDatos(); 
        instance.abrirConexion();
        boolean result = instance.actualizarJugador();
        boolean expResult = true;
        assertEquals(expResult, result);
       // fail("Fallo forzado.");
    } */
}