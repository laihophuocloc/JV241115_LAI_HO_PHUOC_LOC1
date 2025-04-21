package ra.edu.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.edu.dao.course.ICourseDao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CourseValidator implements ConstraintValidator<CourseUnique, String> {
    @Autowired
    private ICourseDao courseDao;
    @Override
    public void initialize(CourseUnique courseUnique) {

    }

    //Kimeer tr
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !courseDao.existByName(s); //Kiểm tra tồn tại
    }
}
