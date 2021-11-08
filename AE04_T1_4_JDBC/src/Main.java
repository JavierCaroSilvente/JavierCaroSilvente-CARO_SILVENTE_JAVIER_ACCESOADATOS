import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, NumberFormatException, IOException {
		
		//Este metodo inserta en la base de datos los datos del CSV, en el caso de que la DB ya contenga algun dato no lo inserta.
		InsertDataInDB(LecturaCSV());
		
		//Este metodo imprime por consola los libros de los autores nacidos antes de 1950.
		Libros_autores_antes_1950();
		
		//Este metodo imprime por consola las editoriales que hayan publicado al menos un libro en el siglo XXI.
		Editorial_un_libro_sigloXXI();
	}
	
	public static List<Libro> LecturaCSV() throws IOException, ClassNotFoundException {
		
		//Lista para los libros que ya estan en la DB
		List<Libro> listaLibrosDB = new ArrayList<Libro>();
		
		//Lista de libros que obtenemos del CSV
		List<Libro> listaLibrosCSV = new ArrayList<Libro>();
	
		//Si hay libros en la base de datos..
		if(ObtenerLibrosDataBase().size() > 0) {
			listaLibrosDB = ObtenerLibrosDataBase();// rellenamos la lista con los libros que ya hay en la base de datos.
		}
		
		//Le pasamos al buffer el fileInputStream "csv" para que lea las lineas, ademas ISO es para que respete el utf-8
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("AE04_T1_4_JDBC_Datos.csv"),"ISO_8859_1"));
			
			//guardamos en linea la primera linea leida del buffer
			String linea = br.readLine();
			
			int contadorLinea = 0; //Contador para evitar la primera linea del CSV que son cabezeras
			
			//Mientras hayan lineas por leer..
			while(linea != null) {
				
				if(contadorLinea > 0) {//Este if es para evitar las cabezeras del csv .. titulo , autor etc..
					String[] lineCSV = linea.split(";");
					
					for(int i = 0; i < 5; i++) {// for que recorre las 5 primeros valores de cada linea que es equivalente al numero de tablas menos id
						
						if(lineCSV[i].isEmpty()) {//si uno de estos valores esta null
							lineCSV[i] = "N.C.";//lo rellenamos con N.C
						}
					}
					
					Libro nuevoLibro = new Libro(lineCSV[0],lineCSV[1],lineCSV[2],lineCSV[3],lineCSV[4],lineCSV[5]); //Creamos un nuevo libro y le pasamos los parametros
					
					if(ExistInDB(listaLibrosDB,nuevoLibro.toString()) == false) {//este metodo nos devuelve si el libro ya esta en la db o no
						listaLibrosCSV.add(nuevoLibro);// si no esta en la db se añade el libro del csv , solo el que no tenemos
					}
				}
				
				
				contadorLinea++; //incrementamos el contador para que sepa cuando a saltado la primera linea asi evitar las cabezeras del csv
				linea = br.readLine(); //leemos linea
			}
			
			br.close(); //cerramos el buffer
			
			return listaLibrosCSV; // devolvemos la lista de libros
	}
	
	public static boolean ExistInDB(List<Libro> listaDB, String autor)
	{
		
		//Esta funcion recibe por parametros la lista de libros de la db y el autor que estan siendo analizados
		boolean exist = false;
		
		for(int i = 0; i < listaDB.size(); i++) {
			
			if(listaDB.get(i).toString().equals(autor)) {
				exist = true; //devuelve true si coincide con alguno de los de la db
				break; // salimos del bucle
			}	
		}
		
		return exist; //devolvemos la bool
	}
	
	public static List<Libro> ObtenerLibrosDataBase() throws ClassNotFoundException { //Obtenemos todos los libros de la db
		
		List<Libro> librosDB = new ArrayList<Libro>();  //Lista donde almacenaremos los libros de db
		
		Class.forName("com.mysql.cj.jdbc.Driver"); //clase mysql
		
		try {
			//datos de conexion para DB
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca?characterEncoding=UTF-8","root","");
			
			Statement stmt = con.createStatement(); //preparacion de la sentencia sql
			ResultSet rs = stmt.executeQuery("SELECT * FROM libros"); // Le pasamos la sentencia sql a analizar
			
			while(rs.next()) {// mientras hayan lineas para leer del resultado de la consulta...
				
				//Creamos un nuevo libro con lso datos obtenidos
				Libro nuevoLibro = new Libro(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
				
				//Añadimos el libro a la lista
				librosDB.add(nuevoLibro);
			}
			
			rs.close();//cerramos la conexion
			con.close();//cerramos la conexion
		} catch(SQLException e) { //capturador de errores
			System.err.println("Error en la conexion");
			e.printStackTrace();
		}
		
		return librosDB;//devuelve la lista de libros de la db
	}
	
	public static void InsertDataInDB(List<Libro> csv) throws ClassNotFoundException, IOException { //Lista de libros que insertaremos en la db
  
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//clase de mysql
			
			//inicializamos statement en null
			PreparedStatement statement = null;
			
			//datos conexion db
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca?characterEncoding=UTF-8","root","");
			
			//sentiencia que le pasamos sql al statement
			statement = con.prepareStatement("INSERT INTO libros (titulo,autor,año_nacimiento,año_publicacion,editorial, numero_paginas) VALUES (?,?,?,?,?,?)");
			
			for(int i = 0; i < csv.size(); i++) {// for que recorre la lista de libros obtenida por parametros
				
				//almacenamos los datos en variables
				String titulo = csv.get(i).getTitulo();
				String autor = csv.get(i).getAutor();
				String año_nacimiento = csv.get(i).getAñoNacimiento();
				String año_publicacion = csv.get(i).getAñoPublicacion(); 
				String editorial = csv.get(i).getEditorial();
				String n_paginas = csv.get(i).getN_Paginas(); 
				
				//Pasamos los datos ordenadamente a la consulta sql
				statement.setString(1, titulo);
				statement.setString(2, autor);
				statement.setString(3, año_nacimiento);
				statement.setString(4, año_publicacion);
				statement.setString(5, editorial);
				statement.setString(6, n_paginas);
				
				//ejecutamos la sentencia
				statement.executeUpdate();
			}
			
			statement.close();//cerramos conexion
			con.close();
			
		} catch(SQLException e) {//capturamos errores
			System.err.println("Error en la conexion");
			e.printStackTrace();
		}
		
	}
	
	public static void Libros_autores_antes_1950() throws ClassNotFoundException
	{
		System.out.println("");
		Class.forName("com.mysql.cj.jdbc.Driver");//clase mysql
		
		try {
			//conexion a la db local host
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","");
			
			//creacion del statment
			Statement stmt = con.createStatement();
			
			// creacion de la consulta sql
			ResultSet rs = stmt.executeQuery("SELECT titulo, autor, año_publicacion FROM libros WHERE año_nacimiento < 1950");
			
			//cabeceras para la impresion por consola
			System.out.println("- Libros de los autores nacidos antes de 1950");
			System.out.format("%28s%35s%45s%1s", "titulo","autor","AñoPublicacion", "\n");
			System.out.format("%28s%35s%45s%1s", "======","=====","==============", "\n");
			
			while(rs.next()) { //mientras hayan datos que leer...
				
				//Imprimimos valores leidos por consulta
				System.out.format("%28s%35s%45s%1s", rs.getString(1).trim(),rs.getString(2).trim(),rs.getString(3).trim(), "\n");
				
			}
			
			stmt.close();//cerramos conexion
			con.close();
		} catch(SQLException e) {//capturamos errores
			System.err.println("Error en la conexion");
			e.printStackTrace();
		}
		
	}
	
	public static void Editorial_un_libro_sigloXXI() throws ClassNotFoundException
	{
		System.out.println("");
		Class.forName("com.mysql.cj.jdbc.Driver");//clase mysql
		
		try {
			
			//conexiona  la db
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","");
			
			//creacion del statement
			Statement stmt = con.createStatement();
			
			//creacion de la consulta sql
			ResultSet rs = stmt.executeQuery("SELECT editorial FROM libros WHERE año_publicacion >= 2001");
			
			//cabeceras para la impresion por consola
			System.out.println("- Editoriales que hayan publicado al menos un libro en el siglo XXI.");
			System.out.format("%28s%1s", "Editorial", "\n");
			System.out.format("%28s%1s", "=========", "\n");
			
			while(rs.next()) {//mientras hayan datos que leer...
				
				//Imprimimos el valor por consola
				System.out.format("%28s%1s", rs.getString(1).trim(), "\n");
			}
			
			stmt.close();//cerramos conexion
			con.close();
		} catch(SQLException e) {//capturamos error
			System.err.println("Error en la conexion");
			e.printStackTrace();
		}
	}
}
