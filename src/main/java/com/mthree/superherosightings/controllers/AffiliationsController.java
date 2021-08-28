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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
     * @return the getHero page
     */
    @PostMapping("/addAffiliation")
    public String addAffiliation(HttpServletRequest request) {
        int heroId, organizationId;
        
        heroId = Integer.parseInt(request.getParameter("heroId"));
        organizationId = Integer.parseInt(request.getParameter("organizationId"));
        
        superheroDataService.addAffiliation(heroId, organizationId);
        
        return "redirect:/getHero?id=" + heroId;
    }
    
    /**
     * Deletes an affiliation
     * @param id the affiliation's id
     * @return 
     */
    @GetMapping("/deleteAffiliation")
    public String deleteAffiliation(Integer id) {
        Affiliation affiliation;
        
        affiliation = superheroDataService.getAffiliation(id);
        superheroDataService.deleteAffiliation(id);
        
        return "redirect:/getHero?id=" + affiliation.getHeroId();
    }
}
