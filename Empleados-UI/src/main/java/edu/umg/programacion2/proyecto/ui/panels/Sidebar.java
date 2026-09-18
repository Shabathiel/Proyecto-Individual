package edu.umg.programacion2.proyecto.ui.panels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.umg.programacion2.proyecto.ui.MainFrame;
import edu.umg.programacion2.proyecto.ui.decorador.Estilos;

public class Sidebar extends JPanel{

	private static final long serialVersionUID = 1L;
	private MainFrame mainFrame;
	
	public Sidebar(MainFrame window){
		mainFrame = window;
		setBackground(Color.gray);
		setPreferredSize(new Dimension(200, 0));
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		initComponents();
	}
	
	public void initComponents() {
		
		JLabel titulo = new JLabel("Empleados");
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		add(titulo);
		
		Estilos.TituloLateral(titulo);
		
		add(Box.createVerticalStrut(20));
		
		JButton bttPrincipal = new JButton("Principal");
		bttPrincipal.addActionListener(e -> {mainFrame.showPanel("Inicio");});
		Estilos.BotonLateral(bttPrincipal);
		add(bttPrincipal);
		
		add(Box.createVerticalStrut(20));
		
		JButton bttGestion = new JButton("Gestion");
		bttGestion.addActionListener(e -> {mainFrame.showPanel("Gestion");});
		Estilos.BotonLateral(bttGestion);
		add(bttGestion);
		
		add(Box.createVerticalStrut(20));
		
		JButton bttReportes = new JButton("Reportes");
		Estilos.BotonLateral(bttReportes);
		bttReportes.addActionListener(e -> {mainFrame.showPanel("Reportes");});
		add(bttReportes);
		
		add(Box.createVerticalStrut(20));
		
		JButton bttSalir = new JButton("Salir");
		bttSalir.addActionListener(e -> {System.exit(0);});
		Estilos.BotonLateral(bttSalir);
		add(bttSalir);
		
		add(Box.createVerticalStrut(20));
		
	}
}
