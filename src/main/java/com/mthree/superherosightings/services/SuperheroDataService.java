/*
 * @author Steven Nguyen
 * @email: steven.686295@gmail.com
 * @date: 
 */

package com.mthree.superherosightings.services;

import com.mthree.superherosightings.daos.SuperheroDao;
import com.mthree.superherosightings.models.Hero;
import com.mthree.superherosightings.models.IdAndName;
import com.mthree.superherosightings.models.Location;
import com.mthree.superherosightings.models.Organization;
import com.mthree.superherosightings.models.Power;
import com.mthree.superherosightings.models.Sighting;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Steven
 */
@Component
public class SuperheroDataService {
    @Autowired
    SuperheroDao dao;
    
    /**
     * Gets all hero names
     * @return a list of hero names and ids
     * @throws DataAccessException 
     */
    @Transactional
    public List<IdAndName> getHeroes() throws DataAccessException {}
    
    /**
     * Gets a hero
     * @param heroId the hero's id
     * @return the hero's data
     * @throws DataAccessException 
     */
    @Transactional
    public Hero getHero(int heroId) throws DataAccessException {}
    
    /**
     * Adds a new hero
     * @param hero the hero's data
     * @throws DataAccessException 
     */
    @Transactional
    public void addHero(Hero hero) throws DataAccessException {}
    
    /**
     * Edits a hero
     * @param hero the hero's new data
     * @throws DataAccessException 
     */
    @Transactional
    public void editHero(Hero hero) throws DataAccessException {}
    
    /**
     * Deletes a hero
     * @param heroId the hero's id
     * @throws DataAccessException 
     */
    @Transactional
    public void deleteHero(int heroId) throws DataAccessException {}
    
    /**
     * Gets all the powers
     * @return a list of the ids and names
     * @throws DataAccessException 
     */
    @Transactional
    public List<IdAndName> getPowers() throws DataAccessException {}
    
    /**
     * Gets a power
     * @param powerId the power's id
     * @return the power's data
     * @throws DataAccessException 
     */
    @Transactional
    public Power getPower(int powerId) throws DataAccessException {}
    
    /**
     * Adds a new power
     * @param power the power's data
     * @throws DataAccessException 
     */
    @Transactional
    public void addPower(Power power) throws DataAccessException {}
    
    /**
     * Edits a power
     * @param power the power's new data
     * @throws DataAccessException 
     */
    @Transactional
    public void editPower(Power power) throws DataAccessException {}
    
    /**
     * Deletes a power
     * @param powerId the power's id
     * @throws DataAccessException 
     */
    @Transactional
    public void deletePower(int powerId) throws DataAccessException {}
    
    /**
     * Gets all the locations
     * @return a list of the ids and names
     * @throws DataAccessException 
     */
    @Transactional
    public List<IdAndName> getLocations() throws DataAccessException {}
    
    /**
     * Gets a location
     * @param locationId the location's id
     * @return the location's data
     * @throws DataAccessException 
     */
    @Transactional
    public Location getLocation(int locationId) throws DataAccessException {}
    
    /**
     * Adds a new location
     * @param location the location's data
     * @throws DataAccessException 
     */
    @Transactional
    public void addLocation(Location location) throws DataAccessException {}
    
    /**
     * Edits a location
     * @param location the location's new data
     * @throws DataAccessException 
     */
    @Transactional
    public void editLocation(Location location) throws DataAccessException {}
    
    /**
     * Deletes a location
     * @param locationId the location's id
     * @throws DataAccessException 
     */
    @Transactional
    public void deleteLocation(int locationId) throws DataAccessException {}
    
    /**
     * Gets all the organizations
     * @return a list of the ids and names
     * @throws DataAccessException 
     */
    @Transactional
    public List<IdAndName> getOrganizations() throws DataAccessException {}
    
    /**
     * Gets a organization
     * @param organizationId the organization's id
     * @return the organization's data
     * @throws DataAccessException 
     */
    @Transactional
    public Organization getOrganization(int organizationId) throws DataAccessException {}
    
    /**
     * Adds a new organization
     * @param organization the organization's data
     * @throws DataAccessException 
     */
    @Transactional
    public void addOrganization(Organization organization) throws DataAccessException {}
    
    /**
     * Edits a organization
     * @param organization the organization new data
     * @throws DataAccessException 
     */
    @Transactional
    public void editOrganization(Organization organization) throws DataAccessException {}
    
    /**
     * Deletes a organization
     * @param organizationId the organization's id
     * @throws DataAccessException 
     */
    @Transactional
    public void deleteOrganization(int organizationId) throws DataAccessException {}
    
    /**
     * Gets all the sightings
     * @return a list of the ids and names
     * @throws DataAccessException 
     */
    @Transactional
    public List<IdAndName> getSightings() throws DataAccessException {}
    
    /**
     * Gets a sighting
     * @param sightingId the sighting's id
     * @return the sighting's data
     * @throws DataAccessException 
     */
    @Transactional
    public Sighting getSighting(int sightingId) throws DataAccessException {}
    
    /**
     * Adds a new sighting
     * @param sighting the sighting's data
     * @throws DataAccessException 
     */
    @Transactional
    public void addSighting(Sighting sighting) throws DataAccessException {}
    
    /**
     * Edits a sighting
     * @param sighting the sighting's new data
     * @throws DataAccessException 
     */
    @Transactional
    public void editSighting(Sighting sighting) throws DataAccessException {}
    
    /**
     * Deletes a sighting
     * @param sightingId the sighting's id
     * @throws DataAccessException 
     */
    @Transactional
    public void deleteSighting(int sightingId) throws DataAccessException {}
}