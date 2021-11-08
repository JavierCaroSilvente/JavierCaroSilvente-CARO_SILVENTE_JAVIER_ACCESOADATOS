public class Libro {

	//Atributos
	String titulo, autor, año_nacimiento, año_publicacion, editorial, n_paginas;
	
	Libro() {}
	
	//constructor
	Libro( String titulo, String autor, String año_nacimiento, String año_publicacion, String editorial, String n_paginas){
		
		this.titulo = titulo;
		this.autor = autor;
		this.año_nacimiento = año_nacimiento;
		this.año_publicacion = año_publicacion;
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
	public String getAñoNacimiento() {
		return año_nacimiento;
	}
	
	public void setAñoNacimiento(String año_nacimiento) {
		this.año_nacimiento = año_nacimiento;
	}
	////////////
	public String getAñoPublicacion() {
		return año_publicacion;
	}
	
	public void setAñoPublicacion(String año_publicacion) {
		this.año_publicacion = año_publicacion;
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
		String infoCompleta = "Objeto libro -> - Titulo: " + titulo + " - Autor: " + autor + " - Año nacimiento: " + año_nacimiento + " - Año publicación: " + año_publicacion + " - Editorial: " + editorial + " - nº Paginas: " + n_paginas;
		return infoCompleta;
	}
	
}
