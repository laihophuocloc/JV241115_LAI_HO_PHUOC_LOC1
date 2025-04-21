package ra.edu.Service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.Entity.Course;
import ra.edu.Entity.Student;
import ra.edu.dao.course.ICourseDao;
import ra.edu.dao.student.IStudentDao;
import ra.edu.dto.request.StudentAdd;
import ra.edu.dto.request.StudentUpdate;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private IStudentDao studentDao;
    @Autowired
    private ICourseDao courseDao;
    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public List<Student> paginationList(String keyword, int page, int size) {
        return studentDao.paginationList(keyword,size, page*size);
    }

    @Override
    public int countTotalPages(int size) {
        int totalElements = studentDao.findAll().size();
        int mod = totalElements % size;
        int total = totalElements/size;
        return mod==0?total:total+1;
    }

    @Override
    public Student findById(String id) {
        return studentDao.findById(id);
    }
    @Transactional
    @Override
    public void create(StudentAdd request) {
        //Upload file
        String uploadPath = "/upload/"+request.getAvatar().getOriginalFilename();
        Student pro = new Student(
                request.getStudentId(),
                request.getStudentName(),
                request.getEmail(),
                request.getPhone(),
                true,
                request.getDateOfBirth(),
                courseDao.findById(request.getCourseId()),
                uploadPath,
                true
                );
        studentDao.save(pro);
    }
    @Transactional
    @Override
    public void update(StudentUpdate request, String id) {
        //Upload file
        String uploadPath = "/uploads/"+request.getAvatar().getOriginalFilename();
        Student entity = studentDao.findById(id);
        Student pro = new Student(
                request.getStudentId(),
                request.getStudentName(),
                request.getEmail(),
                request.getPhone(),
                request.getSex(),
                request.getDateOfBirth(),
                courseDao.findById(request.getCourseId()),
                uploadPath,
                request.getStatus()
        );
        studentDao.save(pro);
    }
    @Transactional
    @Override
    public void delete(String id) {
        studentDao.delete(id);
    }
//
//    @Override
//    public boolean existProductByCategoryId(Long id) {
//        return studentDao.existByCategoryId(id);
//    }
}