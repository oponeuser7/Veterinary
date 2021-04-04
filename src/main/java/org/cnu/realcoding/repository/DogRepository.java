package org.cnu.realcoding.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import lombok.Data;

@Repository
public class DogRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;

}
