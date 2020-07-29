
import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
         
    //  System.setProperty("java.security.policy", "server.policy");
    //   System.setSecurityManager(new SecurityManager()); 
        try {
           
            ServerImplementation s= new ServerImplementation();// creates a server 
            Registry r = java.rmi.registry.LocateRegistry.createRegistry(1099);
            Naming.rebind("localhost", s);//binds the ip for the server 
            System.out.println("RMI Database Server is running ...");
          //  System.setProperty("Djava.rmi.server.codebase", "rmi://localhost/ServerImplementation/");
             Functions f1=new Functions(); // creates an object to do certain database functions 
             f1.createSongTable();//creates the table that the songs are saved in 
            f1.createAlbumTable();//creates the table that the albums are saved in
        } catch (Exception ex) {
            ex.printStackTrace();// in case of any issue it shows where the issue was caused and the path that the compiler went through 
        }
    }
}
