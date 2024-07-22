package com.example.training;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "trainings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Training {
    @Id
    private ObjectId id;
    private LocalDateTime date;
    private TrainingType type;
    private int duration;
    private int caloriesBurned;
    private int heaviness;
    private int exhaustion;
}
