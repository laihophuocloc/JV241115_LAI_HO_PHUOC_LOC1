package ra.edu.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = {CourseValidator.class})
@Target({ElementType.FIELD}) //Đánh dấu tên trường thôi
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseUnique {
    String message() default "{Tên khóa học đã tồn tại}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
