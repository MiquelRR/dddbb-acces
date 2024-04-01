UPDATE Plazas
SET id_pasajero = null, ocupado='no'
WHERE Id_plaza = %s; 
UPDATE Plazas
SET id_pasajero = %s, ocupado='si'
WHERE Id_vuelo = %s AND  id_asiento = %s; 