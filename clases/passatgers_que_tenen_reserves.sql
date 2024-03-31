SELECT DISTINCT Pasajeros.*
FROM Pasajeros
INNER JOIN Plazas ON Pasajeros.numero_pasaporte = Plazas.id_pasajero;
