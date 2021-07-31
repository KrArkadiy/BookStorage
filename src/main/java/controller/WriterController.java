package controller;

import liquibase.pro.packaged.C;
import model.Writer;
import repository.WriterRepository;
import repository.repositoryImplementation.JdbcWriterRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class WriterController {

    WriterRepository jdbcWriterRepository = new JdbcWriterRepositoryImpl();

    public WriterController() {

    }

    public Writer getById(Long aLong) throws ClassNotFoundException, SQLException {
        return jdbcWriterRepository.getById(aLong);
    }

    public List<Writer> getAll() throws ClassNotFoundException, SQLException {
        return jdbcWriterRepository.getAll();
    }

    public void save(Writer writer) throws ClassNotFoundException, SQLException {
        jdbcWriterRepository.save(writer);
    }

    public void update(Writer writer) throws ClassNotFoundException, SQLException {
        jdbcWriterRepository.update(writer);
    }

    public void deleteById(Long aLong) throws ClassNotFoundException, SQLException {
        jdbcWriterRepository.deleteById(aLong);
    }
}
