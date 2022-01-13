package es.florida.mongodb;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;

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
	

	public static void crearLibro(Libro libro) throws ParserConfigurationException, SAXException, IOException {
		
		//Conexion a DB
		MongoClient mongoClient = new MongoClient("localhost",27017);
		MongoDatabase database = mongoClient.getDatabase("Biblioteca");
		
		MongoCollection<Document> coleccion = database.getCollection("Libros");
		
		//Insertar un documento en la coleccion
		System.out.println("Insertar un documento... \n");
		
		//Creacion nuevo doc
		Document doc = new Document();
		
		//Guardamos el id para asignarlo correcatmente en la nueva creación
		int idCount = (int) (coleccion.countDocuments() + 1);
		
		doc.append("Id", String.valueOf(idCount));
		doc.append("Titol", libro.titulo);
		doc.append("Autor", libro.autor);
		doc.append("Any_naixement", libro.añoNacimiento);
		doc.append("Any_publicacio", libro.añoPublicacion);
		doc.append("Editorial", libro.editorial);
		doc.append("Nombre_pagines", libro.numeroPaginas);
		
		//insertamos a la coleccion
		coleccion.insertOne(doc);
		
		//cerramos conexion con db
		mongoClient.close();

		}
	
	public static Libro recuperarLibro(int _identificador) throws ParserConfigurationException, SAXException, IOException {		
				
			//Conexion a DB
			MongoClient mongoClient = new MongoClient("localhost",27017);
			MongoDatabase database = mongoClient.getDatabase("Biblioteca");
			MongoCollection<Document> coleccion = database.getCollection("Libros");
			
			MongoCursor<Document> cursor = coleccion.find().iterator();
			
			//Paso el id de parametros a string para poder pasarselo al query
			String id = String.valueOf(_identificador);
			
			//Obtenemos el id especifico pasado por parametros
			Bson query = eq("Id",id);
			cursor = coleccion.find(query).iterator();
			
			Libro newLibro = new Libro();
			
			//leemos lineas
			while(cursor.hasNext()) {
				//guardamos en un obj json el dato obtenido
				JSONObject obj = new JSONObject(cursor.next().toJson());
				
				//guardamos en una var int el dato obtenido del obj json
				int idInt = Integer.parseInt(obj.getString("Id"));
				
				//creamos el nuevo libro asignandole sus atributos en base al objjson
				newLibro.idLibro = idInt;
				newLibro.titulo = obj.getString("Titol");
				newLibro.autor = obj.getString("Autor");
				newLibro.añoNacimiento = obj.getString("Any_naixement");
				newLibro.añoPublicacion = obj.getString("Any_publicacio");
				newLibro.editorial = obj.getString("Editorial");
				newLibro.numeroPaginas = obj.getString("Nombre_pagines");

			}
			//cerramos conexion
			mongoClient.close();
			
			//retornamos libro
		return newLibro;

	}
	
	public static String mostrarLibro(Libro libro) {
		
		//Este metodo devuelve un texto con la informacion del libro que le pasamos por parametro
			String txtLibro = "";
		
			txtLibro = "Información detallada de un libro: " + System.lineSeparator() +  System.lineSeparator() +
                        "Identificador: " + libro.idLibro + System.lineSeparator() +
                        "Título: " + libro.titulo + System.lineSeparator() +
                        "Autor: " + libro.autor + System.lineSeparator() +
                        "Año de nacimiento: " + libro.añoNacimiento + System.lineSeparator() +
                        "Año de publicacion: " + libro.añoPublicacion + System.lineSeparator() +
                        "Editorial: " + libro.editorial + System.lineSeparator() +
                        "Numero de páginas: " + libro.numeroPaginas;
			
			return txtLibro;
			
		}
	
	public static void borrarLibro(int identificador) throws ParserConfigurationException, SAXException, IOException {
		
		//Conexion a DB
		MongoClient mongoClient = new MongoClient("localhost",27017);
		MongoDatabase database = mongoClient.getDatabase("Biblioteca");
		MongoCollection<Document> coleccion = database.getCollection("Libros");
		
		MongoCursor<Document> cursor = coleccion.find().iterator();
		
		String id = String.valueOf(identificador);
		
		//Borrar un objeto dado su id
		coleccion.deleteOne(eq("Id",id));
		
		//Creamos un int de reposicionamiento en las tablas del id al borrar un libro
		int positionInTable = 0;
		
		//Recorremos la informacion obtenida de db
		while(cursor.hasNext()) {
			
			//Incrementamos en 1 la nueva posicion en la tabla
			positionInTable++;
			
			//obtenemos cada uno de los obj de los libros
			JSONObject obj = new JSONObject(cursor.next().toJson());
			
			String strIdObj = obj.getString("Id");
			
			//Hacemos este Update para dar de nuevo a todos los elementos de la db un id correctamente. por ejemplo si del 1 2 3 4 borro el 3 me quede asi 1 2 3 y no asi 1 2 4 (los id)
			coleccion.updateOne(eq("Id",strIdObj), new Document("$set", new Document("Id",String.valueOf(positionInTable))));
		}
		
		//ponemos la posicion en la tabla de nuevo a 0 para el proximo borrado
		positionInTable = 0;
		
		//cerramos conexion
		mongoClient.close();
		
		}
	
	public static Libro actualizarLibro(int identificador) throws ParserConfigurationException, SAXException, IOException {
		
		Libro libro = new Libro();
		
		//Comprobamos si el identificador es un numero o una letra
		if(isNumeric(vista.getTextField_NuevoAñoDePublicacion_ActualizarLibro().getText()) == true && isNumeric(vista.getTextField_NuevoAñoDeNacimiento_ActualizarLibro().getText()) == true 
				&& isNumeric(vista.getTextField_NuevoNuemroDePaginas_ActualizarLibro().getText()) == true)
		{
			//Conexion DB		
			MongoClient mongoClient = new MongoClient("localhost",27017);
			MongoDatabase database = mongoClient.getDatabase("Biblioteca");
			MongoCollection<Document> coleccion = database.getCollection("Libros");
			
			
			MongoCursor<Document> cursor = coleccion.find().iterator();
			
			//le asignamos al nuevo libro los texto dados desde el input de la interfaz
			libro.setTitulo(vista.getTextField_NuevoTitulo_ActualizarLibro().getText());
			libro.setAutor(vista.getTextField_NuevoAutor_ActualizarLibro().getText());
			libro.setAñoNacimiento(vista.getTextField_NuevoAñoDeNacimiento_ActualizarLibro().getText());
			libro.setAñoPublicacion(vista.getTextField_NuevoAñoDePublicacion_ActualizarLibro().getText());
			libro.setEditorial(vista.getTextField_NuevaEditorial_ActualizarLibro().getText());
			libro.setNumeroPaginas(vista.getTextField_NuevoNuemroDePaginas_ActualizarLibro().getText());
			
			String id = String.valueOf(identificador);
			
			//Actualizamos el libro con los nuevos atributos
			coleccion.updateOne(eq("Id",id), new Document("$set", new Document("Titol", libro.getTitulo())));
			coleccion.updateOne(eq("Id",id), new Document("$set", new Document("Autor", libro.getAutor())));
			coleccion.updateOne(eq("Id",id), new Document("$set", new Document("Any_naixement", libro.getAñoNacimiento())));
			coleccion.updateOne(eq("Id",id), new Document("$set", new Document("Any_publicacio", libro.getAñoPublicacion())));
			coleccion.updateOne(eq("Id",id), new Document("$set", new Document("Editorial", libro.getEditorial())));
			coleccion.updateOne(eq("Id",id), new Document("$set", new Document("Nombre_pagines", libro.getNumeroPaginas())));
			
			String textActualizarLibro = "";
			
			//Añadimos al texto la informacion del libro
			textActualizarLibro += "Actualizando libro..." + System.lineSeparator() + System.lineSeparator() +
						           "Identificador: " + identificador + System.lineSeparator() +
						           "Nuevo Título: " + libro.getTitulo() + System.lineSeparator() +
						           "Nuevo Autor: " + libro.getAutor() + System.lineSeparator() +
						           "Nuevo Año de nacimiento: "+ libro.getAñoNacimiento() + System.lineSeparator() +
						           "Nuevo Año de publicación: "+ libro.getAñoPublicacion() + System.lineSeparator() +
						           "Nueva Editorial: " + libro.getEditorial() + System.lineSeparator() +
						           "Nuevo Número de páginas: " + libro.getNumeroPaginas() + System.lineSeparator() + System.lineSeparator() +
						           "¡Libro actualizado exitosamente!";
			
			vista.getTextAreaOriginal().setText(textActualizarLibro);
			
			mongoClient.close();
			
		}else {
			vista.getTextAreaOriginal().setText("El año de publicación y el número de páginas deben de ser números!");
		}

			return libro;	
		}
	
	public static List<Libro> recuperarTodos() throws ParserConfigurationException, SAXException, IOException{
			
		//Conexion DB	
		MongoClient mongoClient = new MongoClient("localhost",27017);
		MongoDatabase database = mongoClient.getDatabase("Biblioteca");
		MongoCollection<Document> coleccion = database.getCollection("Libros");
		
		MongoCursor<Document> cursor = coleccion.find().iterator();
		cursor = coleccion.find().iterator();
		
		List listaLibros = new ArrayList();
		
		//Recuperar lista de objetos
		while(cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			Libro libro = new Libro();
			
			//le damos al libro la info del obj
			libro.titulo = obj.getString("Titol");
			//le damos al libro la info del obj
			libro.idLibro = obj.getInt("Id");
			
			listaLibros.add(libro);
		}
		
			if(listaLibros.size() <= 0) {
				vista.getTextAreaOriginal().setText("La Biblioteca no tiene libros actualmente!");
			}
			
			//cierra sesión
			mongoClient.close();
			
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

}