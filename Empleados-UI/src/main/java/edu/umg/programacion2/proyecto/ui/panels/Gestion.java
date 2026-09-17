package edu.umg.programacion2.proyecto.ui.panels;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Gestion extends JPanel{

	private static final long serialVersionUID = 1L;
	
	JLabel lblNombre,lblTitulo,lblId,lblDepartamento, lblSalario,lblFecha, lblActivo;
	JTextField txtNombre,txtId,txtSalario,txtFecha;
	JButton bttCreate, bttRead, bttUpdate, bttDelete;
	JComboBox jcbDepartamento;
	JRadioButton jrbSi, jrbNo;
	ButtonGroup btGrupo;

	public Gestion() {
		setLayout(new BorderLayout());
		initComponents();
		
	}
	
	public void initComponents() {
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
		
		bttCreate = new JButton("Guardar");
		bttUpdate = new JButton("Editar");
		bttRead = new JButton("Buscar");
		bttDelete = new JButton("Eliminar");
		
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(lblTitulo, BorderLayout.NORTH);
		
		
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
		
		add(panelBotones,BorderLayout.SOUTH);
	}
}
