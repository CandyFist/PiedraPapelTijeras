package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JTextField;

import gui.Registro;
import modelo.Jugador;

public class MovPartida {
	
	
	
    public static void resolucion(Connection conexion, int jugada, JTextField resultado2, JTextField movmaq2, int movMaquina, Jugador j) {
		actJug(conexion);
    	movMaquina = jugadaIA(movMaquina, conexion, j);
        switch (movMaquina) {
        case 1: movmaq2.setText("Papel");break;
        case 2: movmaq2.setText("Piedra");break;
        case 3: movmaq2.setText("Tijeras");break;
        }
        int resultado = jugada - movMaquina;
        switch (resultado) {
            case -1:
            case 2:
                resultado2.setText("¡Ganaste!");
                ganaScore(conexion, j);
                break;
            case 0:
            	resultado2.setText("¡Empataste!");
                break;
            case 1:
            case -2:
            	resultado2.setText("¡Perdiste!");
                pierdeScore(conexion, j);
                break;
        }
        System.out.println("resolucionfinal");

        System.out.println("Jugadasuma");
        switch (jugada) {
        case 1: sumarJugadaPapel(conexion, j);;break;
        case 2: sumarJugadaPiedra(conexion, j);;break;
        case 3: sumarJugadaTijera(conexion, j);;break;
        }

    }
        
	/*public static void sacarJugadas(Connection conexion, Jugador j)
	{
        	System.out.println("SacandoJugadas");
		    String sql = "SELECT JPIEDRA, JPAPEL, JTIJERAS FROM JUGADORES WHERE NOMBRE = ? ";
	        PreparedStatement sentencia;
	        try {
	            sentencia = conexion.prepareStatement(sql);
	            sentencia.setString(1, j.getAlias());
	            ResultSet rs = sentencia.executeQuery();
	            if (rs.next()) 
	            {
	              Registro.jug.setPiedras(rs.getInt(0));
	              Registro.jug.setPapeles(rs.getInt(1));
	              Registro.jug.setTijeras(rs.getInt(2));
	            }
	            sentencia.close();
	        } catch (SQLException e) {
	               
	        }
	}
	
	public static void sacarScore(Connection conexion, Jugador j)
	{
		    String sql = "SELECT PGANADAS, PPERDIDAS FROM JUGADORES WHERE NOMBRE = ? ";
	        PreparedStatement sentencia;
	        try {
	            sentencia = conexion.prepareStatement(sql);
	            sentencia.setString(1, j.getAlias());
	            ResultSet rs = sentencia.executeQuery();
	            if (rs.next()) 
	            {
	              Registro.jug.setGanadas(rs.getInt(0));
	              Registro.jug.setPerdidas(rs.getInt(1));
	            }
	            sentencia.close();
	        } catch (SQLException e) {
	               
	        }
	}*/
	

	
        public static int jugadaIA(int movIA, Connection conexion, Jugador j) {

            System.out.println("calculando IA");
        	int total;
    		Random randN = new Random();
    		total = Registro.jug.getPapeles() + Registro.jug.getPiedras() + Registro.jug.getTijeras();
            System.out.println(total);
    		double probPr = Registro.jug.getPiedras(); 
    		double probPl = Registro.jug.getPapeles() + probPr; 
    		int eleccion = randN.nextInt(total);
    		eleccion ++;
            System.out.println(eleccion);
    		if (eleccion <= probPr) {
    			movIA = 1;
    		}
    		else if (eleccion <= probPl && eleccion > probPr) {
    			movIA = 3;
    		}
    		else if (eleccion <= total && eleccion > probPl ){
    			movIA = 2;
    		}

            System.out.println("IaCalculada: " + movIA);
    		return movIA;
        }
	
	public static void sumarJugadaPiedra(Connection conexion, Jugador j) {

		String sql = "UPDATE JUGADORES SET JPIEDRA = JPIEDRA+1 WHERE ALIAS = ?";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, Registro.jug.getAlias());
            System.out.println(sql);
            int filas = sentencia.executeUpdate();
            if (filas > 0) {
                System.out.println("Actualización de la jugada");
            }
            sentencia.close();
        } catch (SQLException e) {
               
        }
	    
	}
	
	public static void sumarJugadaTijera(Connection conexion, Jugador j) {

		String sql = "UPDATE JUGADORES SET JTIJERA = JTIJERA+1 WHERE ALIAS = ?";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, Registro.jug.getAlias());
            System.out.println(sql);
            int filas = sentencia.executeUpdate();
            if (filas > 0) {
                System.out.println("Actualización de la jugada");
            }
            sentencia.close();
        } catch (SQLException e) {
               
        }
	    
	}
	
	public static void sumarJugadaPapel(Connection conexion, Jugador j) {

		String sql = "UPDATE JUGADORES SET JPAPEL = JPAPEL+1 WHERE ALIAS = ?";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, Registro.jug.getAlias());
            System.out.println(sql);
            int filas = sentencia.executeUpdate();
            if (filas > 0) {
                System.out.println("Actualización de la jugada");
            }
            sentencia.close();
        } catch (SQLException e) {
               
        }
	    
	}
	
	public static void ganaScore(Connection conexion, Jugador j) {

		String sql = "UPDATE JUGADORES SET PGANADAS = PGANADAS+1 WHERE ALIAS = ?";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, Registro.jug.getAlias());
            System.out.println(sql);
            int filas = sentencia.executeUpdate();
            if (filas > 0) {
                System.out.println("Score sumado.");
            }
            sentencia.close();
        } catch (SQLException e) {
               
        }
	    
	}
	
	public static void pierdeScore(Connection conexion, Jugador j) {

		String sql = "UPDATE JUGADORES SET PPERDIDAS = PPERDIDAS+1 WHERE ALIAS = ?";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, Registro.jug.getAlias());
            System.out.println(sql);
            int filas = sentencia.executeUpdate();
            if (filas > 0) {
                System.out.println("Score restado.");
            }
            sentencia.close();
        } catch (SQLException e) {
               
        }
	    
	}
	
	public static void actJug(Connection conexion)
	{
        System.out.println("Actualizar jug");
		    String sql = "SELECT * FROM JUGADORES WHERE ALIAS = ? ";
	        PreparedStatement sentencia;
	        try {
	            sentencia = conexion.prepareStatement(sql);
	            sentencia.setString(1, Registro.jug.getAlias());
                System.out.println(Registro.jug.getAlias());
	            ResultSet rs = sentencia.executeQuery();
	            if (rs.next()) 
	            {
	                System.out.println("Entra en el if");
	            	Jugador ActJ = new Jugador(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
	            	Registro.jug = ActJ;
	                System.out.println(ActJ.toString());
	            }
	            sentencia.close();
	        } catch (SQLException e) {
	               
	        }
	}
	
	public static float ratio(Connection conexion) {
		actJug(conexion);
		int total = Registro.jug.getGanadas()+Registro.jug.getPerdidas();
		float ratD = 0;
		System.out.println("total: "+total);
		if (total > 0) {
		ratD = (float) Registro.jug.getGanadas();
		ratD = ratD / total;
		}
		return ratD;
	}
	
}
