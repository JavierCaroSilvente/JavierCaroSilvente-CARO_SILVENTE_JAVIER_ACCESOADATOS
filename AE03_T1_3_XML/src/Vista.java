
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.TextField;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Vista {

	private JFrame frame;
	
	
	private JButton Crear_Libro;
	private JButton btnMostrarTodos;
	private JButton btnMostrarDetallado;
	private JButton btnActualizarLibro;
	private JButton btnBorrarLibro;
	private JButton btnCerrarLaBiblioteca;
	
	private JTextArea textArea_Original;
	private JScrollPane scrollPane_Menu;
	private JTextArea textArea_Menu;
	
	private JTextField txtField_Identificador;
	private JTextField txtField_Titulo;
	private JTextField txtField_Autor;
	private JTextField txtField_AñoDePublicacion;
	private JTextField txtField_Editorial;
	private JTextField txtField_NumeroPaginas;
	
	
	private JTextField textField_Identificador_MostrarInfoLibro;
	private JTextField textField_Identificador_BorrarLibro;
	
	private JTextField textField_Identificador_ActualizarLibro;
	private JTextField txtField_NuevoTitulo_ActualizarLibro;
	private JTextField txtField_NuevoAutor_ActualizarLibro;
	private JTextField txtField_NuevoAñoDePublicacion_ActualizarLibro;
	private JTextField txtField_NuevaEditorial_ActualizarLibro;
	private JTextField txtField_NuevoNumeroDePaginas_ActualizarLibro;

	
	public Vista() {
		initialize();
	}
	

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 962, 581);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane_Original = new JScrollPane();
		scrollPane_Original.setBounds(365, 20, 400, 276);
		frame.getContentPane().add(scrollPane_Original);
		
		textArea_Original = new JTextArea();
		textArea_Original.setLineWrap(true);
		textArea_Original.setRows(12);
		scrollPane_Original.setViewportView(textArea_Original);
		scrollPane_Original.getViewport().setView(textArea_Original);
		
		txtField_Identificador = new JTextField();
		txtField_Identificador.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				if(txtField_Identificador.getText().equals("Identificador")) {
					txtField_Identificador.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtField_Identificador.getText().equals("")) {
					txtField_Identificador.setText("Identificador");
				}
			}
		});
		txtField_Identificador.setForeground(Color.LIGHT_GRAY);
		txtField_Identificador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtField_Identificador.setText("Identificador");
		txtField_Identificador.setToolTipText("");
		txtField_Identificador.setBounds(152, 385, 89, 27);
		frame.getContentPane().add(txtField_Identificador);
		txtField_Identificador.setColumns(10);
		
		Crear_Libro = new JButton("Crear Libro");
		Crear_Libro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Crear_Libro.setBounds(22, 385, 120, 27);
		frame.getContentPane().add(Crear_Libro);
		
		btnMostrarTodos = new JButton("Mostrar todos los t\u00EDtulos");
		btnMostrarTodos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnMostrarTodos.setBounds(22, 309, 169, 27);
		frame.getContentPane().add(btnMostrarTodos);
		
		btnMostrarDetallado = new JButton("Mostrar informaci\u00F3n detallada de un libro");
		btnMostrarDetallado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnMostrarDetallado.setBounds(22, 348, 256, 27);
		frame.getContentPane().add(btnMostrarDetallado);
		
		btnActualizarLibro = new JButton("Actualizar libro");
		btnActualizarLibro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnActualizarLibro.setBounds(22, 422, 120, 27);
		frame.getContentPane().add(btnActualizarLibro);
		
		btnBorrarLibro = new JButton("Borrar libro");
		btnBorrarLibro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBorrarLibro.setBounds(22, 459, 120, 27);
		frame.getContentPane().add(btnBorrarLibro);
		
		btnCerrarLaBiblioteca = new JButton("Cerrar la biblioteca");
		btnCerrarLaBiblioteca.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCerrarLaBiblioteca.setBounds(22, 496, 142, 27);
		frame.getContentPane().add(btnCerrarLaBiblioteca);
		
		scrollPane_Menu = new JScrollPane();
		scrollPane_Menu.setBounds(22, 20, 288, 126);
		frame.getContentPane().add(scrollPane_Menu);
		
		textArea_Menu = new JTextArea();
		scrollPane_Menu.setViewportView(textArea_Menu);
		
		txtField_Titulo = new JTextField();
		txtField_Titulo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				if(txtField_Titulo.getText().equals("Titulo")) {
					txtField_Titulo.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(txtField_Titulo.getText().equals("")) {
					txtField_Titulo.setText("Titulo");
				}
			}
		});
		txtField_Titulo.setToolTipText("");
		txtField_Titulo.setText("Titulo");
		txtField_Titulo.setForeground(Color.LIGHT_GRAY);
		txtField_Titulo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtField_Titulo.setColumns(10);
		txtField_Titulo.setBounds(251, 385, 99, 27);
		frame.getContentPane().add(txtField_Titulo);
		
		txtField_Autor = new JTextField();
		txtField_Autor.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtField_Autor.getText().equals("Autor")) {
					txtField_Autor.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtField_Autor.getText().equals("")) {
					txtField_Autor.setText("Autor");
				}
			}
		});
		txtField_Autor.setToolTipText("");
		txtField_Autor.setText("Autor");
		txtField_Autor.setForeground(Color.LIGHT_GRAY);
		txtField_Autor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtField_Autor.setColumns(10);
		txtField_Autor.setBounds(361, 385, 99, 27);
		frame.getContentPane().add(txtField_Autor);
		
		txtField_AñoDePublicacion = new JTextField();
		txtField_AñoDePublicacion.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtField_AñoDePublicacion.getText().equals("Año de publicacion")) {
					txtField_AñoDePublicacion.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtField_AñoDePublicacion.getText().equals("")) {
					txtField_AñoDePublicacion.setText("Año de publicacion");
				}
			}
		});
		txtField_AñoDePublicacion.setToolTipText("");
		txtField_AñoDePublicacion.setText("A\u00F1o de publicacion");
		txtField_AñoDePublicacion.setForeground(Color.LIGHT_GRAY);
		txtField_AñoDePublicacion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtField_AñoDePublicacion.setColumns(10);
		txtField_AñoDePublicacion.setBounds(470, 385, 148, 27);
		frame.getContentPane().add(txtField_AñoDePublicacion);
		
		txtField_Editorial = new JTextField();
		txtField_Editorial.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtField_Editorial.getText().equals("Editorial")) {
					txtField_Editorial.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtField_Editorial.getText().equals("")) {
					txtField_Editorial.setText("Editorial");
				}
			}
		});
		txtField_Editorial.setToolTipText("");
		txtField_Editorial.setText("Editorial");
		txtField_Editorial.setForeground(Color.LIGHT_GRAY);
		txtField_Editorial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtField_Editorial.setColumns(10);
		txtField_Editorial.setBounds(628, 385, 112, 27);
		frame.getContentPane().add(txtField_Editorial);
		
		txtField_NumeroPaginas = new JTextField();
		txtField_NumeroPaginas.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtField_NumeroPaginas.getText().equals("Número de páginas")) {
					txtField_NumeroPaginas.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtField_NumeroPaginas.getText().equals("")) {
					txtField_NumeroPaginas.setText("Número de páginas");
				}
			}
		});
		txtField_NumeroPaginas.setToolTipText("");
		txtField_NumeroPaginas.setText("N\u00FAmero de p\u00E1ginas");
		txtField_NumeroPaginas.setForeground(Color.LIGHT_GRAY);
		txtField_NumeroPaginas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtField_NumeroPaginas.setColumns(10);
		txtField_NumeroPaginas.setBounds(750, 385, 155, 27);
		frame.getContentPane().add(txtField_NumeroPaginas);
		
		
		
		textField_Identificador_ActualizarLibro = new JTextField();
		textField_Identificador_ActualizarLibro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(textField_Identificador_ActualizarLibro.getText().equals("Identificador")) {
					textField_Identificador_ActualizarLibro.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(textField_Identificador_ActualizarLibro.getText().equals("")) {
					textField_Identificador_ActualizarLibro.setText("Identificador");
				}
			}
		});
		textField_Identificador_ActualizarLibro.setToolTipText("");
		textField_Identificador_ActualizarLibro.setText("Identificador");
		textField_Identificador_ActualizarLibro.setForeground(Color.LIGHT_GRAY);
		textField_Identificador_ActualizarLibro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_Identificador_ActualizarLibro.setColumns(10);
		textField_Identificador_ActualizarLibro.setBounds(152, 422, 89, 27);
		frame.getContentPane().add(textField_Identificador_ActualizarLibro);
		
		textField_Identificador_MostrarInfoLibro = new JTextField();
		textField_Identificador_MostrarInfoLibro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(textField_Identificador_MostrarInfoLibro.getText().equals("Identificador")) {
					textField_Identificador_MostrarInfoLibro.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(textField_Identificador_MostrarInfoLibro.getText().equals("")) {
					textField_Identificador_MostrarInfoLibro.setText("Identificador");
				}
			}
		});
		textField_Identificador_MostrarInfoLibro.setToolTipText("");
		textField_Identificador_MostrarInfoLibro.setText("Identificador");
		textField_Identificador_MostrarInfoLibro.setForeground(Color.LIGHT_GRAY);
		textField_Identificador_MostrarInfoLibro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_Identificador_MostrarInfoLibro.setColumns(10);
		textField_Identificador_MostrarInfoLibro.setBounds(288, 348, 89, 27);
		frame.getContentPane().add(textField_Identificador_MostrarInfoLibro);
		
		textField_Identificador_BorrarLibro = new JTextField();
		textField_Identificador_BorrarLibro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(textField_Identificador_BorrarLibro.getText().equals("Identificador")) {
					textField_Identificador_BorrarLibro.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(textField_Identificador_BorrarLibro.getText().equals("")) {
					textField_Identificador_BorrarLibro.setText("Identificador");
				}
			}
		});
		textField_Identificador_BorrarLibro.setToolTipText("");
		textField_Identificador_BorrarLibro.setText("Identificador");
		textField_Identificador_BorrarLibro.setForeground(Color.LIGHT_GRAY);
		textField_Identificador_BorrarLibro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_Identificador_BorrarLibro.setColumns(10);
		textField_Identificador_BorrarLibro.setBounds(152, 459, 89, 27);
		frame.getContentPane().add(textField_Identificador_BorrarLibro);
		
		txtField_NuevoTitulo_ActualizarLibro = new JTextField();
		txtField_NuevoTitulo_ActualizarLibro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtField_NuevoTitulo_ActualizarLibro.getText().equals("Nuevo Título")) {
					txtField_NuevoTitulo_ActualizarLibro.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtField_NuevoTitulo_ActualizarLibro.getText().equals("")) {
					txtField_NuevoTitulo_ActualizarLibro.setText("Nuevo Título");
				}
			}
		});
		txtField_NuevoTitulo_ActualizarLibro.setToolTipText("");
		txtField_NuevoTitulo_ActualizarLibro.setText("Nuevo T\u00EDtulo");
		txtField_NuevoTitulo_ActualizarLibro.setForeground(Color.LIGHT_GRAY);
		txtField_NuevoTitulo_ActualizarLibro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtField_NuevoTitulo_ActualizarLibro.setColumns(10);
		txtField_NuevoTitulo_ActualizarLibro.setBounds(251, 422, 99, 27);
		frame.getContentPane().add(txtField_NuevoTitulo_ActualizarLibro);
		
		txtField_NuevoAutor_ActualizarLibro = new JTextField();
		txtField_NuevoAutor_ActualizarLibro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtField_NuevoAutor_ActualizarLibro.getText().equals("Nuevo Autor")) {
					txtField_NuevoAutor_ActualizarLibro.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtField_NuevoAutor_ActualizarLibro.getText().equals("")) {
					txtField_NuevoAutor_ActualizarLibro.setText("Nuevo Autor");
				}
			}
		});
		txtField_NuevoAutor_ActualizarLibro.setToolTipText("");
		txtField_NuevoAutor_ActualizarLibro.setText("Nuevo Autor");
		txtField_NuevoAutor_ActualizarLibro.setForeground(Color.LIGHT_GRAY);
		txtField_NuevoAutor_ActualizarLibro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtField_NuevoAutor_ActualizarLibro.setColumns(10);
		txtField_NuevoAutor_ActualizarLibro.setBounds(361, 422, 99, 27);
		frame.getContentPane().add(txtField_NuevoAutor_ActualizarLibro);
		
		txtField_NuevoAñoDePublicacion_ActualizarLibro = new JTextField();
		txtField_NuevoAñoDePublicacion_ActualizarLibro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtField_NuevoAñoDePublicacion_ActualizarLibro.getText().equals("Nuevo Año de publicacion")) {
					txtField_NuevoAñoDePublicacion_ActualizarLibro.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtField_NuevoAñoDePublicacion_ActualizarLibro.getText().equals("")) {
					txtField_NuevoAñoDePublicacion_ActualizarLibro.setText("Nuevo Año de publicacion");
				}
			}
		});
		txtField_NuevoAñoDePublicacion_ActualizarLibro.setToolTipText("");
		txtField_NuevoAñoDePublicacion_ActualizarLibro.setText("Nuevo A\u00F1o de publicacion");
		txtField_NuevoAñoDePublicacion_ActualizarLibro.setForeground(Color.LIGHT_GRAY);
		txtField_NuevoAñoDePublicacion_ActualizarLibro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtField_NuevoAñoDePublicacion_ActualizarLibro.setColumns(10);
		txtField_NuevoAñoDePublicacion_ActualizarLibro.setBounds(470, 422, 148, 27);
		frame.getContentPane().add(txtField_NuevoAñoDePublicacion_ActualizarLibro);
		
		txtField_NuevaEditorial_ActualizarLibro = new JTextField();
		txtField_NuevaEditorial_ActualizarLibro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtField_NuevaEditorial_ActualizarLibro.getText().equals("Nueva Editorial")) {
					txtField_NuevaEditorial_ActualizarLibro.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtField_NuevaEditorial_ActualizarLibro.getText().equals("")) {
					txtField_NuevaEditorial_ActualizarLibro.setText("Nueva Editorial");
				}
			}
		});
		txtField_NuevaEditorial_ActualizarLibro.setToolTipText("");
		txtField_NuevaEditorial_ActualizarLibro.setText("Nueva Editorial");
		txtField_NuevaEditorial_ActualizarLibro.setForeground(Color.LIGHT_GRAY);
		txtField_NuevaEditorial_ActualizarLibro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtField_NuevaEditorial_ActualizarLibro.setColumns(10);
		txtField_NuevaEditorial_ActualizarLibro.setBounds(628, 422, 112, 27);
		frame.getContentPane().add(txtField_NuevaEditorial_ActualizarLibro);
		
		txtField_NuevoNumeroDePaginas_ActualizarLibro = new JTextField();
		txtField_NuevoNumeroDePaginas_ActualizarLibro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtField_NuevoNumeroDePaginas_ActualizarLibro.getText().equals("Nuevo Número de páginas")) {
					txtField_NuevoNumeroDePaginas_ActualizarLibro.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtField_NuevoNumeroDePaginas_ActualizarLibro.getText().equals("")) {
					txtField_NuevoNumeroDePaginas_ActualizarLibro.setText("Nuevo Número de páginas");
				}
			}
		});
		txtField_NuevoNumeroDePaginas_ActualizarLibro.setToolTipText("");
		txtField_NuevoNumeroDePaginas_ActualizarLibro.setText("Nuevo N\u00FAmero de p\u00E1ginas");
		txtField_NuevoNumeroDePaginas_ActualizarLibro.setForeground(Color.LIGHT_GRAY);
		txtField_NuevoNumeroDePaginas_ActualizarLibro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtField_NuevoNumeroDePaginas_ActualizarLibro.setColumns(10);
		txtField_NuevoNumeroDePaginas_ActualizarLibro.setBounds(750, 422, 155, 27);
		frame.getContentPane().add(txtField_NuevoNumeroDePaginas_ActualizarLibro);
		
		this.frame.setVisible(true);
	}
	
	public JTextArea getTextAreaMenu() {
		return textArea_Menu;
	}
	
	public JTextArea getTextAreaOriginal() {
		return textArea_Original;
	}
	
	
	/////////Mostrar Todos
	public JButton getBtnMostrarTodos() {
		return btnMostrarTodos;
	}
	
	/////////Borrar Libro
	public JButton getBtnBorrarLibro() {
		return btnBorrarLibro;
	}
	
	public JTextField getTextField_Identificador_BorrarLibro() {
		return textField_Identificador_BorrarLibro;
	}
	
	/////////Cerrar biblioteca
	public JButton getBtnCerrarLaBiblioteca() {
		return btnCerrarLaBiblioteca;
	}
	
	/////////Mostrar libro detallado
	public JButton getBtnMostrarDetallado() {
		return btnMostrarDetallado;
	}
	
	public JTextField getTextFieldMonstrarInfoLibro() {
		return textField_Identificador_MostrarInfoLibro;
	}
	
	
	//////////CREAR LIBRO
	public JButton getBtnCrearLibro() {
		return Crear_Libro;
	}
	
	public JTextField getTextFieldIdentificador() {
		return txtField_Identificador;
	}
	
	public JTextField getTextFieldTitulo() {
		return txtField_Titulo;
	}
	
	public JTextField getTextFieldAutor() {
		return txtField_Autor;
	}
	
	public JTextField getTextFieldAñoDePublicacion() {
		return txtField_AñoDePublicacion;
	}
	
	public JTextField getTextFieldEditorial() {
		return txtField_Editorial;
	}
	
	public JTextField getTextFieldNumeroPaginas() {
		return txtField_NumeroPaginas;
	}
	
	
	////////ACTUALIZAR LIBRO
	public JButton getBtnActualizarLibro() {
		return btnActualizarLibro;
	}
	
	public JTextField getTextField_Identificador_ActualizarLibro() {
		return textField_Identificador_ActualizarLibro;
	}
	
	public JTextField getTextField_NuevoTitulo_ActualizarLibro() {
		return txtField_NuevoTitulo_ActualizarLibro;
	}
	
	public JTextField getTextField_NuevoAutor_ActualizarLibro() {
		return txtField_NuevoAutor_ActualizarLibro;
	}
	public JTextField getTextField_NuevoAñoDePublicacion_ActualizarLibro() {
		return txtField_NuevoAñoDePublicacion_ActualizarLibro;
	}
	public JTextField getTextField_NuevaEditorial_ActualizarLibro() {
		return txtField_NuevaEditorial_ActualizarLibro;
	}
	public JTextField getTextField_NuevoNuemroDePaginas_ActualizarLibro() {
		return txtField_NuevoNumeroDePaginas_ActualizarLibro;
	}
	
	
}
