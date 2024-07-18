package com.example.training;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserTrainingService {
    @Autowired
    private UserTrainingRepository userTrainingRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    public Optional<UserTrainings> getUser(String userId) {
        return userTrainingRepository.findByUserId(userId);
    }
    public UserTrainings addTraining(String userId, Training training) {
        Optional<UserTrainings> userTrainingsOpt = userTrainingRepository.findByUserId(userId);
        UserTrainings userTrainings = userTrainingsOpt.orElseGet(() -> new UserTrainings(new ObjectId(), userId, new ArrayList<>()));

        trainingRepository.save(training);
        userTrainings.getTrainings().add(training);
        return userTrainingRepository.save(userTrainings);
    }

    public List<Training> getTrainingsForUser(String userId) {
        return userTrainingRepository.findByUserId(userId)
                .map(UserTrainings::getTrainings)
                .orElse(new ArrayList<>());
    }

    public Map<Integer, List<Training>> getMonthlyTrainings(String userId, int year, int month) {
        UserTrainings userTrainings = userTrainingRepository.findByUserId(userId).orElse(null);
        if (userTrainings == null) {
            return Collections.emptyMap();
        }

        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDateTime startOfMonth = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = yearMonth.atEndOfMonth().atTime(23, 59, 59);

        List<Training> trainingsInMonth = userTrainings.getTrainings().stream()
                .filter(training -> !training.getDate().isBefore(startOfMonth) && !training.getDate().isAfter(endOfMonth))
                .toList();

        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return trainingsInMonth.stream()
                .collect(Collectors.groupingBy(training -> training.getDate().get(weekFields.weekOfMonth())));
    }


}








