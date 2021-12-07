package es.florida.hibernate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
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
	

	public static void crearLibro(Libro libro) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		
		// Carga la configuracion y crea un session factory
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Libro.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
			
		// Abre una nueva session de la session factory
		session.persist(libro);
				
		//Commit de la transacción y cierra sesión
		session.getTransaction().commit();
		session.close();

		}
	
	public static Libro recuperarLibro(int _identificador) throws ParserConfigurationException, SAXException, IOException {
			
		// Carga la configuracion y crea un session factory
				Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
				configuration.addClass(Libro.class);
				ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
				SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
				
				// Abre una nueva session de la session factory
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				
		//Recuperar un objeto a partir de su id
		Libro newLibro = (Libro) session.get(Libro.class, _identificador);
			
		//Commit de la transacción y cierra sesión
				session.getTransaction().commit();
				session.close();
				
			return newLibro;
		}
	
	public static String mostrarLibro(Libro libro) {
		
		//Este metodo devuelve un texto con la informacion del libro que le pasamos por parametro
			String txtLibro = "";
		
			txtLibro = "Información detallada de un libro: " + System.lineSeparator() +  System.lineSeparator() +
                        "Identificador: " + libro.idLibro + System.lineSeparator() +
                        "Título: " + libro.titulo + System.lineSeparator() +
                        "Autor: " + libro.autor + System.lineSeparator() +
                        "Año de publicacion: " + libro.añoPublicacion + System.lineSeparator() +
                        "Editorial: " + libro.editorial + System.lineSeparator() +
                        "Numero de páginas: " + libro.numeroPaginas;
			
			return txtLibro;
			
		}
	
	public static void borrarLibro(int identificador) throws ParserConfigurationException, SAXException, IOException, TransformerException {
			
		// Carga la configuracion y crea un session factory
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Libro.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		
		// Abre una nueva session de la session factory
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		//Borrar un objeto dado su id
		Libro libro = new Libro();
		libro.setIdLibro(identificador);
		session.delete(libro);
		
		//Commit de la transacción y cierra sesión
		session.getTransaction().commit();
		session.close();
		
		}
	
	public static Libro actualizarLibro(int identificador) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		
		Libro libro = new Libro();
		
		if(isNumeric(vista.getTextField_NuevoAñoDePublicacion_ActualizarLibro().getText()) == true || isNumeric(vista.getTextField_NuevaEditorial_ActualizarLibro().getText()) == true )
		{
			// Carga la configuracion y crea un session factory
						Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
						configuration.addClass(Libro.class);
						ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
						SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
						
						// Abre una nueva session de la session factory
						Session session = sessionFactory.openSession();
						session.beginTransaction();
						
						// Actualiza la información de un objeto dado su id
						libro = (Libro) session.load(Libro.class, identificador);
						libro.setTitulo(vista.getTextField_NuevoTitulo_ActualizarLibro().getText());
						libro.setAutor(vista.getTextField_NuevoAutor_ActualizarLibro().getText());
						libro.setAñoPublicacion(vista.getTextField_NuevoAñoDePublicacion_ActualizarLibro().getText());
						libro.setEditorial(vista.getTextField_NuevaEditorial_ActualizarLibro().getText());
						libro.setNumeroPaginas(vista.getTextField_NuevaEditorial_ActualizarLibro().getText());
						session.update(libro);
						
						String textActualizarLibro = "";
						//Añadimos al texto la informacion del libro
						textActualizarLibro += "Actualizando libro..." + System.lineSeparator() + System.lineSeparator() +
									           "Identificador: " + identificador + System.lineSeparator() +
									           "Nuevo Título: " + libro.getTitulo() + System.lineSeparator() +
									           "Nuevo Autor: " + libro.getAutor() + System.lineSeparator() +
									           "Nuevo Año de publicación: "+ libro.getAñoPublicacion() + System.lineSeparator() +
									           "Nueva Editorial: " + libro.getEditorial() + System.lineSeparator() +
									           "Nuevo Número de páginas: " + libro.getNumeroPaginas() + System.lineSeparator() + System.lineSeparator() +
									           "¡Libro actualizado exitosamente!";
						
						vista.getTextAreaOriginal().setText(textActualizarLibro);
						
						session.getTransaction().commit();
						session.close();
			
		}else {
			vista.getTextAreaOriginal().setText("El año de publicación y el número de páginas deben de ser números!");
		}

			return libro;	
		}
	
	public static List<Libro> recuperarTodos() throws ParserConfigurationException, SAXException, IOException{
			
		// Carga la configuracion y crea un session factory
				Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
				configuration.addClass(Libro.class);
				ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
				SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
				
				// Abre una nueva session de la session factory
				Session session = sessionFactory.openSession();
				session.beginTransaction();
		
				List listaLibros = new ArrayList();
			
			//Recuperar lista de objetos
			
			listaLibros = session.createQuery("FROM Libro").list();
			
			if(listaLibros.size() <= 0) {
				vista.getTextAreaOriginal().setText("La Biblioteca no tiene libros actualmente!");
			}
			
			//Commit de la transacción y cierra sesión
			session.getTransaction().commit();
			session.close();
			
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