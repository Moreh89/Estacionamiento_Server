package persistencia;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;



public class PoolConnection
{
	private Vector <Connection> connections = new Vector<Connection>();
	protected String url;
	protected String driver;
	protected String usuario;
	protected String password;
	protected int cantCon;
	private static PoolConnection pool;
	
	private Connection c;
	
	private PoolConnection()
	{
		getConfiguration();
		for (int i= 0; i< cantCon;i++){
			c = connect();
			if (c == null){
				System.out.println("ssdsd");
				return;
			}else
			connections.add(c);
		}
			
	}
	
	public static PoolConnection getPoolConnection()
	{
		if (pool== null)
			pool =new PoolConnection();
		return pool;
	}
	private Connection connect()
	{
		try
		{
			//Setear driver
			Class.forName (driver).newInstance();
            Connection con = DriverManager.getConnection (url, usuario, password);
            
            return con;
		}
		catch (SQLException e)
		{
			System.out.println("Mensaje Error (connect e): " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		catch (Exception ex)
		{
			System.out.println("Mensaje Error (connect ex): " + ex.getMessage());
			System.out.println("Stack Trace: " + ex.getStackTrace());
			return null;
		}
	}
	public void getConfiguration()
	{
		//String configuracion = "SQL";
		String configuracion = "database.properties";
		Properties propiedades;
	 
	    // Carga del fichero de propiedades 
	    try 
	    {
	       FileInputStream f = new FileInputStream(configuracion);	 
	       propiedades = new Properties();
	       propiedades.load(f);
	       f.close();
	 
       // Leo los valores de configuracion
	       url = propiedades.getProperty("url"); 
	       driver = propiedades.getProperty("driver");
	       usuario = propiedades.getProperty("usuario");
	       password = propiedades.getProperty("password");
	       cantCon = Integer.parseInt(propiedades.getProperty("conexiones"));
	     } 
	    catch (Exception e) 
	     {
				System.out.println("Mensaje Error (getConfiguration): " + e.getMessage());
				e.printStackTrace();
	     }
	}
	public void closeConnections()
	{
		for (int i=0; i<connections.size();i++)
		{
			try
			{
				connections.elementAt(i).close();
			}
			catch(Exception e)
			{
				System.out.println("Mensaje Error: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	public  Connection getConnection()
	{
		Connection c = null;
		if (connections.size()>0)
			c = connections.remove(0);
		else
		{
			c = connect();
			System.out.println("Se ha creado una nueva conexion fuera de los parametros de configuracion");
		}
		return c;
	}
	
	public void realeaseConnection(Connection c)
	{
		connections.add(c);
	}
	
	public String getDataBaseName(){
		
		int indexdatabaseName = url.indexOf("databaseName");
		return url.substring(indexdatabaseName+13);
	}
}
