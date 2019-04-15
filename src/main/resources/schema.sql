CREATE TABLE IF NOT EXISTS Organization (
    id          INTEGER PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL,
    full_name   VARCHAR(100)NOT NULL,
    inn         VARCHAR(10) NOT NULL,
    kpp         VARCHAR(9) NOT NULL,
    address     VARCHAR(200)NOT NULL,
    phone       VARCHAR(20),
    is_active   BOOLEAN NOT NULL,
    version     INTEGER NOT NULL
    );

CREATE TABLE IF NOT EXISTS Office (
    id          INTEGER PRIMARY KEY AUTO_INCREMENT,
    org_id      INTEGER NOT NULL,
    name        VARCHAR(50) NOT NULL,
    address     VARCHAR(200) NOT NULL,
    phone       VARCHAR(20),
    is_active   BOOLEAN NOT NULL,
    version     INTEGER NOT NULL
    );

CREATE TABLE IF NOT EXISTS User (
    id                  INTEGER PRIMARY KEY AUTO_INCREMENT,
    office_id           INTEGER NOT NULL,
    first_name          VARCHAR(50) NOT NULL,
    second_name         VARCHAR(50),
    middle_name         VARCHAR(50),
    position            VARCHAR(50) NOT NULL,
    phone               VARCHAR(20),
    doc_code            VARCHAR(20) NOT NULL,
    doc_name            VARCHAR(50) NOT NULL,
    doc_number          VARCHAR(50) NOT NULL,
    doc_date            VARCHAR(50) NOT NULL,
    citizenship_code    VARCHAR(30) NOT NULL,
    is_identified       BOOLEAN NOT NULL,
    version             INTEGER NOT NULL
    );

CREATE TABLE IF NOT EXISTS Doc (
    id      INTEGER PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(50) NOT NULL,
    code    INTEGER	UNIQUE
    );

CREATE TABLE IF NOT EXISTS Country (
    id      INTEGER PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(50) NOT NULL,
    code    INTEGER UNIQUE
    );

CREATE INDEX IX_Office_Organization_Id ON Office(org_id);
ALTER TABLE Office ADD FOREIGN KEY (org_id) REFERENCES Organization(id);

CREATE INDEX IX_User_Office_Id ON User(office_id);
ALTER TABLE User ADD FOREIGN KEY (office_id) REFERENCES Office(id);

CREATE INDEX IX_User_Doc_Id ON User(doc_code);
ALTER TABLE User ADD FOREIGN KEY (doc_code) REFERENCES Doc(id);

CREATE INDEX IX_User_Country_Id ON User(citizenship_code);
ALTER TABLE User ADD FOREIGN KEY (citizenship_code) REFERENCES Country(id);
