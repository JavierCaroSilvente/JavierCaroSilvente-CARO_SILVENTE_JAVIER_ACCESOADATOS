
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Modelo {
	
	private String text_Menu;
	private  static Vista vista;
	
	
	public Modelo(Vista vista) {
		
		this.vista = vista;
		
		text_Menu = "1. Mostrar todos los títulos de la biblioteca"  + "\n" +
		            "2. Mostrar información detallada de un libro" + "\n" +
		            "3. Crear nuevo libro" + "\n" +
	                "4. Actualizar libro" + "\n" +
		            "5. Borrar libro" + "\n" +
		            "6. Cerrar la biblioteca";
	}
	
	public String TextoMenu() {
		return text_Menu;
	}

	public static int crearLibro(Libro libro) throws ParserConfigurationException, SAXException, IOException, TransformerException {
				
				String returnidentificador = null;
				
				//creamos una lista de libros
				ArrayList<Libro> listaLibros = new ArrayList<Libro>();
				
				//Obtenemos el file de Biblioteca.xml
				File file = new File("Biblioteca.xml");
				
				//Bool que comprueba si el identificador del nuevo libro existe
				boolean existeIdentificador = false;
				
				//solo entrara aqui si el archivo existe y no esta en el directorio
			if(file.exists() && !file.isDirectory()) { 
				
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				
				Document document = dBuilder.parse(file);	
				
				// obtenemos los nodos de libro
				NodeList nodeList = document.getElementsByTagName("libro");
				
				//obtenemos el numero de nodos que sean libro
				int numeroNodos = nodeList.getLength();
				
				//recorremos los nodos libro
				for(int i = 0; i < numeroNodos; i++) {
					
					//obtenemso en nodo cada elemento libro en particular
					Node nodo = nodeList.item(i);
					Element elemento = (Element) nodo;
					
					//Guardamos en variables la informacion de ese nodo "libro"
					String identificador = elemento.getElementsByTagName("identificador").item(0).getTextContent();
					String titulo = elemento.getElementsByTagName("titulo").item(0).getTextContent();
					String autor = elemento.getElementsByTagName("autor").item(0).getTextContent();
					String año_publicacion = elemento.getElementsByTagName("año_publicacion").item(0).getTextContent();
					String editorial = elemento.getElementsByTagName("editorial").item(0).getTextContent();
					String numero_paginas = elemento.getElementsByTagName("numero_paginas").item(0).getTextContent();
					
					//Creamos un nuevo libro
					Libro newLibro = new Libro(identificador, titulo, autor,año_publicacion,editorial,numero_paginas);
					
					//En el caso que el libro introducido ya exista en la lista de libros...
					if(libro.identificador.equals(identificador)) {
						//ponemos la bool en true
						existeIdentificador = true;
						
						//Pasamos por panel el texto que ya existe ese identificador
						vista.getTextAreaOriginal().setText("Ya existe un libro con este identificador! Prueba con otro número!");
					}
					
					//Añadimos el resto de libros que no coinciden
					listaLibros.add(newLibro);
				}
			}
			//En el caso que el libro introducido no exista en la lista de libros...
			if(existeIdentificador == false) {
				//Añadimos el nuevo libro con el nuevo identificador
				listaLibros.add(libro);
				//Metodo que pasandole la lista de libros crea el archivo xml
				CreacionXML(listaLibros);
				
			}
			//devolvemos el identificador del nuevo libro
			return Integer.parseInt(returnidentificador);
		}
	
	public static Libro recuperarLibro(int _identificador) throws ParserConfigurationException, SAXException, IOException {
			
		//Creamos un nuevo file a partir de Biblioteca.xml
			File file = new File("Biblioteca.xml");
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document document = dBuilder.parse(file);
			
			NodeList nodeList = document.getElementsByTagName("libro");
			int numeroNodos = nodeList.getLength();
			
			Libro newLibro = null;
			
			//recorremos los nodos libro del xml
			for(int i = 0; i < numeroNodos; i++) {
				
				Node nodo = nodeList.item(i);
				Element elemento = (Element) nodo;
				
				String identificador = elemento.getElementsByTagName("identificador").item(0).getTextContent();
				
				//si el identificador de la lista de libros coincide con el que le pasamos...
				if(_identificador == Integer.parseInt(identificador)) {
					
					//Almacenamos la informacion del libro coincidente
					String titulo = elemento.getElementsByTagName("titulo").item(0).getTextContent();
					String autor = elemento.getElementsByTagName("autor").item(0).getTextContent();
					String año_publicacion = elemento.getElementsByTagName("año_publicacion").item(0).getTextContent();
					String editorial = elemento.getElementsByTagName("editorial").item(0).getTextContent();
					String numero_paginas = elemento.getElementsByTagName("numero_paginas").item(0).getTextContent();
					
					//creamos un nuevo libro
					newLibro = new Libro(identificador, titulo, autor,año_publicacion,editorial,numero_paginas);
		
				}
			}
			
			//retornamos libro
			return newLibro;
		}
	
	public static String mostrarLibro(Libro libro) {
		
		//Este metodo devuelve un texto con la informacion del libro que le pasamos por parametro
			String txtLibro = "";
		
			txtLibro = "Información detallada de un libro: " + System.lineSeparator() +  System.lineSeparator() +
                        "Identificador: " + libro.identificador + System.lineSeparator() +
                        "Título: " + libro.titulo + System.lineSeparator() +
                        "Autor: " + libro.autor + System.lineSeparator() +
                        "Año de publicacion: " + libro.año_publicacion + System.lineSeparator() +
                        "Editorial: " + libro.editorial + System.lineSeparator() +
                        "Numero de páginas: " + libro.n_paginas;
			
			return txtLibro;
			
		}
	
	public static void borrarLibro(int identificador) throws ParserConfigurationException, SAXException, IOException, TransformerException {
			
			ArrayList<Libro> listaLibros = new ArrayList<Libro>();
			
			File file = new File("Biblioteca.xml");
			Libro newLibro = null;
			
			//si el fichero no existe y no se encuentra en el directorio
			if(file.exists() && !file.isDirectory()) { 
				
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				
				//Creamos el documento a partir del file
				Document document = dBuilder.parse(file);
				
				//Obtenemos los nodos con el tag libro
				NodeList nodeList = document.getElementsByTagName("libro");
				
				//obtenemos el numero de nodos libro
				int numeroNodos = nodeList.getLength();	
				
				//recorremos en el bucle la cantidad de nodos
				for(int i = 0; i < numeroNodos; i++) {
					
					Node nodo = nodeList.item(i);
					Element elemento = (Element) nodo;
					
					//Guardamos la informacion del libro en variables de los diferentes hijos del nodo
					String _identificador = elemento.getElementsByTagName("identificador").item(0).getTextContent();
					String titulo = elemento.getElementsByTagName("titulo").item(0).getTextContent();
					String autor = elemento.getElementsByTagName("autor").item(0).getTextContent();
					String año_publicacion = elemento.getElementsByTagName("año_publicacion").item(0).getTextContent();
					String editorial = elemento.getElementsByTagName("editorial").item(0).getTextContent();
					String numero_paginas = elemento.getElementsByTagName("numero_paginas").item(0).getTextContent();
					
					//Añadimos todos los libros a la lista de libros menos el que le pasamos por identificador asi excluimos el libro en el nuevo fichero
					if(identificador != Integer.parseInt(_identificador)) {
						newLibro = new Libro(_identificador, titulo, autor,año_publicacion,editorial,numero_paginas);
						listaLibros.add(newLibro);
					}else {
						System.out.println("Libro con identificador: " + "'" + _identificador + "'" + " y con titulo: " + "'" + titulo + "'" + " eliminado en 'Biblioteca.xml'");
					}
				}
			}
			
			//Si hay algun libro en la lista...
			if(listaLibros.size() >= 0) {
				//creamos mediante la funcion CreacionXML el xml de la lista de libros que le pasamos.
				CreacionXML(listaLibros);
				
			}
		}
	
	public static void actualizarLibro(int identificador) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		
		
		ArrayList<Libro> listaLibros = new ArrayList<Libro>();
		
		File file = new File("Biblioteca.xml");
		Libro newLibro = null;
		if(file.exists() && !file.isDirectory()) { 
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document document = dBuilder.parse(file);
			
			NodeList nodeList = document.getElementsByTagName("libro");
			int numeroNodos = nodeList.getLength();	
			
			for(int i = 0; i < numeroNodos; i++) {
				
				Node nodo = nodeList.item(i);
				Element elemento = (Element) nodo;
				
				String _identificador = elemento.getElementsByTagName("identificador").item(0).getTextContent();
				
				//Si el identificador pasado pro parametro coincide con alguno de la lista de libros
				if(identificador == Integer.parseInt(_identificador)) {
					
					//Guardamos en variables lo que hay en los textfield
					String titulo = vista.getTextField_NuevoTitulo_ActualizarLibro().getText();
					
					String autor = vista.getTextField_NuevoAutor_ActualizarLibro().getText();
				
					String año_publicacion = vista.getTextField_NuevoAñoDePublicacion_ActualizarLibro().getText();
					
					String editorial = vista.getTextField_NuevaEditorial_ActualizarLibro().getText();
					
					String numero_paginas = vista.getTextField_NuevaEditorial_ActualizarLibro().getText();
					
					//creamos y añadimos el nuevo libro a la lista
					newLibro = new Libro(_identificador, titulo, autor,año_publicacion,editorial,numero_paginas);
					listaLibros.add(newLibro);
					
				
				}else {
					
					//sino ... guardamos en las variables el libro que tocque en el for que ya hay en la lista
					String titulo =elemento.getElementsByTagName("titulo").item(0).getTextContent();
					
					String autor = elemento.getElementsByTagName("autor").item(0).getTextContent();
					
					String año_publicacion = elemento.getElementsByTagName("año_publicacion").item(0).getTextContent();
					
					String editorial = elemento.getElementsByTagName("editorial").item(0).getTextContent();
					
					String numero_paginas = elemento.getElementsByTagName("numero_paginas").item(0).getTextContent();
					
					//creamos y añadimos el libro a la lista
					newLibro = new Libro(_identificador, titulo, autor,año_publicacion,editorial,numero_paginas);
					listaLibros.add(newLibro);
				}
			}
		}
				//creamos mediante la funcion CreacionXML el xml de la lista de libros que le pasamos.
				CreacionXML(listaLibros);
		}
	
	public static ArrayList<Libro> recuperarTodos() throws ParserConfigurationException, SAXException, IOException{
			
			ArrayList<Libro> listaLibros = new ArrayList<Libro>();
			
			File file = new File("Biblioteca.xml");
			
			if(file.exists() && !file.isDirectory()) { 
				
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				
				Document document = dBuilder.parse(file);
				
				NodeList nodeList = document.getElementsByTagName("libro");
				int numeroNodos = nodeList.getLength();
								
				for(int i = 0; i < numeroNodos; i++) {
					
					Node nodo = nodeList.item(i);
					Element elemento = (Element) nodo;
					
					String identificador = elemento.getElementsByTagName("identificador").item(0).getTextContent();
					String titulo = elemento.getElementsByTagName("titulo").item(0).getTextContent();
					String autor = elemento.getElementsByTagName("autor").item(0).getTextContent();
					String año_publicacion = elemento.getElementsByTagName("año_publicacion").item(0).getTextContent();
					String editorial = elemento.getElementsByTagName("editorial").item(0).getTextContent();
					String numero_paginas = elemento.getElementsByTagName("numero_paginas").item(0).getTextContent();
					
					Libro newLibro = new Libro(identificador, titulo, autor,año_publicacion,editorial,numero_paginas);
					
					listaLibros.add(newLibro);
				}
			}
			
			if(listaLibros.size() <= 0) {
				vista.getTextAreaOriginal().setText("La Biblioteca no tiene libros actualmente!");
			}
			
			return listaLibros;
		}

	public static boolean isNumeric(String str) { 

		//En el caso de ser un numero entrara en tru si da error es una letra
		  try {  
		    Double numero = Double.parseDouble(str);  
		    
		    return true;
		   
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
	
	public static void CreacionXML(ArrayList<Libro> listaLibros ) throws ParserConfigurationException, IOException, TransformerException {
		
		//Preparación DOM
				DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
				DocumentBuilder build = dFact.newDocumentBuilder();
				
				Document doc = build.newDocument();
				
				Element nuevaRaiz = doc.createElement("libros");
				
				doc.appendChild(nuevaRaiz);
				
				for(Libro _libro : listaLibros) {
					
					Element elementoBiblioteca = doc.createElement("libro");
					
					nuevaRaiz.appendChild(elementoBiblioteca);
					
					Element ident = doc.createElement("identificador");
					ident.appendChild(doc.createTextNode(String.valueOf(_libro.getIdentificador())));
					elementoBiblioteca.appendChild(ident);
					
					Element titulo = doc.createElement("titulo");
					titulo.appendChild(doc.createTextNode(String.valueOf(_libro.getTitulo())));
					elementoBiblioteca.appendChild(titulo);
					
					Element autor = doc.createElement("autor");
					autor.appendChild(doc.createTextNode(String.valueOf(_libro.getAutor())));
					elementoBiblioteca.appendChild(autor);
					
					Element año_publicacion = doc.createElement("año_publicacion");
					año_publicacion.appendChild(doc.createTextNode(String.valueOf(_libro.getAñoPublicacion())));
					elementoBiblioteca.appendChild(año_publicacion);
					
					Element editorial = doc.createElement("editorial");
					editorial.appendChild(doc.createTextNode(String.valueOf(_libro.getEditorial())));
					elementoBiblioteca.appendChild(editorial);
					
					Element numero_paginas = doc.createElement("numero_paginas");
					numero_paginas.appendChild(doc.createTextNode(String.valueOf(_libro.getN_Paginas())));
					elementoBiblioteca.appendChild(numero_paginas);
					
				}
				
				//Guradar el DOM en disco
				TransformerFactory tranFactory = TransformerFactory.newInstance();
				Transformer aTransformer = tranFactory.newTransformer();
				
				aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
				aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
				aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
				
				DOMSource source = new DOMSource(doc);
				
				FileWriter fw = new FileWriter("Biblioteca.xml");
				StreamResult result = new StreamResult(fw);
				
				aTransformer.transform(source, result);
				fw.close();
		
	}
}