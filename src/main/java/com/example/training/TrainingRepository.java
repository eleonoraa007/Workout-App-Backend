package com.example.training;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends MongoRepository<Training, ObjectId> {
}
