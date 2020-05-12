package me.bill.currencyparser.annotations;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

@Target({ElementType.PARAMETER, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Min(3)
@Max(3)
@NotBlank
@Pattern(regexp = "[0-9]+")
public @interface CodeValid {
}
