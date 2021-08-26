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
public class Location {
    private int locationId;
    private String name;
    private String description;
    private String address;
    private double latitude;
    private double longitude;
    
    public Location(int locationId, String name, String description, String address, double latitude, double longitude) {
        this.locationId = locationId;
        this.name = name;
        this.description = description;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    public int getLocationId() {
        return locationId;
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
    
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }
    
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLatitude() {
        return latitude;
    }
    
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getLongitude() {
        return longitude;
    }
}
