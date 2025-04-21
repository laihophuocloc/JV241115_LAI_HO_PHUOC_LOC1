package ra.edu.dao.course;

import ra.edu.Entity.Course;
import ra.edu.dao.IGenericDao;

import java.util.List;

public interface ICourseDao extends IGenericDao<Course, Integer> {
    List<Course> paginationList(String keyword, int limit, int offset);
    boolean existByName(String name);
}
