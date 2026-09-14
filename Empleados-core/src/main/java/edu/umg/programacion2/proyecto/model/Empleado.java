package edu.umg.programacion2.proyecto.model;

import java.time.LocalDate;

public class Empleado {
	private int id;
	private String nombre;
	private String departamento;
	private int salario; // El salario se encuentra en centavos con conversión en cada metodo
	private LocalDate fecha_ingreso;
	private boolean activo;
	
	
	
	public Empleado(int id, String nombre, String departamento, int salario, LocalDate fecha_ingreso, boolean activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.departamento = departamento;
		this.salario = salario;
		this.fecha_ingreso = fecha_ingreso;
		this.activo = activo;
	}
	
	

	public Empleado(String nombre, String departamento, int salario, LocalDate fecha_ingreso) {
		this.id = 0;
		this.nombre = nombre;
		this.departamento = departamento;
		this.salario = salario;
		this.fecha_ingreso = fecha_ingreso;
		this.activo = true;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getDepartamento() {
		return departamento;
	}



	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}



	public int getSalario() {
		return salario;
	}



	public void setSalario(int salario) {
		this.salario = salario;
	}



	public LocalDate getFecha_ingreso() {
		return fecha_ingreso;
	}



	public void setFecha_ingreso(LocalDate fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}



	public boolean isActivo() {
		return activo;
	}



	public void setActivo(boolean activo) {
		this.activo = activo;
	}



	@Override
	public String toString() {
		return "|" + id + "| " + nombre + " - " + departamento 
				+ " - Q" + salario/100 + " - " + fecha_ingreso 
				+ " - " + ((activo) ? "Activo":"Inactivo");
	}
	
	
}
