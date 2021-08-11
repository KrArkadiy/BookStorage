
package controller;

import model.Post;
import repository.PostRepository;
import repository.repositoryImplementation.JdbcPostRepositoryImpl;

import java.util.List;

public class PostController {

    PostRepository jdbcPostRepository = new JdbcPostRepositoryImpl();

    public PostController() {

    }

    public PostController(PostRepository postRepository) {
        this.jdbcPostRepository = postRepository;
    }

    public Post getById(Long aLong) {
        return jdbcPostRepository.getById(aLong);
    }

    public List<Post> getAll() {
        return jdbcPostRepository.getAll();
    }

    public void update(Post post) {
        jdbcPostRepository.update(post);
    }

    public void save(Post post) {
        jdbcPostRepository.save(post);
    }

    public void deleteById(Long aLong) {
        jdbcPostRepository.deleteById(aLong);
    }
}

