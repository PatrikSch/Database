package databaseUtility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class UtilityTester 
{
	private static Connection con = null;
	private static String host = "db4free.net";
	private static String port = "3306";
	private static String name = "snowtrack";
	private static String user = "snowtrackdb";
	private static String pass = "snowtrack";
	
	public UtilityTester()
	{
		try
		{
			con = Connect.connectToDB( host, port, name, user, pass );
		}
		catch( ClassNotFoundException e )
		{
			System.out.println( "Could not find driver" );
			e.printStackTrace();
		}
		catch( SQLException e )
		{
	        System.out.println( "Could not connect" );
	        System.out.println("SQLException: " + e.getMessage() );
	        System.out.println("SQLState: " + e.getSQLState() );
	        System.out.println("VendorError: " + e.getErrorCode() );	
		}
		
	}
	
	private static Connection getInstance(){
	    if(con == null)
	        new UtilityTester();
	    return con;
	}
	public static void main( String args[] ) throws SQLException
	{
		con = getInstance();
		if( con != null )
		{
			Statement query;
			query = con.createStatement();
			
			Scanner scanner = new Scanner( System.in );
			String input = "i";
			while( !input.contains( "exit" ) || !input.contains( "Exit" ) || !input.contains( "EXIT" ) )
			{
				try
				{
					input = scanner.nextLine();
					if( input.contains( "SELECT" ) )
					{
						ResultSet result = query.executeQuery( input );
				        while (result.next()) 
				        {
				        String kundeid = result.getString("id");
				        String vorname = result.getString("name");
				        String nachname = result.getString("password");
				        int age = result.getInt("age");
				        String info = kundeid + ", " + vorname + ", " + nachname + ", " + age;
				         
				          System.out.println(info);
				        }
					}
					else
						query.executeUpdate( input );
				}
				catch( Exception e )
				{
					e.printStackTrace();
				}
				
			}
			scanner.close();
		}
		else
		{
			System.out.println( "Null" );
		}
	}
}
