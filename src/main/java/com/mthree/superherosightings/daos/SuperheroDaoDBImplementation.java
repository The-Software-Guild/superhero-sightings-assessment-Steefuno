/*
 * @author Steven Nguyen
 * @email: steven.686295@gmail.com
 * @date: 25 Aug 2021
 */

package com.mthree.superherosightings.daos;

import com.mthree.superherosightings.daos.dbmappers.*;
import com.mthree.superherosightings.models.Affiliation;
import com.mthree.superherosightings.models.Hero;
import com.mthree.superherosightings.models.IdAndName;
import com.mthree.superherosightings.models.Location;
import com.mthree.superherosightings.models.Organization;
import com.mthree.superherosightings.models.Power;
import com.mthree.superherosightings.models.Sighting;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Steven
 */
@Repository
public class SuperheroDaoDBImplementation implements SuperheroDao {
    final private JdbcTemplate jdbcTemplate;
    
    final private static String SELECT_ALL_HERO_NAMES =
        "SELECT heroId as id, name as name " +
        "FROM hero " +
        "ORDER BY id "
    ;
    final private static String SELECT_HERO_BY_ID =
        "SELECT * " +
        "FROM hero " +
        "WHERE hero.heroId = ? "
    ;
    final private static String INSERT_NEW_HERO =
        "INSERT INTO hero(name, description, superPowerId) VALUES " +
        "   (?, ?, ?) "
    ;
    final private static String UPDATE_HERO =
        "UPDATE hero " +
        "SET " +
        "   name = ?, " +
        "   description = ?, " +
        "   superPowerId = ? " +
        "WHERE heroId = ? "
    ;
    final private static String DELETE_HERO =
        "DELETE FROM hero " +
        "WHERE heroId = ? "
    ;
    
    final private static String SELECT_ALL_POWER_NAMES =
        "SELECT superPowerId as id, name as name " +
        "FROM superPower " +
        "ORDER BY id "
    ;
    final private static String SELECT_POWER_BY_ID =
        "SELECT * " +
        "FROM superPower " +
        "WHERE superPower.superPowerId = ? "
    ;
    final private static String INSERT_NEW_POWER =
        "INSERT INTO superPower(name) VALUES" +
        "   (?) "
    ;
    final private static String UPDATE_POWER =
        "UPDATE superPower " +
        "SET " +
        "   name = ? " +
        "WHERE superPowerId = ? "
    ;
    final private static String DELETE_POWER =
        "DELETE FROM superPower " +
        "WHERE superPowerId = ? "
    ;
    
    final private static String SELECT_ALL_LOCATION_NAMES =
        "SELECT locationId as id, name as name " +
        "FROM location " +
        "ORDER BY id "
    ;
    final private static String SELECT_LOCATION_BY_ID =
        "SELECT * " +
        "FROM location " +
        "WHERE location.locationId = ? "
    ;
    final private static String INSERT_NEW_LOCATION =
        "INSERT INTO location(name, description, address, latitude, longitude) VALUES " +
        "   (?, ?, ?, ?, ?) "
    ;
    final private static String UPDATE_LOCATION =
        "UPDATE location " +
        "SET " +
        "   name = ?, " +
        "   description = ?, " +
        "   address = ?, " +
        "   latitude = ?, " +
        "   longitude = ? " +
        "WHERE locationId = ? "
    ;
    final private static String DELETE_LOCATION =
        "DELETE FROM location " +
        "WHERE locationId = ? "
    ;
    
    final private static String SELECT_ALL_ORGANIZATION_NAMES =
        "SELECT organizationId as id, name as name " +
        "FROM organization " +
        "ORDER BY id "
    ;
    final private static String SELECT_ORGANIZATION_BY_ID =
        "SELECT * " +
        "FROM organization " +
        "WHERE organization.organizationId = ? "
    ;
    final private static String SELECT_ORGANIZATION_BY_LOCATION =
        "SELECT * " +
        "FROM organization " +
        "WHERE organization.locationId = ? "
    ;
    final private static String INSERT_NEW_ORGANIZATION =
        "INSERT INTO organization(name, description, locationId) VALUES " +
        "   (?, ?, ?) "
    ;
    final private static String UPDATE_ORGANIZATION =
        "UPDATE organization " +
        "SET " +
        "   name = ?, " +
        "   description = ?, " +
        "   locationId = ? " +
        "WHERE organizationId = ? "
    ;
    final private static String DELETE_ORGANIZATION =
        "DELETE FROM organization " +
        "WHERE organizationId = ? "
    ;
    
    final private static String SELECT_HERO_AFFILIATIONS =
        "SELECT affiliationId as id, heroId, organizationId " +
        "FROM heroAffiliatedWithOrganization " +
        "WHERE heroId = ? "
    ;
    final private static String SELECT_ORGANIZATION_AFFILIATIONS =
        "SELECT affiliationId as id, heroId, organizationId " +
        "FROM heroAffiliatedWithOrganization " +
        "WHERE organizationId = ? "
    ;
    final private static String INSERT_AFFILIATION = 
        "INSERT INTO heroAffiliatedWithOrganization(heroId, organizationId) VALUES " +
        "   (?, ?) "
    ;
    final private static String DELETE_AFFILIATION =
        "DELETE FROM heroAffiliatedWithOrganization " +
        "WHERE affiliationId = ? "
    ;
    
    final private static String SELECT_ALL_SIGHTING_NAMES =
        "SELECT sightingId as id, location.name as name " +
        "FROM heroSightedAt " +
        "INNER JOIN location ON location.locationId = heroSightedAt.locationId " +
        "ORDER BY id "
    ;
    final private static String SELECT_SIGHTING_BY_ID =
        "SELECT * " +
        "FROM heroSightedAt " +
        "WHERE heroSightedAt.sightingId = ? "
    ;
    final private static String INSERT_NEW_SIGHTING =
        "INSERT INTO heroSightedAt(heroId, locationId, time) VALUES " +
        "   (?, ?, ?, ?) "
    ;
    final private static String UPDATE_SIGHTING =
        "UPDATE heroSightedAt " +
        "SET " +
        "   heroId = ?, " +
        "   locationId = ?, " +
        "   time = ? " +
        "WHERE sightingId = ? "
    ;
    final private static String DELETE_SIGHTING =
        "DELETE FROM heroSightedAt " +
        "WHERE sightingId = ? "
    ;
    
    /**
     * Constructs a new SuperheroDaoDBImplementation
     * @param jdbcTemplate 
     */
    @Autowired
    public SuperheroDaoDBImplementation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    /**
     * Gets all hero names
     * @return a list of hero names and ids
     * @throws DataAccessException 
     */
    @Override
    public List<IdAndName> getHeroes() throws DataAccessException {
        List<IdAndName> heroesList;
        
        heroesList = jdbcTemplate.query(SELECT_ALL_HERO_NAMES, new IdAndNameMapper());
        
        return heroesList;
    }
    
    /**
     * Gets a hero
     * @param heroId the hero's id
     * @return the hero's data
     * @throws DataAccessException 
     */
    @Override
    public Hero getHero(int heroId) throws DataAccessException {
        Hero hero;
        
        hero = (Hero) jdbcTemplate.queryForObject(SELECT_HERO_BY_ID, new HeroMapper(), heroId);
        
        return hero;
    }
    
    /**
     * Adds a new hero
     * @param hero the hero's data
     * @throws DataAccessException 
     */
    @Override
    public void addHero(Hero hero) throws DataAccessException {
        jdbcTemplate.update(
            (Connection connection) -> {
                PreparedStatement preparedStatement;
                
                preparedStatement = connection.prepareStatement(INSERT_NEW_HERO);
                preparedStatement.setString(1, hero.getName());
                preparedStatement.setString(2, hero.getDescription());
                preparedStatement.setInt(3, hero.getSuperPowerId());
                
                return preparedStatement;
            }
        );
    }
    
    /**
     * Edits a hero
     * @param hero the hero's new data
     * @throws DataAccessException 
     */
    @Override
    public void editHero(Hero hero) throws DataAccessException {
        jdbcTemplate.update(
            (Connection connection) -> {
                PreparedStatement preparedStatement;

                preparedStatement = connection.prepareStatement(UPDATE_HERO);
                preparedStatement.setString(1, hero.getName());
                preparedStatement.setString(2, hero.getDescription());
                preparedStatement.setInt(3, hero.getSuperPowerId());
                preparedStatement.setInt(4, hero.getId());
                
                return preparedStatement;
            }
        );
    }
    
    /**
     * Deletes a hero
     * @param heroId the hero's id
     * @throws DataAccessException 
     */
    @Override
    public void deleteHero(int heroId) throws DataAccessException {
        jdbcTemplate.update(
            (Connection connection) -> {
                PreparedStatement preparedStatement;

                preparedStatement = connection.prepareStatement(DELETE_HERO);
                preparedStatement.setInt(1, heroId);
                
                return preparedStatement;
            }
        );
    }
    
    /**
     * Gets all the powers
     * @return a list of the ids and names
     * @throws DataAccessException 
     */
    @Override
    public List<IdAndName> getPowers() throws DataAccessException {
        List<IdAndName> powersList;
        
        powersList = jdbcTemplate.query(SELECT_ALL_POWER_NAMES, new IdAndNameMapper());
        
        return powersList;
    }
    
    /**
     * Gets a power
     * @param powerId the power's id
     * @return the power's data
     * @throws DataAccessException 
     */
    @Override
    public Power getPower(int powerId) throws DataAccessException {
        Power power;
        
        power = (Power) jdbcTemplate.queryForObject(SELECT_POWER_BY_ID, new PowerMapper(), powerId);
        
        return power;
    }
    
    /**
     * Adds a new power
     * @param power the power's data
     * @throws DataAccessException 
     */
    @Override
    public void addPower(Power power) throws DataAccessException {
        jdbcTemplate.update(
            (Connection connection) -> {
                PreparedStatement preparedStatement;
                
                preparedStatement = connection.prepareStatement(INSERT_NEW_POWER);
                preparedStatement.setString(1, power.getName());
                
                return preparedStatement;
            }
        );
    }
    
    /**
     * Edits a power
     * @param power the power's new data
     * @throws DataAccessException 
     */
    @Override
    public void editPower(Power power) throws DataAccessException {
        jdbcTemplate.update(
            (Connection connection) -> {
                PreparedStatement preparedStatement;

                preparedStatement = connection.prepareStatement(UPDATE_POWER);
                preparedStatement.setString(1, power.getName());
                preparedStatement.setInt(2, power.getId());
                
                return preparedStatement;
            }
        );
    }
    
    /**
     * Deletes a power
     * @param powerId the power's id
     * @throws DataAccessException 
     */
    @Override
    public void deletePower(int powerId) throws DataAccessException {
        jdbcTemplate.update(
            (Connection connection) -> {
                PreparedStatement preparedStatement;

                preparedStatement = connection.prepareStatement(DELETE_POWER);
                preparedStatement.setInt(1, powerId);
                
                return preparedStatement;
            }
        );
    }
    
    /**
     * Gets all the locations
     * @return a list of the ids and names
     * @throws DataAccessException 
     */
    @Override
    public List<IdAndName> getLocations() throws DataAccessException {
        List<IdAndName> locationsList;
        
        locationsList = jdbcTemplate.query(SELECT_ALL_LOCATION_NAMES, new IdAndNameMapper());
        
        return locationsList;
    }
    
    /**
     * Gets a location
     * @param locationId the location's id
     * @return the location's data
     * @throws DataAccessException 
     */
    @Override
    public Location getLocation(int locationId) throws DataAccessException {
        Location location;
        
        location = (Location) jdbcTemplate.queryForObject(SELECT_LOCATION_BY_ID, new LocationMapper(), locationId);
        
        return location;
    }
    
    /**
     * Adds a new location
     * @param location the location's data
     * @throws DataAccessException 
     */
    @Override
    public void addLocation(Location location) throws DataAccessException {
        jdbcTemplate.update(
            (Connection connection) -> {
                PreparedStatement preparedStatement;
                
                preparedStatement = connection.prepareStatement(INSERT_NEW_LOCATION);
                preparedStatement.setString(1, location.getName());
                preparedStatement.setString(2, location.getDescription());
                preparedStatement.setString(3, location.getAddress());
                preparedStatement.setDouble(4, location.getLatitude());
                preparedStatement.setDouble(5, location.getLongitude());
                
                return preparedStatement;
            }
        );
    }
    
    /**
     * Edits a location
     * @param location the location's new data
     * @throws DataAccessException 
     */
    @Override
    public void editLocation(Location location) throws DataAccessException {
        jdbcTemplate.update(
            (Connection connection) -> {
                PreparedStatement preparedStatement;

                preparedStatement = connection.prepareStatement(UPDATE_LOCATION);
                preparedStatement.setString(1, location.getName());
                preparedStatement.setString(2, location.getDescription());
                preparedStatement.setString(3, location.getDescription());
                preparedStatement.setDouble(4, location.getLatitude());
                preparedStatement.setDouble(5, location.getLongitude());
                preparedStatement.setDouble(6, location.getId());
                
                return preparedStatement;
            }
        );
    }
    
    /**
     * Deletes a location
     * @param locationId the location's id
     * @throws DataAccessException 
     */
    @Override
    public void deleteLocation(int locationId) throws DataAccessException {
        jdbcTemplate.update(
            (Connection connection) -> {
                PreparedStatement preparedStatement;

                preparedStatement = connection.prepareStatement(DELETE_LOCATION);
                preparedStatement.setInt(1, locationId);
                
                return preparedStatement;
            }
        );
    }
    
    /**
     * Gets all the organizations
     * @return a list of the ids and names
     * @throws DataAccessException 
     */
    @Override
    public List<IdAndName> getOrganizations() throws DataAccessException {
        List<IdAndName> organizationsList;
        
        organizationsList = jdbcTemplate.query(SELECT_ALL_ORGANIZATION_NAMES, new IdAndNameMapper());
        
        return organizationsList;
    }
    
    /**
     * Gets a organization
     * @param organizationId the organization's id
     * @return the organization's data
     * @throws DataAccessException 
     */
    @Override
    public Organization getOrganization(int organizationId) throws DataAccessException {
        Organization organization;
        
        organization = (Organization) jdbcTemplate.queryForObject(SELECT_ORGANIZATION_BY_ID, new OrganizationMapper(), organizationId);
        
        return organization;
    }
    
    /**
     * Gets organization(s)
     * @param locationId the location's id
     * @return
     * @throws DataAccessException 
     */
    @Override
    public List<Organization> getOrganizationByLocation(int locationId) throws DataAccessException {
        List<Organization> organizationsList;
        
        organizationsList = jdbcTemplate.query(SELECT_ORGANIZATION_BY_LOCATION, new OrganizationMapper(), locationId);
        
        return organizationsList;
    }
    
    /**
     * Adds a new organization
     * @param organization the organization's data
     * @throws DataAccessException 
     */
    @Override
    public void addOrganization(Organization organization) throws DataAccessException {
        jdbcTemplate.update(
            (Connection connection) -> {
                PreparedStatement preparedStatement;
                
                preparedStatement = connection.prepareStatement(INSERT_NEW_ORGANIZATION);
                preparedStatement.setString(1, organization.getName());
                preparedStatement.setString(2, organization.getDescription());
                preparedStatement.setInt(3, organization.getLocationId());
                
                return preparedStatement;
            }
        );
    }
    
    /**
     * Edits a organization
     * @param organization the organization's new data
     * @throws DataAccessException 
     */
    @Override
    public void editOrganization(Organization organization) throws DataAccessException {
        jdbcTemplate.update(
            (Connection connection) -> {
                PreparedStatement preparedStatement;

                preparedStatement = connection.prepareStatement(UPDATE_ORGANIZATION);
                preparedStatement.setString(1, organization.getName());
                preparedStatement.setString(2, organization.getDescription());
                preparedStatement.setInt(3, organization.getLocationId());
                preparedStatement.setInt(4, organization.getId());
                
                return preparedStatement;
            }
        );
    }
    
    /**
     * Deletes a organization
     * @param organizationId the organization's id
     * @throws DataAccessException 
     */
    @Override
    public void deleteOrganization(int organizationId) throws DataAccessException {
        jdbcTemplate.update(
            (Connection connection) -> {
                PreparedStatement preparedStatement;

                preparedStatement = connection.prepareStatement(DELETE_ORGANIZATION);
                preparedStatement.setInt(1, organizationId);
                
                return preparedStatement;
            }
        );
    }
    
    /**
     * Gets all of the organization affiliations for a hero
     * @param heroId the hero's id
     * @return the list of affiliations
     */
    @Override
    public List<Affiliation> getHeroAffiliations(int heroId) throws DataAccessException {
        List<Affiliation> affiliationsList;
        
        affiliationsList = jdbcTemplate.query(SELECT_HERO_AFFILIATIONS, new AffiliationMapper(), heroId);
        
        return affiliationsList;
    }
    
    /**
     * Gets all of the hero affiliations for an organization
     * @param organizationId the organization's id
     * @return the list of affiliations
     */
    @Override
    public List<Affiliation> getOrganizationAffiliations(int organizationId) throws DataAccessException {
        List<Affiliation> affiliationsList;
        
        affiliationsList = jdbcTemplate.query(SELECT_ORGANIZATION_AFFILIATIONS, new AffiliationMapper(), organizationId);
        
        return affiliationsList;
    }
    
    /**
     * Affiliates a hero and an organization
     * @param heroId the hero's id
     * @param organizationId the organization's id
     */
    @Override
    public void setAffiliation(int heroId, int organizationId) throws DataAccessException {
        jdbcTemplate.update(
            (Connection connection) -> {
                PreparedStatement preparedStatement;
                
                preparedStatement = connection.prepareStatement(INSERT_AFFILIATION);
                preparedStatement.setInt(1, heroId);
                preparedStatement.setInt(2, organizationId);
                
                return preparedStatement;
            }
        );
    }
    
    /**
     * Deletes an affiliation
     * @param affiliationId the affiliation's id
     */
    @Override
    public void deleteAffiliation(int affiliationId) throws DataAccessException {
        jdbcTemplate.update(
            (Connection connection) -> {
                PreparedStatement preparedStatement;
                
                preparedStatement = connection.prepareStatement(DELETE_AFFILIATION);
                preparedStatement.setInt(1, affiliationId);
                
                return preparedStatement;
            }
        );
    }
    
    /**
     * Gets all the sightings
     * @return a list of the ids and names
     * @throws DataAccessException 
     */
    @Override
    public List<IdAndName> getSightings() throws DataAccessException {
        List<IdAndName> sightingsList;
        
        sightingsList = jdbcTemplate.query(SELECT_ALL_SIGHTING_NAMES, new IdAndNameMapper());
        
        return sightingsList;
    }
    
    /**
     * Gets a sighting
     * @param sightingId the sighting's id
     * @return the sighting's data
     * @throws DataAccessException 
     */
    @Override
    public Sighting getSighting(int sightingId) throws DataAccessException {
        Sighting sighting;
        
        sighting = (Sighting) jdbcTemplate.queryForObject(SELECT_SIGHTING_BY_ID, new SightingMapper(), sightingId);
        
        return sighting;
    }
    
    /**
     * Adds a new sighting
     * @param sighting the sighting's data
     * @throws DataAccessException 
     */
    @Override
    public void addSighting(Sighting sighting) throws DataAccessException {
        jdbcTemplate.update(
            (Connection connection) -> {
                PreparedStatement preparedStatement;
                
                preparedStatement = connection.prepareStatement(INSERT_NEW_SIGHTING);
                preparedStatement.setInt(1, sighting.getHeroId());
                preparedStatement.setInt(2, sighting.getLocationId());
                preparedStatement.setTimestamp(3, sighting.getTime());
                
                return preparedStatement;
            }
        );
    }
    
    /**
     * Edits a sighting
     * @param sighting the sighting's new data
     * @throws DataAccessException 
     */
    @Override
    public void editSighting(Sighting sighting) throws DataAccessException {
        jdbcTemplate.update(
            (Connection connection) -> {
                PreparedStatement preparedStatement;

                preparedStatement = connection.prepareStatement(UPDATE_SIGHTING);
                preparedStatement.setInt(1, sighting.getHeroId());
                preparedStatement.setInt(2, sighting.getLocationId());
                preparedStatement.setTimestamp(3, sighting.getTime());
                preparedStatement.setInt(4, sighting.getId());
                
                return preparedStatement;
            }
        );
    }
    
    /**
     * Deletes a sighting
     * @param sightingId the sighting's id
     * @throws DataAccessException 
     */
    @Override
    public void deleteSighting(int sightingId) throws DataAccessException {
        jdbcTemplate.update(
            (Connection connection) -> {
                PreparedStatement preparedStatement;

                preparedStatement = connection.prepareStatement(DELETE_SIGHTING);
                preparedStatement.setInt(1, sightingId);
                
                return preparedStatement;
            }
        );
    }
}
