package controller;

import model.Label;
import model.Post;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.LabelRepository;
import repository.PostRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class PostControllerTest {

    private final PostRepository postRepositoryMock = Mockito.mock(PostRepository.class);

    @Test
    void givenListOfPosts_whenRun_thenEqualListOfPostsReturned() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1, "First Content", 1L, 1L, new ArrayList<>()));
        posts.add(new Post(2, "Second Content", 1L, 1L, new ArrayList<>()));
        try {
            when(postRepositoryMock.getAll()).thenReturn(posts);

            List<Post> testPosts = postRepositoryMock.getAll();

            assertEquals(posts, testPosts);
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Error occurred");
        }
    }

    @Test
    void givenId_whenRun_thenPostWithThatIdReturned() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1, "First Content", 1L, 1L, new ArrayList<>()));
        posts.add(new Post(2, "Second Content", 1L, 1L, new ArrayList<>()));
        try {
            when(postRepositoryMock.getById(1L)).thenReturn(posts.get(0));
            when(postRepositoryMock.getById(2L)).thenReturn(posts.get(1));

            Post testPost = postRepositoryMock.getById(1L);
            Post testPost2 = postRepositoryMock.getById(2L);

            assertEquals("First Content", testPost.getContent());
            assertEquals("Second Content", testPost2.getContent());
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Error occurred");
        }
    }

    @Test
    void givenNewPost_whenSave_thenReturnNewPost() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1, "First Content", 1L, 1L, new ArrayList<>()));
        posts.add(new Post(2, "Second Content", 1L, 1L, new ArrayList<>()));
        Post newPost = new Post(3, "Third Content", 1L, 1L, new ArrayList<>());
        try {
            when(postRepositoryMock.save(newPost)).thenReturn(newPost);

            posts.add(newPost);

            assertEquals("Third Content", postRepositoryMock.save(newPost).getContent());
            assertEquals(newPost, posts.get(2));
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Error occurred");
        }
    }

    @Test
    void givenUpdate_whenUpdate_thenReturnUpdatedLabel() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1, "First Content", 1L, 1L, new ArrayList<>()));
        posts.add(new Post(2, "Second Content", 1L, 1L, new ArrayList<>()));
        Post newPost = new Post(3, "Third Content", 1L, 1L, new ArrayList<>());
        try {
            when(postRepositoryMock.update(newPost)).thenReturn(newPost);

            newPost.setContent("Third Content Updated");

            assertEquals("Third Content Updated", postRepositoryMock.update(newPost).getContent());
            assertEquals(3, postRepositoryMock.update(newPost).getId());
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Error occurred");
        }
    }

    @Test
    void givenId_whenRun_thenVerifyTimesOfInvocations() {
        try {
            doNothing().when(postRepositoryMock).deleteById(isA(Long.class));

            postRepositoryMock.deleteById(1L);

            verify(postRepositoryMock, times(1)).deleteById(1L);
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Error occurred");
        }
    }
}