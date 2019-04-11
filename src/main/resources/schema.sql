create TABLE IF NOT EXISTS Organization (
	id			INTEGER PRIMARY KEY AUTO_INCREMENT,
	name		VARCHAR(50) NOT NULL,
	fullName	VARCHAR(100)NOT NULL,
	inn			VARCHAR(10) NOT NULL,
	kpp			VARCHAR(9) NOT NULL,
	address		VARCHAR(200)NOT NULL,
	phone		VARCHAR(20),
	isActive	BOOLEAN NOT NULL,
	version		INTEGER NOT NULL
	);

create TABLE IF NOT EXISTS Office (
	id			INTEGER PRIMARY KEY AUTO_INCREMENT,
	orgId		INTEGER NOT NULL,
	name		VARCHAR(50) NOT NULL,
	address		VARCHAR(200) NOT NULL,
	phone		VARCHAR(20),
	isActive	BOOLEAN NOT NULL,
	version		INTEGER NOT NULL
	);

create TABLE IF NOT EXISTS User (
	id				INTEGER PRIMARY KEY AUTO_INCREMENT,
	officeId		INTEGER NOT NULL,
	firstName		VARCHAR(50) NOT NULL,
	secondName		VARCHAR(50),
	middleName		VARCHAR(50),
	position		VARCHAR(50) NOT NULL,
	phone			VARCHAR(20),
	docCode			VARCHAR(20) NOT NULL,
	docName			VARCHAR(50) NOT NULL,
	docNumber		VARCHAR(50) NOT NULL,
	docDate			VARCHAR(50) NOT NULL,
	citizenshipCode VARCHAR(30) NOT NULL,
	isIdentified	BOOLEAN NOT NULL,
	version			INTEGER NOT NULL
	);

create TABLE IF NOT EXISTS Doc (
	id		INTEGER PRIMARY KEY AUTO_INCREMENT,
	name	VARCHAR(50) NOT NULL,
	code	INTEGER	UNIQUE
	);

create TABLE IF NOT EXISTS Country (
	id		INTEGER PRIMARY KEY AUTO_INCREMENT,
	name	VARCHAR(50) NOT NULL,
	code	INTEGER UNIQUE
	);

create INDEX IX_Office_Organization_ID ON Office(orgId);
alter table Office add FOREIGN KEY (orgId) REFERENCES Organization(id);

create INDEX IX_User_Office_ID ON User(officeId);
alter table User add FOREIGN KEY (officeId) REFERENCES Office(id);

create INDEX IX_User_Doc_ID ON User(docCode);
alter table User add FOREIGN KEY (docCode) REFERENCES Doc(id);

create INDEX IX_User_Country_ID ON User(citizenshipCode);
alter table User add FOREIGN KEY (citizenshipCode) REFERENCES Country(id);
