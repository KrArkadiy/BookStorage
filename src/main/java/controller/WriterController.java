
package controller;

import model.Writer;
import repository.WriterRepository;
import repository.repositoryImplementation.JdbcWriterRepositoryImpl;

import java.util.List;

public class WriterController {

    WriterRepository jdbcWriterRepository = new JdbcWriterRepositoryImpl();

    public WriterController() {

    }

    public WriterController(WriterRepository writerRepository) {
        this.jdbcWriterRepository = writerRepository;
    }

    public Writer getById(Long aLong) {
        return jdbcWriterRepository.getById(aLong);
    }

    public List<Writer> getAll() {
        return jdbcWriterRepository.getAll();
    }

    public void save(Writer writer) {
        jdbcWriterRepository.save(writer);
    }

    public void update(Writer writer) {
        jdbcWriterRepository.update(writer);
    }

    public void deleteById(Long aLong) {
        jdbcWriterRepository.deleteById(aLong);
    }
}

