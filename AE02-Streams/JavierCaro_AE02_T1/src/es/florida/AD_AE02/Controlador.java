package es.florida.AD_AE02;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Controlador {
	
	private Modelo modelo;
	private Vista vista;
	private ActionListener actionListenerBuscar;
	private ActionListener actionListenerRemplazar;
	private String ficheroLectura, ficheroEscritura;
	
	public Controlador (Modelo modelo , Vista vista) {
		
		this.modelo = modelo;
		this.vista = vista;
		
		Control();
	}
	
	public void Control(){
		
		ficheroLectura = modelo.FicheroLectura();
		
		ficheroEscritura = modelo.FicheroEscritura();
		
		MostrarFichero(ficheroLectura,1);
		
		actionListenerBuscar = new ActionListener() { //actua sobre el boton!
			
			public void actionPerformed(ActionEvent actionEvent) {
				
				String palabraBuscar = vista.getTextFieldBuscar().getText(); //String textoAñadir = vista.getTextField().getText();
				
				try {
					
					modelo.BuscarPalabra(palabraBuscar);
					
					
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//modelo.AñadirTexto(textoAñadir);
				
			}
			
			
			
		};
		
		actionListenerRemplazar = new ActionListener() { //actua sobre el boton!
					
					public void actionPerformed(ActionEvent actionEvent) {
						
						vista.getTextAreaModificado().setText("");
						
						String palabraRemplazar = vista.getTextFieldReemplazar().getText(); //String textoAñadir = vista.getTextField().getText();
						String palabraBuscar = vista.getTextFieldBuscar().getText();
						
						try {
							
							modelo.RemplazarPalabra(palabraRemplazar, palabraBuscar );
							//modelo.AñadirTexto(textoAñadir);
							MostrarFichero(ficheroEscritura,2);
							
						} catch (IOException | InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
					
					
				};
		
		vista.getBtnBuscar().addActionListener(actionListenerBuscar); //vista.getAñadir().addActionListener(actionListenerAñadir);
		vista.getBtnReemplazar().addActionListener(actionListenerRemplazar);
	}
	
	private void MostrarFichero(String fichero, int numeroTextArea) {
		
		ArrayList<String> arrayLineas = modelo.contenidoFichero(fichero);
		
		for(String linea : arrayLineas) {
			
			if(numeroTextArea == 1)
				vista.getTextAreaOriginal().append(linea + "\n");//linea a line ava rellenando el text area.
			else
				vista.getTextAreaModificado().append(linea + "\n");
		}
		
	}
}
