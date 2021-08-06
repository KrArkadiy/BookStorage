package controller;

import model.Post;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class PostControllerTest {

    private final PostRepository postRepositoryMock = Mockito.mock(PostRepository.class);
    private PostController postController = new PostController(postRepositoryMock);

    @Test
    void givenListOfPosts_whenRun_thenEqualListOfPostsReturned() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1, "First Content", 1L, 1L, new ArrayList<>()));
        posts.add(new Post(2, "Second Content", 1L, 1L, new ArrayList<>()));

        when(postController.getAll()).thenReturn(posts);

        List<Post> testPosts = postRepositoryMock.getAll();

        assertEquals(posts, testPosts);
    }

    @Test
    void givenId_whenRun_thenPostWithThatIdReturned() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1, "First Content", 1L, 1L, new ArrayList<>()));
        posts.add(new Post(2, "Second Content", 1L, 1L, new ArrayList<>()));

        when(postController.getById(1L)).thenReturn(posts.get(0));
        when(postController.getById(2L)).thenReturn(posts.get(1));

        Post testPost = postController.getById(1L);
        Post testPost2 = postController.getById(2L);

        assertEquals("First Content", testPost.getContent());
        assertEquals("Second Content", testPost2.getContent());
    }

    @Test
    void givenNewPost_whenSave_thenReturnNewPost() {
        PostController postController = Mockito.mock(PostController.class);
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1, "First Content", 1L, 1L, new ArrayList<>()));
        posts.add(new Post(2, "Second Content", 1L, 1L, new ArrayList<>()));
        Post newPost = new Post(3, "Third Content", 1L, 1L, new ArrayList<>());

        doNothing().when(postController).save(isA(Post.class));

        postController.save(newPost);

        verify(postController,times(1)).save(newPost);
    }

    @Test
    void givenUpdate_whenUpdate_thenReturnUpdatedLabel() {
        PostController postController = Mockito.mock(PostController.class);
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1, "First Content", 1L, 1L, new ArrayList<>()));
        posts.add(new Post(2, "Second Content", 1L, 1L, new ArrayList<>()));
        Post newPost = new Post(3, "Third Content", 1L, 1L, new ArrayList<>());

        doNothing().when(postController).update(newPost);

        postController.update(newPost);

        verify(postController,times(1)).update(newPost);
    }

    @Test
    void givenId_whenRun_thenVerifyTimesOfInvocations() {
        PostController postController = Mockito.mock(PostController.class);
        doNothing().when(postController).deleteById(isA(Long.class));

        postController.deleteById(1L);

        verify(postController, times(1)).deleteById(1L);
    }
}