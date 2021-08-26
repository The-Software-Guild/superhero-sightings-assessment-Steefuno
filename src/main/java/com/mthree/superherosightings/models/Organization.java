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
public class Organization {
    private int id;
    private String name;
    private String description;
    private int locationId;
    
    public Organization(int id, String name, String description, int locationId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.locationId = locationId;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    public int getLocationId() {
        return locationId;
    }
}
