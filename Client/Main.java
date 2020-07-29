

import java.net.*;
import java.io.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
//import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

/**
 *
 * @author Fanis
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
   //        System.setProperty("java.security.policy", "client.policy");  // policies 
   //   System.setSecurityManager(new RMISecurityManager()); // creating a security manager 
           
            String url="localhost";
            Operations look_up =(Operations) Naming.lookup(url);// RMI connection
            System.out.println(look_up.connection());//Connection Request
            Frames.mainmenu(look_up);////Loads up the main menu 
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
    
}
