package es.florida.hibernate;

public class Libro {

	//Atributos
	int idLibro;
	String titulo;
	String autor;
	String a�oPublicacion;
	String editorial;
	String numeroPaginas;
	
	public Libro(){}
	
//	//constructor
	public Libro(int idLibro, String titulo, String autor, String a�oPublicacion, String editorial, String numeroPaginas){
		
		this.idLibro = idLibro;
		this.titulo = titulo;
		this.autor = autor;
		this.a�oPublicacion = a�oPublicacion;
		this.editorial = editorial;
		this.numeroPaginas = numeroPaginas;
	}
	
	//constructor
	public Libro(String titulo, String autor, String a�oPublicacion, String editorial, String numeroPaginas){
		
		this.titulo = titulo;
		this.autor = autor;
		this.a�oPublicacion = a�oPublicacion;
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
	
	public String getA�oPublicacion() {
		return a�oPublicacion;
	}
	
	public void setA�oPublicacion(String a�oPublicacion) {
		this.a�oPublicacion = a�oPublicacion;
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
		String infoCompleta = "Objeto libro -> Identificador: "+ idLibro + " - Titulo: " + titulo + " - Autor: " + autor + " - A�o publicaci�n: " + a�oPublicacion + " - Editorial: " + editorial + " - n� Paginas: " + numeroPaginas;
		return infoCompleta;
	}
	
}
