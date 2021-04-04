package org.cnu.realcoding.service;

import org.cnu.realcoding.domain.Dog;
import lombok.Getter;
import org.cnu.realcoding.controller.DogController;
import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.DogNotFoundException;
import org.cnu.realcoding.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DogManagementService {

    @Autowired
    private DogRepository dogrepo;

    public Dog getDogByPhoneNum(String PhoneNum){
        return dogrepo.getDogByPhoneNum(PhoneNum);
    }

    private DogRepository dogRepository;

    public List<Dog> getNameDogs(String name) {
        dogs.contains(name);
        return dogs;
    }

    public void insertDog(Dog dog) {
        dogRepository.postDogs(dog);
    }

    public Dog getDogByName(String name) {
        for (Dog dog : dogs) {
            if (dog.getName().equals(name)) {
                return dog;
            }
        }

        throw new DogNotFoundException();
    }
}
