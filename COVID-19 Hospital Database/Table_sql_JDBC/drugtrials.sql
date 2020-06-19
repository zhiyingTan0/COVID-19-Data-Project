/*drugtrials*/
/*every administration.pid will find a corresponding patient.pid and corresponding status
same pid with same status
then choose all the corresponging stautus's tuples (same pid may appear more than once)
then for each group name. the dinstinct pid will appear only once*/

/*some dname in t1 but not in t2*/

(select t1.dname as drugname, COALESCE(t2.counting ,0) recovered , t1.counting as notrecovered from 
(
	select count(distinct administration.pid) as counting, dname  from 
	administration inner join patient
	on patient.pid=administration.pid 
	where patient.status ='infected' or patient.status ='deceased'
	group by dname
	
)t1 LEFT OUTER JOIN
(
	select count(distinct administration.pid) as counting, dname  from 
	administration inner join patient
	on patient.pid=administration.pid 
	where patient.status='recovered'
	group by dname

)t2 ON t1.dname=t2.dname)
UNION
(select t2.dname as drugname, t2.counting as recovered ,COALESCE(t1.counting ,0) notrecovered  from 
(
	select count(distinct administration.pid) as counting, dname  from 
	administration inner join patient
	on patient.pid=administration.pid 
	where patient.status ='infected' or patient.status ='deceased'
	group by dname
	
)t1 RIGHT OUTER JOIN
(
	select count(distinct administration.pid) as counting, dname  from 
	administration inner join patient
	on patient.pid=administration.pid 
	where patient.status='recovered'
	group by dname

)t2 ON t1.dname=t2.dname);

