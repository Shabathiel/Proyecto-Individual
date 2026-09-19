package edu.umg.programacion2.proyecto.ui.panels;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import edu.umg.programacion2.proyecto.model.Empleado;

public class TablaEmpleados extends AbstractTableModel{
	private static final long serialVersionUID = 1L;

	private List<Empleado> empleados;
	
	private final String[] columns = 
		{"ID","Nombre","Departamento","Salario","Fecha de Contratación","Telefono", "Activo", "Antiguedad"};
	
	static DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public TablaEmpleados(List<Empleado> n) {
		this.empleados = n;
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		return this.empleados.size();
	}
	
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		 Empleado m = empleados.get(fila);

		    switch (columna) {

		        case 0:
		            return m.getId();

		        case 1:
		            return m.getNombre();

		        case 2:
		            return m.getDepartamento();
		            
		        case 3:
		        	return "Q" + m.getSalario() / 100;
		        	
		        case 4:
		        	return m.getFecha_ingreso().format(formateador);
		        	
		        case 5:
		        	return m.getTelefono();
		        	
		        case 6:
		        	return m.isActivo() ? "Si":"No";

		        case 7:
		        	return Period.between(m.getFecha_ingreso(), LocalDate.now()).getYears() + " años";
		        	
		        default:
		            return null;
		    }
	}
	
	public void actualizarDatos(List<Empleado> empleados) {
	    this.empleados = empleados;
	    fireTableDataChanged();
	}
	
}