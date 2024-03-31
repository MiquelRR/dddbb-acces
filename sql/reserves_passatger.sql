SELECT
    Vuelos.id_vuelo,
    Vuelos.destino, 
    aeropuerto_destino.Pais AS pais_destino, 
    Vuelos.origen, 
    aeropuerto_origen.Pais AS pais_origen, 
    Vuelos.fecha, 
    Plazas.id_asiento
FROM 
    Vuelos 
INNER JOIN 
    Plazas ON Vuelos.id_vuelo = Plazas.id_vuelo
INNER JOIN 
    Aeropuertos AS aeropuerto_destino ON Vuelos.destino = aeropuerto_destino.Codigo
INNER JOIN 
    Aeropuertos AS aeropuerto_origen ON Vuelos.origen = aeropuerto_origen.Codigo
WHERE 
    Plazas.id_pasajero = '123';
