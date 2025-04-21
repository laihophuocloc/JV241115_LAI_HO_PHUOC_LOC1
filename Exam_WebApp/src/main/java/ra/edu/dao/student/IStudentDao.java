package ra.edu.dao.student;

import ra.edu.Entity.Student;
import ra.edu.dao.IGenericDao;

import java.util.List;

public interface IStudentDao extends IGenericDao<Student, String> {
    List<Student> paginationList(String keyword, int limit, int offset);
//    boolean existByName(String name);
//    boolean existByCategoryId(Long catId);
}
