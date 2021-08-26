/*
 * @author Steven Nguyen
 * @email: steven.686295@gmail.com
 * @date: 
 */

package com.mthree.superherosightings.controllers;

import com.mthree.superherosightings.models.IdAndName;
import com.mthree.superherosightings.models.Sighting;
import com.mthree.superherosightings.services.SuperheroDataService;
import java.sql.Timestamp;
import java.time.Instant;
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
public class SightingsController {
    @Autowired
    SuperheroDataService superheroDataService;
    
    /**
     * Gets the data to display on the getSightings page
     * @param model the page's model
     * @return the getSightings page
     */
    @GetMapping("getSightings")
    public String displaySightings(Model model) {
        List<IdAndName> sightings;
        
        sightings = superheroDataService.getSightings();
        
        model.addAttribute("sightings", sightings);
        return "getSightings";
    }
    
    /**
     * Gets the data to display on the getSightings page
     * @param id the sighting's id
     * @param model the page's model
     * @return the getSighting page
     */
    @GetMapping("getSighting")
    public String displaySighting(Integer id, Model model) {
        Sighting sighting;
        
        sighting = superheroDataService.getSighting(id);
        model.addAttribute("sighting", sighting);
        
        return "getSighting";
    }
    
    /**
     * Adds an sighting
     * @param request the request details
     * @return the getSightings page
     */
    @PostMapping("addSighting")
    public String addSighting(HttpServletRequest request) {
        int heroId, locationId;
        Timestamp time;
        Sighting sighting;
        
        heroId = Integer.parseInt(request.getParameter("heroId"));
        locationId = Integer.parseInt(request.getParameter("locationId"));
        time = Timestamp.from(Instant.now());
        
        sighting = new Sighting(-1, heroId, locationId, time);
        superheroDataService.addSighting(sighting);
        
        return "redirect:/getSightings";
    }
    
    /**
     * Gets the data to display on the editSighting page
     * @param id the sighting's id
     * @param model the sighting's model
     * @return 
     */
    @GetMapping("editSighting")
    public String displayEditSighting(Integer id, Model model) {
        Sighting sighting;
        
        sighting = superheroDataService.getSighting(id);
        model.addAttribute("sighting", sighting);
        
        return "editSighting";
    }
    
    /**
     * Receives the data to edit the sighting and sends it to the data service
     * @param id the sighting's id
     * @param request the data to add to the sighting
     * @return a redirect to the sighting's info page
     */
    @PostMapping("editSighting")
    public String editSighting(Integer id, HttpServletRequest request) {
        int heroId, locationId;
        Timestamp time;
        Sighting sighting;
        
        heroId = Integer.parseInt(request.getParameter("heroId"));
        locationId = Integer.parseInt(request.getParameter("locationId"));
        time = Timestamp.from(Instant.now());
        
        sighting = new Sighting(id, heroId, locationId, time);
        superheroDataService.editSighting(sighting);
        return "redirect:/getSighting?id=" + id;
    }
    
    /**
     * Deletes an sighting
     * @param id the sighting's id
     * @return 
     */
    @GetMapping("deleteSighting")
    public String deleteSighting(Integer id) {
        superheroDataService.deleteSighting(id);
        
        return "redirect:/getSightings";
    }
}