package org.cnu.realcoding.controller;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.service.DogManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class DogController {

    @Autowired
    private DogManagementService dogManagementService;

    @PostMapping("/dogs")
    public void creatDogs(@RequestBody Dog dog) {
        dogManagementService.insertDog(dog);
    }
    //---------------------------------------------------------------------------------------------------------
    // 이름 조회
    @GetMapping("/dogs")
    public List<Dog> getDogByName(@RequestParam String name){
        return dogManagementService.getDogByName(name);
    }

    // 주인 이름 조회
    @GetMapping("/dogs/{ownerName}")
    public List<Dog> getDogByOwnerCNTR(@PathVariable String ownerName) {
        return dogManagementService.getDogByOwnerSRVC(ownerName);
    }

    // 폰 넘버 조회
    @GetMapping
    public List<Dog> getDogByPhoneNum(@RequestParam String PhoneNum) {
        return dogManagementService.getDogByPhoneNum(PhoneNum);
    }

    // allkey 조회
    @GetMapping("/dogs/{name}/{ownerName}/{ownerPhoneNumber}")
    public Dog getDogByAllKey(@PathVariable String name,
                              @PathVariable String ownerName,
                              @PathVariable String ownerPhoneNumber){
        return dogManagementService.getDogByAllKey(name, ownerName, ownerPhoneNumber);
    }

    //---------------------------------------------------------------------------------------------------------
    @PutMapping("/dogs")
    public void putDogCNTR(@PathVariable String name,
                           @PathVariable String ownerName,
                           @PathVariable String ownerPhoneNumber,
                           @RequestBody Dog dog) {
        dogManagementService.putDogSRVC(name, ownerName, ownerPhoneNumber, dog);
    }

    //---------------------------------------------------------------------------------------------------------
    @PatchMapping
    public void addMedicalRecord(@RequestParam String name,
                                 @RequestParam String ownerName,
                                 @RequestParam String ownerPhoneNumber,
                                 @RequestParam String medicalRecord) {
        dogManagementService.addMedicalRecord(dogManagementService.getDogByAllKey(name, ownerName, ownerPhoneNumber), medicalRecord);
    }

    // 견종 변경
    @PatchMapping("/dogs/{name}/{ownerName}/{ownerPhoneNumber}/{changeKind}")
    public void updateDogKind(@PathVariable String name, @PathVariable String ownerName, @PathVariable String ownerPhoneNumber,  @PathVariable String changeKind) {
        dogManagementService.updateDogKind(name, ownerName, ownerPhoneNumber, changeKind);
    }
}