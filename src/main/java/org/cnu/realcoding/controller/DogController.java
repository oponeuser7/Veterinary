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

    @GetMapping("/dogs")
    public List<Dog> getDogByOwnerCNTR(@PathVariable String ownerName) {
        return dogManagementService.getDogByOwnerSRVC(ownerName);
    }

    @PutMapping
    public void putDogCNTR(@RequestBody Dog dog) {
        dogManagementService.putDogSRVC(dog);
    }
}
