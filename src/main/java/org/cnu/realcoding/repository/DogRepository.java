package org.cnu.realcoding.repository;

import org.cnu.realcoding.domain.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DogRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean check_Exist(String name, String ownerName, String ownerPhoneNumber) {
        Criteria criteria = new Criteria("name");
        criteria.is(name).and("ownerName").is(ownerName)
                .and("ownerPhoneNumber").is(ownerPhoneNumber);
        Query query = new Query(criteria);
        return mongoTemplate.exists(query, Dog.class);
    }


    // post ---------------------------------------------------------------------------------------------------------

    // 강아지 등록
    public void postDogs(Dog dog) {
        mongoTemplate.insert(dog);
    }


    // get---------------------------------------------------------------------------------------------------------

    // 이름 조회
    public List<Dog> getDogByName(String name){
        Criteria criteria = new Criteria("name");
        criteria.is(name);
        Query q = new Query(criteria);
        return mongoTemplate.find(q, Dog.class);
    }

    // 주인 이름 조회
    public List<Dog> getDogByOwnerRPST(String ownerName) {
        Criteria ctr = new Criteria("ownerName");
        ctr.is(ownerName);
        Query query = new Query(ctr);
        return mongoTemplate.find(query, Dog.class);
    }

    // 폰 넘버 조회
    public List<Dog> getDogByPhoneNum(String phoneNum) {
        Criteria criteria = new Criteria("ownerPhoneNumber");
        criteria.is(phoneNum);
        Query q = new Query(criteria);
        return mongoTemplate.find(q, Dog.class);
    }

    // allkey 조회
    public Dog getDogByAllKey(String name, String ownerName, String ownerPhoneNumber) {
        Criteria criteria = new Criteria("name");
        criteria.is(name).and("ownerName").is(ownerName).and("ownerPhoneNumber").is(ownerPhoneNumber);
        Query query = new Query(criteria);

        return mongoTemplate.findOne(query, Dog.class);
    }


    // put--------------------------------------------------------------------------------------------------------

    // 강아지 덮어쓰기
    public void putDogRPST(String name,
                           String ownerName,
                           String ownerPhoneNumber,
                           Dog d) {
        Criteria criteria = new Criteria("name").is(name)
                .and("ownerName").is(ownerName)
                .and("ownerPhoneNumber").is(ownerPhoneNumber);
        Query query = new Query(criteria);
        mongoTemplate.remove(query, Dog.class);
        mongoTemplate.insert(d);
    }


    // patch ---------------------------------------------------------------------------------------------------------

    // 진료기록 추가
    public void addMedicalRecord(String name, String ownerName, String ownerPhoneNumber, List<String> medicalRecord) {
        Criteria criteria = new Criteria("name");
        criteria.is(name).and("ownerName").is(ownerName)
                .and("ownerPhoneNumber").is(ownerPhoneNumber);
        Query query = new Query(criteria);
        Update update = new Update();
        List<String> exist_one = mongoTemplate.findOne(query, Dog.class).getMedicalRecords();

        for(String record : medicalRecord){
            exist_one.add(record);
        }
        update.set("medicalRecords", exist_one);
        mongoTemplate.updateFirst(query, update, Dog.class);

    }

    // 견종 변경
    public void updateDogKind(String name, String ownerName, String ownerPhoneNumber, String changeKind){
        Criteria criteria = new Criteria("name");
        criteria.is(name).and("ownerName").is(ownerName).and("ownerPhoneNumber").is(ownerPhoneNumber);
        Query q = new Query(criteria);
        Update update = new Update();
        update.set("Kind", changeKind);
        mongoTemplate.updateFirst(q, update, Dog.class);
    }
}
