SELECT pname as name, email, phone 
FROM person
WHERE pid IN
(
	SELECT pid2 as pid FROM 
	contact INNER JOIN patient
	ON contact.pid1=patient.pid AND email = 'dub.vizer@br.com'
);
