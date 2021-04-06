package org.cnu.realcoding.service;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.ChangeMedicalRecordException;
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

    // post ---------------------------------------------------------------------------------------------------------

    // 강아지 등록
    public void insertDog(Dog dog) {
        if(dogRepository.check_Exist(dog.getName(), dog.getOwnerName(), dog.getOwnerPhoneNumber())){
            throw new DogAlreadyExistsException();
        }else{
            dogRepository.postDogs(dog);
        }
    }


    // get---------------------------------------------------------------------------------------------------------

    // 이름 조회
    public List<Dog> getDogByName(String name){
        List<Dog> mongo_dog = dogRepository.getDogByName(name);
        if(mongo_dog.size() > 0) {
            return mongo_dog;
        }else {
            throw new DogNotFoundException();
        }
    }

    // 주인 이름 조회
    public List<Dog> getDogByOwnerSRVC(String ownerName) {
        List<Dog> mongo_dog = dogRepository.getDogByOwnerRPST(ownerName);
        if(mongo_dog.size() > 0){
            return mongo_dog;
        }
        else{
            throw new DogNotFoundException();
        }
    }

    // 폰 넘버 조회
    public List<Dog> getDogByPhoneNum(String PhoneNum) {
        List<Dog> mongo_dog = dogRepository.getDogByPhoneNum(PhoneNum);
        if(mongo_dog.size() > 0){
            return mongo_dog;
        }
        else{
            throw new DogNotFoundException();
        }
    }

    // allkey 조회
    public Dog getDogByAllKey(String name, String ownerName, String ownerPhoneNumber){
        Dog mongo_dog = dogRepository.getDogByAllKey(name, ownerName, ownerPhoneNumber);
        if(!dogRepository.check_Exist(name, ownerName, ownerPhoneNumber)) {
            throw new DogNotFoundException();
        }else {
            return mongo_dog;
        }
    }


    // put--------------------------------------------------------------------------------------------------------

    // 강아지 덮어쓰기
    public void putDogSRVC(String name,
                           String ownerName,
                           String ownerPhoneNumber,
                           Dog dog) {
        if(dogRepository.check_Exist(name, ownerName, ownerPhoneNumber)) {
            Dog oldDog = dogRepository.getDogByAllKey(name, ownerName, ownerPhoneNumber);
            if(!(dog.getMedicalRecords().equals(oldDog.getMedicalRecords()))) {
                throw new ChangeMedicalRecordException();
            } else {
                dogRepository.putDogRPST(name, ownerName, ownerPhoneNumber, dog);
            }
        } else {
            throw new DogNotFoundException();
        }
    }


    // patch ---------------------------------------------------------------------------------------------------------

    // 진료기록 추가
    public void addMedicalRecord(String name, String ownerName, String ownerPhoneNumber, List<String> medicalRecord) {
        if(!dogRepository.check_Exist(name, ownerName, ownerPhoneNumber)) {
            throw new DogNotFoundException();
        }
        else {
            dogRepository.addMedicalRecord(name, ownerName, ownerPhoneNumber, medicalRecord);
        }
    }

    // 견종 변경
    public void updateDogKind(String name, String ownerName, String ownerPhoneNumber, String changeKind){
        if(!dogRepository.check_Exist(name, ownerName, ownerPhoneNumber)) {
            throw new DogNotFoundException();
        } else {
            dogRepository.updateDogKind(name, ownerName, ownerPhoneNumber, changeKind);
        }
    }
}
