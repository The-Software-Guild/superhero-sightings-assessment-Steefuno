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
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 
 * @author Steven
 */
@Controller
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
     * @param redirectAttributes model to add redirect data into
     * @return the getLocation page
     */
    @GetMapping("getLocation")
    public String displayLocation(Integer id, Model model, RedirectAttributes redirectAttributes) {
        Location location;
        
        try {
            location = superheroDataService.getLocation(id);
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to get location: Invalid ID."
            });
            return "redirect:/getLocations";
        }
        model.addAttribute("location", location);
        
        return "getLocation";
    }
    
    /**
     * Adds a location
     * @param request the request details
     * @param redirectAttributes model to add redirect data into
     * @return the getLocations page
     */
    @PostMapping("addLocation")
    public String addLocation(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String name, description, address;
        double latitude, longitude;
        Location location;
        
        name = request.getParameter("name");
        description = request.getParameter("description");
        address = request.getParameter("address");
        try {
            latitude = Double.parseDouble(request.getParameter("latitude"));
            longitude = Double.parseDouble(request.getParameter("latitude"));
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to add location: Latitude and/or Longitude are invalid."
            });
            return "redirect:/getLocations";
        }
        
        location = new Location(-1, name, description, address, latitude, longitude);
        superheroDataService.addLocation(location);
        
        return "redirect:/getLocations";
    }
    
    /**
     * Gets the data to display on the editLocation page
     * @param id the location's id
     * @param model the location's model
     * @param redirectAttributes model to add redirect data into
     * @return 
     */
    @GetMapping("editLocation")
    public String displayEditLocation(Integer id, Model model, RedirectAttributes redirectAttributes) {
        Location location;
        
        try {
            location = superheroDataService.getLocation(id);
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to get location: Invalid ID."
            });
            return "redirect:/getLocations";
        }
        model.addAttribute("location", location);
        
        return "editLocation";
    }
    
    /**
     * Receives the data to edit the location and sends it to the data service
     * @param request the data to add to the location
     * @param redirectAttributes model to add redirect data into
     * @return a redirect to the location's info page
     */
    @PostMapping("editLocation")
    public String editLocation(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String name, description, address;
        int id;
        double latitude, longitude;
        Location location;
        
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to get location: Invalid ID."
            });
            return "redirect:/getLocations";
        }
        name = request.getParameter("name");
        description = request.getParameter("description");
        address = request.getParameter("address");
        try {
            latitude = Double.parseDouble(request.getParameter("latitude"));
            longitude = Double.parseDouble(request.getParameter("latitude"));
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to get location: Invalid Latitude and Longitude."
            });
            return "redirect:/editLocation?id=" + id;
        }
        
        location = new Location(id, name, description, address, latitude, longitude);
        superheroDataService.editLocation(location);
        return "redirect:/getLocation?id=" + id;
    }
    
    /**
     * Deletes a location
     * @param id the location's id
     * @param redirectAttributes model to add redirect data into
     * @return a redirect to getLocations
     */
    @GetMapping("deleteLocation")
    public String deleteLocation(Integer id, RedirectAttributes redirectAttributes) {
        try {
            superheroDataService.deleteLocation(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to get location: Invalid Latitude and Longitude."
            });
            return "redirect:/getLocations";
        }
        
        return "redirect:/getLocations";
    }
}
