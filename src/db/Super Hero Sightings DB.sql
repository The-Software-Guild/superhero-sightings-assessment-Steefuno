-- reconstruct the database
DROP DATABASE IF EXISTS SuperHeroSightingsDB;
CREATE DATABASE SuperHeroSightingsDB;

USE SuperHeroSightingsDB;

CREATE TABLE location(
	locationId INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL DEFAULT("no name"),
    description VARCHAR(100) NOT NULL DEFAULT("no description"),
    address VARCHAR(100) DEFAULT("no address"),
    latitude DECIMAL(6) NOT NULL,
    longitude DECIMAL(6) NOT NULL,
    PRIMARY KEY (locationId)
);

CREATE TABLE organization(
	organizationId INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL DEFAULT("no name"),
    description VARCHAR(100) NOT NULL DEFAULT("no description"),
    locationId INT,
    PRIMARY KEY (organizationId),
    CONSTRAINT FOREIGN KEY fk_organization_location(locationId)
		REFERENCES location(locationId)
);

CREATE TABLE heroAffiliatedWithOrganization(
	heroId INT NOT NULL,
    organizationId INT NOT NULL,
    CONSTRAINT FOREIGN KEY fk_heroAffiliatedWithOrganization_hero(heroId)
		REFERENCES hero(heroId),
    CONSTRAINT FOREIGN KEY fk_heroAffiliatedWithOrganization_organization(organizationId)
		REFERENCES organization(organizationId)
);

CREATE TABLE superPower(
	superPowerId INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    PRIMARY KEY (superPowerId)
);

CREATE TABLE heroSightedAt(
	heroId INT NOT NULL,
    locationId INT NOT NULL,
    CONSTRAINT FOREIGN KEY fk_heroSightedAt_hero(heroId)
		REFERENCES hero(heroId),
    CONSTRAINT FOREIGN KEY fk_heroSightedAt_location(locationId)
		REFERENCES location(locationId)
);