package edu.umg.programacion2.proyecto.ui.panels;

import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import edu.umg.programacion2.proyecto.dao.EmpleadosDAO;

public class Reportes extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel titulo;
	private JTable tabla;
	private JScrollPane scroll;
	public TablaEmpleados modelo;
	
	public Reportes() {
		setLayout(new BorderLayout());
		
		initComponents();
	}
	
	public void initComponents() {
		titulo = new JLabel("Pestaña de reportes");
		add(titulo,BorderLayout.NORTH);
		
		try {
			modelo = new TablaEmpleados(EmpleadosDAO.listarTodos());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "No se pudieron cargar los empleados.\n" + e,"Advertencia",JOptionPane.WARNING_MESSAGE);
		}
		tabla = new JTable(modelo);
		scroll = new JScrollPane(tabla);
		
		add(scroll, BorderLayout.SOUTH);
	}

}
