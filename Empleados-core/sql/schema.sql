-- Creación de la base de datos
CREATE DATABASE IF NOT EXISTS empleados;
USE empleados;

-- Creación de la tabla empleados
CREATE TABLE IF NOT EXISTS empleados(
	id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    departamento VARCHAR(30) NOT NULL,
    salario INT,
    fecha DATE NOT NULL,
    activo BOOL DEFAULT TRUE
);