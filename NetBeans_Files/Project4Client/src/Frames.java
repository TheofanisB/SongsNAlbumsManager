
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;

import static java.awt.SystemColor.info;
import java.awt.event.*;
import java.io.*;
import static java.lang.String.valueOf;
import java.net.Socket;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.border.TitledBorder;
import org.sqlite.util.StringUtils;

public class Frames extends JFrame {

    protected static void mainmenu(Operations look_up) {//  main menu window 

        JFrame frame = new JFrame();
        JPanel row1, row2, row3, row4, row5, viewrow;
        JLabel info;

        JButton create_album_button, delete_album_button, add_song_button, remove_song_button, edit_playlist_button, edit_song_button, view_album_list, view_album, cancel;//User selection buttons

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.setTitle("Album Manager");// titlos parathirou
        frame.setLayout(new FlowLayout());

        row1 = new JPanel();
        row2 = new JPanel();
        row3 = new JPanel();
        row4 = new JPanel();
        row5 = new JPanel();
        viewrow = new JPanel();
        info = new JLabel("Pick an Option!");
// keimena koumpion
        create_album_button = new JButton("Create Album ");
        delete_album_button = new JButton("Delete Album");
        add_song_button = new JButton("Add Song");
        remove_song_button = new JButton("Remove Song");
        edit_playlist_button = new JButton("Edit Album");
        edit_song_button = new JButton("Edit Song");
        view_album_list = new JButton("View Album List");
        view_album = new JButton("View Album");

        cancel = new JButton("Exit");

        Container panel = frame.getContentPane();
        GridLayout layout = new GridLayout(6, 2);
        panel.setLayout(layout);
        FlowLayout flowlayout = new FlowLayout();

        row1.setLayout(flowlayout);
        row1.add(info);

        row2.setLayout(flowlayout);
        row2.add(create_album_button);
        row2.add(delete_album_button);

        row3.setLayout(flowlayout);
        row3.add(add_song_button);
        row3.add(remove_song_button);

        row4.add(edit_playlist_button);
        row4.add(edit_song_button);

        viewrow.setLayout(flowlayout);
        viewrow.add(view_album_list);
        viewrow.add(view_album);

        row5.add(cancel);

        frame.add(row1);
        frame.add(row2);
        frame.add(row3);
        frame.add(row4);
        frame.add(viewrow);
        frame.add(row5);

        cancel.addActionListener(new ActionListener() {// cancellation button
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

            }
        });
        create_album_button.addActionListener(new ActionListener() { // album creation album 
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                createalbum(look_up);

            }
        });
        delete_album_button.addActionListener(new ActionListener() { // Album Deletion Button 
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                deletealbum(look_up);

            }
        });
        add_song_button.addActionListener(new ActionListener() { // Buttons that lets us add a song to a playlist 
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                addsong(look_up);

            }
        });

        remove_song_button.addActionListener(new ActionListener() { //Button that deletes a song from a playlist  
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                removesong(look_up);

            }
        });
        edit_playlist_button.addActionListener(new ActionListener() { //Button that allows data modification of an album  
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                editplaylist(look_up);

            }
        });
        edit_song_button.addActionListener(new ActionListener() { //Button that allows us to modify a song's data  
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                editsong(look_up);

            }
        });
        view_album_list.addActionListener(new ActionListener() { //button that allows the user to read all the album names 
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                   
                    viewallalbums(look_up);
                } catch (RemoteException ex) {
                    Logger.getLogger(Frames.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        view_album.addActionListener(new ActionListener() { //button that shows all the data of an album 
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                viewalbum(look_up);

            }
        });

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    protected static void createalbum(Operations look_up) { //album creation window  

        JFrame frame = new JFrame();
        JPanel row1, row2, row3, row4, row5, row6;
        JLabel info, descr_label, photo_label, genre_label, yor_label;
        JTextField  description, photo, genre, yor;
        JButton add, cancel;

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.setTitle("New Album");
        frame.setLayout(new FlowLayout());
        FlowLayout flowlayout = new FlowLayout();

        row1 = new JPanel();
        info = new JLabel("Fill in the Album information");
        row1.setLayout(flowlayout);
        row1.add(info);

        row2 = new JPanel();

        descr_label = new JLabel("Title: ");
        description = new JTextField(40);
        row2.setLayout(flowlayout);
        row2.add(descr_label);
        row2.add(description);

        row3 = new JPanel();
        photo_label = new JLabel("Photo: ");
        photo = new JTextField(40);
        row3.setLayout(flowlayout);
        row3.add(photo_label);
        row3.add(photo);

        row4 = new JPanel();
        genre_label = new JLabel("Genre: ");
        genre = new JTextField(40);
        row4.setLayout(flowlayout);
        row4.add(genre_label);
        row4.add(genre);

        row5 = new JPanel();
        row5 = new JPanel();
        yor_label = new JLabel("Year of Release: ");
        yor = new JTextField(4);
        row5.setLayout(flowlayout);
        row5.add(yor_label);
        row5.add(yor);

        row6 = new JPanel();
        add = new JButton("Add Album");
        cancel = new JButton("Cancel");

        row6.setLayout(flowlayout);
        row6.add(add);
        row6.add(cancel);

        Container panel = frame.getContentPane();
        GridLayout layout = new GridLayout(7, 2);
        panel.setLayout(layout);

        frame.add(row1);
        frame.add(row2);
        frame.add(row3);
        frame.add(row4);
        frame.add(row5);
        frame.add(row6);

        cancel.addActionListener(new ActionListener() { //return button  
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                mainmenu(look_up);

            }
        });

        add.addActionListener(new ActionListener() {//Add an Album Button  .If the data are valid then theyre sent to the database 
            public void actionPerformed(ActionEvent e) {

                try {
                    if ((description.getText()).equals("") || (photo.getText()).equals("") || (genre.getText()).equals("") || (yor.getText()).equals("")) {//blank check
                        JOptionPane.showMessageDialog(frame, "Please, Fill the gaps!!!", "Error Message", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Album album1 = new Album(description.getText(), photo.getText(), genre.getText(), Integer.parseInt(yor.getText()));
                        boolean flag = look_up.addalbum(album1); //Checks  if the data are not in the database already elegxei
                        if (flag == true) { //returns true if the album was added 
                            System.out.println("Succesfully added!");
                            JOptionPane.showMessageDialog(null, "The Album was added!", "Success", JOptionPane.INFORMATION_MESSAGE);

                            frame.dispose();// closes the window 
                            mainmenu(look_up);//returns to the previous window
                        } else {// if it returns false then 
                            System.out.println("Failed to add the Album!");
                            JOptionPane.showMessageDialog(null, "The Album was not added", "Failure", JOptionPane.ERROR_MESSAGE);
                            description.setText("");//empties the entries since those values were invalid  
                            photo.setText("");
                            genre.setText("");
                            yor.setText("");
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(Frames.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    protected static void deletealbum(Operations look_up) {// delete album graphics 

        JFrame frame = new JFrame();
        JPanel row1, row2, row4, row5, row6;
        JLabel info, descr_label, genre_label, yor_label;
        JTextField  description,  genre, yor;
        JButton delete, cancel;

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.setTitle("Delete Album");
        frame.setLayout(new FlowLayout());
        FlowLayout flowlayout = new FlowLayout();

        row1 = new JPanel();
        info = new JLabel("Fill in the Album information");
        row1.setLayout(flowlayout);
        row1.add(info);

        row2 = new JPanel();

        descr_label = new JLabel("Title: ");
        description = new JTextField(40);
        row2.setLayout(flowlayout);
        row2.add(descr_label);
        row2.add(description);

        row4 = new JPanel();
        genre_label = new JLabel("Genre: ");
        genre = new JTextField(40);
        row4.setLayout(flowlayout);
        row4.add(genre_label);
        row4.add(genre);

        row5 = new JPanel();
        row5 = new JPanel();
        yor_label = new JLabel("Year of Release: ");
        yor = new JTextField(4);
        row5.setLayout(flowlayout);
        row5.add(yor_label);
        row5.add(yor);

        row6 = new JPanel();
        delete = new JButton("Delete Album");
        cancel = new JButton("Cancel");

        row6.setLayout(flowlayout);
        row6.add(delete);
        row6.add(cancel);

        Container panel = frame.getContentPane();
        GridLayout layout = new GridLayout(7, 2);
        panel.setLayout(layout);

        frame.add(row1);
        frame.add(row2);
        frame.add(row4);
        frame.add(row5);
        frame.add(row6);

        delete.addActionListener(new ActionListener() {//delete album button 
            public void actionPerformed(ActionEvent e) {

                try {
                    if ((description.getText()).equals("") || (genre.getText()).equals("") || (yor.getText()).equals("")) {
                        JOptionPane.showMessageDialog(frame, "Please, Fill the gaps!!!", "Error Message", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Album album1 = new Album(description.getText(), "", genre.getText(), Integer.parseInt(yor.getText()));
                        Boolean flag = look_up.deletealbum(album1);//sends the object that will be deleted 
                        if (flag == true) { //if it was deleted 
                            System.out.println("Egine i diagrafi!");
                            JOptionPane.showMessageDialog(null, "Album was succesfully deleted!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            frame.dispose();//closes the window 
                            mainmenu(look_up);//goes back to the main menu 
                        } else {//in case it returns false ,which means that the deletion was not done 
                            System.out.println("The album was not deleted");
                            JOptionPane.showMessageDialog(frame, "Unable to delete Album", "Error", JOptionPane.ERROR_MESSAGE);
                            description.setText("");
                            genre.setText("");
                            yor.setText("");
                        }

                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(Frames.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                mainmenu(look_up);

            }
        });

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    protected static void addsong(Operations look_up) { // menu that adds a new song 

        JFrame frame = new JFrame();
        JPanel row1, row2, row3, row4,row6, row7;
        JLabel info, title_label, artist_label, duration_label, album_name_label;
        JTextField title1, duration, artist, album_name;
        JButton add, cancel;

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.setTitle("New Song");
        frame.setLayout(new FlowLayout());
        FlowLayout flowlayout = new FlowLayout();

        row1 = new JPanel();
        info = new JLabel("Fill in the Song information");
        row1.setLayout(flowlayout);
        row1.add(info);

        row2 = new JPanel();

        title_label = new JLabel("Title: ");
        title1 = new JTextField(40);
        row2.setLayout(flowlayout);
        row2.add(title_label);
        row2.add(title1);

        row3 = new JPanel();
        artist_label = new JLabel("Artist: ");
        artist = new JTextField(40);
        row3.setLayout(flowlayout);
        row3.add(artist_label);
        row3.add(artist);

        row4 = new JPanel();
        duration_label = new JLabel("Duration: ");
        duration = new JTextField(40);
        row4.setLayout(flowlayout);
        row4.add(duration_label);
        row4.add(duration);

        row6 = new JPanel();
        album_name_label = new JLabel("Album Name: ");
        album_name = new JTextField(40);
        row6.setLayout(flowlayout);
        row6.add(album_name_label);
        row6.add(album_name);

        row7 = new JPanel();
        add = new JButton("Add Song");
        cancel = new JButton("Cancel");

        row7.setLayout(flowlayout);
        row7.add(add);
        row7.add(cancel);

        Container panel = frame.getContentPane();
        GridLayout layout = new GridLayout(8, 2);
        panel.setLayout(layout);
        frame.add(row1);
        frame.add(row2);
        frame.add(row3);
        frame.add(row4);
        frame.add(row6);
        frame.add(row7);

        cancel.addActionListener(new ActionListener() {//koumpi akirosis kai epistrofis sto main menu 
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                mainmenu(look_up);
            }
        });
        add.addActionListener(new ActionListener() {// koumpi prosthikis tragoudiou 
            public void actionPerformed(ActionEvent e) {

                try {

                    if ((title1.getText()).equals("") || (duration.getText()).equals("") || (artist.getText()).equals("") || (album_name.getText()).equals("")) {//blank check
                        JOptionPane.showMessageDialog(frame, "Please, Fill the gaps!!!", "Error Message", JOptionPane.ERROR_MESSAGE);
                    } else {//creates a song 
                        Song song1 = new Song(title1.getText(), duration.getText(), artist.getText(), album_name.getText());
                        Boolean flag = look_up.addsong(song1);//sends an addition request  

                        if (flag == true) { //if its true then the song addition was done  
                            System.out.println("The addition was done ");
                            JOptionPane.showMessageDialog(null, "The song was succesfully added", "Success!", JOptionPane.INFORMATION_MESSAGE);
                            frame.dispose();
                            mainmenu(look_up);
                        } else {// false which means the song addition wasnt done 
                            System.out.println("Failed to add a Song!");
                            JOptionPane.showMessageDialog(frame, "Adding the song was not possible", "Error", JOptionPane.ERROR_MESSAGE);

                            title1.setText("");
                            duration.setText("");
                            artist.setText("");
                            album_name.setText("");
                        }

                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(Frames.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame.dispose();
                mainmenu(look_up);

            }
        });

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    protected static void removesong(Operations look_up) { //song deletion menu 

        JFrame frame = new JFrame();
        JPanel row1, row2, row3, row4, row6, row7;
        JLabel info, title_label, artist_label, duration_label, album_name_label;
        JTextField title1, duration, artist, album_name;
        JButton remove, cancel;

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.setTitle("Remove a Song");
        frame.setLayout(new FlowLayout());
        FlowLayout flowlayout = new FlowLayout();

        row1 = new JPanel();
        info = new JLabel("Fill in the Song information");
        row1.setLayout(flowlayout);
        row1.add(info);

        row2 = new JPanel();

        title_label = new JLabel("Title: ");
        title1 = new JTextField(40);
        row2.setLayout(flowlayout);
        row2.add(title_label);
        row2.add(title1);

        row3 = new JPanel();
        artist_label = new JLabel("Artist: ");
        artist = new JTextField(40);
        row3.setLayout(flowlayout);
        row3.add(artist_label);
        row3.add(artist);

        row4 = new JPanel();
        duration_label = new JLabel("Duration: ");
        duration = new JTextField(40);
        row4.setLayout(flowlayout);
        row4.add(duration_label);
        row4.add(duration);

        row6 = new JPanel();
        album_name_label = new JLabel("Album Name: ");
        album_name = new JTextField(40);
        row6.setLayout(flowlayout);
        row6.add(album_name_label);
        row6.add(album_name);

        row7 = new JPanel();
        remove = new JButton("Remove Song");
        cancel = new JButton("Cancel");

        row7.setLayout(flowlayout);
        row7.add(remove);
        row7.add(cancel);

        Container panel = frame.getContentPane();
        GridLayout layout = new GridLayout(8, 2);
        panel.setLayout(layout);
        frame.add(row1);
        frame.add(row2);
        frame.add(row3);
        frame.add(row4);
        frame.add(row6);
        frame.add(row7);

        remove.addActionListener(new ActionListener() {//song removal button
            public void actionPerformed(ActionEvent e) {

                try {

                    if ((title1.getText()).equals("") || (duration.getText()).equals("") || (artist.getText()).equals("") || (album_name.getText()).equals("")) {//elegxos gia kena 
                        JOptionPane.showMessageDialog(frame, "Please, Fill the gaps!!!", "Error Message", JOptionPane.ERROR_MESSAGE);
                    } else {//if the data is valid   
                        Song song1 = new Song(title1.getText(), duration.getText(), artist.getText(), album_name.getText());
                        Boolean flag = look_up.removesong(song1);
                        if (flag == true) {//if the song was deleted 
                            System.out.println("Song Deleted!");
                            JOptionPane.showMessageDialog(null, "The song was succesfully deleted", "Success!", JOptionPane.INFORMATION_MESSAGE);
                            frame.dispose();
                            mainmenu(look_up);
                        } else {//if the song was not deleted 
                            JOptionPane.showMessageDialog(frame, "The song could not be deleted", "Error!", JOptionPane.ERROR_MESSAGE);
                            System.out.println("Could not delete the song");
                            title1.setText("");
                            duration.setText("");
                            artist.setText("");
                            album_name.setText("");
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(Frames.class.getName()).log(Level.SEVERE, null, ex);
                }

                frame.dispose();
                mainmenu(look_up);
            }
        });

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                mainmenu(look_up);
            }
        });

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    protected static void editplaylist(Operations look_up) {// edit info of playlist 

        JFrame frame = new JFrame();
        JPanel row1, row2, row3, row4, row6, row7;
        JLabel info, descr_label, photo_label, genre_label, yor_label;
        JTextField descr, photo, genre, yor;
        JButton cancel;
        JPanel newrow2, newrow3, newrow4, newrow6;
        JLabel new_descr_label, new_photo_label, new_genre_label, new_yor_label;
        JTextField new_descr, new_photo, new_genre, new_yor;
        JButton edit;
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.setTitle("Edit an Album");
        frame.setLayout(new FlowLayout());
        FlowLayout flowlayout = new FlowLayout();

        row1 = new JPanel();
        info = new JLabel("Fill in the information of the album");
        row1.setLayout(flowlayout);
        row1.add(info);

        row2 = new JPanel();

        descr_label = new JLabel("Description: ");
        descr = new JTextField(40);
        row2.setLayout(flowlayout);
        row2.add(descr_label);
        row2.add(descr);

        row3 = new JPanel();
        photo_label = new JLabel("Photo: ");
        photo = new JTextField(40);
        row3.setLayout(flowlayout);
        row3.add(photo_label);
        row3.add(photo);

        row4 = new JPanel();
        genre_label = new JLabel("Genre: ");
        genre = new JTextField(40);
        row4.setLayout(flowlayout);
        row4.add(genre_label);
        row4.add(genre);

        row6 = new JPanel();
        yor_label = new JLabel("Year of Release: ");
        yor = new JTextField(40);
        row6.setLayout(flowlayout);
        row6.add(yor_label);
        row6.add(yor);

        newrow2 = new JPanel();

        new_descr_label = new JLabel("New Description: ");
        new_descr = new JTextField(40);
        newrow2.setLayout(flowlayout);
        newrow2.add(new_descr_label);
        newrow2.add(new_descr);

        newrow3 = new JPanel();
        new_photo_label = new JLabel("New Photo: ");
        new_photo = new JTextField(40);
        newrow3.setLayout(flowlayout);
        newrow3.add(new_photo_label);
        newrow3.add(new_photo);

        newrow4 = new JPanel();
        new_genre_label = new JLabel("New Genre: ");
        new_genre = new JTextField(40);
        newrow4.setLayout(flowlayout);
        newrow4.add(new_genre_label);
        newrow4.add(new_genre);

        newrow6 = new JPanel();
        new_yor_label = new JLabel("New Year of Release: ");
        new_yor = new JTextField(40);
        newrow6.setLayout(flowlayout);
        newrow6.add(new_yor_label);
        newrow6.add(new_yor);

        row7 = new JPanel();
        edit = new JButton("Edit Album");
        cancel = new JButton("Cancel");

        row7.setLayout(flowlayout);
        row7.add(edit);
        row7.add(cancel);

        Container panel = frame.getContentPane();
        GridLayout layout = new GridLayout(13, 2);
        panel.setLayout(layout);
        frame.add(row1);
        frame.add(row2);
        frame.add(row3);
        frame.add(row4);
        frame.add(row6);
         frame.add(newrow2);
        frame.add(newrow3);
        frame.add(newrow4);
        frame.add(newrow6);
        frame.add(row7);

        edit.addActionListener(new ActionListener() {//modify info button  
            public void actionPerformed(ActionEvent e) {
                if ((descr.getText()).equals("") || (photo.getText()).equals("") || (genre.getText()).equals("") || (yor.getText()).equals("") // elegxos gia kena 
                     || (new_descr.getText()).equals("")   || (new_photo.getText()).equals("") || (new_genre.getText()).equals("")|| (new_yor.getText()).equals("")
                        
                        ) {
                    
                    JOptionPane.showMessageDialog(frame, "Please, Fill the gaps!!!", "Error Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    Album album1 = new Album(descr.getText(), photo.getText(), genre.getText(), Integer.parseInt(yor.getText()));// palia stoixeia
                    Album album2 =new Album(new_descr.getText(), new_photo.getText(), new_genre.getText(), Integer.parseInt(new_yor.getText()));// nea stoixeia 
                    boolean flag;
                    try {
                        flag = look_up.editAlbum( album1, album2);//album edit request  
                    } catch (RemoteException ex) {
                        Logger.getLogger(Frames.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (flag=true) { //if the modification was done then it returns true  
                        JOptionPane.showMessageDialog(null, "The album was succesfully edited", "Success! ", JOptionPane.INFORMATION_MESSAGE);
                        
                        
                    } else{ // if the modification was not done then it returns false  
                        JOptionPane.showMessageDialog(null, "We were unable to edit the album", "Error ", JOptionPane.ERROR_MESSAGE);
                        
                    }
                }

                frame.dispose();
                mainmenu(look_up);
            }
        });

        cancel.addActionListener(new ActionListener() {//return button  
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                mainmenu(look_up);
            }
        });

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    protected static void editsong(Operations look_up) {//song edit menu  

       JFrame frame = new JFrame();
        JPanel row1, row2, row3, row4, row5, row6, row7,oldrow,oldtitlerow,oldalbumrow,newrow;
        JLabel info, title_label, artist_label, duration_label, album_name_label,old_label,oldtitle_label,oldalbum_label,newrowlabel;
        JTextField title1, duration, artist, album_name,oldtitle,oldalbum;
        JButton edit, cancel;

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.setTitle("Edit Song");
        frame.setLayout(new FlowLayout());
        FlowLayout flowlayout = new FlowLayout();

        row1 = new JPanel();
        info = new JLabel("Edit the song information");
        row1.setLayout(flowlayout);
        row1.add(info);
        
        
      oldrow = new JPanel();
      oldrow.setLayout(flowlayout);
        old_label = new JLabel("Old information");
     oldrow.add(old_label);
        
        
          oldtitlerow = new JPanel();
      oldtitle_label = new JLabel("Old Title: ");
        oldtitle = new JTextField(40);
        oldtitlerow.setLayout(flowlayout);
        oldtitlerow.add(oldtitle_label);
        oldtitlerow.add(oldtitle);
      
          oldalbumrow = new JPanel();

       oldalbum_label = new JLabel("Old Album: ");
        oldalbum = new JTextField(40);
        oldalbumrow.setLayout(flowlayout);
        oldalbumrow.add(oldalbum_label);
        oldalbumrow.add(oldalbum);
        
        
        newrow= new JPanel();
         newrowlabel = new JLabel("New Information");
        newrow.setLayout(flowlayout);
        newrow.add(newrowlabel);
        

        row2 = new JPanel();

        title_label = new JLabel("Title: ");
        title1 = new JTextField(40);
        row2.setLayout(flowlayout);
        row2.add(title_label);
        row2.add(title1);

        row3 = new JPanel();
        artist_label = new JLabel("Artist: ");
        artist = new JTextField(40);
        row3.setLayout(flowlayout);
        row3.add(artist_label);
        row3.add(artist);

        row4 = new JPanel();
        duration_label = new JLabel("Duration: ");
        duration = new JTextField(40);
        row4.setLayout(flowlayout);
        row4.add(duration_label);
        row4.add(duration);

        row6 = new JPanel();
        album_name_label = new JLabel("Album Name: ");
        album_name = new JTextField(40);
        row6.setLayout(flowlayout);
        row6.add(album_name_label);
        row6.add(album_name);

        row7 = new JPanel();
        edit = new JButton("Edit Song");
        cancel = new JButton("Cancel");

        row7.setLayout(flowlayout);
        row7.add(edit);
        row7.add(cancel);

        Container panel = frame.getContentPane();
        GridLayout layout = new GridLayout(15, 2);
        panel.setLayout(layout);
        frame.add(row1);
        frame.add(oldrow);
        frame.add(oldtitlerow);
        frame.add(oldalbumrow);
        frame.add(newrow);
        frame.add(row2);
        frame.add(row3);
        frame.add(row4);
        frame.add(row6);
        frame.add(row7);

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                mainmenu(look_up);

            }
        });

        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if ((oldtitle.getText()).equals("") || (oldalbum.getText()).equals("")|| (title1.getText()).equals("")|| (artist.getText()).equals("")|| (duration.getText()).equals("")|| (album_name.getText()).equals("")) {
                    JOptionPane.showMessageDialog(frame, "Please, Fill the gaps!!!", "Error Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    //Song(String title, String artist, String duration, String albumname)
                    Song song1 = new Song(oldtitle.getText(), "", "", oldalbum.getText());//data before the edit 
                    Song song2 =new Song(title1.getText(),artist.getText(),duration.getText(),album_name.getText()); // data after they were edited 
                    JTable record;
                    boolean flag;
                    try {
                        flag = look_up.editSong(song1,song2);// song edit request 
                    } catch (RemoteException ex) {
                        Logger.getLogger(Frames.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (flag=true) {    // if the edit was done 
                        JOptionPane.showMessageDialog(frame, "The song was succesfully edited!!!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                        
                    } else { //if the edit was not done  
                        JOptionPane.showMessageDialog(frame, "The song was succesfully edited!!!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                    }

                }

                frame.dispose();
                mainmenu(look_up);
            }
        });

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    

    protected static void viewallalbums(Operations look_up) throws RemoteException { // view all the albums 

        JTable record = look_up.viewall();//request to view all the albums  
        if (record.getRowCount() == 0 || record.getRowCount() == 0) {    // if its empty https://stackoverflow.com/questions/867194/java-resultset-how-to-check-if-there-are-any-results
            System.out.println("No data");
            JOptionPane.showMessageDialog(null, "No Albums Found", "Empty Database", JOptionPane.INFORMATION_MESSAGE);
        } else { // if there's data in the database 
            System.out.println("Emfanisi Stoixeion");
            JOptionPane.showMessageDialog(null, new JScrollPane(record));
        }
    }

    protected static void viewalbum(Operations look_up) { //view info of an album menu 

        JFrame frame = new JFrame();
        JPanel row1, row2, row4, row5, row6;
        JLabel info, descr_label, genre_label, yor_label;
        JTextField description, genre, yor;
        JButton search, cancel;

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.setTitle("Search Album");
        frame.setLayout(new FlowLayout());
        FlowLayout flowlayout = new FlowLayout();

        row1 = new JPanel();
        info = new JLabel("Fill in the Album information you want to search");
        row1.setLayout(flowlayout);
        row1.add(info);

        row2 = new JPanel();

        descr_label = new JLabel("Title: ");
        description = new JTextField(40);
        row2.setLayout(flowlayout);
        row2.add(descr_label);
        row2.add(description);

        row4 = new JPanel();
        genre_label = new JLabel("Genre: ");
        genre = new JTextField(40);
        row4.setLayout(flowlayout);
        row4.add(genre_label);
        row4.add(genre);

        row5 = new JPanel();
        row5 = new JPanel();
        yor_label = new JLabel("Year of Release: ");
        yor = new JTextField(4);
        row5.setLayout(flowlayout);
        row5.add(yor_label);
        row5.add(yor);

        row6 = new JPanel();
        search = new JButton("Search Album");
        cancel = new JButton("Cancel");

        row6.setLayout(flowlayout);
        row6.add(search);
        row6.add(cancel);

        Container panel = frame.getContentPane();
        GridLayout layout = new GridLayout(7, 2);
        panel.setLayout(layout);

        frame.add(row1);
        frame.add(row2);

        frame.add(row4);
        frame.add(row5);
        frame.add(row6);

        cancel.addActionListener(new ActionListener() {//return back to the main menu button  
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                mainmenu(look_up);
            }
        });
        search.addActionListener(new ActionListener() {//button that searches the data and returns a JTable 
            public void actionPerformed(ActionEvent e) {

                if ((description.getText()).equals("") || (genre.getText()).equals("") || (yor.getText()).equals("")) {
                    JOptionPane.showMessageDialog(frame, "Please, Fill the gaps!!!", "Error Message", JOptionPane.ERROR_MESSAGE);
                } else {// if there's nothing missing
                    Album album1 = new Album(description.getText(), "", genre.getText(), Integer.parseInt(yor.getText()));

                    //  Song song1 = new Song(oldtitle.getText(), "", "", oldalbum.getText());
                    JTable record;
                    try {
                        record = look_up.searchalbum(album1);
                        if (record.getRowCount() == 0 || record.getRowCount() == 0) {    // if its empty https://stackoverflow.com/questions/867194/java-resultset-how-to-check-if-there-are-any-results
                            System.out.println("No data");
                            JOptionPane.showMessageDialog(null, "No data returned", "No such album ", JOptionPane.INFORMATION_MESSAGE);
                            description.setText("");
                            genre.setText("");
                            yor.setText("");

                        } else {
                            // record 
                            JOptionPane.showMessageDialog(null, new JScrollPane(record));
                            System.out.println("Emfanisi Tragoudion");
                            ///----------- Printing song info 
                            JTable record2 = look_up.songsOfAnAlbum(album1);
                            if (record2.getRowCount() == 0 || record2.getRowCount() == 0) {    // an einai adeio  https://stackoverflow.com/questions/867194/java-resultset-how-to-check-if-there-are-any-results
                                System.out.println("No data");
                                JOptionPane.showMessageDialog(null, "This Album has no songs", "No songs", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                System.out.println("Emfanisi Tragoudion");
                                JOptionPane.showMessageDialog(null, new JScrollPane(record2));
                            }
                            ///---- telos emfanisis tragoudion
                            frame.dispose();

                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(Frames.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                frame.dispose();
                mainmenu(look_up);
            }
        });

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
