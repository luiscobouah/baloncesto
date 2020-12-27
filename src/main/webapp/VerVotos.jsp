<!DOCTYPE html>
<%@ page import="java.util.ArrayList" %>
<%@ page import="modelo.Jugador" %>
<html>
    <head>
        <title>Votaci&oacute;n mejor jugador liga ACB</title>
        <link href="estilos.css" rel="stylesheet" type="text/css" />
    </head>
    <body class="votosJugadores">
        <%   	
            ArrayList<Jugador> listaJugadores = (ArrayList<Jugador>) session.getAttribute("jugadores");
        %>    
        <h1>Votaci&oacute;n al mejor jugador de la liga ACB</h1>
        <br>
        <h2>Votos Jugadores</h2>
        <table border="2">
            <tr>       
            <td>JUGADOR</td>
            <td>VOTOS</td>
            </tr>
            <%
            
            for (int i=0;i < listaJugadores.size(); i++ )
            {
                out.println("<tr>");
                out.println("<td>"+listaJugadores.get(i).getNombre()+"</td>");
                out.println("<td>"+listaJugadores.get(i).getVotos()+"</td>");
                out.println("</tr>");
            }

            %>
        </table> 
        <br>
        <br> 
        <a href="index.html"> Ir al comienzo</a>
    </body>
</html>
