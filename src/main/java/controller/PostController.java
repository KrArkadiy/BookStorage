package controller;

import model.Post;
import repository.PostRepository;
import repository.repositoryImplementation.JdbcPostRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class PostController {

    PostRepository jdbcPostRepository = new JdbcPostRepositoryImpl();

    public PostController() {

    }

    public Post getById(Long aLong) throws ClassNotFoundException, SQLException {
        return jdbcPostRepository.getById(aLong);
    }

    public List<Post> getAll() throws ClassNotFoundException, SQLException {
        return jdbcPostRepository.getAll();
    }

    public void update(Post post) throws ClassNotFoundException, SQLException {
        jdbcPostRepository.update(post);
    }

    public void save(Post post) throws ClassNotFoundException, SQLException {
        jdbcPostRepository.save(post);
    }

    public void deleteById(Long aLong) throws ClassNotFoundException, SQLException {
        jdbcPostRepository.deleteById(aLong);
    }
}
