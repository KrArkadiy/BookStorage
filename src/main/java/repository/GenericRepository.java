package repository;

import java.sql.SQLException;
import java.util.List;

public interface GenericRepository<T, ID> {

    T getById(ID id);

    List<T> getAll();

    T update(T t);

    T save(T t);

    void deleteById(ID id);
}
