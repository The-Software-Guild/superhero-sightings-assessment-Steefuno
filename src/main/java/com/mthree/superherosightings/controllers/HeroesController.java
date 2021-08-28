/*
 * @author Steven Nguyen
 * @email: steven.686295@gmail.com
 * @date: 
 */

package com.mthree.superherosightings.controllers;

import com.mthree.superherosightings.models.Affiliation;
import com.mthree.superherosightings.models.Hero;
import com.mthree.superherosightings.models.IdAndName;
import com.mthree.superherosightings.services.SuperheroDataService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 
 * @author Steven
 */
@Controller
public class HeroesController {
    @Autowired
    SuperheroDataService superheroDataService;
    
    /**
     * Gets the data to display on the getHeroes page
     * @param model the page's model
     * @return the getHeroes page
     */
    @GetMapping("/getHeroes")
    public String displayHeroes(Model model) {
        List<IdAndName> heroes;
        
        heroes = superheroDataService.getHeroes();
        
        model.addAttribute("heroes", heroes);
        return "/getHeroes";
    }
    
    /**
     * Gets the data to display on the getHero page
     * @param id the hero's id
     * @param model the page's model
     * @return the getHero page
     */
    @GetMapping("/getHero")
    public String displayHero(Integer id, Model model) {
        Hero hero;
        List<Affiliation> affiliations;
        
        hero = superheroDataService.getHero(id);
        affiliations = superheroDataService.getHeroAffiliations(id);
        model.addAttribute("hero", hero);
        model.addAttribute("affiliations", affiliations);
        
        return "/getHero";
    }
    
    @GetMapping("/addHero")
    public String displayAddHero(Model model) {
        return "/addHero";
    }
    
    /**
     * Adds a hero
     * @param request the request details
     * @return the getHeroes page
     */
    @PostMapping("/addHero")
    public String addHero(HttpServletRequest request) {
        String name, description;
        int powerId;
        Hero hero;
        
        name = request.getParameter("name");
        description = request.getParameter("description");
        powerId = Integer.parseInt(request.getParameter("powerId"));
        
        hero = new Hero(-1, name, description, powerId);
        superheroDataService.addHero(hero);
        
        return "redirect:/getHeroes";
    }
    
    /**
     * Gets the data to display on the editHero page
     * @param id the hero's id
     * @param model the page's model
     * @return 
     */
    @GetMapping("/editHero")
    public String displayEditHero(Integer id, Model model) {
        Hero hero;
        
        hero = superheroDataService.getHero(id);
        model.addAttribute("hero", hero);
        
        return "/editHero";
    }
    
    /**
     * Receives the data to edit the hero and sends it to the data service
     * @param request the data to add to the hero along with the hero id
     * @return a redirect to the hero's info page
     */
    @PostMapping("/editHero")
    public String editHero(HttpServletRequest request) {
        String name, description;
        int id, powerId;
        Hero hero;
        
        id = Integer.parseInt(request.getParameter("id"));
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
    @GetMapping("/deleteHero")
    public String deleteHero(Integer id) {
        superheroDataService.deleteHero(id);
        
        return "redirect:/getHeroes";
    }
}
