package ra.edu.dao.course;

import org.springframework.stereotype.Repository;
import ra.edu.Entity.Course;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CourseDaoImpl implements ICourseDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Course> paginationList(String keyword, int limit, int offset) {
        return entityManager.createQuery("From Course C where C.courseName like : key", Course.class)
                .setParameter("key", "%" + keyword + "%")
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public List<Course> findAll() {
        return entityManager.createQuery("From Course ", Course.class).getResultList();
    }

    @Override
    public Course findById(Integer id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public void save(Course course) {
        if(course.getCourseId() == 0) {
            entityManager.persist(course);
        } else {
            entityManager.merge(course);
        }
    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(findById(id));
    }

    @Override
    public boolean existByName(String name) {
        return !entityManager.createQuery("From Course C where C.courseName like : name")
                .setParameter("name", name)
                .getResultList().isEmpty();
    }
}
