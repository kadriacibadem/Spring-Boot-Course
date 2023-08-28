package com.kadri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private Coach coach;
    @Autowired
    public Controller(@Qualifier("swimCoach") Coach coach) {
        this.coach = coach;
    }

    @GetMapping("/configbean")
    public String getDailyWorkout() {
        return coach.getDailyWorkout();
    }
}
