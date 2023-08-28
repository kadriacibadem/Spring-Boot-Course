package com.kadri;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class TennisCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Tennis Coach";
    }
}
