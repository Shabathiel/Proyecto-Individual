package edu.umg.programacion2.proyecto.ui.panels;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.umg.programacion2.proyecto.ui.decorador.Estilos;

public class Inicio extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public Inicio() {
        setLayout(new GridBagLayout());
        setBackground(Estilos.FONDO);

        // --- Contenedor vertical ---
        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setOpaque(false);

        // Título grande
        JLabel titulo = new JLabel("Sistema de Empleados");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titulo.setForeground(Estilos.PRIMARIO);
        titulo.setAlignmentX(CENTER_ALIGNMENT);

        // Subtítulo
        JLabel subtitulo = new JLabel("Programación 2 · Proyecto UMG");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitulo.setForeground(Estilos.ACENTO);
        subtitulo.setAlignmentX(CENTER_ALIGNMENT);

        // Línea decorativa
        JLabel linea = new JLabel("────────────────────────");
        linea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        linea.setForeground(Estilos.BORDE);
        linea.setAlignmentX(CENTER_ALIGNMENT);

        // Mensaje de bienvenida
        JLabel mensaje = new JLabel("Selecciona una pestaña para comenzar");
        mensaje.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        mensaje.setForeground(Estilos.TEXTO);
        mensaje.setAlignmentX(CENTER_ALIGNMENT);
        mensaje.setHorizontalAlignment(SwingConstants.CENTER);

        // Ensamblado con espaciado
        contenido.add(titulo);
        contenido.add(Box.createVerticalStrut(8));
        contenido.add(subtitulo);
        contenido.add(Box.createVerticalStrut(20));
        contenido.add(linea);
        contenido.add(Box.createVerticalStrut(20));
        contenido.add(mensaje);

        // Lo centramos en el panel principal
        add(contenido, new GridBagConstraints());
    }

}
