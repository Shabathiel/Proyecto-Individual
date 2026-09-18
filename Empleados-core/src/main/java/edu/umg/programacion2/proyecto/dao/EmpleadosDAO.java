package edu.umg.programacion2.proyecto.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import edu.umg.programacion2.proyecto.model.Empleado;

public class EmpleadosDAO {
	private static final Properties CONFIG= cargarConfiguracion();
    
    private static final String URL = CONFIG.getProperty("db.url");
    private static final String USUARIO = CONFIG.getProperty("db.user");
    private static final String PASSWORD = CONFIG.getProperty("db.password");
    
    private static Properties cargarConfiguracion() {
        Properties propiedades = new Properties();

        try (InputStream entrada = EmpleadosDAO.class
                .getClassLoader()
                .getResourceAsStream("db.properties")) {

            if (entrada == null) {
                throw new RuntimeException("No se encontro db.properties");
            }

            propiedades.load(entrada);

        } catch (IOException e) {
            throw new RuntimeException("Error al cargar db.properties", e);
        }

        return propiedades;
    }
    
    public static int crearEmpleado(Empleado empleado) throws SQLException {
        String sql = "INSERT INTO empleados (nombre, departamento, salario, fecha_ingreso) VALUES (?, ?, ?, ?)";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement statement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getDepartamento());
            statement.setInt(3, empleado.getSalario());
            statement.setDate(4, Date.valueOf(empleado.getFecha_ingreso()));
            statement.executeUpdate();

            try (ResultSet claves = statement.getGeneratedKeys()) {
                if (claves.next()) {
                    return claves.getInt(1);
                }
                return -1;
            }
        }
    }

    public static List<Empleado> listarTodos() throws SQLException {
        String sql = "SELECT id, nombre, departamento, salario, fecha_ingreso, activo FROM empleados ORDER BY id";
        List<Empleado> empleados = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
            	empleados.add(mapearFila(resultado));
            }
        }
        return empleados;
    }

    public static Optional<Empleado> buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, nombre, departamento, salario, fecha_ingreso, activo FROM empleados WHERE id = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultado = statement.executeQuery()) {
                if (resultado.next()) {
                    return Optional.of(mapearFila(resultado));
                }
                return Optional.empty();
            }
        }
    }

    public static boolean actualizarEmpleado(Empleado emp) throws SQLException {
        String sql = "UPDATE empleados SET nombre = ?, departamento = ?, salario = ?, activo = ? WHERE id = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, emp.getNombre());
            statement.setString(2, emp.getDepartamento());
            statement.setInt(3, emp.getSalario());
            statement.setBoolean(4, emp.isActivo());
            statement.setInt(5,emp.getId());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        }
    }
    
    public static boolean eliminarEmpleado(int id) throws SQLException {
    	String sql = "DELETE FROM empleados WHERE id = ?";
    	
    	try(Connection conexion = DriverManager.getConnection(URL,USUARIO,PASSWORD);
    		PreparedStatement statement = conexion.prepareStatement(sql)){
    		
    		statement.setInt(1,id);
    		
    		int filasAfectadas = statement.executeUpdate();
    		return filasAfectadas > 0;
    	}
    }

    public static List<String> obtenerDepartamentos() throws SQLException {
    	String sql = "SELECT DISTINCT departamento FROM empleados";	
    	List<String> depas = new ArrayList<>();
    	
    	try(Connection conexion = DriverManager.getConnection(URL,USUARIO,PASSWORD);
    		PreparedStatement statement = conexion.prepareStatement(sql);
    		ResultSet resultado = statement.executeQuery()){
    		
    		
    		while (resultado.next()) {
    			depas.add(resultado.getString("departamento"));
    		}
    		return depas;
    	}
    }
    
    private static Empleado mapearFila(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        String nombre = resultado.getString("nombre");
        String departamento = resultado.getString("departamento");
        int salario  = resultado.getInt("salario");
        LocalDate fecha_ingreso = resultado.getDate("fecha_ingreso").toLocalDate();
        boolean activo = resultado.getBoolean("activo");
        return new Empleado(id, nombre, departamento, salario, fecha_ingreso, activo);
    }
}
