package org.cnu.realcoding.repository;

import org.cnu.realcoding.domain.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import lombok.Data;

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
}
