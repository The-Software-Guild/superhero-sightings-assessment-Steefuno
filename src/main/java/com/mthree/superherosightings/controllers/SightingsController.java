/*
 * @author Steven Nguyen
 * @email: steven.686295@gmail.com
 * @date: 
 */

package com.mthree.superherosightings.controllers;

import com.mthree.superherosightings.services.SuperheroDataService;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    
}
