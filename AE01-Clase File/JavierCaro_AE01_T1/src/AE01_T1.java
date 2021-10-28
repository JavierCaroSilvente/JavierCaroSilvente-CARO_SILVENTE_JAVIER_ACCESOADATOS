import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AE01_T1 {

	public static void main(String[] args) throws NumberFormatException, IOException{
		
		//Llamo al metodo menu
		Menu(args);
	}
	
	public static void Menu(String[] args) throws IOException{
		
		try {
			System.out.println("Menu de opcions\n1 --> para obtener información.\n2 --> para crear una carpeta.\n3 --> para crear un fichero.\n4 --> para eliminar ficheros/carpetas.\n5 --> para renombrar ficheros/carpetas.");
			System.out.print("Introduzca uno de los siguientes numeros para obtener la opcion deseada: \n");
			
			//pido por teclado el metodo de las opciones que quiero llamar
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			
			int numeroSeleccionado = Integer.parseInt(br.readLine());
			
			//segun el numero introducido el switch llama al metodo que pertoque
			switch(numeroSeleccionado) {
			
			case 1:
				GetInformacion(args);
				break;
				
			case 2:
				CrearCarpeta(args);
				break;
				
			case 3:
				CrearFichero(args);
				break;
				
			case 4:
				Elimina(args);
				break;
				
			case 5:
				Renombra(args);
				break;
			}
		    } catch (IOException e) {//En el caso de error enviaremos un mensaje
		      System.out.println("Lo sentimeos a ocurrido un error!.");
		      e.printStackTrace();
		    }
		
		//Este metodo le dice al usuario si quiere volver a inicializar el menu
		VolverMenu(args);
	}
	
	public static void GetInformacion(String[] args) throws IOException{
		
		//almacenamos el parametro que recojamos
		String nombre = args[0];
		File file = new File(nombre);
		
		//si el parametro es un fichero..
		if(file.isFile()) {
			
			//imprimimios el nombre del fichero y el tamaño en bytes
			System.out.println("Es un fichero nombrado: " + file.getName());
			System.out.println("Tamaño del fichero es: " + file.length() + " bytes");
			
		}else if(file.isDirectory()) {//si el parametro es un documento..
			
			System.out.println("Es un directorio nombrado: " + file.getName());
			
			//recorremos mediante bucle for el contenido del directorio dado y imprimimos cada uno de ellos por consola
			System.out.println("Contiene: ");
			for(String elemento:file.list()) {
				System.out.println(elemento);
			}
			
			//Imprimimos por consola espacio libre, disponible, total, ultima modificacion y si esta oculto accediendo a los diferentes metodos de la clase file.
			System.out.println("Con un espacio libre de: " + file.getFreeSpace()/1024/1024/1024 + " GB");
			System.out.println("Con un espacio disponible de: " + file.getUsableSpace()/1024/1024/1024 + " GB");
			System.out.println("Con un espacio total de: " + file.getTotalSpace()/1024/1024/1024 + " GB");	
			System.out.println("Ultima modificación: " + new Date(file.lastModified()));
			System.out.println("El archivo esta oculto: " + file.isHidden());
		}
		
	
	}
	
	public static void CrearCarpeta(String[] args) throws IOException{
		
		 System.out.println("Introduce el nombre del nuevo directorio: ");
		 
		 //Pedimos por teclado el nombre del directorio que vamos a crear
		 InputStreamReader isr = new InputStreamReader(System.in);
		 BufferedReader br = new BufferedReader(isr);
		 
		 //Almacenamos el nombre en una variable que extraemos del buffer.
		 String nombreDirectorio = br.readLine();
		 
		 //Creamos un file de la clase file y le pasamos donde lo creamos args[0] y el nombre.
		 File file = new File(args[0],nombreDirectorio);
		 
		 //Creo el directorio nombrado en el caso contrario aviso de fallo
	     if (file.mkdirs()) {
	         System.out.println("El directorio " + '"'+ nombreDirectorio + '"'+ " a sido creado en la ruta local " + file.getAbsolutePath());
	     } else {
	         System.out.println("Fallo al crear el directorio!");
	     }
	}
	
	public static void CrearFichero(String[] args) {
		
		try {
			
			//Inicializamos Input y buffered para poder pedir por teclado
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			
			//Lista para guardar los nombres de los directorios disponibles
			List<String> directoriosDisponibles = new ArrayList<String>();
			
			//variables incrementable para poder dar un numero en el recorrido del for
			int i = 1;
			
			System.out.println("Elige el directorio donde deseas crear el fichero: ");
			
			//file lo obtenemos del argumento pasado
			File file = new File(args[0]);
			
			//For que recorre los elementos del directorio pasado
			for(String elemento:file.list()) {
				//Creamos clase que filtra los elementos de la lista devolviendo true solo de los que son directorios
				FiltroDirectorios filtro = new FiltroDirectorios(elemento);
				//Recorremos otro For solo de los elementos filtrados que si son Directorios
				for(String directorio:file.list(filtro)) { 
					//Imprimimos la posicion del elemento empezando por 1 y el directorio en orden
					System.out.println( i + "---> "+ directorio);
					//Añadimos los directorios a una lista de directorios encontrados
					directoriosDisponibles.add(directorio);
					i++;
				}
				//salimos del for para que no se repita más.
				break;
			}
			
			//De los directorios que se nos lista por consola guardaremos aqui el numero que nosotros seleccionemos
			String directorioSeleccionado = br.readLine();
			//Variable donde almacenaremos el directorio que coincida con el numero que nostros hemos seleccionado
			String directorioLista = "";
			
			//Recorremos el For con los directorios guardados en nuestra lista de directorios
			for(int j = 0; j <= directoriosDisponibles.size(); j++) {
			
				//Si el directorio que nosotros hemos seleccionado coincide con el de la posicion de la lista
				if(j +1 == (Integer.parseInt(directorioSeleccionado)) ){
					//guardamos en esta variable el nombre del directorio coincidente
					directorioLista = directoriosDisponibles.get(j);
				}
			}
			
			//Guardaremos en variables el nombre del fichero y el de la extension que queramos que tenga
			System.out.println("Introduce el nombre de tu nuevo fichero:\nEjemplo: fichero1");
			String nombreFichero = br.readLine();
			System.out.println("Introduce la extension de tu nuevo fichero:\nEjemplo: .txt");
			String extension = br.readLine();
			
			//Creamos el archivo en la ruta absoluta que le pasamos por argumento encadenado con el directorio que hemos seleccionado añadiendole la extension
		      File newfile = new File(file.getAbsolutePath() + "/" + directorioLista,nombreFichero + extension);
		    
		      //Comprobamos si el archivo existe o no para que no se repitan
		      if (newfile.createNewFile()) {
		    	  //Indicamos que se a credao + su nombre + donde se encuentra
		        System.out.println("Fichero creado: " + newfile.getName() + " a sido creado en la ruta " + newfile.getAbsolutePath());
		      } else {
		        System.out.println("Este fichero ya existe!.");
		      }
		    } catch (IOException e) {//Pasamos este mensaje en caso de error
		      System.out.println("Lo sentimeos a ocurrido un error!.");
		      e.printStackTrace();
		    }
	}
	
	public static void Elimina(String[] args) throws NumberFormatException, IOException {
		
		//Lista donde almacenaremos solo los archivos
		List<String> ficherosDisponibles = new ArrayList<String>();
		
		System.out.println("Introduce mediante numeros que desea eliminar ...");
		System.out.println("1 --> Para eliminar un fichero");
		System.out.println("2 --> Para eliminar un Directorio");
		
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		//Almacenamos el numero que seleccionamos si eliminar un fichero o un directorio.
		int numeroSeleccionado = Integer.parseInt(br.readLine());
		
		//Creamos la clase file para que recoge por parametros el directorio indicado
		File file = new File(args[0]);
		
		//Inicializamos "i" para darle un numero a cada fichero que se muestra por pantalla
		int i = 1;
		
		//Switch para cada caso de nuestra eleccion
		switch (numeroSeleccionado) {
		
		case 1:
			//elegimos eliminar fichero
			System.out.println("Cual de los siguientes ficheros desea eliminar?");
			//For que recorre el contenido del directorio pasado por argumentos a File
			for(String elemento:file.list()) {
				//Nueva clase para filtrar solo los archivos/ficheros
				FiltroArchivo filtro = new FiltroArchivo(elemento);
				//For que recorre solo los ficheros
				for(String fichero:file.list(filtro)) {
					//Imprimimos por consola cada fichero en orden con su respectivo numero en orden
					System.out.println(i + "--> " + fichero);
					//Añadimos todo los ficheros filtrados del directorio en una lista de ficheros
					ficherosDisponibles.add(fichero);
					i++;
				}
				//salimos del bucle
				break;
			}
			//guardamos el numero del fichero de la lista que queremos eliminar
			int numeroFichero = Integer.parseInt(br.readLine());
			
			//For que recorre los ficheros disponibles
			for(int j = 0; j < ficherosDisponibles.size(); j++) {
				//Cuando coincide el numero que hemos seleccionado con el indice que nos interesa de la lista
				if(numeroFichero == j+1) {
					//Creamos un nuevo objeto de tipo file con la ruta indicandole que fichero es el que queremos eliminar
					File deleteFile = new File(file.getAbsolutePath() + "/" + ficherosDisponibles.get(j));
					System.out.println("Se a eliminado correctamente el archivo: " + deleteFile.getName());
					//eliminamos el fichero
					deleteFile.delete();
				}
			}
			
				break;
					
		case 2:
			//elegimos eliminar directorio
			//Mismo proceso que el anterior con los ficheros pero filtrando directorios!
			System.out.println("Cual de los siguientes irectorios desea eliminar?");
			for(String elemento:file.list()) {
				FiltroDirectorios filtro = new FiltroDirectorios(elemento);
				for(String fichero:file.list(filtro)) {
					System.out.println(i + "--> " + fichero);
					ficherosDisponibles.add(fichero);
					i++;
				}
				break;
			}
			
			int numeroDirectorio = Integer.parseInt(br.readLine());
			
			for(int j = 0; j < ficherosDisponibles.size(); j++) {
				
				if(numeroDirectorio == j +1) {
					
					File deleteDirectorio = new File(file.getAbsolutePath() + "/" + ficherosDisponibles.get(j));
					System.out.println("Se a eliminado correctamente el directorio: " + deleteDirectorio.getName());
					deleteDirectorio.delete();
				}
			}
			break;
		}
	}
	
	public static void Renombra(String[] args) throws NumberFormatException, IOException {

		List<String> listaElementos = new ArrayList<String>();
		
		System.out.println("Introduce mediante numeros que desea renombrar ...");
		System.out.println("1 --> Para renombrar un fichero");
		System.out.println("2 --> Para renombrar un Directorio");
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		//guardamos en una variable nuestra seleccion renombrar fichero o directorio
		int numero = Integer.parseInt(br.readLine());
		
		File file = new File(args[0]);
		
		//Indice para la posicion del fichero al imprimir por pantalla
		int i = 1;
		
		//Switch para escoger entre fichero y directorio
		switch (numero) {
		
		case 1:
			//Para renombrar ficheros
			System.out.println("Cual de los siguientes ficheros desea renombrar?");
			//Recorremos el for con todo el contenido del directorio pasado a file por argumentos
			for(String elemento:file.list()) {
				//Filtramos los que son archivos
				FiltroArchivo filtro = new FiltroArchivo(elemento);
				//Recorremos otro for esta vez solo de los ficheros
				for(String fichero:file.list(filtro)) {
					//Imprimimos la posicion con "i" y el fichero
					System.out.println(i + "--> " + fichero);
					//Añadimos el fichero a una lista de ficheros
					listaElementos.add(fichero);
					i++;
				}
				
				break;
			}
			
			//aqui guardaremos el numero del fichero que decidimos renombrar
			int numeroFichero = Integer.parseInt(br.readLine());
			System.out.println("Introduce el nuevo nombre: ");
			
			//guardamos el nuevo nombre que le pasamos por teclado
			String newName = br.readLine();
			
			//Recorremos el for de la lista de ficheros
			for(int j = 0; j < listaElementos.size(); j++) {
				
				//si el numero introducido, eleccion del usuario, coincide con el fichero de la lista...
				if(numeroFichero == j +1) {
					
					//Creamos el file que tendra el nuevo nombre con su ruta
					File renameFile = new File(file.getAbsolutePath() + "/" + listaElementos.get(j));
					//Inicializamos el file que tendra el nuevo nombre.
					File newNameFile;
					
					// este if es para saber si el fichero seleccionado tiene extension y así poder respetar el mantener la extension o no.
					if(listaElementos.get(j).contains(".")) {
						
						//obtenemos el punto donde esta la extension
						int separacionExtension = listaElementos.get(j).indexOf(".");
						//alamacenamos en la variable la extension
						String extension = listaElementos.get(j).substring(separacionExtension);
						
						//En el nuevo file añadimos la ruta absoluta con el nuevo nombre y la extension
						newNameFile = new File(file.getAbsolutePath() + "/" + newName + extension);
						//renombramos el archivo con el nuevo nombre del newFile
						renameFile.renameTo(newNameFile);
						System.out.println("Se a renombrado correctamente el archivo: " + newNameFile.getName());
						
					}else {
						//Misma situacion pero sin extension
						newNameFile = new File(file.getAbsolutePath() + "/" + newName);
						renameFile.renameTo(newNameFile);
						System.out.println("Se a renombrado correctamente el archivo: " + newNameFile.getName());
					}
				}
			}
				break;
				
		case 2:
			//Para renombrar directorios
			System.out.println("Cual de los siguientes directorios desea renombrar?");
			//Recorremos con un for el contenido del directorio pasado en file por argumentos
			for(String elemento:file.list()) {
				// nuevo Objeto filtro que solo devuelve los directorios
				FiltroDirectorios filtro = new FiltroDirectorios(elemento);
				//Recorremos la lista pero solo de los directorios ya filtrados
				for(String directorio:file.list(filtro)) {
					//imprimimos por consola los directorios indicando el indice de posicion
					System.out.println(i + "--> " + directorio);
					//Guardamos todos los directorios recorridos en una lista 
					listaElementos.add(directorio);
					i++;
				}
				
				break;
			}
			//Aqui almacenamos el numero de directorio que hemos seleccionado para renombrar
			int numeroDirectorio = Integer.parseInt(br.readLine());
			System.out.println("Introduce el nuevo nombre para el directorio: ");
			
			//Almacenamos el nuevo nombre para el directorio
			String newNameDirectory = br.readLine();
			
			//Recorremos el for de la lista de directorios, solo directorio porque hemos filtrado
			for(int j = 0; j < listaElementos.size(); j++) {
				
				//Si el numero que hemos seleccionado de directorio mostrado por pantalla coindice con el de la lista...
				if(numeroDirectorio == j +1) {
					
					//Creamos un nuevo file para poder elegir que file sera renombrado
					File renameFile = new File(file.getAbsolutePath() + "/" + listaElementos.get(j));
					//Creamos otro file con el nuevo nombre
					File newNameFile = new File(file.getAbsolutePath() + "/" + newNameDirectory);
					//Renombramos el file con el nuevo nombre
					renameFile.renameTo(newNameFile);
					System.out.println("Se a renombrado correctamente el directorio a: " + newNameFile.getName());
					
				}
			}
				break;
		}
		
	}

	public static void VolverMenu(String[] args) throws IOException {
		//Esta funcion pregunta al usuario si quiere pasar o no de nuevo por el menu
		 System.out.print("\nVolver al menu principal\nEscribe Si o No: ");
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String respuesta;
		
		//Bucle para saber si hemos introducido si o no , en caso de ser algo distinto repetir
		while(true){
			respuesta = br.readLine().toLowerCase();
			   
			    if(respuesta.equalsIgnoreCase("si")){
			      break;
			    }else if(respuesta.equalsIgnoreCase("no")){
			      break;
			    }else{
			      System.out.println("Debes introducir  SI o NO");
			    }
			}
		
		//En caso de introducir si ejecutar de nuevo el metodo Menu
		if(respuesta.equals("si")) {
			Menu(args);
		}else if(respuesta.equals("no")) {//salimos de la aplicacion
			System.out.println("Saliendo de la aplicación...");
			 System.exit(0);
		}
		
	}
}

