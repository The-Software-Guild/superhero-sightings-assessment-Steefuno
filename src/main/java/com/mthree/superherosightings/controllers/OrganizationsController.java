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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
     * @return the getOrganization page
     */
    @GetMapping("getOrganization")
    public String displayOrganization(Integer id, Model model) {
        Organization organization;
        
        organization = superheroDataService.getOrganization(id);
        model.addAttribute("organization", organization);
        
        return "getOrganization";
    }
    
    /**
     * Adds an organization
     * @param request the request details
     * @return the getOrganizations page
     */
    @PostMapping("addOrganization")
    public String addOrganization(HttpServletRequest request) {
        String name, description;
        int locationId;
        Organization organization;
        
        name = request.getParameter("name");
        description = request.getParameter("description");
        locationId = Integer.parseInt(request.getParameter("locationId"));
        
        organization = new Organization(-1, name, description, locationId);
        superheroDataService.addOrganization(organization);
        
        return "redirect:/getOrganizations";
    }
    
    /**
     * Gets the data to display on the editOrganization page
     * @param id the organization's id
     * @param model the organization's model
     * @return 
     */
    @GetMapping("editOrganization")
    public String displayEditOrganization(Integer id, Model model) {
        Organization organization;
        
        organization = superheroDataService.getOrganization(id);
        model.addAttribute("organization", organization);
        
        return "editOrganization";
    }
    
    /**
     * Receives the data to edit the organization and sends it to the data service
     * @param request the data to add to the organization
     * @return a redirect to the organization's info page
     */
    @PostMapping("editOrganization")
    public String editOrganization(HttpServletRequest request) {
        String name, description;
        int id, locationId;
        Organization organization;
        
        id = Integer.parseInt(request.getParameter("id"));
        name = request.getParameter("name");
        description = request.getParameter("description");
        locationId = Integer.parseInt(request.getParameter("locationId"));
        
        organization = new Organization(id, name, description, locationId);
        superheroDataService.editOrganization(organization);
        return "redirect:/getOrganization?id=" + id;
    }
    
    /**
     * Deletes an organization
     * @param id the organization's id
     * @return 
     */
    @GetMapping("deleteOrganization")
    public String deleteOrganization(Integer id) {
        superheroDataService.deleteOrganization(id);
        
        return "redirect:/getOrganizations";
    }
}