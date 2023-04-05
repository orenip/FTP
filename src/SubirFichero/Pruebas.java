package SubirFichero;
	/*
	 * To change this license header, choose License Headers in Project Properties.
	 * To change this template file, choose Tools | Templates
	 * and open the template in the editor.
	 */


	import java.io.BufferedInputStream;
	import java.io.FileInputStream;
	import java.io.IOException;
	import org.apache.commons.net.ftp.FTP;
	import org.apache.commons.net.ftp.FTPClient;
	import org.apache.commons.net.ftp.FTPFile;

	/**
	 *
	 * @author damiansu
	 */
public class Pruebas {




		public static void main(String[] args) {
			FTPClient cliente = new FTPClient();
			String servidor = "mail.damiansu.com";
			String user = "alumnosuax";
			String pasw = "vasAprobarMiAsignatura";

			try {
				System.out.println("Conectándose a " + servidor);
				cliente.connect(servidor);
				cliente.enterLocalPassiveMode();
				boolean login = cliente.login(user, pasw);

				// vamos a enviar un archivo binario
				cliente.setFileType(FTP.BINARY_FILE_TYPE);
				//String direc = "nuevo_directorio";
				String direc = "alumnosuax";
				// NO hay que crear ese directorio en la ruta dónde te conectas
				// lo gestionamos abajo

				if (login) {
					System.out.println("Login correcto");
					// Si el directorio no existe, se crea

					if (!cliente.changeWorkingDirectory(direc)) {
						//String directorio = "nuevo_directorio";
						String directorio = "alumnosuax";
						if (cliente.makeDirectory(directorio)) {
							System.out.println("Directorio: " + directorio + " creado");
							cliente.changeWorkingDirectory(directorio);
						} else {
							System.out.println("No se ha podido crear el directorio");
							System.exit(0);
						}
					}

					System.out.println("Directorio actual: " + cliente.printWorkingDirectory());

					// stream de entrada con el fichero a subir
					// tiene que existir en esa ruta
					/*String archivo = "C:\\Users\\josan\\Desktop\\holaquepasa.txt";

					BufferedInputStream in = new BufferedInputStream(new FileInputStream(archivo));

					if (cliente.storeFile("holaquepasa.txt", in))
						System.out.println("Subido correctamente...");
					else
						System.out.println("No se ha podido subir...");
					*/
					 FTPFile[] file = cliente.listFiles();
			            System.out.println("Ficheros del directorio actual: "+file.length);
			            String tipos[] = {"Fichero", "Directorio", "Enlace simbólico"};
			            
			            for(int i = 0; i< file.length;i++){
			                System.out.println("\t"+file[i].getName() + " => " + tipos[file[i].getType()]);
			            }

					// para eliminar un fichero se utiliza deleteFile(). Devuelve un booleano

					String fichero = "/home/alumnosuax/nuevo_directorio/holaquepasa.txt";
					//String fichero = "holaquepasa.txt";
					if (cliente.deleteFile(fichero))
						System.out.println("Fichero eliminado... ");
					else
						System.out.println("No se ha podido eliminar  el fichero... ");
					
					FTPFile[] file2 = cliente.listFiles();
					System.out.println("Ficheros del directorio despues: "+file2.length);
					 String tipos2[] = {"Fichero", "Directorio", "Enlace simbólico"};
			            
			            for(int i = 0; i< file2.length;i++){
			                System.out.println("\t"+file2[i].getName() + " => " + tipos2[file2[i].getType()]);
			            }

				//	in.close(); // cerrar el flujo
					cliente.logout(); // logout del usuario
					cliente.disconnect(); // desconexión del servidor

				}

			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	//para renombrar un fichero se utiliza el método rename(nombreAntiguo, nombreNuevo)
	//Devuelve un booleano
	/*

	String direct = "/nuevo_directorio";
	cliente.changeWorkingDirectory(direct);
	if(cliente.remote("C1b3rwall_Academy_2020_Certificate.pdf","certificado_cyber.pdf"))
	    System.out.println("Fichero renombrado... ");
	else
	    System.out.println("No se ha podido renombrar  el fichero... ");


	*/

	//para eliminar un fichero se utiliza deleteFile(). Devuelve un booleano
	/*
	String fichero = "/nuevo_directorio/certificado_cyber.pdf";
	if(cliente.deleteFile(fichero))
	    System.out.println("Fichero eliminado... ");
	else
	    System.out.println("No se ha podido eliminar  el fichero... ");

	*/

	/*
	Crea un programa que permitea subir fichero al servidor FTP local, al directorio
	raíz del usuairo que se conecte. Utiliza la clase JFileChooser para seleccionar
	el fichero a subir de tu disco duro.
	Cuando termine la subida, se debe mostrar un mensaje indicándolo
	*/