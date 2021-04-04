package org.cnu.realcoding.controller;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.service.DogManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DogController {

    @Autowired
    private DogManagementService dogManagementService;

    @PostMapping("/dogs")
    public void creatDogs(@RequestBody Dog dog) {
        dogManagementService.insertDog(dog);
    }

    @GetMapping("dogs{name}/{ownerName}/{ownerPhoneNumber}")
    public Dog getDogByAllKey(@PathVariable String name,
                              @PathVariable String ownerName,
                              @PathVariable String ownerPhoneNumber){
        return dogManagementService.getDogByAllKey(name, ownerName, ownerPhoneNumber);
    }

}

