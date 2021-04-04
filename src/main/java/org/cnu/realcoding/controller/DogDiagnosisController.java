package org.cnu.realcoding.controller;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.service.DogManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DogDiagnosisController {

    @Autowired
    DogManagementService dogManagementService;

    @GetMapping("/dogs/{ownerPhoneNumber}")
    public Dog getDogByPhoneNum(@RequestParam String PhoneNum ){
        return dogManagementService.getDogByPhoneNum(PhoneNum);
    }

}
