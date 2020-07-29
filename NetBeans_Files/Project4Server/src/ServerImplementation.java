
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
import java.rmi.*;
import java.rmi.server.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class ServerImplementation extends UnicastRemoteObject implements Operations {

    Functions f1 = new Functions();
    ArrayList<Song> SongList = new ArrayList();

    public ServerImplementation() throws Exception {//creating a server object 
        super();

    }

    public String connection() { // returns a  "Connected"  String
        return "Connected";
    }

    public boolean addalbum(Album album1) { // method that creates a new album 
        Statement st = null;
        synchronized (this) {
        try {
            st = (f1.getConnection()).createStatement();
            (f1.getConnection()).setAutoCommit(false);
            //String description, String photo, String genre, int year_of_release
            String sql_insert_statement = "INSERT INTO ALBUMS (DESCRIPTION, PHOTO,GENRE,YOR) "
                    + "VALUES ('" + album1.getDescription() + "','" + album1.getPhoto() + "','" + album1.getGenre() + "'," + album1.getYear_of_release() + ");";//sql code that creates a new table
            st.executeUpdate(sql_insert_statement);//executing the SQL statement 
            st.close();//closing statement
            (f1.getConnection()).commit();
            System.out.println("Album inserted successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
        }
    }

    public boolean addsong(Song song1) {// mMethod that creates a song 
        Statement st = null;
        synchronized (this) {
        try {
            st = (f1.getConnection()).createStatement();// Creates a statement
            (f1.getConnection()).setAutoCommit(false);

            // String title, String artist, String duration, String albumname
            String sql_insert_statement = "INSERT INTO SONGS (TITLE, ARTIST,DURATION,ALBUMNAME) " 
                    + "VALUES ('" + song1.getTitle() + "','" + song1.getArtist() + "','" + song1.getDuration() + "','" + song1.getAlbumname() + "');";//sql statement to add a song
           System.out.println("The statement is " +sql_insert_statement);
            st.executeUpdate(sql_insert_statement);//executing the command 
            st.close();
            (f1.getConnection()).commit();
            System.out.println("Song inserted successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
        }
    }

    public boolean removesong(Song song1) { // song deletion method 
        {
            synchronized (this) {
            Statement st = null;
            try {
                st = (f1.getConnection()).createStatement();
                (f1.getConnection()).setAutoCommit(false);
                // String title, String artist, String duration, String albumname
                String sql_insert_statement = "DELETE FROM SONGS WHERE (\n"
                        + "TITLE= '" + song1.getTitle() 
                        + "' AND ARTIST= '" + song1.getArtist() 
                        + "' AND DURATION='" + song1.getDuration() 
                       + "' AND ALBUMNAME='"  + song1.getAlbumname() 
                        + "');"; // sql 
                st.executeUpdate(sql_insert_statement);
                st.close();
                (f1.getConnection()).commit();
                System.out.println("Clients inserted successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;//if there was an issue executing the SQL command it will return false  

            }
            return true;// if everything went accordingly it returns true .
        }
        }
    }

    public boolean deletealbum(Album album1) {// Deletes the album and the songs that belong to it 
        {
            Statement st = null;
            synchronized (this) {
            try {
                st = (f1.getConnection()).createStatement();
                (f1.getConnection()).setAutoCommit(false);
                String sql_insert_statement = "DELETE FROM ALBUMS WHERE (\n"
                       + "DESCRIPTION= '" + album1.getDescription() 
                        + "' AND GENRE='" + album1.getGenre() 
                        + "'AND YOR='" + album1.getYear_of_release() 
                        + "');";//entoli gia diagrafi ton album
                st.executeUpdate(sql_insert_statement);//ektelesi entolis 

                st.close();
                (f1.getConnection()).commit();
                System.out.println("Clients inserted successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false; //issue deleting the album in SQL
            }
            return true; // deletion was done succesfully 
            }

        }

    }

    public JTable searchsong(Song song1) { // method that searches for a song 
        Statement st = null;
        ResultSet records = null;
        try {
            st = (f1.getConnection()).createStatement();
            (f1.getConnection()).setAutoCommit(false);

            //Song(String title, String artist, String duration, String albumname)
            //String description, String photo, String genre, int year_of_release
            String sql_insert_statement = "SELECT * FROM SONGS WHERE "
                    + "(TITLE='" + song1.getTitle() 
                    + "' AND ARTIST='" + song1.getArtist() 
                    + "' AND DURATION='" + song1.getDuration() 
                    + "' AND ALBUMNAME='" + song1.getAlbumname() + "');";//sql command to find a song 
            records = st.executeQuery(sql_insert_statement);//executing the command 
            st.close();
            (f1.getConnection()).commit();//Permamently update the DB
            System.out.println("Search Complete!");
             return resultSetToTable(records);//converts the ResultSet to a JTable
        } catch (SQLException ex) {
            ex.printStackTrace();
             return null;// in case of an empty table 
        }

       // return null;
    }

    public JTable searchalbum(Album album1) {
        Statement st = null;
        ResultSet records = null;
        try {
            st = (f1.getConnection()).createStatement();
            (f1.getConnection()).setAutoCommit(false);

            //Song(String title, String artist, String duration, String albumname)
            //String description, String photo, String genre, int year_of_release
            String sql_insert_statement = "SELECT * FROM ALBUMS WHERE  "
                    + "( DESCRIPTION='" + album1.getDescription() 
                    + "'AND PHOTO='" + album1.getPhoto() 
                    + "' AND GENRE= '" + album1.getGenre() 
                    + "' AND YOR='" + album1.getYear_of_release() 
                    + "');";// entol
            records = st.executeQuery(sql_insert_statement);//puts the results in a result set 
            st.close();
            (f1.getConnection()).commit();
            System.out.println("Returned results!");
             return resultSetToTable(records);// ResultSet->JTable 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public JTable songsOfAnAlbum(Album album1){// Method that turns a ResultSet of an album to a JTable
        Statement st = null;
        ResultSet records = null;
        try {
            st = (f1.getConnection()).createStatement();
            (f1.getConnection()).setAutoCommit(false);

           
            String sql_insert_statement = "SELECT * FROM SONGS WHERE  ( ALBUMNAME='" + album1.getDescription() + "');";
            records = st.executeQuery(sql_insert_statement);
            st.close();
            (f1.getConnection()).commit();
            System.out.println("Returned results!");
             return resultSetToTable(records);//returns the songs to a JTable 
        } catch (SQLException ex) {//returns an empty  JTable
            ex.printStackTrace();
              return null;
        }

      
    }
    
    
    
    
    public boolean editSong(Song song1, Song song2){// methodos gia epeksergasia stoixeion tragoudiou
      //  JTable flag=searchsong(song1);
      //  if (flag.getRowCount() == 0 || flag.getRowCount() == 0)
     //   {
     //   return false;
    //    }
     //   else
     //   {
             Statement st = null; //creating a statement so we can execute SQL commands  
       
        try {
            st = (f1.getConnection()).createStatement();
            (f1.getConnection()).setAutoCommit(false);
            //UPDATE SONGS SET TITLE='1234', ARTIST='1234', DURATION='1234', ALBUMNAME='1234' WHERE TITLE='1234'  AND ALBUMNAME='1234';
String sql_statement = "UPDATE  SONGS "+
        "SET TITLE='" + song2.getTitle() 
        + "', ARTIST='" + song2.getArtist() 
        + "', DURATION=' " + song2.getDuration() 
        + "', ALBUMNAME= '" + song2.getAlbumname() + "'"
        + "WHERE (TITLE='" + song1.getTitle() 
        + "'AND  ALBUMNAME='" + song1.getAlbumname()
        + "');";// entoli SQL gia enimerosi tou tragoudiou
System.out.println("The statement is " +sql_statement);
     st.executeQuery(sql_statement);//executing SQL
            st.close();
            (f1.getConnection()).commit();//Permamently update the DB
            System.out.println("Search Complete!");
       } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
               return true;
      //  }
        
        
        
        
        
        
        
       
}
    
    
    
    
    public boolean editAlbum(Album album1,Album album2){//Method that edits an album 
   //     JTable flag=searchalbum(album1);
    //    if (flag.getRowCount() == 0 || flag.getRowCount() == 0)//den ipirxe ara girnaei false gt dn mporouse na ginei edit
     //   {
    //    return false;
     //   }
    //    else
     //   {
             Statement st = null;
        
        try {
            st = (f1.getConnection()).createStatement();
            (f1.getConnection()).setAutoCommit(false);
            //UPDATE albums SET description='1234', photo='1234', genre='1234', yor='1234' WHERE description='1234' AND photo='1234' AND genre='1234' AND yor='1234';
String sql_statement = "UPDATE  ALBUMS "
        +"SET DESCRIPTION='" + album1.getDescription() + "',PHOTO='" + album2.getPhoto() + "',GENRE='" + album2.getGenre() + "',YOR='" + album2.getYear_of_release() + "'"
        +"WHERE ( DESCRIPTION='" + album1.getDescription() 
        + "'AND PHOTO='" + album1.getPhoto() 
        + "'AND GENRE='" + album1.getGenre() 
        + "'AND YOR=" + album1.getYear_of_release()
        + ");";
System.out.println("The statement is " +sql_statement);
     st.executeQuery(sql_statement);//executing UPDATE data
            st.close();
            (f1.getConnection()).commit();
            System.out.println("Search Complete!");
       } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
               return true;
      //  }
        
        
        
        
        
        
        
       
}
    
    
    
    
    
    
    public JTable viewall() {//method that shows all the album names in the database 
        Statement st = null;
        ResultSet records = null;
        try {
            st = (f1.getConnection()).createStatement();
            (f1.getConnection()).setAutoCommit(false);

            //Song(String title, String artist, String duration, String albumname)
            //String description, String photo, String genre, int year_of_release
            String sql_insert_statement = "SELECT DESCRIPTION FROM ALBUMS ;";
            records = st.executeQuery(sql_insert_statement);
            st.close();
            (f1.getConnection()).commit();
            System.out.println("Returned results!");
            return resultSetToTable(records);//results in JTable form 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      return null;
      
    }
    
    
           
        protected static JTable resultSetToTable(ResultSet result) throws SQLException{
    
     JTable table = new JTable(buildTableModel( result));// corverts the result to JTable

    // Closes the Connection

    // and that's how i print the data  
     //JOptionPane.showMessageDialog(null, new JScrollPane(table));
    
    
    return table;
    
    }
        
        
        
        public static DefaultTableModel buildTableModel(ResultSet rs) //https://stackoverflow.com/questions/10620448/most-simple-code-to-populate-jtable-from-resultset
        throws SQLException {

    ResultSetMetaData metaData = rs.getMetaData();

    // names of columns
    Vector<String> columnNames = new Vector<>();
    int columnCount = metaData.getColumnCount();
    for (int column = 1; column <= columnCount; column++) {
        columnNames.add(metaData.getColumnName(column));
    }

    // data of the table
    Vector<Vector<Object>> data = new Vector<>();
    while (rs.next()) {
        Vector<Object> vector = new Vector<>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {// creates table and columns 
            vector.add(rs.getObject(columnIndex));
        }
        data.add(vector);
    }

    return new DefaultTableModel(data, columnNames);

}

   
        
      
    
    
    
    

}
