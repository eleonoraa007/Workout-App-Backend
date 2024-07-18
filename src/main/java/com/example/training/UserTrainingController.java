package com.example.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/trainings")
@CrossOrigin(origins = "*")
public class UserTrainingController {

    @Autowired
    UserTrainingService userTrainingService;

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<UserTrainings>> getUserTrainings(@PathVariable String userId) {
        return new ResponseEntity<>(userTrainingService.getUser(userId), HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<UserTrainings> addTraining(@PathVariable String userId, @RequestBody Training training) {
        return new ResponseEntity<>(userTrainingService.addTraining(userId, training), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/allTrainings")
    public ResponseEntity<List<Training>> getTrainings(@PathVariable String userId) {
        return new ResponseEntity<>(userTrainingService.getTrainingsForUser(userId), HttpStatus.OK);
    }

    @GetMapping("/monthlyTrainings")
    public ResponseEntity<Map<Integer, List<Training>>> getMonthlyTrainings(@RequestParam("userId") String userId, @RequestParam("year") int year, @RequestParam("month") int month) {
        return new ResponseEntity<>(userTrainingService.getMonthlyTrainings(userId, year, month), HttpStatus.OK);
    }
}





