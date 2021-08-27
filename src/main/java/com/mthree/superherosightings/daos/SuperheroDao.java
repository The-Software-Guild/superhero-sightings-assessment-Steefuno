/*
 * @author Steven Nguyen
 * @email: steven.686295@gmail.com
 * @date: 25 Aug 2021
 */
package com.mthree.superherosightings.daos;

import com.mthree.superherosightings.models.Affiliation;
import com.mthree.superherosightings.models.Hero;
import com.mthree.superherosightings.models.IdAndName;
import com.mthree.superherosightings.models.Location;
import com.mthree.superherosightings.models.Organization;
import com.mthree.superherosightings.models.Power;
import com.mthree.superherosightings.models.Sighting;
import java.util.List;
import org.springframework.dao.DataAccessException;

// https://stackoverflow.com/questions/2366813/on-duplicate-key-ignore
/**
 *
 * @author Steven
 */
public interface SuperheroDao {
    /**
     * Gets all hero names
     * @return a list of the ids and names
     * @throws DataAccessException 
     */
    public abstract List<IdAndName> getHeroes() throws DataAccessException;
    
    /**
     * Gets a hero
     * @param heroId the hero's id
     * @return the hero's data
     * @throws DataAccessException 
     */
    public abstract Hero getHero(int heroId) throws DataAccessException;
    
    /**
     * Adds a new hero
     * @param hero the hero's data
     * @throws DataAccessException 
     */
    public abstract void addHero(Hero hero) throws DataAccessException;
    
    /**
     * Edits a hero
     * @param hero the hero's new data
     * @throws DataAccessException 
     */
    public abstract void editHero(Hero hero) throws DataAccessException;
    
    /**
     * Deletes a hero
     * @param heroId the hero's id
     * @throws DataAccessException 
     */
    public abstract void deleteHero(int heroId) throws DataAccessException;
    
    /**
     * Gets all the powers
     * @return a list of the ids and names
     * @throws DataAccessException 
     */
    public abstract List<IdAndName> getPowers() throws DataAccessException;
    
    /**
     * Gets a power
     * @param powerId the power's id
     * @return the power's data
     * @throws DataAccessException 
     */
    public abstract Power getPower(int powerId) throws DataAccessException;
    
    /**
     * Adds a new power
     * @param power the power's data
     * @throws DataAccessException 
     */
    public abstract void addPower(Power power) throws DataAccessException;
    
    /**
     * Edits a power
     * @param power the power's new data
     * @throws DataAccessException 
     */
    public abstract void editPower(Power power) throws DataAccessException;
    
    /**
     * Deletes a power
     * @param powerId the power's id
     * @throws DataAccessException 
     */
    public abstract void deletePower(int powerId) throws DataAccessException;
    
    /**
     * Gets all the locations
     * @return a list of the ids and names
     * @throws DataAccessException 
     */
    public abstract List<IdAndName> getLocations() throws DataAccessException;
    
    /**
     * Gets a location
     * @param locationId the location's id
     * @return the location's data
     * @throws DataAccessException 
     */
    public abstract Location getLocation(int locationId) throws DataAccessException;
    
    /**
     * Adds a new location
     * @param location the location's data
     * @throws DataAccessException 
     */
    public abstract void addLocation(Location location) throws DataAccessException;
    
    /**
     * Edits a location
     * @param location the location's new data
     * @throws DataAccessException 
     */
    public abstract void editLocation(Location location) throws DataAccessException;
    
    /**
     * Deletes a location
     * @param locationId the location's id
     * @throws DataAccessException 
     */
    public abstract void deleteLocation(int locationId) throws DataAccessException;
    
    /**
     * Gets all the organizations
     * @return a list of the ids and names
     * @throws DataAccessException 
     */
    public abstract List<IdAndName> getOrganizations() throws DataAccessException;
    
    /**
     * Gets a organization
     * @param organizationId the organization's id
     * @return the organization's data
     * @throws DataAccessException 
     */
    public abstract Organization getOrganization(int organizationId) throws DataAccessException;
    
    /**
     * Gets organization(s)
     * @param locationId the location's id
     * @return
     * @throws DataAccessException 
     */
    public abstract List<Organization> getOrganizationByLocation(int locationId) throws DataAccessException;
    
    /**
     * Adds a new organization
     * @param organization the organization's data
     * @throws DataAccessException 
     */
    public abstract void addOrganization(Organization organization) throws DataAccessException;
    
    /**
     * Edits a organization
     * @param organization the organization's new data
     * @throws DataAccessException 
     */
    public abstract void editOrganization(Organization organization) throws DataAccessException;
    
    /**
     * Deletes a organization
     * @param organizationId the organization's id
     * @throws DataAccessException 
     */
    public abstract void deleteOrganization(int organizationId) throws DataAccessException;
    
    /**
     * Gets all of the organization affiliations for a hero
     * @param heroId the hero's id
     * @return the list of affiliations
     */
    public List<Affiliation> getHeroAffiliations(int heroId) throws DataAccessException;
    
    /**
     * Gets all of the hero affiliations for an organization
     * @param organizationId the organization's id
     * @return the list of affiliations
     */
    public List<Affiliation> getOrganizationAffiliations(int organizationId) throws DataAccessException;
    
    /**
     * Affiliates a hero and an organization
     * @param heroId the hero's id
     * @param organizationId the organization's id
     */
    public void setAffiliation(int heroId, int organizationId) throws DataAccessException;
    
    /**
     * Deletes an affiliation
     * @param affiliationId the affiliation's id
     */
    public void deleteAffiliation(int affiliationId) throws DataAccessException;

    /**
     * Gets all the sightings
     * @return a list of the ids and names
     * @throws DataAccessException 
     */
    public abstract List<IdAndName> getSightings() throws DataAccessException;
    
    /**
     * Gets a sighting
     * @param sightingId the sighting's id
     * @return the sighting's data
     * @throws DataAccessException 
     */
    public abstract Sighting getSighting(int sightingId) throws DataAccessException;
    
    /**
     * Adds a new sighting
     * @param sighting the sighting's data
     * @throws DataAccessException 
     */
    public abstract void addSighting(Sighting sighting) throws DataAccessException;
    
    /**
     * Edits a sighting
     * @param sighting the sighting's new data
     * @throws DataAccessException 
     */
    public abstract void editSighting(Sighting sighting) throws DataAccessException;
    
    /**
     * Deletes a sighting
     * @param sightingId the sighting's id
     * @throws DataAccessException 
     */
    public abstract void deleteSighting(int sightingId) throws DataAccessException;
}
