package edu.umg.programacion2.proyecto.ui.decorador;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Estilos {
	
	// Fuentes
	public static final Font TITULO     = new Font("SansSerif", Font.BOLD, 24);
	public static final Font SUBTITULO  = new Font("SansSerif", Font.BOLD, 18);
	public static final Font ETIQUETA   = new Font("SansSerif", Font.PLAIN, 17);
	public static final Font CAMPO      = new Font("SansSerif", Font.PLAIN, 15);
	public static final Font BOTON      = new Font("SansSerif", Font.BOLD, 14);


    // Colores
	public static final Color PRIMARIO   = new Color(0x2C3E50);
    public static final Color ACENTO     = new Color(0x3498DB);
    public static final Color PELIGRO    = new Color(0xE74C3C);
    public static final Color FONDO      = new Color(0xF4F6F7);
    public static final Color TEXTO      = new Color(0x2C3E50);
    public static final Color BORDE      = new Color(0xBDC3C7);
    // Espaciados reutilizables
    public static final Insets PADDING_CAMPO = new Insets(6, 8, 6, 8);
    public static final int GAP_FILA = 8;
    public static final int GAP_BOTONES = 15;
    
    
	public static void BotonLateral(JButton b) {
		b.setFont(BOTON);
	    b.setPreferredSize(new Dimension(150, 40)); // ancho y alto
	    b.setMaximumSize(new Dimension(150, 40));
	    b.setBackground(ACENTO);
	    b.setForeground(Color.WHITE);
	    b.setFocusPainted(false);
	    b.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
	    b.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    b.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
	
	public static void TituloLateral(JLabel label) {
		label.setFont(TITULO);
		label.setForeground(TEXTO);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
	}
	
	public static void Titulo(JLabel label) {
		label.setFont(TITULO);
		label.setForeground(PRIMARIO);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0)); // respiro arriba/abajo
	}
	
	public static void CamposGestion(JTextField c) {
	    c.setFont(CAMPO);
	    c.setPreferredSize(new Dimension(300, 34)); // ancho y alto
	    c.setBorder(BorderFactory.createCompoundBorder(
	        BorderFactory.createLineBorder(new Color(0xBDC3C7)),
	        BorderFactory.createEmptyBorder(4, 8, 4, 8) // padding interno
	    ));
	}
	
	public static void CamposGestion(JComboBox<String> campo) {
		 campo.setFont(CAMPO);
		 campo.setPreferredSize(new Dimension(240, 34));
		 campo.setBackground(Color.WHITE);
	}
	
	public static void BotonGestion(JButton b) {
	    b.setFont(BOTON);
	    b.setPreferredSize(new Dimension(150, 40)); // ancho y alto
	    b.setBackground(ACENTO);
	    b.setForeground(Color.WHITE);
	    b.setFocusPainted(false);
	    b.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
	    b.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	public static void radio(JRadioButton r) {
	    r.setFont(ETIQUETA);
	    r.setForeground(TEXTO);
	    r.setOpaque(false); // transparente, para que se vea el fondo del panel
	    r.setPreferredSize(new Dimension(70, 30));
	}
	
	public static void etiqueta(JLabel l) {
        l.setFont(ETIQUETA);
        l.setForeground(TEXTO);
        l.setPreferredSize(new Dimension(200, 30));
    }
	
	public static void tabla(JTable t) {
	    t.setFont(CAMPO);
	    t.setRowHeight(28);                     // ← esto es lo que agranda las filas
	    t.getTableHeader().setFont(SUBTITULO);
	    t.getTableHeader().setPreferredSize(new Dimension(0, 36));
	    t.setGridColor(new Color(0xDDDDDD));
	    t.setShowGrid(true);
	    t.setSelectionBackground(ACENTO);
	    t.setSelectionForeground(Color.WHITE);
	}
	
	public static void botonPeligro(JButton b) {
        BotonGestion(b);
        b.setBackground(PELIGRO);
    }
}
