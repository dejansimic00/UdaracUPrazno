SELECT * FROM RADNO_VRIJEME;
delete from RADNO_VRIJEME where id = 2;

insert into RADNO_VRIJEME (id_osoba, id_posao, pocetak) VALUES ('786453', '1', NOW());

update RADNO_VRIJEME 
set kraj = now()
where id = 7;

alter table RADNO_VRIJEME auto_increment = 6;



SELECT 
    id_osoba,
    DATE(pocetak) AS day,
    SEC_TO_TIME(SUM(TIMESTAMPDIFF(SECOND, pocetak, IFNULL(kraj, NOW())))) AS total_work_time
FROM 
    RADNO_VRIJEME 
GROUP BY 
    id_osoba,
    DATE(pocetak);



SELECT 
    CONCAT(o.ime, ' ', o.prezime) AS worker_name,
    DATE(w.pocetak) AS day,
    SEC_TO_TIME(SUM(TIMESTAMPDIFF(SECOND, w.pocetak, IFNULL(w.kraj, NOW())))) AS total_work_time
FROM 
    RADNO_VRIJEME w
JOIN 
    OSOBA o ON w.id_osoba = o.id_osoba
GROUP BY 
    o.id_osoba,
    DATE(w.pocetak)