public class Libro {

	//Atributos
	String titulo, autor, a�o_nacimiento, a�o_publicacion, editorial, n_paginas;
	
	Libro() {}
	
	//constructor
	Libro( String titulo, String autor, String a�o_nacimiento, String a�o_publicacion, String editorial, String n_paginas){
		
		this.titulo = titulo;
		this.autor = autor;
		this.a�o_nacimiento = a�o_nacimiento;
		this.a�o_publicacion = a�o_publicacion;
		this.editorial = editorial;
		this.n_paginas = n_paginas;
	}
	
	//Geters y seters
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
	public String getA�oNacimiento() {
		return a�o_nacimiento;
	}
	
	public void setA�oNacimiento(String a�o_nacimiento) {
		this.a�o_nacimiento = a�o_nacimiento;
	}
	////////////
	public String getA�oPublicacion() {
		return a�o_publicacion;
	}
	
	public void setA�oPublicacion(String a�o_publicacion) {
		this.a�o_publicacion = a�o_publicacion;
	}
	////////////
	public String getEditorial() {
		return editorial;
	}
	
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	////////////
	public String getN_Paginas() {
		return n_paginas;
	}
	
	public void setN_Paginas(String n_paginas) {
		this.n_paginas = n_paginas;
	}
	
	//Para devolver toda la info del libro
	public String toString() {
		String infoCompleta = "Objeto libro -> - Titulo: " + titulo + " - Autor: " + autor + " - A�o nacimiento: " + a�o_nacimiento + " - A�o publicaci�n: " + a�o_publicacion + " - Editorial: " + editorial + " - n� Paginas: " + n_paginas;
		return infoCompleta;
	}
	
}
