package bd;

import java.awt.Frame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import gui.Partida;
import gui.Registro;
import modelo.Jugador;

public class MovRegistros {
	
	public static int maxID (Connection conexion)
	{
		int num = 0;
        PreparedStatement sentencia;
        String sql = "SELECT MAX(ID) FROM JUGADORES";
        try {
            sentencia = conexion.prepareStatement(sql);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) 
            {
               num = rs.getInt(1);
            }
            sentencia.close();
        } catch (SQLException e) {
               
        }
        return num;
		
	}
	
	// crear un jugador
	public static void crear(Connection conexion, Jugador j, JLabel lblUsuarioDesconocido)
	{
		if (consultar(conexion, j)) {
		
		//int nID;
        String sql = "INSERT INTO JUGADORES VALUES (? , ? , ? , 0 , 0 , 1 , 1 , 1)"; //¿Quizá aquí añadir un nivel de experiencia, que a tantas partidas ganadas te lo modifique?
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            System.out.println(j.toString());
            sentencia.setInt(1,maxID(conexion)+1);
            sentencia.setString(2,j.getAlias());
            sentencia.setString(3,j.getContraseña());
            int filas = sentencia.executeUpdate();
            if (filas > 0) {
                System.out.println(j.getAlias()+" se ha introducido satisfactoriamente.");
    			lblUsuarioDesconocido.setText("");
    			JOptionPane.showMessageDialog(null, j.getAlias()+" se ha creado con éxito.");
            }
            sentencia.close();
        } catch (SQLException e) {}
		
		}
		else {
		    System.out.println("El nombre de usuario -"+j.getAlias()+"- ya está en uso.");
			JOptionPane.showMessageDialog(null, "El nombre de usuario -"+j.getAlias()+"- ya está en uso.");
			lblUsuarioDesconocido.setText("Reintroducir datos");
		}
	                
	   
		
	}
	
	
	
		public static void modificar(Connection conexion,Jugador j)
		{
			String sqlupdate = "UPDATE JUGADORES SET ALIAS = ?, CONTRASEÑA = ? WHERE ID = ?";
			PreparedStatement sentencia;
		        try {
		            sentencia = conexion.prepareStatement(sqlupdate);
		            sentencia.setString(1,j.getAlias());
		            sentencia.setString(2,j.getContraseña());
		            sentencia.setInt(3,j.getId());
		            int filas = sentencia.executeUpdate();
		            if (filas > 0) {
		                System.out.println("Jugador modificado");
		            }
		            sentencia.close();
		        } catch (SQLException e) {
		                
		        }
		}
		

		public static void borrar(Connection conexion,Jugador j)
		{
			    String sql = "DELETE FROM JUGADORES WHERE ID = ? ";
		        PreparedStatement sentencia;
		        try {
		            sentencia = conexion.prepareStatement(sql);
		            sentencia.setInt(1, j.getId());
		            int filas = sentencia.executeUpdate();
		            if (filas > 0) 
		            {
		               System.out.println("Jugador eliminado");
		            }
		            sentencia.close();
		        } catch (SQLException e) {
		               
		        }
		        
		}
		
		
		public static boolean consultar(Connection conexion,Jugador j)
		{
			boolean existencia = true;
			    String sql = "SELECT * FROM JUGADORES WHERE ALIAS = ? ";
		        PreparedStatement sentencia;
		        try {
		            sentencia = conexion.prepareStatement(sql);
		            sentencia.setString(1, j.getAlias());
		            ResultSet rs = sentencia.executeQuery();
		            if (rs.next()) 
		            {
		               existencia = false;
		            }
		            sentencia.close();
		        } catch (SQLException e) {
		               
		        }
		        return existencia;
		}
		
		public static int consultarID(Connection conexion,Jugador j)
		{
				int cID = -1;
			    String sql = "SELECT ID FROM JUGADORES WHERE ALIAS = ? AND CONTRASEÑA = ?";
		        PreparedStatement sentencia;
		        try {
		            sentencia = conexion.prepareStatement(sql);
		            sentencia.setString(1, j.getAlias());
		            sentencia.setString(2, j.getContraseña());
		            ResultSet rs = sentencia.executeQuery();
		            if (rs.next()) 
		            {
			            cID = (rs.getInt(1));
		            }
		            sentencia.close();
		        } catch (SQLException e) {
		               
		        }
		        return cID;
		}

		public static boolean comprobar(Connection conexion, int id)
		{
			    String sql = "SELECT ID FROM JUGADORES WHERE ID = ? ";
		        PreparedStatement sentencia;
		        boolean comp = false;
		        try {
		            sentencia = conexion.prepareStatement(sql);
		            sentencia.setInt(1, id);
		            ResultSet rs = sentencia.executeQuery();
		            if (rs.next()) 
		            {
		              comp = true;
		            }
		            sentencia.close();
		        } catch (SQLException e) {
		               
		        }
		       return comp;
		        
		}
		
		
		public static void loguearse(Connection conexion, Jugador j, JLabel lblUsuarioDesconocido, Frame frame)
		{
			if (comprobar(conexion, consultarID(conexion, j))) {

				Registro.jug = j;
				Partida.main(null);
				lblUsuarioDesconocido.setText("");
				frame.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Usuario Desconocido");
				lblUsuarioDesconocido.setText("Reintroducir datos");
			}
		}
		
		

}
