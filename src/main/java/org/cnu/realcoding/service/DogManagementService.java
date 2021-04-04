package org.cnu.realcoding.service;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogManagementService {

    @Autowired
    private DogRepository dogRepository;

    public List<Dog> getDogByOwnerSRVC(String ownerName) {
        return dogRepository.getDogByOwnerRPST(ownerName);
    }

    public void putDogSRVC(Dog dog) {
        
    }
}
