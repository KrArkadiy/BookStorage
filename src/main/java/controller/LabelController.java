package controller;

import model.Label;
import repository.LabelRepository;
import repository.repositoryImplementation.JdbcLabelRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class LabelController {

    LabelRepository jdbcLabelRepository = new JdbcLabelRepositoryImpl();

    public LabelController() {

    }

    public List<Label> getAll() throws ClassNotFoundException, SQLException {
        return jdbcLabelRepository.getAll();
    }

    public Label getById(Long aLong) throws ClassNotFoundException, SQLException {
        return jdbcLabelRepository.getById(aLong);
    }

    public void save(Label label) throws ClassNotFoundException, SQLException {
        jdbcLabelRepository.save(label);
    }

    public void update(Label label) throws ClassNotFoundException, SQLException {
        jdbcLabelRepository.update(label);
    }

    public void deleteById(Long aLong) throws ClassNotFoundException, SQLException {
        jdbcLabelRepository.deleteById(aLong);
    }
}
