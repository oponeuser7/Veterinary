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

    public void insertDog(Dog dog) {
        if(dogRepository.check_Exist(dog.getName(), dog.getOwnerName(), dog.getOwnerPhoneNumber())){
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

    // 견종 변경
    public void updateDogKind(String name, String ownerName, String ownerPhoneNumber, String changeKind){
        if(dogRepository.check_Exist(name, ownerName, ownerPhoneNumber)) {
            dogRepository.updateDogKind(name, ownerName, ownerPhoneNumber, changeKind);
        } else {
            throw new DogNotFoundException();
        }
    }

    public Dog getDogByAllKey(String name, String ownerName, String ownerPhoneNumber){
        Dog mongo_dog = dogRepository.getDogByAllKey(name, ownerName, ownerPhoneNumber);
        if(dogRepository.check_Exist(name, ownerName, ownerPhoneNumber)) {
            throw new DogNotFoundException();
        }else {
            return mongo_dog;
        }
    }
    public Dog getDogByPhoneNum(String PhoneNum) {
        if(dogRepository.getDogByPhoneNum(PhoneNum) == null)
            throw new DogNotFoundException();
        return dogRepository.getDogByPhoneNum(PhoneNum);
    }

    public void addMedicalRecord(Dog dog, String medicalRecord) {
        if(!dogRepository.check_Exist(dog.getName(), dog.getOwnerName(), dog.getOwnerPhoneNumber())) {
            throw new DogNotFoundException();
        }
        else {
            dog.getMedicalRecords().add(medicalRecord);
            dogRepository.addMedicalRecord(dog, dog.getMedicalRecords());
        }
    }
}
