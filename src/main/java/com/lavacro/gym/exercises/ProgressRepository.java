package com.lavacro.gym.exercises;

import com.lavacro.gym.model.ExercisesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressRepository extends MongoRepository<ExercisesEntity, String> {
}
