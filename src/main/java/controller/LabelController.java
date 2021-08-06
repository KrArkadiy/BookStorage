package controller;

import model.Label;
import repository.LabelRepository;
import repository.repositoryImplementation.JdbcLabelRepositoryImpl;

import java.util.List;

public class LabelController {

    LabelRepository jdbcLabelRepository = new JdbcLabelRepositoryImpl();

    public LabelController() {

    }

    public LabelController(LabelRepository labelRepository) {
        this.jdbcLabelRepository = labelRepository;
    }

    public List<Label> getAll() {
        return jdbcLabelRepository.getAll();
    }

    public Label getById(Long aLong) {
        return jdbcLabelRepository.getById(aLong);
    }

    public void save(Label label) {
        jdbcLabelRepository.save(label);
    }

    public void update(Label label) {
        jdbcLabelRepository.update(label);
    }

    public void deleteById(Long aLong) {
        jdbcLabelRepository.deleteById(aLong);
    }
}
