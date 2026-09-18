package edu.umg.programacion2.proyecto.ui.panels;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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

	public Gestion() {
		setLayout(new BorderLayout());
		initComponents();
		
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
		
		jcbDepartamento = new JComboBox<>();
		jcbDepartamento.setEditable(true);
		
		bttCreate = new JButton("Crear Empleado");
		bttUpdate = new JButton("Editar Empleado");
		bttRead = new JButton("Buscar");
		bttDelete = new JButton("Eliminar");
		
		// Aqui iran los estilos de todos los elementos pero para que no se haga muy pesada esta clase se usara una clase externa para decorarlos
		
		Estilos.CamposGestion(txtFecha);
		Estilos.CamposGestion(txtNombre);
		Estilos.CamposGestion(txtId);
		Estilos.CamposGestion(txtSalario);
		Estilos.CamposGestion(jcbDepartamento);
		
		
		// El titulo ira fuera de las demas cuadriculas para no estorbar mucho
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(lblTitulo, BorderLayout.NORTH);
		
		//Se crea la cuadriculoa para que se haga más facil el colocar los elementos interactuables
		
		JPanel panelCampos = new JPanel(new GridBagLayout()); 
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;gbc.gridy = 0;gbc.gridwidth = 1;
		panelCampos.add(lblId,gbc);
		
		gbc.gridx = 1;gbc.gridy = 0;gbc.gridwidth = 2;
		panelCampos.add(txtId,gbc);
		
		gbc.gridx = 0;gbc.gridy = 1;gbc.gridwidth = 1;
		panelCampos.add(lblNombre,gbc);
		
		gbc.gridx = 1;gbc.gridy = 1;gbc.gridwidth = 2;
		panelCampos.add(txtNombre,gbc);
		
		gbc.gridx = 0;gbc.gridy = 2;gbc.gridwidth = 1;
		panelCampos.add(lblDepartamento,gbc);
		
		gbc.gridx = 1;gbc.gridy = 2;gbc.gridwidth = 2;
		panelCampos.add(jcbDepartamento,gbc);
		
		gbc.gridx = 0;gbc.gridy = 3;gbc.gridwidth = 1;
		panelCampos.add(lblSalario,gbc);
		
		gbc.gridx = 1;gbc.gridy = 3;gbc.gridwidth = 2;
		panelCampos.add(txtSalario,gbc);
		
		gbc.gridx = 0;gbc.gridy = 4;gbc.gridwidth = 1;
		panelCampos.add(lblFecha,gbc);
		
		gbc.gridx = 1;gbc.gridy = 4;gbc.gridwidth = 2;
		panelCampos.add(txtFecha,gbc);
		
		gbc.gridx = 0;gbc.gridy = 5;gbc.gridwidth = 1;
		panelCampos.add(lblActivo,gbc);
		
		gbc.gridx = 1;gbc.gridy = 5;gbc.gridwidth = 1;
		panelCampos.add(jrbSi,gbc);
		
		gbc.gridx = 2;gbc.gridy = 5;gbc.gridwidth = 1;
		panelCampos.add(jrbNo,gbc);
		
		add(panelCampos,BorderLayout.CENTER);
		
		
		//Panel de botones para ordenar la interfaz
		JPanel panelBotones = new JPanel();

		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
		
		panelBotones.add(Box.createHorizontalStrut(10));
		panelBotones.add(bttCreate);
		panelBotones.add(Box.createHorizontalStrut(10));
		panelBotones.add(bttRead);
		panelBotones.add(Box.createHorizontalStrut(10));
		panelBotones.add(bttUpdate);
		panelBotones.add(Box.createHorizontalStrut(10));
		panelBotones.add(bttDelete);
		panelBotones.add(Box.createHorizontalStrut(10));
		
		panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		
		// Aquí están las funciones para cada boton
		bttCreate.addActionListener(e -> { Guardar();});
		
		add(panelBotones,BorderLayout.SOUTH);
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

	public Empleado obtenerEmpleado() {
		String textoId = txtId.getText();
		int id;
		
		if (textoId.isBlank() && ! textoId.matches("-?\\d+(\\.\\d+)?")) id = 0;
		else id = Integer.parseInt(textoId);
		
		String nombre = txtNombre.getText();
		double salario = Double.parseDouble(txtSalario.getText());
		salario *= 100; //Para que se guarde en centavos
		
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fecha = LocalDate.parse(txtFecha.getText(),formateador);
		String departamento = (String) jcbDepartamento.getSelectedItem();
		boolean activo;
		if (jrbSi.isSelected() ) activo = true;
		else activo = false;
		
		return new Empleado(id, nombre, departamento, (int) salario, fecha,activo);
	}
	
	public static boolean esFechaValida(String fechaTexto) {
        try {
            DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(fechaTexto, formateador);
            return true; // La fecha es válida y coincide con el formato
        } catch (DateTimeParseException e) {
            return false; // La fecha es inválida o no coincide con el formato
        }
    }

}
