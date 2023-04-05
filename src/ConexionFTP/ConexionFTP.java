/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionFTP;

import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author damiansu
 */
public class ConexionFTP {
    public static void main(String[] args) {
        FTPClient cliente = new FTPClient();
        String servFTP = "mail.damiansu.com";
        System.out.println("Nos conectamos a: "+ servFTP);
        String usuario = "alumnosuax";
        String clave = "vasAprobarMiAsignatura";
        try{
            cliente.connect(servFTP);
            cliente.enterLocalPassiveMode();//modo Pasivo
            
            boolean login = cliente.login(usuario, clave);
            if(login)
                System.out.println("Login correcto....");
            else{
                System.out.println("Login incorrecto...");
                cliente.disconnect();
                System.exit(1);
            }
            cliente.changeWorkingDirectory("/home/alumnosuax/nuevo_directorio");
            System.out.println("Directorio actual: "+ cliente.printWorkingDirectory());
            FTPFile[] file = cliente.listFiles();
            System.out.println("Ficheros del directorio actual: "+file.length);
            
            //array para visualizar el tipo de fichero
            String tipos[] = {"Fichero", "Directorio", "Enlace simbólico"};
            
            for(int i = 0; i< file.length;i++){
                System.out.println("\t"+file[i].getName() + " => " + tipos[file[i].getType()]);
            }
            
            boolean logout = cliente.logout();
            if(logout)
                System.out.println("Logout del servidor FTP...");
            else
                System.out.println("Error al hacer logout... ");
            
            cliente.disconnect();
            System.out.println("Desconectando....");
   
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        
    }
    
}