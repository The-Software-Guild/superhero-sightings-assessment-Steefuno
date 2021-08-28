/*
 * @author Steven Nguyen
 * @email: steven.686295@gmail.com
 * @date: 
 */

package com.mthree.superherosightings.services;

import com.mthree.superherosightings.daos.SuperheroDao;
import com.mthree.superherosightings.models.Affiliation;
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
    public List<IdAndName> getHeroes() throws DataAccessException {
        return dao.getHeroes();
    }
    
    /**
     * Gets a hero
     * @param heroId the hero's id
     * @return the hero's data
     * @throws DataAccessException 
     */
    @Transactional
    public Hero getHero(int heroId) throws DataAccessException {
        return dao.getHero(heroId);
    }
    
    /**
     * Adds a new hero
     * @param hero the hero's data
     * @throws DataAccessException 
     */
    @Transactional
    public void addHero(Hero hero) throws DataAccessException {
        dao.addHero(hero);
    }
    
    /**
     * Edits a hero
     * @param hero the hero's new data
     * @throws DataAccessException 
     */
    @Transactional
    public void editHero(Hero hero) throws DataAccessException {
        dao.editHero(hero);
    }
    
    /**
     * Deletes a hero
     * @param heroId the hero's id
     * @throws DataAccessException 
     */
    @Transactional
    public void deleteHero(int heroId) throws DataAccessException {
        dao.getHeroAffiliations(heroId)
            .stream()
            .filter(
                (affiliation) -> affiliation.getHeroId() == heroId
            )
            .forEach(
                (affiliation) -> {
                    dao.deleteAffiliation(affiliation.getId());
                }
            )
        ;
        dao.getSightings()
            .forEach(
                (sighting) -> {
                    dao.deleteSighting(sighting.getId());
                }
            )
        ;
        dao.deleteHero(heroId);
    }
    
    /**
     * Gets all the powers
     * @return a list of the ids and names
     * @throws DataAccessException 
     */
    @Transactional
    public List<IdAndName> getPowers() throws DataAccessException {
        return dao.getPowers();
    }
    
    /**
     * Gets a power
     * @param powerId the power's id
     * @return the power's data
     * @throws DataAccessException 
     */
    @Transactional
    public Power getPower(int powerId) throws DataAccessException {
        return dao.getPower(powerId);
    }
    
    /**
     * Adds a new power
     * @param power the power's data
     * @throws DataAccessException 
     */
    @Transactional
    public void addPower(Power power) throws DataAccessException {
        dao.addPower(power);
    }
    
    /**
     * Edits a power
     * @param power the power's new data
     * @throws DataAccessException 
     */
    @Transactional
    public void editPower(Power power) throws DataAccessException {
        dao.editPower(power);
    }
    
    /**
     * Deletes a power
     * @param powerId the power's id
     * @throws DataAccessException 
     */
    @Transactional
    public void deletePower(int powerId) throws DataAccessException {
        dao.deletePower(powerId);
    }
    
    /**
     * Gets all the locations
     * @return a list of the ids and names
     * @throws DataAccessException 
     */
    @Transactional
    public List<IdAndName> getLocations() throws DataAccessException {
        return dao.getLocations();
    }
    
    /**
     * Gets a location
     * @param locationId the location's id
     * @return the location's data
     * @throws DataAccessException 
     */
    @Transactional
    public Location getLocation(int locationId) throws DataAccessException {
        return dao.getLocation(locationId);
    }
    
    /**
     * Adds a new location
     * @param location the location's data
     * @throws DataAccessException 
     */
    @Transactional
    public void addLocation(Location location) throws DataAccessException {
        dao.addLocation(location);
    }
    
    /**
     * Edits a location
     * @param location the location's new data
     * @throws DataAccessException 
     */
    @Transactional
    public void editLocation(Location location) throws DataAccessException {
        dao.editLocation(location);
    }
    
    /**
     * Deletes a location
     * @param locationId the location's id
     * @throws DataAccessException 
     * @throws Exception 
     */
    @Transactional
    public void deleteLocation(int locationId) throws DataAccessException, Exception {
        if (dao.getOrganizationByLocation(locationId).size() > 0) {
            throw new Exception("Cannot delete location with existing organizations on location");
        }
        dao.deleteLocation(locationId);
    }
    
    /**
     * Gets all the organizations
     * @return a list of the ids and names
     * @throws DataAccessException 
     */
    @Transactional
    public List<IdAndName> getOrganizations() throws DataAccessException {
        return dao.getOrganizations();
    }
    
    /**
     * Gets a organization
     * @param organizationId the organization's id
     * @return the organization's data
     * @throws DataAccessException 
     */
    @Transactional
    public Organization getOrganization(int organizationId) throws DataAccessException {
        return dao.getOrganization(organizationId);
    }
    
    /**
     * Adds a new organization
     * @param organization the organization's data
     * @throws DataAccessException 
     */
    @Transactional
    public void addOrganization(Organization organization) throws DataAccessException {
        dao.addOrganization(organization);
    }
    
    /**
     * Edits a organization
     * @param organization the organization new data
     * @throws DataAccessException 
     */
    @Transactional
    public void editOrganization(Organization organization) throws DataAccessException {
        dao.editOrganization(organization);
    }
    
    /**
     * Deletes a organization
     * @param organizationId the organization's id
     * @throws DataAccessException 
     */
    @Transactional
    public void deleteOrganization(int organizationId) throws DataAccessException {
        dao.deleteOrganization(organizationId);
    }
    
    /**
     * Gets an affiliation
     * @param affiliationId the affiliation's id
     * @return the affiliation
     */
    @Transactional
    public Affiliation getAffiliation(int affiliationId) throws DataAccessException {
        return dao.getAffiliation(affiliationId);
    }
    
    /**
     * Gets all of the organization affiliations for a hero
     * @param heroId the hero's id
     * @return the list of affiliations
     */
    @Transactional
    public List<Affiliation> getHeroAffiliations(int heroId) throws DataAccessException {
        return dao.getHeroAffiliations(heroId);
    }
    
    /**
     * Gets all of the hero affiliations for an organization
     * @param organizationId the organization's id
     * @return the list of affiliations
     */
    @Transactional
    public List<Affiliation> getOrganizationAffiliations(int organizationId) throws DataAccessException {
        return dao.getOrganizationAffiliations(organizationId);
    }
    
    /**
     * Affiliates a hero and an organization
     * @param heroId the hero's id
     * @param organizationId the organization's id
     */
    @Transactional
    public void addAffiliation(int heroId, int organizationId) throws DataAccessException {
        dao.setAffiliation(heroId, organizationId);
    }
    
    /**
     * Deletes an affiliation
     * @param affiliationId the affiliation's id
     */
    @Transactional
    public void deleteAffiliation(int affiliationId) throws DataAccessException {
        dao.deleteAffiliation(affiliationId);
    }
    
    /**
     * Gets all the sightings
     * @return a list of the ids and names
     * @throws DataAccessException 
     */
    @Transactional
    public List<IdAndName> getSightings() throws DataAccessException {
        return dao.getSightings();
    }
    
    /**
     * Gets a sighting
     * @param sightingId the sighting's id
     * @return the sighting's data
     * @throws DataAccessException 
     */
    @Transactional
    public Sighting getSighting(int sightingId) throws DataAccessException {
        return dao.getSighting(sightingId);
    }
    
    /**
     * Adds a new sighting
     * @param sighting the sighting's data
     * @throws DataAccessException 
     */
    @Transactional
    public void addSighting(Sighting sighting) throws DataAccessException {
        dao.addSighting(sighting);
    }
    
    /**
     * Edits a sighting
     * @param sighting the sighting's new data
     * @throws DataAccessException 
     */
    @Transactional
    public void editSighting(Sighting sighting) throws DataAccessException {
        dao.editSighting(sighting);
    }
    
    /**
     * Deletes a sighting
     * @param sightingId the sighting's id
     * @throws DataAccessException 
     */
    @Transactional
    public void deleteSighting(int sightingId) throws DataAccessException {
        dao.deleteSighting(sightingId);
    }
}