/*
 * @author Steven Nguyen
 * @email: steven.686295@gmail.com
 * @date: 
 */

package com.mthree.superherosightings.controllers;

import com.mthree.superherosightings.models.IdAndName;
import com.mthree.superherosightings.models.Location;
import com.mthree.superherosightings.services.SuperheroDataService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Steven
 */
@RestController
@RequestMapping("/api/super_hero_sighting")
public class LocationsController {
    @Autowired
    SuperheroDataService superheroDataService;
    
    /**
     * Gets the data to display on the getLocations page
     * @param model the page's model
     * @return the getLocations page
     */
    @GetMapping("getLocations")
    public String displayLocations(Model model) {
        List<IdAndName> locations;
        
        locations = superheroDataService.getLocations();
        
        model.addAttribute("locations", locations);
        return "getLocations";
    }
    
    /**
     * Gets the data to display on the getLocation page
     * @param id the location's id
     * @param model the page's model
     * @return the getLocation page
     */
    @GetMapping("getLocation")
    public String displayLocation(Integer id, Model model) {
        Location location;
        
        location = superheroDataService.getLocation(id);
        model.addAttribute("location", location);
        
        return "getLocation";
    }
    
    /**
     * Adds a location
     * @param request the request details
     * @return the getLocations page
     */
    @PostMapping("addLocation")
    public String addLocation(HttpServletRequest request) {
        String name, description, address;
        double latitude, longitude;
        Location location;
        
        name = request.getParameter("name");
        description = request.getParameter("description");
        address = request.getParameter("address");
        latitude = Double.parseDouble(request.getParameter("latitude"));
        longitude = Double.parseDouble(request.getParameter("latitude"));
        
        location = new Location(-1, name, description, address, latitude, longitude);
        superheroDataService.addLocation(location);
        
        return "redirect:/getLocations";
    }
    
    /**
     * Gets the data to display on the editLocation page
     * @param id the location's id
     * @param model the location's model
     * @return 
     */
    @GetMapping("editLocation")
    public String displayEditLocation(Integer id, Model model) {
        Location location;
        
        location = superheroDataService.getLocation(id);
        model.addAttribute("location", location);
        
        return "editLocation";
    }
    
    /**
     * Receives the data to edit the location and sends it to the data service
     * @param id the location's id
     * @param request the data to add to the location
     * @return a redirect to the location's info page
     */
    @PostMapping("editLocation")
    public String editLocation(Integer id, HttpServletRequest request) {
        String name, description, address;
        double latitude, longitude;
        Location location;
        
        name = request.getParameter("name");
        description = request.getParameter("description");
        address = request.getParameter("address");
        latitude = Double.parseDouble(request.getParameter("latitude"));
        longitude = Double.parseDouble(request.getParameter("latitude"));
        
        location = new Location(id, name, description, address, latitude, longitude);
        superheroDataService.editLocation(location);
        return "redirect:/getLocation?id=" + id;
    }
    
    /**
     * Deletes a location
     * @param id the location's id
     * @return 
     */
    @GetMapping("deleteLocation")
    public String deleteLocation(Integer id) {
        superheroDataService.deleteLocation(id);
        
        return "redirect:/getLocations";
    }
}
