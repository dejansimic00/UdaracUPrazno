CREATE SCHEMA evidencija_radnog_vremena default character set utf8 default collate utf8_unicode_ci;
use evidencija_radnog_vremena;

CREATE TABLE OSOBA
(
	id_osoba int(15) PRIMARY KEY,
    ime varchar(64) NOT null,
    prezime varchar(64) NOT null,
    jmbg varchar(13) UNIQUE NOT null,
    slika varchar(256) NOT null,
    sef tinyint(1) not null
);

CREATE TABLE SEF
(
    id_osoba int(15),
    id_sef int(15),
    PRIMARY KEY (id_osoba, id_sef),
    FOREIGN KEY (id_osoba) REFERENCES OSOBA(id_osoba),
    FOREIGN KEY (id_sef) REFERENCES OSOBA(id_osoba)
);

CREATE TABLE SMJENA
(
    id_smjena int(15) PRIMARY KEY,
    naziv varchar(64) not null,
    pocetak time not null,
    kraj time not null
);

CREATE TABLE NALOG
(
    id_osoba int(15) PRIMARY KEY,
    korisnicko_ime varchar(64) UNIQUE NOT null,
    lozinka varchar(64) not null,
    uloga tinyint(1) not null,
    FOREIGN KEY (id_osoba) REFERENCES OSOBA(id_osoba)
);

CREATE TABLE POSAO
(
  	id_posao int(15) PRIMARY KEY,
    naziv varchar(64) UNIQUE NOT null,
    koeficijent decimal(10, 2) not null
);

CREATE TABLE PROSTORIJA
(
    id_prostorija int(15) PRIMARY KEY,
    port varchar(128) not null,
    kamera varchar(128) not null
);

CREATE TABLE OSOBA_PROSTORIJA
(
    id_osoba int(15),
    id_prostorija int(15),
    PRIMARY KEY (id_osoba, id_prostorija),
    FOREIGN KEY (id_osoba) REFERENCES OSOBA(id_osoba),
    FOREIGN KEY (id_prostorija) REFERENCES PROSTORIJA(id_prostorija)
);

CREATE TABLE RADNO_VRIJEME
(
    id int(15) PRIMARY KEY,
    id_osoba int(15) not null,
    id_posao int(15) not null,
    pocetak datetime,
    kraj datetime
)
