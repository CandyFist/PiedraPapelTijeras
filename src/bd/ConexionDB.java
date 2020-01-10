package bd;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
	private static Connection con;
	
	public static void createConnection()
	{
		try
		  {
		        con = DriverManager.getConnection("jdbc:mysql://localhost/pruebas?noAccessToProcedureBodies=true&serverTimezone=UTC","root", "root");   
		  }   
		catch (SQLException e) {e.printStackTrace();}

	}
		
	public static Connection getConnection()
	{
		
		return con;
	}
	
	public static void deleteConnection()
	{
		try {
			if (con !=null)
			{
				if (!con.isClosed())
				{
					con.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
