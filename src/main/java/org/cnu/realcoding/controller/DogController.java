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

    @GetMapping("dogs/{name}/{ownerName}/{ownerPhoneNumber}")
    public Dog getDogByAllKey(@PathVariable String name,
                              @PathVariable String ownerName,
                              @PathVariable String ownerPhoneNumber){
        return dogManagementService.getDogByAllKey(name, ownerName, ownerPhoneNumber);
    }

    @GetMapping("/dogs/{ownerPhoneNumber}")
    public Dog getDogByPhoneNum(@RequestParam String PhoneNum) {
        return dogManagementService.getDogByPhoneNum(PhoneNum);
    }

    @PatchMapping
    public void addMedicalRecord(@PathVariable String name,
                                 @PathVariable String ownerName,
                                 @PathVariable String ownerPhoneNumber,
                                 @PathVariable String medicalRecord) {
        dogManagementService.addMedicalRecord(dogManagementService.getDogByAllKey(name, ownerName, ownerPhoneNumber), medicalRecord);
    }

}

