package es.florida.mongodb;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import es.florida.mongodb.Vista;

//clase controlador que contiene el metodo Control, ademas de tener los atributos de los actionlistener y clases
public class Controlador {
	
	private Modelo modelo;
	private Vista vista;
	private ActionListener actionListenerMostrarTodosLosTitulos;
	private ActionListener actionListenerMostrarInfoDetalladaLibro;
	private ActionListener actionListenerCrearLibro;
	private ActionListener actionListenerActualizarLibro;
	private ActionListener actionListenerBorrarLibro;
	private ActionListener actionListenerCerrarBiblioteca;
	private String textoMenu;
	
	//constructor
	public Controlador (Modelo modelo , Vista vista) {
		
		this.modelo = modelo;
		this.vista = vista;
		
		Control();
	}
	
	public void Control(){
		
		//muestra en el menu el texto del menu recibido de modelo
		textoMenu = modelo.TextoMenu();
		vista.getTextAreaMenu().setText(textoMenu);
		
		
		//Acciones que se ejecutaran al pulsar los botones
		actionListenerMostrarTodosLosTitulos = new ActionListener() { 
			
			public void actionPerformed(ActionEvent actionEvent) {
			
				  List<Libro> listaLibros = null;
				  
				try {
					//Guardamos en la lista de todos los libros obtenidos de modelo y a funcion recuperar todos.
					listaLibros = Modelo.recuperarTodos();
					
					//Variable texto que sera la que se muestre en el Panel
					String texto = "Todos los libros." + System.lineSeparator();
					
					//For que recorre todos los libros y va añadiendo al texto su informacion.
					for(Libro libro : listaLibros) {
						   
						//texto += System.lineSeparator() + "Identificador: " + libro.identificador + "\n";
						texto += "ID Libro: " + libro.idLibro + " Titulo: " + libro.titulo + System.lineSeparator();
						  
						//Muestra la informacion de la variable texto en el panel AreaOriginal.
						  vista.getTextAreaOriginal().setText(texto);
					   }
				} catch (ParserConfigurationException | SAXException | IOException e) {
					e.printStackTrace();
				}
			}
			
		};
		actionListenerCrearLibro = new ActionListener() {
			
			public void actionPerformed(ActionEvent actionEvent) {
				
				try {
					
					//If que comprueba si en los campos oportunos se introduce un numero o una letra
					if(Modelo.isNumeric(vista.getTextFieldAñoDeNacimiento().getText()) == true && Modelo.isNumeric(vista.getTextFieldAñoDePublicacion().getText()) == true &&
							Modelo.isNumeric(vista.getTextFieldNumeroPaginas().getText()) == true) {
						
						//Almacenamos en variables la informacion de los textField
						String titulo = vista.getTextFieldTitulo().getText();
						String autor = vista.getTextFieldAutor().getText();
						String año_nacimiento = vista.getTextFieldAñoDeNacimiento().getText();
						String año_publicacion = vista.getTextFieldAñoDePublicacion().getText();
						String editorial = vista.getTextFieldEditorial().getText();
					   	String numero_paginas = vista.getTextFieldNumeroPaginas().getText();
						
					   	//Creamos variable de texto que mostraremos en panel
						String textCrearLibro = "";
						
						//Añadimos al texto la informacion del libro
						textCrearLibro += "Creando libro..." + System.lineSeparator() + System.lineSeparator() +
								           "Título: " + titulo + System.lineSeparator() +
								           "Autor: " + autor + System.lineSeparator() +
								           "Año de nacimiento: "+ año_nacimiento + System.lineSeparator() +
								           "Año de publicación: "+ año_publicacion + System.lineSeparator() +
								           "Editorial: " + editorial + System.lineSeparator() +
								           "Número de páginas: " + numero_paginas + System.lineSeparator() + System.lineSeparator() +
								           "¡Libro creado exitosamente!";
						
						//Mostramos el libro por el panel 
						vista.getTextAreaOriginal().setText(textCrearLibro);
						
						//Creamos un nuevo libro
						Libro newLibro = new Libro(titulo,autor,año_nacimiento,año_publicacion,editorial,numero_paginas);
					   
						//Le pasamos a la funcion crear libro desde la clase Modelo el nuevo libro por parametro.
					   Modelo.crearLibro(newLibro);
					}else {
						vista.getTextAreaOriginal().setText("Los campos de tipo numerico no pueden contener letras!.");
					}
							
				} catch ( Exception e) {
				
					vista.getTextAreaOriginal().setText("No se ha podido crear el libro, asegurate de rellenar todos los campos correctamente.");
				}
			}	
		};
		actionListenerMostrarInfoDetalladaLibro = new ActionListener() {
					
					public void actionPerformed(ActionEvent actionEvent) {
						
						//Comprobamos que el identificador introducido existe y es correcto
					try {
						int identificador = Integer.parseInt(vista.getTextFieldMonstrarInfoLibro().getText());
						
							if(Modelo.recuperarLibro(identificador).idLibro != 0) {
								vista.getTextAreaOriginal().setText("");
								
								vista.getTextAreaOriginal().setText(Modelo.mostrarLibro(Modelo.recuperarLibro(identificador)));
								
							}else {
								vista.getTextAreaOriginal().setText("El Identificador introducido no existe!");
							}
								
						} catch ( Exception e) {
							
							vista.getTextAreaOriginal().setText("El Identificador introducido no existe!");
						}
					}	
				};
		actionListenerBorrarLibro = new ActionListener() {
					
					public void actionPerformed(ActionEvent actionEvent) {
						 
					try {
						  
						   //Comprobacion del identificador del textField Borrar si es un numero o una letra
						   while(Modelo.isNumeric(vista.getTextField_Identificador_BorrarLibro().getText()) == false) {
								  
								vista.getTextAreaOriginal().setText("El Identificador introducido debe ser un número!");
								break;
							}
							
							if(Modelo.isNumeric(vista.getTextField_Identificador_BorrarLibro().getText()) == true) {
								
								 //le paso a la funcion borrarLibro el identificador
								Modelo.borrarLibro(Integer.parseInt(vista.getTextField_Identificador_BorrarLibro().getText()));
								 //Mostramos un mensaje del libro borrado
								 vista.getTextAreaOriginal().setText("El libro con el identificador " + "'" + Integer.parseInt(vista.getTextField_Identificador_BorrarLibro().getText()) + "'" + " ha sido eliminado correctamente.");
							}
							
						} catch ( Exception e) {
						
							 vista.getTextAreaOriginal().setText("Error al intentar borrar el libro con identificador: " + Integer.parseInt(vista.getTextField_Identificador_BorrarLibro().getText()));
						}
						
					}	
				};
		actionListenerActualizarLibro = new ActionListener() {
					
					public void actionPerformed(ActionEvent actionEvent) {
						
					try {
						  
						//Comprobamos si el identificador es un numero o una letra
						  while(Modelo.isNumeric(vista.getTextField_Identificador_ActualizarLibro().getText()) == false) {
							  
								vista.getTextAreaOriginal().setText("El Identificador introducido debe ser un número!");
								break;
							}
							
							if(Modelo.isNumeric(vista.getTextField_Identificador_ActualizarLibro().getText()) == true) {
								//guardamos en identificador el textfield
								 int identificador = Integer.parseInt(vista.getTextField_Identificador_ActualizarLibro().getText());
								 //Le paso a la funcion actualizar libro el identificador del textfield
								  Modelo.actualizarLibro(identificador);
							}
						 
						} catch ( Exception e) {
						
							vista.getTextAreaOriginal().setText("No se encuentra el identificador introducido!");
						}
					}	
				};
		actionListenerCerrarBiblioteca = new ActionListener() {
			
			public void actionPerformed(ActionEvent actionEvent) {
				
			try {
				  
				//Cerrar aplicacion
				Runtime.getRuntime().exit(0);
					
				} catch ( Exception e) {
				
					e.printStackTrace();
				}
			}	
		};
				
		//Add listeners de los botones.
		vista.getBtnMostrarTodos().addActionListener(actionListenerMostrarTodosLosTitulos);	
		vista.getBtnCrearLibro().addActionListener(actionListenerCrearLibro);	
		vista.getBtnMostrarDetallado().addActionListener(actionListenerMostrarInfoDetalladaLibro);
		vista.getBtnBorrarLibro().addActionListener(actionListenerBorrarLibro);
		vista.getBtnActualizarLibro().addActionListener(actionListenerActualizarLibro);
		vista.getBtnCerrarLaBiblioteca().addActionListener(actionListenerCerrarBiblioteca);
	}
					
}