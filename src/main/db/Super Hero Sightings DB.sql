-- reconstruct the database
DROP DATABASE IF EXISTS SuperheroSightingsDB;
CREATE DATABASE SuperheroSightingsDB;

USE SuperheroSightingsDB;

CREATE TABLE location(
	locationId INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL DEFAULT("no name"),
    description VARCHAR(100) NOT NULL DEFAULT("no description"),
    address VARCHAR(100) DEFAULT("no address"),
    latitude DECIMAL(7,4) NOT NULL, -- 4 decimal places, 3 integer spaces
    longitude DECIMAL(7,4) NOT NULL,
    PRIMARY KEY (locationId)
);

CREATE TABLE organization(
	organizationId INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL DEFAULT("no name"),
    description VARCHAR(100) NOT NULL DEFAULT("no description"),
    locationId INT UNSIGNED NOT NULL,
    PRIMARY KEY (organizationId),
    CONSTRAINT FOREIGN KEY fk_organization_location(locationId)
		REFERENCES location(locationId)
);

CREATE TABLE superPower(
	superPowerId INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    PRIMARY KEY (superPowerId)
);

CREATE TABLE hero(
	heroId INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    description VARCHAR(100) NOT NULL DEFAULT("no description"),
    superPowerId INT UNSIGNED NOT NULL,
    PRIMARY KEY (heroId),
    CONSTRAINT FOREIGN KEY fk_hero_superPower(superPowerId)
		REFERENCES superPower(superPowerId)
);

CREATE TABLE heroAffiliatedWithOrganization(
	affiliationId INT UNSIGNED NOT NULL AUTO_INCREMENT,
	heroId INT UNSIGNED NOT NULL,
    organizationId INT UNSIGNED NOT NULL,
    PRIMARY KEY(affiliationId),
    CONSTRAINT FOREIGN KEY fk_heroAffiliatedWithOrganization_hero(heroId)
		REFERENCES hero(heroId),
    CONSTRAINT FOREIGN KEY fk_heroAffiliatedWithOrganization_organization(organizationId)
		REFERENCES organization(organizationId)
);

CREATE TABLE heroSightedAt(
	sightingId INT UNSIGNED NOT NULL AUTO_INCREMENT,
	heroId INT UNSIGNED NOT NULL,
    locationId INT UNSIGNED NOT NULL,
    PRIMARY KEY (sightingId),
    CONSTRAINT FOREIGN KEY fk_heroSightedAt_hero(heroId)
		REFERENCES hero(heroId),
    CONSTRAINT FOREIGN KEY fk_heroSightedAt_location(locationId)
		REFERENCES location(locationId)
);

/*
Ensure we have enough data for tests:
only some tests needed to show understanding of order of deletes and error handling
	Must show failure when deleting X with an existing Y: (X, Y)
		location, organization
			delete location 1
            KEEP to show error handling
	Must show cascading delete when deleting X with an existing Y: (X, Y)
		location, heroSightedAt
			after deleting organization 1, delete location 1
            to show that we have a check for locations being valid to delete
		hero, heroAffiliatedWithOrganization
			delete hero 2
            to show cascade of deletes (also cascades to delete heroSightedAt)
*/

INSERT INTO location(name, description, address, latitude, longitude) VALUES
	("location1", "description1", "1 1st Street, City 1, State 1", 10, 10.5),
	("location2", "description2", "2 2nd Avenue, City 2, State 2", 55.7, 22.8974)
;

INSERT INTO organization(name, description, locationId) VALUES
	("organization1", "description1", 1),
    ("organization2", "description2", 2)
;

INSERT INTO superPower(name) VALUES
	("running real fast"),
    ("running real slow")
;

INSERT INTO hero(name, description, superPowerId) VALUES
	("hero1", "He's the hero that runs real fast.", 1),
    ("hero2", "He's the hero that runs real slow.", 2)
;

INSERT INTO heroAffiliatedWithOrganization(heroId, organizationId) VALUES
	(1, 1),
    (1, 2),
    (2, 2)
;

INSERT INTO heroSightedAt(heroId, locationId) VALUES
	(1, 1),
    (1, 2),
    (2, 2)
;