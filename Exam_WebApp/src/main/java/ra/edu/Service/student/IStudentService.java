package ra.edu.Service.student;

import ra.edu.Entity.Student;
import ra.edu.Service.IGenericService;
import ra.edu.dto.request.StudentAdd;
import ra.edu.dto.request.StudentUpdate;

import java.util.List;

public interface IStudentService extends IGenericService<Student, String, StudentAdd, StudentUpdate> {
    List<Student> paginationList(String keyword, int page, int size);
    int countTotalPages(int size);
//    boolean existProductByCategoryId(String id);
}
