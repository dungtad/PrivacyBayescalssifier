
import java.sql.*;

public class FirstExample {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://192.168.124.99/KMA";

   //  Database credentials
   static final String USER = "tad";
   static final String PASS = "123456";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      //String sql;
      //sql = "SELECT * FROM bayes";
      //for(int i=0;i<99;++i){
      ResultSet rs = stmt.executeQuery("SELECT * FROM bayes");
     // System.out.print("resss: " + rs);

      //STEP 5: Extract data from result set
      System.out.println("Table\n");
      System.out.println("ID\t   Outlook\t   Temperature\t    Humidity\t      Windy     \tClass");

      while(rs.next()){
         //Retrieve by column name
         int id  = rs.getInt("id");
         String Outlook = rs.getString("Outlook");
         String Temperature = rs.getString("Temperature");
         String Humidity = rs.getString("Humidity");
         String Windy = rs.getString("Windy");
         String Class = rs.getString("Class");
         
         System.out.print(id+"\t\t"+Outlook+"\t\t"+Temperature+"\t\t"+Humidity+"\t\t"+Windy+"\t\t"+Class);
         System.out.println();
         //Display values
         //System.out.print("ID: " + id);
         //System.out.print(", Outlook: " + Outlook);
         //System.out.print(", Temperature: " + Temperature);
         //System.out.print(", Humidity: " + Humidity);
         //System.out.print(", Windy: " + Windy);
         //System.out.println(", Class: " + Class);
      }
      //STEP 6: Clean-up environment
      rs.close();
   //}
      stmt.close();
      conn.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}//end FirstExample