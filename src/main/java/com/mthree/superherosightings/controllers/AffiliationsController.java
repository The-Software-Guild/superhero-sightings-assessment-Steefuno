/*
 * @author Steven Nguyen
 * @email: steven.686295@gmail.com
 * @date: 
 */

package com.mthree.superherosightings.controllers;

import com.mthree.superherosightings.models.Affiliation;
import com.mthree.superherosightings.services.SuperheroDataService;
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
public class AffiliationsController {
    @Autowired
    SuperheroDataService superheroDataService;
    
    @GetMapping("/addAffiliation")
    public String displayAddHero(Integer heroId, Model model) {
        model.addAttribute("heroId", heroId);
        
        return "/addAffiliation";
    }
    
    /**
     * Adds an affiliation
     * @param request the request details
     * @param redirectAttributes model to add redirect data into
     * @return the getHero page
     */
    @PostMapping("/addAffiliation")
    public String addAffiliation(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        int heroId, organizationId;
        
        try {
            heroId = Integer.parseInt(request.getParameter("heroId"));
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to add affiliation: Invalid Hero ID."
            });
            return "redirect:/getHeroes";
        }
        try {
            organizationId = Integer.parseInt(request.getParameter("organizationId"));
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to add affiliation: Invalid Organization ID."
            });
            return "redirect:/addAffiliation?heroId=" + heroId;
        }
        try {
            superheroDataService.addAffiliation(heroId, organizationId);
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to add affiliation: Hero and/or Organization does not exist."
            });
            return "redirect:/addAffiliation?heroId=" + heroId;
        }
        
        return "redirect:/getHero?id=" + heroId;
    }
    
    /**
     * Deletes an affiliation
     * @param id the affiliation's id
     * @param redirectAttributes model to add redirect data into
     * @return 
     */
    @GetMapping("/deleteAffiliation")
    public String deleteAffiliation(Integer id, RedirectAttributes redirectAttributes) {
        Affiliation affiliation;
        
        try {
            affiliation = superheroDataService.getAffiliation(id);
            superheroDataService.deleteAffiliation(id);
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to delete affiliation: Affiliation does not exist."
            });
            return "redirect:/getHeroes";
        }
        
        return "redirect:/getHero?id=" + affiliation.getHeroId();
    }
}
