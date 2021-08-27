-- reconstruct the database
DROP DATABASE IF EXISTS SuperheroSightingsTestDB;
CREATE DATABASE SuperheroSightingsTestDB;

USE SuperheroSightingsTestDB;

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