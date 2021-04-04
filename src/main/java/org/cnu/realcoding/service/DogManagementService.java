package org.cnu.realcoding.service;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DogManagementService {

    @Autowired
    private DogRepository dogrepo;

    public Dog getDogByPhoneNum(String PhoneNum){
        return dogrepo.getDogByPhoneNum(PhoneNum);
    }
}
