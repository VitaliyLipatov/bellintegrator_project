create TABLE IF NOT EXISTS Organization (
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

create TABLE IF NOT EXISTS Office (
    id          INTEGER PRIMARY KEY AUTO_INCREMENT,
    org_id      INTEGER NOT NULL,
    name        VARCHAR(50) NOT NULL,
    address     VARCHAR(200) NOT NULL,
    phone       VARCHAR(20),
    is_active   BOOLEAN NOT NULL,
    version     INTEGER NOT NULL
    );

create TABLE IF NOT EXISTS User (
    id                  INTEGER PRIMARY KEY AUTO_INCREMENT,
    office_id           INTEGER NOT NULL,
    doc_id              INTEGER NOT NULL,
    country_id          INTEGER NOT NULL,
    first_name          VARCHAR(50) NOT NULL,
    second_name         VARCHAR(50),
    middle_name         VARCHAR(50),
    position            VARCHAR(50) NOT NULL,
    phone               VARCHAR(20),
    is_identified       BOOLEAN NOT NULL,
    version             INTEGER NOT NULL
    );

create TABLE IF NOT EXISTS Doc (
    id              INTEGER PRIMARY KEY AUTO_INCREMENT,
    type_id         INTEGER NOT NULL,
    number          VARCHAR(50) NOT NULL,
    date            DATE NOT NULL
    );

create TABLE IF NOT EXISTS Doc_Type(
    id      INTEGER PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(50) NOT NULL,
    code    VARCHAR(20) NOT NULL
    );

create TABLE IF NOT EXISTS Country (
    id      INTEGER PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(50) NOT NULL,
    code    VARCHAR(20) NOT NULL
    );

create INDEX IX_Office_Organization_Id ON Office(org_id);
alter table Office add FOREIGN KEY (org_id) REFERENCES Organization(id);

create INDEX IX_User_Office_Id ON User(office_id);
alter table User add FOREIGN KEY (office_id) REFERENCES Office(id);

create INDEX IX_User_Doc_Id ON User(doc_id);
alter table User add FOREIGN KEY (doc_id) REFERENCES Doc(id);

create INDEX IX_User_Country_Id ON User(country_id);
alter table User add FOREIGN KEY (country_id) REFERENCES Country(id);

create INDEX IX_Doc_Doc_Type_Id ON Doc(type_id);
alter table Doc add FOREIGN KEY (type_id) REFERENCES Doc_Type(id);
