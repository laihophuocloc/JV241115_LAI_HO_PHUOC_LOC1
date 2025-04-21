package ra.edu.Service.course;

import ra.edu.Entity.Course;
import ra.edu.Service.IGenericService;
import ra.edu.dto.request.CourseAdd;
import ra.edu.dto.request.CourseUpdate;

import java.util.List;

public interface ICourseService extends IGenericService<Course, Integer, CourseAdd, CourseUpdate> {
    List<Course> paginationList(String keyword, int page, int size);
    int countTotalPages(int size);

}
