package edu.umg.programacion2.proyecto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import edu.umg.programacion2.proyecto.dao.EmpleadosDAO;
import edu.umg.programacion2.proyecto.model.Empleado;

public class Main {
	private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    agregarEmpleado();
                    break;
                case 2:
                    listarEmpleados();
                    break;
                case 3:
                    buscarEmpleado();
                    break;
                case 4:
                    actualizarEmpleado();
                    break;
                case 5:
                    eliminarEmpleado();
                    break;
                case 6:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opcion invalida. Intenta de nuevo.");
            }
            System.out.println();
        } while (opcion != 6);

        teclado.close();
    }

    private static void mostrarMenu() {
        System.out.println("=== CRUD de Empleados (MySQL) ===");
        System.out.println("1. Agregar Empleado");
        System.out.println("2. Listar todos los empleados");
        System.out.println("3. Buscar empleados por ID");
        System.out.println("4. Actualizar un empleado");
        System.out.println("5. Eliminar empleado");
        System.out.println("6. Salir");
        System.out.print("Elige una opcion: ");
    }

    private static int leerOpcion() {
        while (!teclado.hasNextInt()) {
            System.out.print("Escribe un numero valido: ");
            teclado.next();
        }
        int opcion = teclado.nextInt();
        teclado.nextLine();
        return opcion;
    }
    
    private static double leerDouble() {
        while (!teclado.hasNextDouble()) {
            System.out.print("Escribe un numero valido: Q");
            teclado.next();
        }
        double valor = teclado.nextDouble();
        teclado.nextLine();
        return valor;
    }

    private static void agregarEmpleado() {
        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();
        System.out.print("Departamento: ");
        String departamento = teclado.nextLine();
        System.out.print("Salario: Q");
        int salario = (int) (leerDouble() * 100);
        
        //Comprobaciones en caso de que este vacio o que salario sea negativo
        if (nombre.isEmpty() || departamento.isEmpty()) {
        	System.out.println("Los campos tienen que estar completos");
        	return;
        };
        
        if (salario < 1) {
        	System.out.println("El salario tiene que ser positivo");
        	return;
        }
        
        try {
            int id = EmpleadosDAO.crearEmpleado(new Empleado(nombre, departamento, salario,LocalDate.now()));
            System.out.println("Empleado creado con id " + id);
        } catch (SQLException e) {
            System.err.println("Error al crear el Empleado: " + e.getMessage());
        }
    }

    private static void listarEmpleados() {
        try {
            List<Empleado> Empleados = EmpleadosDAO.listarTodos();
            if (Empleados.isEmpty()) {
                System.out.println("No hay Empleados registrados todavia.");
                return;
            }
            for (Empleado Empleado : Empleados) {
                System.out.println(Empleado);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los Empleados: " + e.getMessage());
        }
    }

    private static void buscarEmpleado() {
        System.out.print("ID a buscar: ");
        int id = leerOpcion();

        try {
            Optional<Empleado> Empleado = EmpleadosDAO.buscarPorId(id);
            if (Empleado.isPresent()) {
                System.out.println("Encontrado: " + Empleado.get());
            } else {
                System.out.println("No existe ningun Empleado con ese carnet.");
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar el Empleado: " + e.getMessage());
        }
    }

    private static void actualizarEmpleado() {
    	System.out.print("Id del empleado a actualizar: ");
    	int id = leerOpcion();
    	Empleado emp = null;

    	try {
    		Optional<Empleado> empleadoActual = EmpleadosDAO.buscarPorId(id);
    		if (empleadoActual.isPresent()) {
    			emp = empleadoActual.get();
    		} else {
    			System.out.println("No existe ningun Empleado con ese id.");
    			return;
    		}
    	} catch (SQLException e) {
            System.err.println("Error al buscar el Empleado: " + e.getMessage());
        }

		System.out.println("Nombre del empleado actual: " + emp.getNombre());
		System.out.print("Nuevo nombre a ingresar (en blanco si no dese replazar): ");
		String nombre = teclado.nextLine();
		
		System.out.println("Departamento actual: " + emp.getDepartamento());
		System.out.print("Nuevo departamento a ingresar (en blanco si no dese replazar): ");
		String departamento = teclado.nextLine();
		
		System.out.println("Salario actual: " + emp.getSalario()/100);
		System.out.print("Nuevo Salario (en blanco si no desea cambiar): Q" );
		int salario = (int) (leerDouble() * 100);
		
		System.out.println("El empleado se encuentra " + ((emp.isActivo()) ? "Activo" : "inactivo"));
		System.out.print("¿El empleado se encuentra activo ahora? (Y si/* no): ");
		boolean activo = (teclado.nextLine().toUpperCase().equals("Y")) ? true:false;
		
		// Validaciones
		emp.setId(id);
		if (!nombre.isBlank()) emp.setNombre(nombre);
		if (!departamento.isBlank()) emp.setNombre(nombre);
		if (salario > 0) emp.setSalario(salario);
		emp.setActivo(activo);

		try {
			boolean actualizado = EmpleadosDAO.actualizarEmpleado(emp);
			if (actualizado) {
				System.out.println("Empleado actualizado.");
			} else {
				System.out.println("No existe ningun Empleado con ese id.");
			}
		} catch (SQLException e) {
			System.err.println("Error al actualizar el Empleado: " + e.getMessage());
		}

    }

    private static void eliminarEmpleado() {
        System.out.print("Carnet del Empleado a eliminar: ");
        int id = leerOpcion();

        try {
            boolean eliminado = EmpleadosDAO.eliminarEmpleado(id);
            if (eliminado) {
                System.out.println("Empleado eliminado.");
            } else {
                System.out.println("No existe ningun Empleado con ese id.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el Empleado: " + e.getMessage());
        }
    }
}
