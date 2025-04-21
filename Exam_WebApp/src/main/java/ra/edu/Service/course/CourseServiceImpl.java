package ra.edu.Service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.Entity.Course;
import ra.edu.dao.course.ICourseDao;
import ra.edu.dto.request.CourseAdd;
import ra.edu.dto.request.CourseUpdate;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {
//    @Autowired
//    private IProductDao productDao;
    @Autowired
    private ICourseDao courseDao;
    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public List<Course> paginationList(String keyword, int page, int size) {
        return courseDao.paginationList(keyword, size, page*size);
    }

    @Override
    public Course findById(Integer id) {
        return courseDao.findById(id);
    }
    @Transactional
    @Override
    public void create(CourseAdd courseAdd) {
        Course course = new Course(
                0,
                courseAdd.getCourseName(),
                courseAdd.getDescription()
        );
        courseDao.save(course);
    }
    @Transactional
    @Override
    public void update(CourseUpdate courseUpdate, Integer id) {
        Course cat = new Course(
                id,
                courseUpdate.getCourseName(),
                courseUpdate.getDescription()
        );
        courseDao.save(cat);
    }
    @Transactional
    @Override
    public void delete(Integer id) {
        courseDao.delete(id);
    }

    @Override
    public int countTotalPages(int size) {
        int totalElements = courseDao.findAll().size();
        int mod = totalElements % size;
        int total = totalElements / size;
        return mod==0?total:total + 1;
    }
}
