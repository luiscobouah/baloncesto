import java.sql.*;
import java.util.ArrayList;

import modelo.Jugador;

public class ModeloDatos {

    private Connection con;
    private Statement set;
    private ResultSet rs;

    public void abrirConexion() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbHost = System.getenv().get("DATABASE_HOST");
            String dbPort = System.getenv().get("DATABASE_PORT");
            String dbName = System.getenv().get("DATABASE_NAME");
            String dbUser = System.getenv().get("DATABASE_USER");
            String dbPass = System.getenv().get("DATABASE_PASS");
            String url = dbHost + ":" + dbPort + "/" + dbName;          
            con = DriverManager.getConnection(url, dbUser, dbPass);

        } catch (Exception e) {
            
        }
    }

    public boolean existeJugador(String nombre) {
        boolean existe = false;
        String cad;
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Jugadores");
            while (rs.next()) {
                cad = rs.getString("Nombre");
                cad = cad.trim();
                if (cad.compareTo(nombre.trim()) == 0) {
                    existe = true;
                    System.out.println("existe");
                }
            }
            rs.close();
            set.close();
        } catch (Exception e) {
           
        }
        return (existe);
    }

    public void actualizarJugador(String nombre) {
        try {
            set = con.createStatement();
            set.executeUpdate("UPDATE Jugadores SET votos=votos+1 WHERE nombre " + " LIKE '%" + nombre + "%'");
            rs.close();
            set.close();
        } catch (Exception e) {
           
        }
    }

    public void insertarJugador(String nombre) {
        try {
            set = con.createStatement();
            set.executeUpdate("INSERT INTO Jugadores " + " (nombre,votos) VALUES ('" + nombre + "',1)");
            rs.close();
            set.close();
        } catch (Exception e) {
           
        }
    }

    public boolean reiniciarVotos() {
        boolean resultado;
        try {
            set = con.createStatement();
            set.executeUpdate("UPDATE Jugadores SET votos=0");
            rs.close();
            set.close();
            resultado = true;

        } catch (Exception e) {
           
            resultado = false;
        }

        return resultado;
    }

    public ArrayList<Jugador> obtenerJugadoresVotos() {
       ArrayList<Jugador> jugadoresList = new  ArrayList<Jugador>();
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Jugadores");

            while(rs.next()){
               Jugador jugador = new Jugador();
               jugador.setId(rs.getInt("id"));
               jugador.setNombre(rs.getString("nombre"));
               jugador.setVotos(rs.getInt("votos"));
               jugadoresList.add(jugador);
            }           

            rs.close();
            set.close();            

        } catch (Exception e) {
         
                   }

        return jugadoresList;
    }

    public void cerrarConexion() {
        try {
            con.close();
        } catch (Exception e) {
           
        }
    }

}
