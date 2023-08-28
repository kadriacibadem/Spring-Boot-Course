package com.luv2code.springdemo.mvc.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Constraint(validatedBy = CourseCodeConstraintValidator.class) // bu annotation'ın hangi class'ı kullanacağını belirtiyoruz
@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD}) // bu annotationın method ve field'larda kullanılabileceğini belirtiyoruz
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME) // bu annotationın runtime'da kullanılacağını belirtiyoruz
public @interface CourseCode {
    // define default course code
    public String value() default "LUV";

    // define default error message
    public String message() default "must start with LUV";

    // define default groups
    public Class<?>[] groups() default {};

    // define default payloads
    public Class<? extends jakarta.validation.Payload>[] payload() default {};
}
