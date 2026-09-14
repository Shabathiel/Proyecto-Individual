-- Creación de la base de datos
CREATE DATABASE IF NOT EXISTS empleados;
USE empleados;

-- Creación de la tabla empleados
-- Para evitar errores de redondeo entre modulos se utiliza el salario como entero guardando el dato en centavos
-- Al momento de interactuar con el se debe tener en cuenta la conversión a unidades
CREATE TABLE IF NOT EXISTS empleados(
	id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    departamento VARCHAR(30) NOT NULL,
    salario INT NOT NULL,
    fecha_ingreso DATE NOT NULL,
    activo BOOL DEFAULT TRUE,
    CONSTRAINT CHK_salario_rule CHECK(salario > 0)
);

INSERT IGNORE INTO empleados (nombre, departamento, salario, fecha_ingreso, activo) VALUES
('Carlos Mendoza', 'Tecnología', 350000, '2023-01-15', TRUE),
('Ana Lucía Gómez', 'Recursos Humanos', 280000, '2022-05-10', TRUE),
('Roberto Estrada', 'Finanzas', 420000, '2021-09-01', TRUE),
('María José Flores', 'Marketing', 290000, '2023-03-20', TRUE),
('Diego Alvarado', 'Tecnología', 380000, '2022-11-12', TRUE),
('Sofía Ramírez', 'Ventas', 250000, '2024-02-01', TRUE),
('Fernando Castillo', 'Operaciones', 310000, '2020-07-18', FALSE),
('Elena Morales', 'Finanzas', 400000, '2019-10-05', TRUE),
('Javier Ortiz', 'Ventas', 260000, '2023-08-14', TRUE),
('Claudia Gutiérrez', 'Recursos Humanos', 275000, '2024-01-10', FALSE);