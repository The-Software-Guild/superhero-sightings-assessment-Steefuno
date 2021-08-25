/*
 * @author Steven Nguyen
 * @email: steven.686295@gmail.com
 * @date: 
 */

package com.mthree.superherosightings.controllers;

import com.mthree.superherosightings.services.SuperheroDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 
 * @author Steven
 */
@Controller
public class SuperheroSightingsController {
    @Autowired
    SuperheroDataService superheroDataService;
    
    
}
