import java.io.File;
import java.io.FilenameFilter;

public class FiltroArchivo implements FilenameFilter {

	String directorio;
	
	FiltroArchivo(String directorio){
		this.directorio = directorio;
	}

	@Override //metodo que devuelve si es un archivo
	public boolean accept(File dir, String name) {
		// TODO Auto-generated method stub
		 return new File(dir, name).isFile();
	}
}