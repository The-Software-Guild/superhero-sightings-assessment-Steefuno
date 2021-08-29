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
     * @param redirectAttributes model to add redirect data into
     * @return the getHero page
     */
    @GetMapping("/getHero")
    public String displayHero(Integer id, Model model, RedirectAttributes redirectAttributes) {
        Hero hero;
        List<Affiliation> affiliations;
        
        try {
            hero = superheroDataService.getHero(id);
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to find hero: Make sure the ID is valid."
            });
            return "redirect:/getHeroes";
        }
        
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
     * @param redirectAttributes model to add redirect data into
     * @return the getHeroes page
     */
    @PostMapping("/addHero")
    public String addHero(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String name, description;
        int powerId;
        Hero hero;
        
        name = request.getParameter("name");
        description = request.getParameter("description");
        
        try {
            powerId = Integer.parseInt(request.getParameter("powerId"));
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to add hero: Make sure the Power ID is valid."
            });
            return "redirect:/addHero";
        }
        
        hero = new Hero(-1, name, description, powerId);
        try {
            superheroDataService.addHero(hero);
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to add hero: Make sure the IDs are valid."
            });
            return "redirect:/addHero";
        }
        
        return "redirect:/getHeroes";
    }
    
    /**
     * Gets the data to display on the editHero page
     * @param id the hero's id
     * @param model the page's model
     * @param redirectAttributes model to add redirect data into
     * @return 
     */
    @GetMapping("/editHero")
    public String displayEditHero(Integer id, Model model, RedirectAttributes redirectAttributes) {
        Hero hero;
        
        try {
            hero = superheroDataService.getHero(id);
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to find hero: Make sure the ID is valid."
            });
            return "redirect:/getHeroes";
        }
        model.addAttribute("hero", hero);
        
        return "/editHero";
    }
    
    /**
     * Receives the data to edit the hero and sends it to the data service
     * @param request the data to add to the hero along with the hero id
     * @param redirectAttributes model to add redirect data into
     * @return a redirect to the hero's info page
     */
    @PostMapping("/editHero")
    public String editHero(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String name, description;
        int id, powerId;
        Hero hero;
        
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to edit hero: Make sure the ID is valid."
            });
            return "redirect:/getHeroes";
        }
        name = request.getParameter("name");
        description = request.getParameter("description");
        try {
            powerId = Integer.parseInt(request.getParameter("powerId"));
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to edit hero: Make sure the Power ID is valid."
            });
            return "redirect:/getHero?id=" + id;
        }
        
        hero = new Hero(id, name, description, powerId);
        
        try {
            superheroDataService.editHero(hero);
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to edit hero: Make sure the Power and Hero IDs are valid."
            });
            return "redirect:/getHero?id=" + id;
        }
        return "redirect:/getHero?id=" + id;
    }
    
    /**
     * Deletes a hero
     * @param id the hero's id
     * @param redirectAttributes model to add redirect data into
     * @return 
     */
    @GetMapping("/deleteHero")
    public String deleteHero(Integer id, RedirectAttributes redirectAttributes) {
        try {
            superheroDataService.deleteHero(id);
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to delete hero: Make sure the ID is valid."
            });
            return "redirect:/getHeroes";
        }
        
        return "redirect:/getHeroes";
    }
}
