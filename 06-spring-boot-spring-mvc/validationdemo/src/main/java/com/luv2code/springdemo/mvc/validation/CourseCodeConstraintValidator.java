package com.luv2code.springdemo.mvc.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    private String coursePrefix; // bu değer @CourseCode annotation'ı ile gelen değer olacak

    @Override
    public void initialize(CourseCode courseCode) {
        coursePrefix = courseCode.value(); // @CourseCode annotation'ı ile gelen değeri coursePrefix'e atıyoruz
    }

    @Override
    public boolean isValid(String theCode, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;
        if (theCode != null) {
            result = theCode.startsWith("LUV");
        } else {
            result = true;
        }
        return result;
    }
}
