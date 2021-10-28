package es.florida.AD_AE02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Modelo {
	
	private String fichero_lectura;
	private String fichero_escritura;
	
	public Modelo() {
		fichero_lectura = "AE02_T1_2_Streams_Groucho.txt";
		fichero_escritura = "AE02_T1_2_Streams_Groucho_copia.txt";
		
	}
	
	public ArrayList<String> contenidoFichero(String fichero){
		
		ArrayList<String> contenidoFichero = new ArrayList<String>();
		
		File f = new File(fichero);
		
		try {
			
			FileReader fr =  new FileReader(f, StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(fr);
			
			String linea = br.readLine();
			
			while(linea != null) {
				
				contenidoFichero.add(linea);
				linea = br.readLine();
			}
			
			br.close();
			fr.close();
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);//para mostrar por popup Mirar más!
			
		}
		
		return contenidoFichero;
		
	}
	
	public String FicheroEscritura() {
		return fichero_escritura;
	}
	
	public String FicheroLectura() {
		return fichero_lectura;
	}
	
	
	public int BuscarPalabra(String palabra) throws IOException, InterruptedException {
		
		int contador = 0;
		
		File fichero = new File(FicheroLectura());
		
		FileReader fr = new FileReader(fichero,StandardCharsets.UTF_8);
		BufferedReader br = new BufferedReader(fr);
		
		String contenido;
		
		while((contenido = br.readLine()) != null) {
			
			if ( contenido.toLowerCase().indexOf(palabra.toLowerCase()) != -1 ) {

				 contador++;

				}
		}
		
		JOptionPane.showMessageDialog(null,contador,"Palabra "+ "'" + palabra  + "'" + " encontradas: ",JOptionPane.INFORMATION_MESSAGE);
		
		br.close();
		fr.close();
		
		return contador;
		
	}
	
	public void RemplazarPalabra(String palabraRemplazar, String palabraBuscar) throws IOException, InterruptedException {
			
		
			File fichero = new File(FicheroLectura());
			File ficheroNuevo = new File(FicheroEscritura());
			
			FileReader fr = new FileReader(fichero,StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(fr);
			
			FileWriter fw = new FileWriter(ficheroNuevo,StandardCharsets.UTF_8);
			BufferedWriter bw = new BufferedWriter(fw);
			
			String contenido;
			
			while((contenido = br.readLine()) != null) {
				
					if ( contenido.toLowerCase().indexOf(palabraBuscar.toLowerCase()) != -1 ) {

					   bw.write(contenido.replaceAll(palabraBuscar, palabraRemplazar));
					  

					} else {

					   System.out.println("not found");
					 bw.write(contenido);

					}
				
				bw.newLine();
			}
			
			br.close();
			fr.close();
			bw.close();
			fw.close();;
		}

}
