# Sistema de Gestión de Empleados

Una aplicación de escritorio desarrollada en **Java** diseñada para la gestión eficiente y centralizada de información de empleados. El sistema cuenta con una interfaz gráfica moderna implementada con **Java Swing** y un almacenamiento persistente mediante **MySQL**.

---

## 📋 Tabla de Contenidos

1. [Características Principales](#-características-principales)
2. [Tecnologías Utilizadas](#-tecnologías-utilizadas)
3. [Requisitos del Sistema](#-requisitos-del-sistema)
4. [Configuración de la Base de Datos](#-configuración-de-la-base-de-datos)
5. [Instalación y Ejecución](#-instalación-y-ejecución)
   - [Ejecución desde Eclipse IDE](#1-ejecución-desde-eclipse-ide)
   - [Ejecución mediante Consola / Terminal](#2-ejecución-mediante-consola--terminal)
6. [Estructura del Proyecto](#-estructura-del-proyecto)
7. [Licencia](#-licencia)

---

## ✨ Características Principales

* **Gestión de Empleados:** Alta, baja, modificación y consulta de registros de empleados.
* **Interfaz de Usuario Intuitiva:** Desarrollada con componentes Swing para facilitar la navegación y operación del usuario.
* **Persistencia de Datos:** Conexión robusta a base de datos relacional MySQL.
* **Arquitectura Modular:** Separación clara entre la lógica de negocio (Core) y la interfaz gráfica (UI).

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje de Programación:** Java (JDK 8 o superior)
* **Interfaz Gráfica (GUI):** Java Swing
* **Base de Datos:** MySQL
* **Gestión de Dependencias:** Apache Maven
* **Entorno de Desarrollo:** Eclipse IDE

---

## 💻 Requisitos del Sistema

Antes de comenzar, asegúrate de tener instalado:

* **Java Development Kit (JDK):** Versión 8, 11 o superior.
* **Servidor MySQL:** MySQL Server 5.7 o superior (o MariaDB).
* **Apache Maven:** Integrado en Eclipse o instalado de forma independiente para consola.
* **IDE Recomendado:** Eclipse IDE for Java Developers.

---

## 🗄️ Configuración de la Base de Datos

1. Inicia tu servidor de MySQL.
2. Crea la base de datos necesaria para el sistema (ajusta el nombre según tu script):
   ```sql
   CREATE DATABASE gestion_empleados;
   ```
3. Ejecuta los scripts SQL para la creación de tablas e inserción de datos iniciales.
4. Configura las credenciales de conexión (`URL`, `Usuario`, `Contraseña`) en el archivo de propiedades o clase de conexión dentro del proyecto (`Empleados-Core`).

---

## 🚀 Instalación y Ejecución

### 1. Ejecución desde Eclipse IDE

1. **Clonar / Importar:**
   * Abre Eclipse IDE.
   * Dirígete a `File` > `Import...` > `Maven` > `Existing Maven Projects`.
   * Selecciona el directorio raíz donde se encuentran los módulos del proyecto y finaliza la importación.

2. **Actualizar Dependencias:**
   * Haz clic derecho sobre el proyecto en el *Package Explorer*.
   * Selecciona `Maven` > `Update Project...` (o presiona `Alt + F5`).

3. **Iniciar la Aplicación:**
   * Localiza la clase principal `MainUI` dentro del módulo de interfaz (`Empleados-UI`).
   * Haz clic derecho sobre `MainUI.java`.
   * Selecciona `Run As` > `Java Application`.

---

### 2. Ejecución mediante Consola / Terminal

Si deseas compilar y ejecutar el proyecto directamente desde la línea de comandos:

1. **Compilar e instalar las dependencias con Maven:**
   Navega al directorio principal de la solución e instala los módulos ejecutando:
   ```bash
   mvn clean install
   ```

2. **Ejecutar la interfaz gráfica:**
   Dirígete a la carpeta del módulo de UI (`Empleados-UI`) y ejecuta el comando de Maven para iniciar la clase principal:
   ```bash
   cd Empleados-UI
   mvn exec:java -Dexec.mainClass="MainUI"
   ```
   *(Nota: Reemplaza `"MainUI"` por el paquete completo si la clase se encuentra dentro de uno, por ejemplo: `com.empleados.ui.MainUI`)*.

---

## 📁 Estructura del Proyecto

El proyecto está organizado en una arquitectura por capas que separa responsabilidades para un código más limpio y mantenible:

```text
Gestion-Empleados/
│
├── Empleados-Core/        # Capa de Lógica de Negocio y Datos
│   ├── src/main/java/     # Modelos, DAOs, Servicios y Conexión DB
│   └── pom.xml            # Gestión de dependencias Core
│
└── Empleados-UI/          # Capa de Interfaz Gráfica de Usuario
    ├── src/main/java/     # Formularios Swing, Vistas y Controlador MainUI
    └── pom.xml            # Dependencias de la Interfaz
```

---

## 📄 Licencia

Este proyecto se distribuye bajo la licencia MIT.
