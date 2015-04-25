/**
 * This Class provides functions for inserting and
 * selecting data from DBMS
 * 
 * @author Patrik Schweigl
 * @version 1.0
 */


package databaseUtility;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Utility 
{
	/**
	 * Inserts the given params into the table person
	 * 
	 * This function is used for the Use Cases:
	 * 		- " UC 3.5 - Register"
	 * 
	 * @param query - the sql statement 
	 * @param name - the name of the person
	 * @param nick - the nickname of the person
	 * @param eMail - the E-Mail of the person
	 * @param pass - the password of the user
	 * @param age - the age of the user
	 * @return - either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException
	 */
	public static int insertIntoDBPerson( Statement query, String name, String nick, String eMail, String pass, int age ) throws SQLException
	{
		String sql = "INSERT INTO person( id, name, nickname, e_mail, password, age ) "
					 + "VALUES( default, '"+ name + "', '" + nick + "', '" + eMail + "', '" + pass + "', " + age + ");";
		return query.executeUpdate(sql);
		
	}
	
	/**
	 * Inserts the given params into the table tour
	 * 
	 * This function is used for the Use Cases:
	 * 		- " UC 3.8 - Create tour"
	 * 
	 * @param query - the sql statement
	 * @param name - the name of the tour
	 * @param loc - the location of the tour
	 * @param diff - the difficulty of the tour; (1) easy to (5) hard
	 * @param dist - the distance from your position to the location of the tour
	 * @param length - the length of the tour
	 * @param date - the upload date
	 * @param idUser - the id of the user, which has uploaded the tour
	 * @return - either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException
	 */
	public static int insertIntoDBTour( Statement query, String name, String desc,  String loc, int diff, int length, Date date, int idUser ) throws SQLException
	{
		String sql = "INSERT INTO tour( id, name, describtion, location, difficulty, length, date, idUser )" 
					 +" VALUES( default, '"+ name + "', '" + desc + "', '" + loc + "', " + diff + ", " + length + ", " + date + ", " + idUser + ");";
		return query.executeUpdate(sql);
	}
	
	/**
	 * Inserts the given params into the table comments
	 * 
	 * This function is used for the Use Cases:
	 * 		- " UC 3.10 - Create comment"
	 * 
	 * @param query - the sql statement
	 * @param text - the comment itself
	 * @param idUser - the id of the user, which has written the comment
	 * @param idTour - the id of the tour, which has been commented
	 * @return - either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException
	 */
	public static int insertIntoDBComment( Statement query, String text, int idUser, int idTour ) throws SQLException
	{
		String sql = "INSERT INTO person( id, text, idUser, idTour ) "
				 + "VALUES( default, '"+ text + "', " + idUser + ", " + idTour + ");";
		return query.executeUpdate(sql);
	}
	
	/**
	 * Get the tours, which suits the criteria "where"
	 * 
	 * This function is used for the Use Cases:
	 * 		- "UC 3.2 - Show all tours"
	 * 		- "UC 3.3 - Search tour"
	 * 		- "UC 3.14 - Manage tours"
	 * 
	 * @param query - the sql statement
	 * @param select - select columns from the table
	 * @param where - conditions
	 * @return - a ResultSet object that contains the data produced by the given query
	 * @throws SQLException
	 */
	public static ResultSet selectFromDBTour( Statement query, String select, String where ) throws SQLException
	{
		String sql;
		if( where.equals( "" ) )
			 sql = "SELECT " + select + " FROM tour;";
		else
			 sql = "SELECT " + select + " FROM tour WHERE " + where + ";";
		
		ResultSet result = query.executeQuery(sql);
		return result;
	}
	
	/**
	 * 
	 * Get the persons, which suits the criteria "where"
	 * 
	 * This function is used for the Use Cases:
	 * 		- " UC 3.15 - Manage User"
	 * 
	 * @param query - the sql statement
	 * @param select - select columns from the table
	 * @param where - conditions
	 * @return - a ResultSet object that contains the data produced by the given query
	 * @throws SQLException
	 */
	public static ResultSet selectFromDBPerson( Statement query, String select, String where ) throws SQLException
	{
		String sql;
		if( where.equals( "" ) )
			 sql = "SELECT " + select + " FROM person;";
		else
			 sql = "SELECT " + select + " FROM person WHERE " + where + ";";
		
		ResultSet result = query.executeQuery(sql);
		return result;
	}
	
	/**
	 * Get the comments, which suits the criteria "where"
	 * 
	 * This function is used for the Use Cases:
	 * 		- " UC 3.16 - Manage comments"
	 * 
	 * @param query - the sql statement
	 * @param select - select columns from the table
	 * @param where - conditions
	 * @return - a ResultSet object that contains the data produced by the given query
	 * @throws SQLException
	 */
	public static ResultSet selectFromDBComment( Statement query, String select, String where ) throws SQLException
	{
		String sql;
		if( where.equals( "" ) )
			 sql = "SELECT " + select + " FROM comment;";
		else
			 sql = "SELECT " + select + " FROM comment WHERE " + where + ";";
		
		ResultSet result = query.executeQuery(sql);
		return result;
	}
	
	/**
	 * Change the attributes from the chosen person
	 * 
	 * 	 * This function is used for the Use Cases:
	 * 		- " UC 3.15 - Manage User"
	 * 
	 * @param query - the sql statement 
	 * @param id - the id of the person
	 * @param name - the name of the person
	 * @param nick - the nickname of the person
	 * @param eMail - the E-Mail of the person
	 * @param pass - the password of the user
	 * @param age - the age of the user
	 * @return - either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException
	 */
	public static int UpdateDBPerson( Statement query, int id, String name, String nick, String eMail, String pass, int age ) throws SQLException
	{
		String sql = "UPDATE person SET name = '" + name + "', nickname = '" + nick + "', e_mail = '" + eMail + "', password = '" 
					 + pass + "', age = " + age + " Where id = " + id + ";";
		return query.executeUpdate( sql );
	}
	
	/**
	 * Change the attributes from the chosen tour
	 * 
	 * 	 * This function is used for the Use Cases:
	 * 		- " UC 3.15 - Manage User"
	 * 
	 * @param query - the sql statement
	 * @param id - the id of the tour
	 * @param name - the name of the tour
	 * @param loc - the location of the tour
	 * @param diff - the difficulty of the tour; (1) easy to (5) hard
	 * @param dist - the distance from your position to the location of the tour
	 * @param length - the length of the tour
	 * @param date - the upload date
	 * @param idUser - the id of the user, which has uploaded the tour
	 * @return - either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException
	 */
	public static int UpdateDBTour( Statement query, int id, String name, String desc,  String loc, int diff, int length, Date date ) throws SQLException
	{
		String sql = "UPDATE tour SET name = '" + name + "', describtion = '" + desc + "', location = '" + loc + "', difficulty = " 
					 + diff + ", length = " + length + ", date = " + date + "  Where id = " + id + ";";
		return query.executeUpdate( sql );
	}
	
	/**
	 * Change the attributes from the chosen comment
	 * 
	 * 	 * This function is used for the Use Cases:
	 * 		- " UC 3.16 - Manage Comments"
	 * 
	 * @param query - the sql statement
	 * @param id - the id of the comment
	 * @param text - the comment itself
	 * @return - either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException
	 */
	public static int UpdateDBComment ( Statement query, int id, String text ) throws SQLException
	{
		String sql = "UPDATE comment SET text = '" + text + "' WHERE id = " + id + ";";
		return query.executeUpdate( sql );
	}
	
	/**
	 * Deletes the entry with id "id" from the DB
	 * 
	 * 	 * This function is used for the Use Cases:
	 * 		- " UC 3.9 - Edit tours"
	 * 		- " UC 3.14 - Manage person"
	 * 		- " UC 3.15 - Manage tour"
	 * 		- " UC 3.16 - Manage comment"
	 * 
	 * @param query - the sql statement
	 * @param from
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static int DeleteDBPEntry( Statement query, String from, int id ) throws SQLException
	{
		String sql = "DELETE FROM " + from + " Where id = " + id + ";";
		return query.executeUpdate( sql );
	}

}