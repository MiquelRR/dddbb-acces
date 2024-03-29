CREATE DATABASE IF NOT EXISTS Reservas;

USE Reservas;

CREATE TABLE IF NOT EXISTS Vuelos (
    id_vuelo INT AUTO_INCREMENT PRIMARY KEY,
    origen VARCHAR(100),
    destino VARCHAR(100),
    fecha DATE
);

CREATE TABLE IF NOT EXISTS Pasajeros (
    numero_pasaporte VARCHAR(20) PRIMARY KEY,
    nombre_pasajero VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS Aeropuertos (
    Codigo VARCHAR(5) PRIMARY KEY,
    Nombre VARCHAR(30),
    Pais VARCHAR(25),
    Ciudad VARCHAR(25)
);

CREATE TABLE IF NOT EXISTS Plazas (
    Id_plaza INT AUTO_INCREMENT PRIMARY KEY,
    id_asiento VARCHAR(5),
    id_pasajero VARCHAR(20),
    id_vuelo INT,
    ocupado ENUM('si', 'no') DEFAULT 'no',
    FOREIGN KEY (id_vuelo) REFERENCES Vuelos(id_vuelo),
    FOREIGN KEY (id_pasajero) REFERENCES Pasajeros(numero_pasaporte)
);

CREATE TABLE IF NOT EXISTS Tipo_de_Avion (
    Id_tipo INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(100),
    asientos INT
);