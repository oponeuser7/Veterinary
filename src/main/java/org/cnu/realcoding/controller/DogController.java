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

    // 강아지 등록
    @PostMapping("/dogs")
    public void creatDogs(@RequestBody Dog dog) {
        dogManagementService.insertDog(dog);
    }

    //---------------------------------------------------------------------------------------------------------
    // 이름 조회
    @GetMapping("/dogs/name")
    public List<Dog> getDogByName(@RequestParam String name){
        return dogManagementService.getDogByName(name);
    }

    // 주인 이름 조회
    @GetMapping("/dogs/owner-name")
    public List<Dog> getDogByOwnerCNTR(@RequestParam String ownerName) {
        return dogManagementService.getDogByOwnerSRVC(ownerName);
    }

    // 폰 넘버 조회
    @GetMapping("/dogs/owner-phone-number")
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
    // 강아지 덮어쓰기
    @PutMapping("/dogs/{name}/{ownerName}/{ownerPhoneNumber}")
    public void putDogCNTR(@PathVariable String name,
                           @PathVariable String ownerName,
                           @PathVariable String ownerPhoneNumber,
                           @RequestBody Dog dog) {
        dogManagementService.putDogSRVC(name, ownerName, ownerPhoneNumber, dog);
    }

    //---------------------------------------------------------------------------------------------------------
    //진료기록 추가
    @PatchMapping("dogs/medical-records/{name}/{ownerName}/{ownerPhoneNumber}/{medicalRecord}")
    public void addMedicalRecord(@PathVariable String name,
                                 @PathVariable String ownerName,
                                 @PathVariable String ownerPhoneNumber,
                                 @PathVariable String medicalRecord) {
        dogManagementService.addMedicalRecord(dogManagementService.getDogByAllKey(name, ownerName, ownerPhoneNumber), medicalRecord);
    }

    // 견종 변경
    @PatchMapping("/dogs/kind/{name}/{ownerName}/{ownerPhoneNumber}/{changeKind}")
    public void updateDogKind(@PathVariable String name,
                              @PathVariable String ownerName,
                              @PathVariable String ownerPhoneNumber,
                              @PathVariable String changeKind){
        dogManagementService.updateDogKind(name, ownerName, ownerPhoneNumber, changeKind);
    }
}