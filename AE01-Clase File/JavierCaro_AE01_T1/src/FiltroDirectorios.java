import java.io.File;
import java.io.FilenameFilter;

public class FiltroDirectorios implements FilenameFilter {

	String directorio;
	
	FiltroDirectorios(String directorio){
		this.directorio = directorio;
	}

	@Override  //metodo que devuelve si es un directorio
	public boolean accept(File dir, String name) {
		// TODO Auto-generated method stub
		 return new File(dir, name).isDirectory();
	}
}
