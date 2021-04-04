package org.cnu.realcoding.service;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.DogAlreadyExistsException;
import org.cnu.realcoding.exception.DogNotFoundException;
import org.cnu.realcoding.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DogManagementService {

    @Autowired
    private DogRepository dogRepository;

    // 포스트
    public void insertDog(Dog dog) {
        if(dogRepository.check_Exist(dog)){
            throw new DogAlreadyExistsException();
        }else{
            dogRepository.postDogs(dog);
        }
    }

    // 이름 조회
    public List<Dog> getDogByName(String name){
        List<Dog> mongo_dog = dogRepository.getDogByName(name);
        if(mongo_dog != null) {
            return mongo_dog;
        }else {
            throw new DogNotFoundException();
        }
    }

    // 키 조회
    public Dog getDogByAllKey(String name, String ownerName, String ownerPhoneNumber){
        Dog mongo_dog = dogRepository.getDogByAllKey(name, ownerName, ownerPhoneNumber);
        if(mongo_dog != null) {
            return mongo_dog;
        }else {
            throw new DogNotFoundException();
        }
    }
    public Dog getDogByPhoneNum(String PhoneNum){
        return dogRepository.getDogByPhoneNum(PhoneNum);
    }

    // 견종 변경
    public void updateDogKind(Dog dog, String changeKind){
        dogRepository.updateDogKind(dog, changeKind);
    }
}
