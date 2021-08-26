/*
 * @author Steven Nguyen
 * @email: steven.686295@gmail.com
 * @date: 
 */
package com.mthree.superherosightings.services;

import com.mthree.superherosightings.daos.SuperheroDao;
import com.mthree.superherosightings.models.Hero;
import com.mthree.superherosightings.models.IdAndName;
import com.mthree.superherosightings.models.Power;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Steven
 */
@SpringBootTest
public class SuperheroDataServiceTest {
    @Autowired
    private SuperheroDataService service;
    
    @Autowired
    private SuperheroDao dao;
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        dao.getLocations().forEach(location -> {
            dao.deleteLocation(location.getId());
        });
        dao.getOrganizations().forEach(organization -> {
            dao.getOrganizationAffiliations(organization.getId()).forEach(affiliation -> {
                dao.deleteAffiliation(affiliation.getId());
            });
            dao.deleteOrganization(organization.getId());
        });
        dao.getSightings().forEach(sighting -> {
            dao.deleteSighting(sighting.getId());
        });
        dao.getHeroes().forEach(hero -> {
            dao.deleteHero(hero.getId());
        });
        dao.getPowers().forEach(power -> {
            dao.deletePower(power.getId());
        });
    }
    
    @AfterEach
    public void tearDown() {
        dao.getLocations().forEach(location -> {
            dao.deleteLocation(location.getId());
        });
        dao.getOrganizations().forEach(organization -> {
            dao.getOrganizationAffiliations(organization.getId()).forEach(affiliation -> {
                dao.deleteAffiliation(affiliation.getId());
            });
            dao.deleteOrganization(organization.getId());
        });
        dao.getSightings().forEach(sighting -> {
            dao.deleteSighting(sighting.getId());
        });
        dao.getHeroes().forEach(hero -> {
            dao.deleteHero(hero.getId());
        });
        dao.getPowers().forEach(power -> {
            dao.deletePower(power.getId());
        });
    }

    /**
     * Test of getHeroes method, of class SuperheroDataService.
     */
    @Test
    public void GetHeroes_Should_Be_0_On_Start() {
        List<IdAndName> heroes;
        
        heroes = service.getHeroes();
        
        assertEquals(
            heroes.size(),
            0,
            "should have no ids."
        );
    }
    
    @Test
    public void GetHeroes_Should_Get_Names() {
        IdAndName[] heroes;
        IdAndName[] powers;
        
        dao.addPower(
            new Power(
                -1,
                "Super fast running"
            )
        );
        powers = dao.getPowers().toArray(new IdAndName[0]);
        
        dao.addHero(
            new Hero(
                -1,
                "Alice",
                "Runs really fast",
                powers[0].getId()
            )
        );
        dao.addHero(
            new Hero(
                -1,
                "Bob",
                "Runs quickly",
                powers[0].getId()
            )
        );
        heroes = service.getHeroes().toArray(new IdAndName[0]);
        
        assertEquals(
            heroes.length,
            2,
            "should have 2 ids."
        );
        assertEquals(
            heroes[0].getName(),
            "Alice",
            "first hero should be Alice"
        );
        assertEquals(
            heroes[1].getName(),
            "Bob",
            "second hero should be Bob"
        );
    }

    /**
     * Test of getHero method, of class SuperheroDataService.
     */
    @Test
    public void GetHero_Should_Get_Hero() {
        IdAndName[] heroes;
        IdAndName[] powers;
        
        dao.addPower(
            new Power(
                -1,
                "Super fast running"
            )
        );
        powers = dao.getPowers().toArray(new IdAndName[0]);
        
        dao.addHero(
            new Hero(
                -1,
                "Alice",
                "Runs really fast",
                powers[0].getId()
            )
        );
        dao.addHero(
            new Hero(
                -1,
                "Bob",
                "Runs quickly",
                powers[0].getId()
            )
        );
        heroes = dao.getHeroes().toArray(new IdAndName[0]);
        
        assertEquals(
            service.getHero(heroes[0].getId()).getDescription(),
            "Runs really fast",
            "first hero's description should be \"Runs really fast\""
        );
        assertEquals(
            service.getHero(heroes[1].getId()).getDescription(),
            "Runs quickly",
            "second hero's description should be \"Runs quickly\""
        );
    }
    
    @Test
    public void GetHero_Should_Throw_Exception_On_Invalid_ID() {
        try {
            service.getHero(1);
            fail("GetHero should have thrown an exception");
        } catch (DataAccessException e) {
            // do nothing
        }
    }
    
    /**
     * Test of addHero method, of class SuperheroDataService.
     */
    @Test
    public void testAddHero() {
        IdAndName[] heroes;
        IdAndName[] powers;
        
        dao.addPower(
            new Power(
                -1,
                "Super fast running"
            )
        );
        powers = dao.getPowers().toArray(new IdAndName[0]);
        
        service.addHero(
            new Hero(
                -1,
                "Alice",
                "Runs really fast",
                powers[0].getId()
            )
        );
        heroes = dao.getHeroes().toArray(new IdAndName[0]);
        
        assertEquals(
            heroes.length,
            1,
            "should have 1 hero"
        );
        assertEquals(
            heroes[0].getName(),
            "Alice",
            "first hero should be Alice"
        );
    }

    /**
     * Test of editHero method, of class SuperheroDataService.
     */
    @Test
    public void EditHero_Should_Edit_Hero() {
        IdAndName[] heroes;
        IdAndName[] powers;
        Hero hero;
        
        dao.addPower(
            new Power(
                -1,
                "Super fast running"
            )
        );
        powers = dao.getPowers().toArray(new IdAndName[0]);
        
        dao.addHero(
            new Hero(
                -1,
                "Alice",
                "Runs really fast",
                powers[0].getId()
            )
        );
        heroes = dao.getHeroes().toArray(new IdAndName[0]);
        
        service.editHero(
            new Hero(
                heroes[0].getId(),
                "Aiden",
                "Runs a bit fast",
                powers[0].getId()
            )
        );
        
        hero = dao.getHero(heroes[0].getId());
        assertEquals(
            hero.getName(),
            "Aiden",
            "hero's name should be Aiden"
        );
        assertEquals(
            hero.getDescription(),
            "Runs a bit fast",
            "hero's desc should be \"Runs a bit fast\""
        );
    }

    /**
     * Test of deleteHero method, of class SuperheroDataService.
     */
    @Test
    public void testDeleteHero() {
        IdAndName[] heroes;
        IdAndName[] powers;
        
        dao.addPower(
            new Power(
                -1,
                "Super fast running"
            )
        );
        powers = dao.getPowers().toArray(new IdAndName[0]);
        
        dao.addHero(
            new Hero(
                -1,
                "Alice",
                "Runs really fast",
                powers[0].getId()
            )
        );
        heroes = dao.getHeroes().toArray(new IdAndName[0]);
        
        service.deleteHero(heroes[0].getId());
        heroes = dao.getHeroes().toArray(new IdAndName[0]);
        
        assertEquals(
            heroes.length,
            0,
            "should have 0 ids."
        );
    }
}
