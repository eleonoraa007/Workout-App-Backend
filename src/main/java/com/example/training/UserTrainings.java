package com.example.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "userTrainings")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTrainings {
    @Id
    private ObjectId id;
    private String userId;
    @DocumentReference
    private List<Training> trainings;

}
