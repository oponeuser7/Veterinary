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

    @GetMapping("/dogs/{ownerName}")
    public List<Dog> getDogByOwnerCNTR(@PathVariable String ownerName) {
        return dogManagementService.getDogByOwnerSRVC(ownerName);
    }

    @PutMapping("/dogs")
    public void putDogCNTR(@RequestBody Dog dog) {
        dogManagementService.putDogSRVC(dog);
    }

    @PostMapping("/dogs")
    public void creatDogs(@RequestBody Dog dog) {
        dogManagementService.insertDog(dog);
    }

    @GetMapping("/dogs/{name}/{ownerName}/{ownerPhoneNumber}")
    public Dog getDogByAllKey(@PathVariable String name,
                              @PathVariable String ownerName,
                              @PathVariable String ownerPhoneNumber){
        return dogManagementService.getDogByAllKey(name, ownerName, ownerPhoneNumber);
    }

    @GetMapping
    public Dog getDogByPhoneNum(@RequestParam String PhoneNum) {
        return dogManagementService.getDogByPhoneNum(PhoneNum);
    }

    @PatchMapping
    public void addMedicalRecord(@RequestParam String name,
                                 @RequestParam String ownerName,
                                 @RequestParam String ownerPhoneNumber,
                                 @RequestParam String medicalRecord) {
        dogManagementService.addMedicalRecord(dogManagementService.getDogByAllKey(name, ownerName, ownerPhoneNumber), medicalRecord);
    }

    // 이름 조회
    @GetMapping("/dogs/{name}")
    public List<Dog> getDogByName(@PathVariable String name){
        return dogManagementService.getDogByName(name);
    }

    // 견종 변경
    @PatchMapping("/dogs/{name}/{ownerName}/{ownerPhoneNumber}/{changeKind}")
    public void updateDogKind(@PathVariable String name, @PathVariable String ownerName, @PathVariable String ownerPhoneNumber,  @PathVariable String changeKind){
        dogManagementService.updateDogKind(name, ownerName, ownerPhoneNumber, changeKind);
    }


}

