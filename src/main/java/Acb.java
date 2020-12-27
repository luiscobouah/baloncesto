
import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import modelo.Jugador;


public class Acb extends HttpServlet {

    private ModeloDatos bd;
    private ArrayList<Jugador> jugadoresList = new  ArrayList<Jugador>();

    public void init(ServletConfig cfg) throws ServletException {
        bd = new ModeloDatos();
        bd.abrirConexion();
    }

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession s = req.getSession(true);
        String nombreP = (String) req.getParameter("txtNombre");
        String nombre = (String) req.getParameter("R1");
        String accion = (String) req.getParameter("accion");

        if (accion.equals("Votar")) {

            if (nombre.equals("Otros")) {
                nombre = (String) req.getParameter("txtOtros");
            }
            if (bd.existeJugador(nombre)) {
                bd.actualizarJugador(nombre);
            } else {
                bd.insertarJugador(nombre);
            }
            s.setAttribute("nombreCliente", nombreP);
            // Llamada a la página jsp que nos da las gracias
            res.sendRedirect(res.encodeRedirectURL("TablaVotos.jsp"));
            
        } else {

            if (accion.equals("Poner votos a cero")){
                bd.reiniciarVotos();
                res.sendRedirect(res.encodeRedirectURL("ResultadoReiniciarVotos.jsp"));
            } else {

                if (accion.equals("Ver votos")){
                    jugadoresList = bd.obtenerJugadoresVotos();
                    s.setAttribute("jugadores", jugadoresList);
                    res.sendRedirect(res.encodeRedirectURL("VerVotos.jsp"));
                }
            }
        }
       
    }

    public void destroy() {
        bd.cerrarConexion();
        super.destroy();
    }
}
