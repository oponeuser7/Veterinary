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
    public void creatDogs(@RequestBody Dog dog){
        dogManagementService.insertDog(dog);
    }

    @GetMapping("/dogs")
    public List<Dog> getAllDogs(){

        return dogManagementService.getDogs();
    }
    @GetMapping("/dogs")
    public List<Dog> getNameDogs(String name){

        return dogManagementService.getNameDogs(name);
    }

    // localhost:8009/dogs?name=ian
    // localhost:8009/dogs/ian
    // @GetMapping("/dogs{name}")
    @GetMapping("/dogs")
    public Dog getDogByName(@RequestParam String name){
        return dogManagementService.getDogByName(name);
    }
}
