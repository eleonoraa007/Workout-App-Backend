package com.example.training;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTrainingRepository extends MongoRepository<UserTrainings, ObjectId> {
    Optional<UserTrainings> findByUserId(String userId);
}
