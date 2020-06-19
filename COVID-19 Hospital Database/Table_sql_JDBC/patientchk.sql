
/* select all the visit record where text contains breathing
Find out the pid of this speciofic patient */
SELECT text, name, vday as date, hour
FROM visit LEFT OUTER JOIN professional
ON visit.hid = professional.hid AND text LIKE '%breathing%' 
WHERE pid IN
(
	SELECT pid 
	FROM patient
	WHERE pname='Samantha Adam'

)
ORDER BY date DESC, hour DESC;


