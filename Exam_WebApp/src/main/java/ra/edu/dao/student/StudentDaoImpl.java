package ra.edu.dao.student;

import org.springframework.stereotype.Repository;
import ra.edu.Entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StudentDaoImpl implements IStudentDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> paginationList(String keyword, int limit, int offset) {
        return entityManager.createQuery("FROM Student C WHERE C.studentName like :key", Student.class)
                .setParameter("key", "%" + keyword + "%")
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public List<Student> findAll() {
        return entityManager.createQuery("FROM Student ", Student.class).getResultList();
    }

//    @Override
//    public boolean existByName(String name) {
//        return !entityManager.createQuery("FROM Product  C where  C.productName like :name")
//                .setParameter("name", name)
//                .getResultList().isEmpty();
//    }

//    @Override
//    public boolean existByCategoryId(Long catId) {
//        return !entityManager.createQuery("FROM Product P WHERE P.category.categoryId = :catId")
//                .setParameter("catId", catId)
//                .getResultList().isEmpty();
//    }

    @Override
    public Student findById(String id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void save(Student student) {
        if (student.getStudentId() == null) {
            // thêm mới
            entityManager.persist(student);
        } else {
            entityManager.merge(student);
        }
    }

    @Override
    public void delete(String id) {
        entityManager.remove(findById(id));
    }
}