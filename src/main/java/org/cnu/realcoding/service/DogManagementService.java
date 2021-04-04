package org.cnu.realcoding.service;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.DogAlreadyExistsException;
import org.cnu.realcoding.exception.DogNotFoundException;
import org.cnu.realcoding.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DogManagementService {

    @Autowired
    private DogRepository dogRepository;

    public void insertDog(Dog dog) {
        if(dogRepository.check_Exist(dog)){
            System.out.println("이미 존재하는 강아지입니다.");
            throw new DogAlreadyExistsException();
        }else{
            dogRepository.postDogs(dog);
        }
    }

    public Dog getDogByAllKey(Dog dog){
        Dog mongo_dog = dogRepository.getDogByAllKey(dog);
        if(mongo_dog != null) {
            return mongo_dog;
        }else {
            System.out.println("존재하지 않는 강아지입니다. ");
            throw new DogNotFoundException();
        }
    }
    public Dog getDogByPhoneNum(String PhoneNum){
        return dogRepository.getDogByPhoneNum(PhoneNum);
    }
}
