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

public class Biblioteca {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		
		//Creamos objetos vista y modelo y controlador los recibe por parametro.
		Vista vista = new Vista();
		Modelo modelo = new Modelo(vista);
		Controlador controlador = new Controlador(modelo,vista);
	}
}
	

	
