
import javax.swing.*;
import java.net.*;
import java.rmi.*;
import java.sql.ResultSet;
import java.util.ArrayList;

public interface Operations extends Remote {

    public String connection() throws RemoteException;// Connection Request

    public boolean addalbum(Album album1) throws RemoteException; // Album addition request

    public boolean deletealbum(Album album1) throws RemoteException; // album deletion request

    public boolean addsong(Song song1) throws RemoteException;// song addition request

    public boolean removesong(Song song1) throws RemoteException;// song deletion request

    public JTable searchsong(Song song1) throws RemoteException;// song search request 

    public JTable searchalbum(Album album1) throws RemoteException;// album search request 

    public boolean editSong(Song song1, Song song2) throws RemoteException;// song edit request 

    public boolean editAlbum(Album album1, Album album2) throws RemoteException; // edit album request  

    public JTable viewall() throws RemoteException; //view all names of albums request 

    public JTable songsOfAnAlbum(Album album1) throws RemoteException; //request to show all songs of an album  aitima 

}
