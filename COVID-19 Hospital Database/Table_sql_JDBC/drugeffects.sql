/*For all the pid with dname=tocili have more than one records of administration
find the smallest date among them.
Then find all the visit record after first date of those people
Try to find out the keyword dizz
*/
select count(distinct t1.pid) as countDizz
from visit inner join
(
	select pid, min(aday)as first 
	from administration 
	where dname='tocilizumab'
	group by pid
)t1
on visit.pid=t1.pid 
where vday>first and text like '%dizz%';


