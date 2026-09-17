package edu.umg.programacion2.proyecto.ui.decorador;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Estilos {
	public static void BotonLateral(JButton boton) {
		boton.setAlignmentX(Component.CENTER_ALIGNMENT);
		boton.setPreferredSize(new Dimension(180,40));
		boton.setMaximumSize(new Dimension(180,40));
	}
	
	public static void TituloLateral(JLabel label) {
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setPreferredSize(new Dimension(180,40));
		label.setMaximumSize(new Dimension(180,40));
	}
}
