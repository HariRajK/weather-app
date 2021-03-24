package code.test.weather.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ZipCodeValidator.class})
@Documented
public @interface ValidateZipCode {
    String message() default "You have entered invalid zip code.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
