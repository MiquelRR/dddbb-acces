SELECT Vuelos.*
FROM Vuelos
LEFT JOIN Plazas ON Vuelos.id_vuelo = Plazas.id_vuelo AND Plazas.id_pasajero = 'ABC'
WHERE Plazas.Id_plaza IS NULL;
