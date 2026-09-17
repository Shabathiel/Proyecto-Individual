package edu.umg.programacion2.proyecto.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.umg.programacion2.proyecto.ui.panels.Gestion;
import edu.umg.programacion2.proyecto.ui.panels.Inicio;
import edu.umg.programacion2.proyecto.ui.panels.Reportes;
import edu.umg.programacion2.proyecto.ui.panels.Sidebar;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	JPanel container;
	CardLayout layout;
	
	public MainFrame() {
		
		setSize(1000,600);
		setTitle("Gestión de Empleados");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
		
		initComponents();
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
	
	public void initComponents() {
		Sidebar sidebar = new Sidebar(this);
		this.add(sidebar, BorderLayout.WEST);
		
		layout = new CardLayout();
		container = new JPanel(layout);
		
		Gestion gestion = new Gestion();
		Reportes reportes = new Reportes();
		Inicio principal = new Inicio();
		
		container.add(principal, "Inicio");
		container.add(reportes, "Reportes");
		container.add(gestion, "Gestion");
		
		layout.show(container,"Inicio");
		
		add(container, BorderLayout.CENTER);
	}
	
	public void showPanel(String name) {
		layout.show(container, name);
	}
	
}
