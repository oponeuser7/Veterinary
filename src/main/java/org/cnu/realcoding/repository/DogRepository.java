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

    public List<Dog> getDogByOwnerRPST(String ownerName) {
        Criteria ctr = new Criteria("ownerName");
        ctr.is(ownerName);
        Query query = new Query(ctr);
        return mongoTemplate.find(query, Dog.class);
    }

    public Dog getDogByPhoneNum(String phoneNum) {
        Criteria criteria = new Criteria("ownerPhoneNumber");
        criteria.is(phoneNum);
        Query q = new Query(criteria);
        return mongoTemplate.findOne(q, Dog.class);
    }

    public void postDogs(Dog dog) {
        mongoTemplate.insert(dog);
    }

    public void addMedicalRecord(Dog dog, List<String> medicalRecord) {
        Criteria criteria = new Criteria("name");
        criteria.is(dog.getName()).and("ownerName").is(dog.getOwnerName())
                .and("ownerPhoneNumber").is(dog.getOwnerPhoneNumber());
        Query query = new Query(criteria);
        Update update = new Update();
        update.set("medicalRecords", medicalRecord);
        mongoTemplate.updateFirst(query, update, Dog.class);
    }

    public boolean check_Exist(String name, String ownerName, String ownerPhoneNumber) {
        Criteria criteria = new Criteria("name");
        criteria.is(name).and("ownerName").is(ownerName)
                .and("ownerPhoneNumber").is(ownerPhoneNumber);
        Query query = new Query(criteria);
        return mongoTemplate.exists(query, Dog.class);
    }

    public Dog getDogByAllKey(String name, String ownerName, String ownerPhoneNumber) {
        Criteria criteria = new Criteria("name");
        criteria.is(name).and("ownerName").is(ownerName).and("ownerPhoneNumber").is(ownerPhoneNumber);
        Query query = new Query(criteria);

        return mongoTemplate.findOne(query, Dog.class);
    }

    public void putDogRPST(Dog d) {
        Criteria crt = new Criteria("name").is(d.getName())
                .and("ownerName").is(d.getOwnerName())
                .and("ownerPhoneNumber").is(d.getOwnerPhoneNumber());
        Query query = new Query(crt);
        mongoTemplate.remove(query, Dog.class);
        mongoTemplate.insert(d);
    }
}
