package view;

import java.sql.SQLException;

public abstract class BasicView {

    abstract void getById() throws ClassNotFoundException, SQLException;

    abstract void getAll() throws SQLException, ClassNotFoundException;

    abstract void deleteById() throws SQLException, ClassNotFoundException;

    abstract void save() throws SQLException, ClassNotFoundException;

    abstract void update() throws SQLException, ClassNotFoundException;
}
