// GradedExercise_4_1: OddenDisplayAuthors.java
// Displaying the contents of the authors table.
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class OddenDisplayAuthors 
{
   public static void main(String args[])
   {
	   final String query1 = "SELECT AuthorID, FirstName, LastName FROM Authors"
	   		+ " WHERE LastName LIKE 'D%'";
	   final String query2 = "SELECT AuthorID, FirstName, LastName FROM Authors"
	   		+ " ORDER BY LastName ASC";
	   final String query3 = "SELECT AuthorID, FirstName, LastName FROM Authors"
		   		+ " ORDER BY LastName DESC";
	   final String query4 = "SELECT FirstName, LastName, ISBN FROM Authors a"
		   		+ " INNER JOIN AuthorISBN ai ON a.AuthorID = ai.AuthorID"
		   		+ " ORDER BY LastName, FirstName";
	   final String query5 = "SELECT AuthorID, FirstName, LastName FROM Authors";
	   
	   queryDB("Authors table where last name starts with a 'D':", query1);
	   queryDB("\nAuthors table ordered by ascending last name:", query2);
	   queryDB("\nAuthors table ordered by descending last name:", query3);
	   queryDB("\nAuthors table where authorID matches the authorID associated with the ISBN:", query4);
	   queryDB("\nAuthor names and IDs from Authors table:", query5);
   } // end main
   
   public static String queryDB(String info, String query) {
	   
	   final String DATABASE_URL = "jdbc:derby:C:\\Users\\Student\\Documents\\CIS319\\JavaProjects\\Odden_GuidedPractice_4_3\\bookDB\\books";
	   // use try-with-resources to connect to and query the database
      try (  
         Connection connection = DriverManager.getConnection(
            DATABASE_URL, "deitel", "deitel"); 
         Statement statement = connection.createStatement(); 
         ResultSet resultSet = statement.executeQuery(query))
      {
         // get ResultSet's meta data
         ResultSetMetaData metaData = resultSet.getMetaData();
         int numberOfColumns = metaData.getColumnCount();     
         
         System.out.print(info + "\n\n");

         // display the names of the columns in the ResultSet
         for (int i = 1; i <= numberOfColumns; i++) {
        	 System.out.print("\t");
        	 System.out.print(metaData.getColumnName(i));
         }
         System.out.println();
         
         // display query results
         while (resultSet.next()) 
         {
            for (int i = 1; i <= numberOfColumns; i++) {
            	if (i == 1) {
            		System.out.print("\t");
                 	System.out.print(resultSet.getObject(i));
            	} else {
            		System.out.print("\t\t");
                 	System.out.print(resultSet.getObject(i));
            	}
            }
            System.out.println();
         } 
      } // AutoCloseable objects' close methods are called now 
      catch (SQLException sqlException)                                
      {                                                                  
         sqlException.printStackTrace();
      }
	return DATABASE_URL; 
   }
} // end class OddenDisplayAuthors