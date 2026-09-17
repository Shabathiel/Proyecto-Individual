package edu.umg.programacion2.proyecto.ui.panels;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Reportes extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel titulo;
	private JTable tabla;
	private JScrollPane scroll;
	
	public Reportes() {
		setLayout(new BorderLayout());
		
		initComponents();
	}
	
	public void initComponents() {
		titulo = new JLabel("Pestaña de reportes");
		add(titulo,BorderLayout.NORTH);
		
		tabla = new JTable();
		scroll = new JScrollPane(tabla);
		
		add(scroll, BorderLayout.SOUTH);
	}

}
