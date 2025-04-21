package ra.edu.Service;

import java.util.List;

public interface IGenericService<T, E, U, V> {
    //U: DTO request gửi lên
    //V: DTO update gửi lên
    List<T> findAll();
    T findById(E id);
    void create(U u);
    void update(V v, E id);
    void delete(E id);
}
