/*
 * @author Steven Nguyen
 * @email: steven.686295@gmail.com
 * @date: 
 */

package com.mthree.superherosightings.models;

/**
 * 
 * @author Steven
 */
public class Affiliation {
    private int id;
    private int heroId;
    private int organizationId;
    
    public Affiliation(int id, int heroId, int organizationId) {
        this.id = id;
        this.heroId = heroId;
        this.organizationId = organizationId;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    
    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }
    public int getHeroId() {
        return heroId;
    }
    
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }
    public int getOrganizationId() {
        return organizationId;
    }
}
