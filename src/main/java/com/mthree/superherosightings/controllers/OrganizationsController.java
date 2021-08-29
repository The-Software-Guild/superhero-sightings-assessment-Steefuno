/*
 * @author Steven Nguyen
 * @email: steven.686295@gmail.com
 * @date: 
 */

package com.mthree.superherosightings.controllers;

import com.mthree.superherosightings.models.IdAndName;
import com.mthree.superherosightings.models.Organization;
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
public class OrganizationsController {
    @Autowired
    SuperheroDataService superheroDataService;
    
    /**
     * Gets the data to display on the getOrganizations page
     * @param model the page's model
     * @return the getOrganizations page
     */
    @GetMapping("getOrganizations")
    public String displayOrganizations(Model model) {
        List<IdAndName> organizations;
        
        organizations = superheroDataService.getOrganizations();
        
        model.addAttribute("organizations", organizations);
        return "getOrganizations";
    }
    
    /**
     * Gets the data to display on the getOrganizations page
     * @param id the organization's id
     * @param model the page's model
     * @param redirectAttributes model to add redirect data into
     * @return the getOrganization page
     */
    @GetMapping("getOrganization")
    public String displayOrganization(Integer id, Model model, RedirectAttributes redirectAttributes) {
        Organization organization;
        
        try {
            organization = superheroDataService.getOrganization(id);
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to get organization: Invalid ID."
            });
            return "redirect:/getOrganizations";
        }
        model.addAttribute("organization", organization);
        
        return "getOrganization";
    }
    
    /**
     * Adds an organization
     * @param request the request details
     * @param redirectAttributes model to add redirect data into
     * @return the getOrganizations page
     */
    @PostMapping("addOrganization")
    public String addOrganization(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String name, description;
        int locationId;
        Organization organization;
        
        name = request.getParameter("name");
        description = request.getParameter("description");
        try {
            locationId = Integer.parseInt(request.getParameter("locationId"));
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to add organization: Invalid location ID."
            });
            return "redirect:/getOrganizations";
        }
        
        organization = new Organization(-1, name, description, locationId);
        
        try {
            superheroDataService.addOrganization(organization);
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to add organization: Invalid Location ID"
            });
            return "redirect:/getOrganizations";
        }
        
        return "redirect:/getOrganizations";
    }
    
    /**
     * Gets the data to display on the editOrganization page
     * @param id the organization's id
     * @param model the organization's model
     * @param redirectAttributes model to add redirect data into
     * @return 
     */
    @GetMapping("editOrganization")
    public String displayEditOrganization(Integer id, Model model, RedirectAttributes redirectAttributes) {
        Organization organization;
        
        try {
            organization = superheroDataService.getOrganization(id);
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to get organization: Invalid ID"
            });
            return "redirect:/getOrganizations";
        }
        model.addAttribute("organization", organization);
        
        return "editOrganization";
    }
    
    /**
     * Receives the data to edit the organization and sends it to the data service
     * @param request the data to add to the organization
     * @param redirectAttributes model to add redirect data into
     * @return a redirect to the organization's info page
     */
    @PostMapping("editOrganization")
    public String editOrganization(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String name, description;
        int id, locationId;
        Organization organization;
        
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to edit organization: Invalid ID."
            });
            return "redirect:/getOrganizations";
        }
        name = request.getParameter("name");
        description = request.getParameter("description");
        try {
            locationId = Integer.parseInt(request.getParameter("locationId"));
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to edit organization: Invalid location ID."
            });
            return "redirect:/getOrganization?id=" + id;
        }
        
        organization = new Organization(id, name, description, locationId);
        try {
            superheroDataService.editOrganization(organization);
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to edit organization: Invalid ID or Location ID"
            });
            return "redirect:/getOrganization?id=" + id;
        }
        return "redirect:/getOrganization?id=" + id;
    }
    
    /**
     * Deletes an organization
     * @param id the organization's id
     * @param redirectAttributes model to add redirect data into
     * @return 
     */
    @GetMapping("deleteOrganization")
    public String deleteOrganization(Integer id, RedirectAttributes redirectAttributes) {
        try {
            superheroDataService.deleteOrganization(id);
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("notifications", new String[]{
                "Failed to delete organization: Invalid ID"
            });
            return "redirect:/getOrganizations";
        }
        
        return "redirect:/getOrganizations";
    }
}