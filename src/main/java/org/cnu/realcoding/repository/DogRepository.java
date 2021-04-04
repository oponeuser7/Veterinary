package org.cnu.realcoding.repository;

import org.cnu.realcoding.domain.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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

    public Dog getDogByPhoneNum(String phoneNum) {
        Criteria ctr = new Criteria("ownerPhoneNumber");
        ctr.is(phoneNum);
        Query q = new Query(ctr);
        return mongoTemplate.findOne(q, Dog.class);
    }

    public void postDogs(Dog dog) {
        if(check_exist(getDogByKey(dog))) {
            mongoTemplate.insert(dog);
        }
    }

    public Dog getDogByKey(Dog dog) {
        Criteria criteria = new Criteria("name");
        criteria.is(dog.getName()).and("ownerName").is(dog.getOwnerName())
                .and("ownerPhoneNumber").is(dog.getOwnerPhoneNumber());
        Query query = new Query(criteria);

        return mongoTemplate.findOne(query, Dog.class);
    }

    public boolean check_exist(Dog dog){
        if(dog == null){
            return false;
        }else {
            return true;
        }
    }
}
