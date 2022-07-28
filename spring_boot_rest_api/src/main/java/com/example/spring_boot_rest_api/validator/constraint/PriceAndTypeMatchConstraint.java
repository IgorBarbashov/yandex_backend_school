package com.example.spring_boot_rest_api.validator.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PriceAndTypeMatchValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PriceAndTypeMatchConstraint {

    String message() default "Price invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
