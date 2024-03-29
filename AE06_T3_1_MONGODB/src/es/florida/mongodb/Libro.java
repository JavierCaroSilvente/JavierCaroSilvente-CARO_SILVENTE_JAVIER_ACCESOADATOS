package es.florida.mongodb;


public class Libro {

	//Atributos
	int idLibro;
	String titulo;
	String autor;
	String aņoNacimiento;
	String aņoPublicacion;
	String editorial;
	String numeroPaginas;
	
	public Libro(){}
	
//	//constructor
	public Libro(int idLibro, String titulo, String autor, String aņoNacimiento, String aņoPublicacion, String editorial, String numeroPaginas){
		
		this.idLibro = idLibro;
		this.titulo = titulo;
		this.autor = autor;
		this.aņoNacimiento = aņoNacimiento;
		this.aņoPublicacion = aņoPublicacion;
		this.editorial = editorial;
		this.numeroPaginas = numeroPaginas;
	}
	
	//constructor
	public Libro(String titulo, String autor,String aņoNacimiento, String aņoPublicacion, String editorial, String numeroPaginas){
		
		this.titulo = titulo;
		this.autor = autor;
		this.aņoNacimiento = aņoNacimiento;
		this.aņoPublicacion = aņoPublicacion;
		this.editorial = editorial;
		this.numeroPaginas = numeroPaginas;
	}
	
	//////////// GETERS Y SETERS
	public int getIdLibro() {
		return idLibro;
	}
	
	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}
	///////////
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	///////////
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	////////////
		
	public String getAņoNacimiento() {
		return aņoNacimiento;
	}
	
	public void setAņoNacimiento(String aņoNacimiento) {
		this.aņoNacimiento = aņoNacimiento;
	}
	
	////////////
	
	public String getAņoPublicacion() {
		return aņoPublicacion;
	}
	
	public void setAņoPublicacion(String aņoPublicacion) {
		this.aņoPublicacion = aņoPublicacion;
	}
	////////////
	public String getEditorial() {
		return editorial;
	}
	
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	////////////
	public String getNumeroPaginas() {
		return numeroPaginas;
	}
	
	public void setNumeroPaginas(String numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
	
	//Para devolver toda la info del libro
	public String toString() {
		String infoCompleta = "Objeto libro -> Identificador: "+ idLibro + " - Titulo: " + titulo + " - Autor: " + autor + " - Aņo nacimiento: " + aņoNacimiento + " - Aņo publicaciķn: " + aņoPublicacion + " - Editorial: " + editorial + " - nē Paginas: " + numeroPaginas;
		return infoCompleta;
	}
	
}
