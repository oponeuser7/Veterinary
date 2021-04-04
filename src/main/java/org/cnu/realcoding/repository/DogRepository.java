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

    public List<Dog> getDogByOwnerRPST(String ownerName) {
        Criteria ctr = new Criteria("ownerName");
        ctr.is(ownerName);
        Query query = new Query(ctr);
        return mongoTemplate.find(query, Dog.class);
    }
}
