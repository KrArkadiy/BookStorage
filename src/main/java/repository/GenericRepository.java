package repository;

import java.sql.SQLException;
import java.util.List;

public interface GenericRepository <T,ID> {

    T getById(ID id) throws ClassNotFoundException, SQLException;

    List<T> getAll() throws ClassNotFoundException, SQLException;

    T update(T t) throws ClassNotFoundException, SQLException;

    T save(T t) throws ClassNotFoundException, SQLException;

    void deleteById(ID id) throws ClassNotFoundException, SQLException;
}
