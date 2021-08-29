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
     * @param redirectAttributes model to insert redirect data into
     * @return the getPower page
     */
    @GetMapping("getPower")
    public String displayPower(Integer id, Model model, RedirectAttributes redirectAttributes) {
        Power power;
        
        try {
            power = superheroDataService.getPower(id);
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to find power: Make sure the ID is valid."
            });
            return "redirect:/getPowers";
        }
        model.addAttribute("power", power);
        
        return "getPower";
    }
    
    /**
     * Gets the data to display the addPower page
     * @param model the page's model
     * @return the addPower page
     */
    @GetMapping("addPower")
    public String displayAddPower(Model model) {
        return "addPower";
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
     * @param redirectAttributes model to insert redirect data into
     * @return 
     */
    @GetMapping("editPower")
    public String displayEditPower(Integer id, Model model, RedirectAttributes redirectAttributes) {
        Power power;
        
        try {
            power = superheroDataService.getPower(id);
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to find power: Make sure the ID is valid."
            });
            return "redirect:/getPowers";
        }
        model.addAttribute("power", power);
        
        return "editPower";
    }
    
    /**
     * Receives the data to edit the power and sends it to the data service
     * @param request the data to add to the power
     * @param redirectAttributes model to add redirect data into
     * @return a redirect to the power's info page
     */
    @PostMapping("editPower")
    public String editPower(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String name;
        int id;
        Power power;
        
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to edit power: Invalid ID."
            });
            return "redirect:/getPowers";
        }
        name = request.getParameter("name");
        
        power = new Power(id, name);
        superheroDataService.editPower(power);
        return "redirect:/getPower?id=" + id;
    }
    
    /**
     * Deletes an power
     * @param id the power's id
     * @param redirectAttributes model to insert redirect data into
     * @return 
     */
    @GetMapping("deletePower")
    public String deletePower(Integer id, RedirectAttributes redirectAttributes) {
        try {
            superheroDataService.deletePower(id);
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to delete power: Power cannot delete with existing heroes."
            });
            return "redirect:/getPowers";
        }
        
        return "redirect:/getPowers";
    }
}