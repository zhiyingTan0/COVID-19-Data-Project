/*hotspot*/
/*select all the not null borough field of individual
then inner join wieh patient, each patient pid has a corresponding individual pid
if the patient has borough */

/* goup by bname and find distinct pid(primnary key) , where choose count(# patient) > 0 
then descending order of counting, then ascending order of alphabetical*/


/* Here my understanding is that infected include recovered., infected and deceased*/
SELECT bname as blockname, COUNT(patient.pid) as counting FROM
patient INNER JOIN individual
ON patient.pid=individual.pid AND individual.bname <> NULL
GROUP BY bname
HAVING COUNT(patient.pid)>0
ORDER BY counting DESC, blockname


