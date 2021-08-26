/*
 * @author Steven Nguyen
 * @email: steven.686295@gmail.com
 * @date: 
 */

package com.mthree.superherosightings.controllers;

import com.mthree.superherosightings.models.Hero;
import com.mthree.superherosightings.models.IdAndName;
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
public class HeroesController {
    @Autowired
    SuperheroDataService superheroDataService;
    
    /**
     * Gets the data to display on the getHeroes page
     * @param model the page's model
     * @return the getHeroes page
     */
    @GetMapping("getHeroes")
    public String displayHeroes(Model model) {
        List<IdAndName> heroes;
        
        heroes = superheroDataService.getHeroes();
        
        model.addAttribute("heroes", heroes);
        return "getHeroes";
    }
    
    /**
     * Gets the data to display on the getHero page
     * @param id the hero's id
     * @param model the page's model
     * @return the getHero page
     */
    @GetMapping("getHero")
    public String displayHero(Integer id, Model model) {
        Hero hero;
        
        hero = superheroDataService.getHero(id);
        model.addAttribute("hero", hero);
        
        return "getHero";
    }
    
    /**
     * Adds a hero
     * @param request the request details
     * @return the getHeroes page
     */
    @PostMapping("addHero")
    public String addHero(HttpServletRequest request) {
        String name, description, powerName;
        Hero hero;
        
        name = request.getParameter("name");
        description = request.getParameter("description");
        powerName = request.getParameter("power");
        
        hero = new Hero(-1, name, description, powerName);
        superheroDataService.addHero(hero);
        
        return "redirect:/getHeroes";
    }
    
    /**
     * Gets the data to display on the editHero page
     * @param id the hero's id
     * @param model the page's model
     * @return 
     */
    @GetMapping("editHero")
    public String displayEditHero(Integer id, Model model) {
        Hero hero;
        
        hero = superheroDataService.getHero(id);
        model.addAttribute("hero", hero);
        
        return "editHero";
    }
    
    /**
     * Receives the data to edit the hero and sends it to the data service
     * @param id the hero's id
     * @param request the data to add to the hero along with the hero id
     * @return a redirect to the hero's info page
     */
    @PostMapping("editHero")
    public String editHero(Integer id, HttpServletRequest request) {
        String name, description;
        int powerId;
        Hero hero;
        
        name = request.getParameter("name");
        description = request.getParameter("description");
        powerId = Integer.parseInt(request.getParameter("powerId"));
        
        hero = new Hero(id, name, description, powerId);
        superheroDataService.editHero(hero);
        return "redirect:/getHero?id=" + id;
    }
    
    /**
     * Deletes a hero
     * @param id the hero's id
     * @return 
     */
    @GetMapping("deleteHero")
    public String deleteHero(Integer id) {
        superheroDataService.deleteHero(id);
        
        return "redirect:/getHeroes";
    }
}
