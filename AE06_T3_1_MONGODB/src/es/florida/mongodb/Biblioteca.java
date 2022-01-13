package es.florida.mongodb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Biblioteca {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		
		//Creamos objetos vista y modelo y controlador los recibe por parametro.
		Vista vista = new Vista();
		Modelo modelo = new Modelo(vista);
		Controlador controlador = new Controlador(modelo,vista);
	}
}
	

	
