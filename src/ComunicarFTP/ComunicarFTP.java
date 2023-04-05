/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComunicarFTP;

import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


/*
Necesitamos bajarnos la librearía coommons-net-3-8-0.jar
https://commons.apache.org/proper/commons-net/download_net.cgi

Levantamos el servidor FTP
*/
public class ComunicarFTP {
    public static void main(String[] args) throws IOException {
        
        FTPClient cliente = new FTPClient();
        String servFTP = "mail.damiansu.com"; //servidor FTP
        System.out.println("Nos conectamos a: "+servFTP);
        cliente.connect(servFTP);
        
        //respuesta del servidor FTP
        System.out.println(cliente.getReplyString());
        
        //código de respuesta
        int respuesta = cliente.getReplyCode();
        System.out.println("Respuesta: "+respuesta);
        
        //Comprobración del código de respuesta
        if (!FTPReply.isPositiveCompletion(respuesta)){
            cliente.disconnect();
            System.out.println("Conexión rechazada: "+respuesta);
            System.exit(0);
        }
        
        //Desconexión del servidor FTP
        cliente.disconnect();
        System.out.println("Conexión finalizada");
    }
    
}