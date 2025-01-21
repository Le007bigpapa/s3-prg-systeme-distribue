--
-- PostgreSQL database dump
--

-- Dumped from database version 13.6 (Debian 13.6-1.pgdg110+1)
-- Dumped by pg_dump version 14.1

-- Started on 2022-05-25 18:13:12 EDT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 16385)
-- Name: app; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA app;


ALTER SCHEMA app OWNER TO postgres;

SET default_tablespace = '';


--
-- TOC entry 201 (class 1259 OID 16431)
-- Name: message; Type: TABLE; Schema: app; Owner: postgres
--


CREATE TABLE app.Usager(
                           cip VARCHAR(8) ,
                           email VARCHAR(50) ,
                           PRIMARY KEY(cip)
);

ALTER TABLE app.Usager OWNER TO postgres;

CREATE TABLE app.projet(
                           Id_projet SERIAL,
                           titre VARCHAR(50)  NOT NULL,
                           description VARCHAR(300) ,
                           date_creation TIMESTAMP NOT NULL,
                           date_modifier TIMESTAMP,
                           cip_createur VARCHAR(8),
                           cip_proprietaire VARCHAR(8)  NOT NULL,
                           cip_derniere_modif VARCHAR(8) ,
                           PRIMARY KEY(Id_projet),
                           FOREIGN KEY(cip_createur) REFERENCES app.Usager(cip) ON DELETE SET NULL ON UPDATE CASCADE,
                           FOREIGN KEY(cip_proprietaire) REFERENCES app.Usager(cip) ON DELETE RESTRICT ON UPDATE CASCADE,
                           FOREIGN KEY(cip_derniere_modif) REFERENCES app.Usager(cip) ON DELETE SET NULL ON UPDATE CASCADE
);

ALTER TABLE app.projet OWNER TO postgres;

CREATE TABLE app.tache(
                          Id_tache SERIAL,
                          titre VARCHAR(50)  NOT NULL,
                          description VARCHAR(300) ,
                          date_creation TIMESTAMP NOT NULL,
                          date_modifier TIMESTAMP,
                          Id_projet INTEGER NOT NULL,
                          cip_createur VARCHAR(8)  NOT NULL,
                          Id_tache_parent INTEGER,
                          PRIMARY KEY(Id_tache),
                          FOREIGN KEY(Id_projet) REFERENCES app.projet(Id_projet)ON DELETE CASCADE ON UPDATE CASCADE,
                          FOREIGN KEY(cip_createur) REFERENCES app.Usager(cip)ON DELETE CASCADE ON UPDATE CASCADE,
                          FOREIGN KEY(Id_tache_parent) REFERENCES app.tache(Id_tache) ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER TABLE app.tache OWNER TO postgres;

CREATE TABLE app.session(
                            Id_session SERIAL,
                            commencer_a TIMESTAMP NOT NULL,
                            fini_a TIMESTAMP,
                            Id_tache INTEGER NOT NULL,
                            cip_Usager VARCHAR(8)  NOT NULL,
                            PRIMARY KEY(Id_session),
                            FOREIGN KEY(cip_Usager) REFERENCES app.Usager(cip) ON DELETE CASCADE ON UPDATE CASCADE,
                            FOREIGN KEY(Id_tache) REFERENCES app.tache(Id_tache) ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER TABLE app.session OWNER TO postgres;

CREATE TABLE app.Usager_tache(
                                 cip_Usager VARCHAR(8) ,
                                 Id_tache INTEGER,
                                 admin BOOLEAN,
                                 PRIMARY KEY(Id_tache, cip_Usager),
                                 FOREIGN KEY(cip_Usager) REFERENCES app.Usager(cip) ON DELETE CASCADE ON UPDATE CASCADE,
                                 FOREIGN KEY(Id_tache) REFERENCES app.tache(Id_tache) ON DELETE CASCADE ON UPDATE CASCADE
);
ALTER TABLE app.Usager_tache OWNER TO postgres;

CREATE TABLE app.Usager_projet(
                                 cip_Usager VARCHAR(8) ,
                                 Id_projet INTEGER,
                                 admin BOOLEAN,
                                 PRIMARY KEY(Id_projet,cip_Usager),
                                 FOREIGN KEY(cip_Usager) REFERENCES app.Usager(cip) ON DELETE CASCADE ON UPDATE CASCADE,
                                 FOREIGN KEY(Id_projet) REFERENCES app.projet(Id_projet) ON DELETE CASCADE ON UPDATE CASCADE
);
ALTER TABLE app.Usager_projet OWNER TO postgres;


CREATE OR REPLACE FUNCTION app.insert_into_usager_projet()
    RETURNS TRIGGER AS $$
BEGIN
    -- Insert into Usager_projet with cip_createur as cip_Usager
INSERT INTO app.Usager_projet (cip_Usager, Id_projet, admin)
VALUES (NEW.cip_createur, NEW.Id_projet, TRUE);
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_insert_into_usager_projet
AFTER INSERT ON app.projet
FOR EACH ROW
EXECUTE FUNCTION app.insert_into_usager_projet();


-- Insérer des données dans la table Usager
INSERT INTO app.Usager (cip, email) VALUES
                                        ('merm1504', 'merm1504@usherbrooke.ca'),
                                        ('lamv1904', 'lamv1904@usherbrooke.ca'),
                                        ('fora1819', 'fora1819@usherbrooke.ca'),
                                        ('pelc1105', 'pelc1105@usherbrooke.ca'),
                                        ('rodv1001', 'rodv1001@usherbrooke.ca');

-- Insérer des données dans la table Projet
INSERT INTO app.projet (titre, description, date_creation, date_modifier, cip_createur, cip_proprietaire, cip_derniere_modif) VALUES
                                                                                                                                  ('Projet Alpha', 'Description du projet Alpha', '2024-05-20 10:00:00', '2024-05-21 12:00:00', 'merm1504', 'merm1504', 'lamv1904'),
                                                                                                                                  ('Projet Beta', 'Description du projet Beta', '2024-05-18 09:00:00', '2024-05-22 15:00:00', 'lamv1904', 'fora1819', 'pelc1105'),
                                                                                                                                  ('Projet Gamma', 'Description du projet Gamma', '2024-05-19 11:00:00', '2024-05-23 14:00:00', 'fora1819', 'pelc1105', 'rodv1001');

-- Insérer des données dans la table Tache
INSERT INTO app.tache (titre, description, date_creation, date_modifier, Id_projet, cip_createur, Id_tache_parent) VALUES
                                                                                                                       ('Tache 1', 'Description de la tache 1', '2024-05-20 10:30:00', '2024-05-21 12:30:00', 1, 'merm1504', NULL),
                                                                                                                       ('Tache 2', 'Description de la tache 2', '2024-05-18 09:30:00', '2024-05-22 15:30:00', 2, 'lamv1904', NULL),
                                                                                                                       ('Tache 3', 'Description de la tache 3', '2024-05-19 11:30:00', '2024-05-23 14:30:00', 3, 'fora1819', NULL),
                                                                                                                       ('Sous-tache 1.1', 'Description de la sous-tache 1.1', '2024-05-20 11:00:00', '2024-05-21 13:00:00', 1, 'pelc1105', 1),
                                                                                                                       ('Sous-tache 2.1', 'Description de la sous-tache 2.1', '2024-05-18 10:00:00', '2024-05-22 16:00:00', 2, 'rodv1001', 2);

-- Insérer des données dans la table Session
INSERT INTO app.session (commencer_a, fini_a, Id_tache, cip_usager) VALUES
                                                                        ('2024-05-21 14:00:00', '2024-05-21 17:00:00', 1, 'merm1504'),
                                                                        ('2024-05-22 10:00:00', '2024-05-22 14:00:00', 2, 'lamv1904'),
                                                                        ('2024-05-23 13:00:00', '2024-05-23 16:00:00', 3, 'fora1819'),
                                                                        ('2024-05-21 15:00:00', '2024-05-21 18:00:00', 4, 'pelc1105'),
                                                                        ('2024-05-22 11:00:00', '2024-05-22 13:00:00', 5, 'rodv1001');

-- Insérer des données dans la table Usager_Tache
INSERT INTO app.usager_tache (cip_usager, Id_tache, admin) VALUES
                                                               ('merm1504', 1, TRUE),
                                                               ('lamv1904', 2, TRUE),
                                                               ('fora1819', 3, TRUE),
                                                               ('pelc1105', 4, TRUE),
                                                               ('rodv1001', 5, TRUE);
--
-- PostgreSQL database dump complete
--