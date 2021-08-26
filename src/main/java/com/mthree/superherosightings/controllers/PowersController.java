/*
 * @author Steven Nguyen
 * @email: steven.686295@gmail.com
 * @date: 
 */

package com.mthree.superherosightings.controllers;

import com.mthree.superherosightings.models.IdAndName;
import com.mthree.superherosightings.models.Power;
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
public class PowersController {
    @Autowired
    SuperheroDataService superheroDataService;
    
    /**
     * Gets the data to display on the getPowers page
     * @param model the page's model
     * @return the getPowers page
     */
    @GetMapping("getPowers")
    public String displayPowers(Model model) {
        List<IdAndName> powers;
        
        powers = superheroDataService.getPowers();
        
        model.addAttribute("powers", powers);
        return "getPowers";
    }
    
    /**
     * Gets the data to display on the getPowers page
     * @param id the power's id
     * @param model the page's model
     * @return the getPower page
     */
    @GetMapping("getPower")
    public String displayPower(Integer id, Model model) {
        Power power;
        
        power = superheroDataService.getPower(id);
        model.addAttribute("power", power);
        
        return "getPower";
    }
    
    /**
     * Adds an power
     * @param request the request details
     * @return the getPowers page
     */
    @PostMapping("addPower")
    public String addPower(HttpServletRequest request) {
        String name;
        Power power;
        
        name = request.getParameter("name");
        
        power = new Power(-1, name);
        superheroDataService.addPower(power);
        
        return "redirect:/getPowers";
    }
    
    /**
     * Gets the data to display on the editPower page
     * @param id the power's id
     * @param model the power's model
     * @return 
     */
    @GetMapping("editPower")
    public String displayEditPower(Integer id, Model model) {
        Power power;
        
        power = superheroDataService.getPower(id);
        model.addAttribute("power", power);
        
        return "editPower";
    }
    
    /**
     * Receives the data to edit the power and sends it to the data service
     * @param id the power's id
     * @param request the data to add to the power
     * @return a redirect to the power's info page
     */
    @PostMapping("editPower")
    public String editPower(Integer id, HttpServletRequest request) {
        String name;
        Power power;
        
        name = request.getParameter("name");
        
        power = new Power(id, name);
        superheroDataService.editPower(power);
        return "redirect:/getPower?id=" + id;
    }
    
    /**
     * Deletes an power
     * @param id the power's id
     * @return 
     */
    @GetMapping("deletePower")
    public String deletePower(Integer id) {
        superheroDataService.deletePower(id);
        
        return "redirect:/getPowers";
    }
}