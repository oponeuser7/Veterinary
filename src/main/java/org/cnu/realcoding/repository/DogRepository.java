package org.cnu.realcoding.repository;

import org.cnu.realcoding.domain.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import lombok.Data;

import java.util.List;

@Repository
public class DogRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;


    public void postDogs(Dog dog) {
        if(check_exist(dog) == null) {
            mongoTemplate.insert(dog);
        }else{
            System.out.println("이미 존재하는 강아지 입니다.");
        }
    }

    public Dog check_exist(Dog dog){
        Criteria criteria = new Criteria("name");
        criteria.is(dog.getName()).and("ownerName").is(dog.getOwnerName())
                .and("ownerPhoneNumber").is(dog.getOwnerPhoneNumber());
        Query query = new Query(criteria);
        Dog mongo_dog = mongoTemplate.findOne(query, Dog.class);
        if( mongo_dog== null){
            return null;
        }else {
            return mongo_dog;
        }
    }

    public Dog getDogByKey(Dog dog) {
        Criteria criteria = new Criteria("name");
        criteria.is(dog.getName()).and("ownerName").is(dog.getOwnerName())
                .and("ownerPhoneNumber").is(dog.getOwnerPhoneNumber());
        Query query = new Query(criteria);

        return mongoTemplate.findOne(query, Dog.class);
    }

}
