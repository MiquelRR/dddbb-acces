USE Reservas;
SELECT * FROM Tipo_de_Avion 
WHERE asientos >= 10 
ORDER BY asientos LIMIT 1;
