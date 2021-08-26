/*
 * @author Steven Nguyen
 * @email: steven.686295@gmail.com
 * @date: 
 */

package com.mthree.superherosightings.models;

import java.sql.Timestamp;

/**
 * 
 * @author Steven
 */
public class Sighting {
    private int id;
    private int heroId;
    private int locationId;
    private Timestamp time;
    
    public Sighting(int id, int heroId, int locationId, Timestamp time) {
        this.id = id;
        this.heroId = heroId;
        this.locationId = locationId;
        this.time = time;
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
    
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    public int getLocationId() {
        return locationId;
    }
    
    public void setTime(Timestamp time) {
        this.time = time;
    }
    public Timestamp getTime() {
        return time;
    }
}
