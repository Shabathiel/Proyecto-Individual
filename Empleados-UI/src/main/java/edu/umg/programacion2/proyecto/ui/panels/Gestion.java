package edu.umg.programacion2.proyecto.ui.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import edu.umg.programacion2.proyecto.dao.EmpleadosDAO;
import edu.umg.programacion2.proyecto.model.Empleado;
import edu.umg.programacion2.proyecto.ui.decorador.Estilos;


public class Gestion extends JPanel{

	private static final long serialVersionUID = 1L;
	
	JLabel lblNombre,lblTitulo,lblId,lblDepartamento, lblSalario,lblFecha, lblActivo;
	JTextField txtNombre,txtId,txtSalario,txtFecha;
	JButton bttCreate, bttRead, bttUpdate, bttDelete;
	JComboBox<String> jcbDepartamento;
	JRadioButton jrbSi, jrbNo;
	ButtonGroup btGrupo;
	
	//Este es un atributo porque no compensaba hacer una clase solo para eso
	//Y no quería estarlo declarando a cada rato, lo voy a usar varías veces
	static DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Gestion() {
		setLayout(new BorderLayout());
		setBackground(Estilos.FONDO);
		initComponents();
		decorateComponents();
		
	}
	
	public void initComponents() {
		//Inicializamos los componentes globales
		lblTitulo = new JLabel("Gestiones");
		lblId = new JLabel("ID: ");
		lblNombre = new JLabel("Nombre: ");
		lblDepartamento = new JLabel("Departamento: ");
		lblSalario = new JLabel("Salario: ");
		lblFecha = new JLabel("Fecha de contratación: ");
		lblActivo = new JLabel("Activo");
		
		txtNombre = new JTextField();
		txtId = new JTextField();
		txtSalario = new JTextField();
		txtFecha = new JTextField();
		
		btGrupo = new ButtonGroup();
		jrbSi = new JRadioButton("Si");
		jrbNo = new JRadioButton("No");
		
		btGrupo.add(jrbSi);
		btGrupo.add(jrbNo);
		
		jcbDepartamento = new JComboBox<>();
		jcbDepartamento.setEditable(true);
		
		try {
			List<String> elementos = EmpleadosDAO.obtenerDepartamentos();
			jcbDepartamento.addItem("");
			for(String e: elementos) {
				jcbDepartamento.addItem(e);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "No se pudieron cargar los departamentos." + e,"Advertencia",JOptionPane.WARNING_MESSAGE);
		}
		
		bttCreate = new JButton("Crear Empleado");
		bttUpdate = new JButton("Editar Empleado");
		bttRead = new JButton("Buscar");
		bttDelete = new JButton("Eliminar");
				
		// El titulo ira fuera de las demas cuadriculas para no estorbar mucho
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(lblTitulo, BorderLayout.NORTH);
		
		//Se crea la cuadriculoa para que se haga más facil el colocar los elementos interactuables
		
		JPanel panelCampos = new JPanel(new GridBagLayout());
		panelCampos.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40)); // margen exterior
		panelCampos.setBackground(Estilos.FONDO);

		// Helpers
		JLabel[] etiquetas = {lblId, lblNombre, lblDepartamento, lblSalario, lblFecha, lblActivo};
		Component[] campos = {txtId, txtNombre, jcbDepartamento, txtSalario, txtFecha, null};

		for (int i = 0; i < etiquetas.length; i++) {
		    panelCampos.add(etiquetas[i], gbc(0, i, 1, 1.0, GridBagConstraints.HORIZONTAL));
		    if (campos[i] != null) {
		        panelCampos.add(campos[i], gbc(1, i, 2, 1.0, GridBagConstraints.HORIZONTAL));
		    }
		}

		// Fila especial de los radio buttons
		JPanel panelActivo = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		panelActivo.setOpaque(false);
		panelActivo.add(jrbSi);
		panelActivo.add(jrbNo);
		panelCampos.add(panelActivo, gbc(1, 5, 2, 1.0, GridBagConstraints.HORIZONTAL));
		
		add(panelCampos,BorderLayout.WEST);
		
		//Panel de botones para ordenar la interfaz
		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, Estilos.GAP_BOTONES, 15));
		panelBotones.setBackground(Estilos.FONDO);
		panelBotones.add(bttCreate);
		panelBotones.add(bttRead);
		panelBotones.add(bttUpdate);
		panelBotones.add(bttDelete);
		
		// Aquí están las funciones para cada boton
		bttCreate.addActionListener(e -> { Guardar();});
		bttRead.addActionListener(e -> {Leer();});
		bttUpdate.addActionListener(e -> {Editar();});
		bttDelete.addActionListener(e -> {Borrar();});
		
		add(panelBotones,BorderLayout.SOUTH);
	}
	
	public void decorateComponents() {
		Estilos.Titulo(lblTitulo);
		
		// Aqui iran los estilos de todos los elementos pero para que no se haga muy pesada esta clase se usara una clase externa para decorarlos
		
		Estilos.CamposGestion(txtFecha);
		Estilos.CamposGestion(txtNombre);
		Estilos.CamposGestion(txtId);
		Estilos.CamposGestion(txtSalario);
		Estilos.CamposGestion(jcbDepartamento);
		
		Estilos.BotonGestion(bttCreate);
		Estilos.BotonGestion(bttRead);
		Estilos.BotonGestion(bttUpdate);
		Estilos.botonPeligro(bttDelete);
		
		Estilos.etiqueta(lblNombre);
		Estilos.etiqueta(lblDepartamento);
		Estilos.etiqueta(lblId);
		Estilos.etiqueta(lblSalario);
		Estilos.etiqueta(lblFecha);
		Estilos.etiqueta(lblActivo);
		
		Estilos.radio(jrbNo);
		Estilos.radio(jrbSi);
	}
	
	// *Bloque de funciones para comunicarse con el dao*
	// Aunque sea muy cargado es la mejor opción ya que neceisto interactuar constantemente con los elementos y entre clases se haría mucho embrollo con las referencías.
	
	public void Guardar() {
		//El monton lineas de excepciones y la mayoría no usa las mismas así que no puedo crear un metodo :(
		if ( txtNombre.getText().isBlank() || txtSalario.getText().isBlank() || txtFecha.getText().isBlank() || jcbDepartamento.getSelectedItem() == null ) {
			JOptionPane.showMessageDialog(this, "Necesita llenar los campos para continuar.","Advertencia",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (! esFechaValida(txtFecha.getText())) {
			JOptionPane.showMessageDialog(this, "El campo fecha está mal formateado.(dd/MM/yyyy)","Advertencia",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (LocalDate.parse(txtFecha.getText(),formateador).isAfter((LocalDate.now()))) {
			JOptionPane.showMessageDialog(this, "El campo fecha no puede ser despues de hoy. " + LocalDate.now().toString(),"Advertencia",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (! txtSalario.getText().matches("-?\\d+(\\.\\d+)?")) {
			JOptionPane.showMessageDialog(this, "El ingresado debe ser un valor numérico mayor que cero","Advertencia",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (Double.parseDouble(txtSalario.getText()) <= 0){
			JOptionPane.showMessageDialog(this, "El ingresado debe ser un valor numérico mayor que cero","Advertencia",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		try {
			int producto = EmpleadosDAO.crearEmpleado(obtenerEmpleado());
			JOptionPane.showMessageDialog(this, "Empleado creado con exito con id: ." + producto,"Exito",JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error al crear el empleado. " + e,"Advertencia",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		
	}
	
	public boolean Leer() {
		if ( txtId.getText().isBlank() || ! txtId.getText().matches("-?\\d+(\\.\\d+)?")) {
			JOptionPane.showMessageDialog(this, "Necesita llenar el campo ID con un valor numérico valido.","Advertencia",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		int id = Integer.parseInt(txtId.getText());
		Empleado empleado = null;
		
		try {
			Optional<Empleado> emp = EmpleadosDAO.buscarPorId(id);
			  if (emp.isPresent()) {
	                empleado = emp.get();
	            } else {
	            	JOptionPane.showMessageDialog(this, "No se encontró empleado con el id","Advertencia",JOptionPane.WARNING_MESSAGE);
	            	return true;
	            }
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error al leer el empleado. " + e,"Advertencia",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		txtNombre.setText(empleado.getNombre());
		txtSalario.setText(String.valueOf(empleado.getSalario()/100));
		txtFecha.setText(empleado.getFecha_ingreso().format(formateador));
		jcbDepartamento.getEditor().setItem(empleado.getDepartamento());
		if (empleado.isActivo()) jrbSi.setSelected(true);
		else jrbNo.setSelected(true);
		
		return true;
	}
	
	public void Editar() {
		String stringId = txtId.getText();
		String nombre = txtNombre.getText();
		String stringSalario = txtSalario.getText();
		Object seleccionDepartamento = jcbDepartamento.getSelectedItem();
		Empleado empleado = null;
		
		boolean camposVacios = nombre.isBlank() && stringSalario.isBlank() && seleccionDepartamento == null  && btGrupo.getSelection() == null; 
		if ( camposVacios || txtId.getText().isBlank() ) {
			JOptionPane.showMessageDialog(this, "Necesita llenar los campos para continuar.","Advertencia",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (! stringId.matches("-?\\d+(\\.\\d+)?")) {
			JOptionPane.showMessageDialog(this, "Necesita llenar el campo ID con un valor numérico valido.","Advertencia",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (! stringSalario.matches("-?\\d+(\\.\\d+)?") && ! stringSalario.isBlank()) {
			JOptionPane.showMessageDialog(this, "El ingresado debe ser un valor numérico mayor que cero 1","Advertencia",JOptionPane.WARNING_MESSAGE);
			return;
		}

		
		int id = Integer.parseInt(txtId.getText());
		try {
			Optional<Empleado> emp = EmpleadosDAO.buscarPorId(id);
			  if (emp.isPresent()) {
	                empleado = emp.get();
	            } else {
	            	JOptionPane.showMessageDialog(this, "No se encontró empleado con el id","Advertencia",JOptionPane.WARNING_MESSAGE);
	            	return;
	            }
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error al actualizar el empleado. " + e,"Advertencia",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (! nombre.isBlank()) empleado.setNombre(nombre);
		if (! stringSalario.isBlank()) empleado.setSalario((int) (Double.parseDouble(stringSalario) * 100)  );
		if (seleccionDepartamento != null) empleado.setDepartamento(seleccionDepartamento.toString());
		if (btGrupo.getSelection() != null) empleado.setActivo(jrbSi.isSelected());
	
		try {
			boolean actualizado = EmpleadosDAO.actualizarEmpleado(empleado);
			if (actualizado) {
				JOptionPane.showMessageDialog(this, "Empleado actualizado con exit." ,"Exito",JOptionPane.INFORMATION_MESSAGE);
			} else {
				System.out.println("No existe ningun Empleado con ese id.");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error al actualizar el empleado. " + e,"Advertencia",JOptionPane.WARNING_MESSAGE);
		}
		
		
	}
	
	public void Borrar() {
		
		if(! Leer()) return;
		
		if (  JOptionPane.showConfirmDialog (this, "¿Esta seguró que quiere borrar el empleado?","Advertencia",JOptionPane.YES_NO_OPTION) == JOptionPane.	NO_OPTION) {
			return;
		}
		
		int id = Integer.parseInt(txtId.getText());
		
		try {
            boolean eliminado = EmpleadosDAO.eliminarEmpleado(id);
            if (eliminado) {
            	JOptionPane.showMessageDialog(this, "Empleado eliminado.","Exito",JOptionPane.INFORMATION_MESSAGE);
            } else {
            	JOptionPane.showMessageDialog(this, "No se encontró empleado con el id","Advertencia",JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(this, "Error al eliminar el empleado. " + e,"Advertencia",JOptionPane.WARNING_MESSAGE);
        }
		
		txtId.setText("");
		txtNombre.setText("");
		txtSalario.setText("");
		txtFecha.setText("");
		jcbDepartamento.setSelectedItem(null);
		btGrupo.clearSelection();
	}

	public Empleado obtenerEmpleado() {
		String textoId = txtId.getText();
		int id;
		
		if (textoId.isBlank() && ! textoId.matches("-?\\d+(\\.\\d+)?")) id = 0;
		else id = Integer.parseInt(textoId);
		
		String nombre = txtNombre.getText();
		double salario = Double.parseDouble(txtSalario.getText());
		salario *= 100; //Para que se guarde en centavos
		
		LocalDate fecha = LocalDate.parse(txtFecha.getText(),formateador);
		String departamento = (String) jcbDepartamento.getSelectedItem();
		boolean activo;
		if (jrbSi.isSelected() ) activo = true;
		else activo = false;
		
		return new Empleado(id, nombre, departamento, (int) salario, fecha,activo,"Borar despues");
	}
	
	public static boolean esFechaValida(String fechaTexto) {
        try {
            LocalDate.parse(fechaTexto, formateador);
            return true; // La fecha es válida y coincide con el formato
        } catch (DateTimeParseException e) {
            return false; // La fecha es inválida o no coincide con el formato
        }
    }

	
	private GridBagConstraints gbc(int x, int y, int w, double wx, int fill) {
	    GridBagConstraints g = new GridBagConstraints();
	    g.gridx = x;
	    g.gridy = y;
	    g.gridwidth = w;
	    g.weightx = wx;
	    g.fill = fill;
	    g.insets = new Insets(5, 8, 5, 8); // ¡Espaciado entre filas!
	    g.anchor = GridBagConstraints.WEST;
	    return g;
	}

}
