CREATE TABLE MAISON
(
    ID      INTEGER PRIMARY KEY,
    NOM     VARCHAR(50),
    SURFACE NUMERIC(15, 2),
    ETAGE   INTEGER,
    LAT     NUMERIC(15, 10),
    LONG    NUMERIC(15, 10)
);

CREATE TABLE COMPOSITION
(
    ID  INTEGER PRIMARY KEY,
    NOM VARCHAR(50)
);

CREATE TABLE STYLE
(
    ID            INTEGER PRIMARY KEY,
    NOM           VARCHAR(50),
    IDCOMPOSITION INT REFERENCES COMPOSITION (ID),
    COEF          NUMERIC(6, 2)
);

CREATE TABLE ARRONDISSEMENT
(
    ID  INTEGER PRIMARY KEY,
    NOM VARCHAR(50)
);

CREATE TABLE ARRONDISSEMENTFILLE
(
    ID               INTEGER PRIMARY KEY,
    IDARRONDISSEMENT INT REFERENCES ARRONDISSEMENT (ID),
    LAT              NUMERIC(15, 10),
    LONG             NUMERIC(15, 10)
);

CREATE TABLE PAYEMENTLOYER
(
    ID       INTEGER PRIMARY KEY,
    IDMAISON INTEGER REFERENCES MAISON (ID),
    MOIS     INTEGER,
    ANNEE    INTEGER
);

CREATE TABLE CONFIGURATION
(
    CLE    VARCHAR(50) PRIMARY KEY,
    VALEUR VARCHAR(255)
);

CREATE SEQUENCE maison_seq
start with 1
increment by 1
nocache ;

CREATE OR REPLACE FUNCTION get_maison_seq
    RETURN VARCHAR2
    IS
    current_val NUMBER;
BEGIN
    SELECT maison_seq.CURRVAL INTO current_val FROM dual;
    RETURN 'PRD00' || TO_CHAR(current_val);
EXCEPTION
    WHEN OTHERS THEN
        RETURN 'Error: Sequence value not initialized. Call MAISON_SEQ.NEXTVAL first.';
END;


CREATE SEQUENCE arrondissement_seq
    start with 1
    increment by 1
    nocache ;

CREATE OR REPLACE FUNCTION get_arrondissement_seq
    RETURN VARCHAR2
    IS
    current_val NUMBER;
BEGIN
    SELECT arrondissement_seq.CURRVAL INTO current_val FROM dual;
    RETURN 'ARD00' || TO_CHAR(current_val);
EXCEPTION
    WHEN OTHERS THEN
        RETURN 'Error: Sequence value not initialized. Call ARRONDISSEMENT_SEQ.NEXTVAL first.';
END;


CREATE SEQUENCE arrondissementfille_seq
    start with 1
    increment by 1
    nocache ;

CREATE OR REPLACE FUNCTION get_arrondissementfille_seq
    RETURN VARCHAR2
    IS
    current_val NUMBER;
BEGIN
    SELECT arrondissementfille_seq.CURRVAL INTO current_val FROM dual;
    RETURN 'ARDF00' || TO_CHAR(current_val);
EXCEPTION
    WHEN OTHERS THEN
        RETURN 'Error: Sequence value not initialized. Call ARRONDISSEMENTFILLE_SEQ.NEXTVAL first.';
END;

CREATE SEQUENCE payementloyer_seq
    start with 1
    increment by 1
    nocache ;

CREATE OR REPLACE FUNCTION get_payement_seq
    RETURN VARCHAR2
    IS
    current_val NUMBER;
BEGIN
    SELECT payementloyer_seq.CURRVAL INTO current_val FROM dual;
    RETURN 'PAYL00' || TO_CHAR(current_val);
EXCEPTION
    WHEN OTHERS THEN
        RETURN 'Error: Sequence value not initialized. Call ARRONDISSEMENT_SEQ.NEXTVAL first.';
END;