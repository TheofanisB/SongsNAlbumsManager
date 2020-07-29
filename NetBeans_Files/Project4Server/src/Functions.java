
import java.sql.*;
import java.util.Vector;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Functions {
      private Connection connection;
     
      
      public Functions() {
        connection = null;
        try {
           
            Class.forName("org.sqlite.JDBC"); //Database Connection 
            connection = DriverManager.getConnection("jdbc:sqlite:albumDB.db");
            System.out.println("Connection to Database success!!");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public  void createAlbumTable(){// table creation method 
       
        Statement st = null;
        try {
            st = connection.createStatement();//creates a statement so were able to use SQL 
            String sql_create_statement = "CREATE TABLE IF NOT EXISTS ALBUMS (\n"
                    + " DESCRIPTION TEXT PRIMARY KEY NOT NULL, \n"
                    + " PHOTO TEXT NOT NULL, \n"
                    + " GENRE TEXT NOT NULL, \n"
                    + " YOR INT NOT NULL\n"
                    + ");";
 //ftiaxnei to string pou tha periexei SQL kodika
            st.executeUpdate(sql_create_statement);// ektelesi SQL kodika
            System.out.println("Album Table was succesfully created");
            st.close();// kleinei to statement
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
      public void createSongTable(){// method that creates song table 
       
        Statement st = null;// creates statement statement
        try {
            st = connection.createStatement();//  connects the statement with the database 
            String sql_create_statement = "CREATE TABLE IF NOT EXISTS  SONGS (\n"
                    + "TITLE      TEXT   PRIMARY KEY      NOT NULL, \n"
                    + " ARTIST            TEXT             NOT NULL, \n"
                    + " DURATION          VARCHAR          NOT NULL,\n "
                    + " ALBUMNAME         TEXT             NOT NULL \n" 
                    + ");"; // SQL code in a string  
            st.executeUpdate( sql_create_statement );//executing the sql code  
            System.out.println("Song was succesfully created");
            st.close();// closing the  statement
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    
    
      public void dropAlbumTable() {//method that deletes the album table 
        Statement st = null;
        String sql_update = "DROP TABLE ALBUMS;";
        try {
            st = this.connection.createStatement();
            st.executeUpdate(sql_update);
        } catch (SQLException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
       public Connection getConnection() {
        return this.connection;
    }
       
       
  
       
       
}
    
    
    
    
    
    

