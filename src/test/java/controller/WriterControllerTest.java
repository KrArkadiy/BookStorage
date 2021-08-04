package controller;

import model.Label;
import model.Writer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.WriterRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class WriterControllerTest {

    private final WriterRepository writerRepositoryMock = Mockito.mock(WriterRepository.class);

    @Test
    void givenListOfWriters_whenRun_thenEqualListOfWritersReturned() {
        List<Writer> writers = new ArrayList<>();
        writers.add(new Writer(1, "First Name", new ArrayList<>()));
        writers.add(new Writer(2, "Second Name", new ArrayList<>()));
        try {
            when(writerRepositoryMock.getAll()).thenReturn(writers);

            List<Writer> testWriters = writerRepositoryMock.getAll();

            assertEquals(writers, testWriters);
        } catch (ClassNotFoundException | SQLException exception){
            System.out.println("Error occurred");
        }
    }

    @Test
    void givenId_whenRun_thenWriterWithThatIdReturned() {
        List<Writer> writers = new ArrayList<>();
        writers.add(new Writer(1, "First Name", new ArrayList<>()));
        writers.add(new Writer(2, "Second Name", new ArrayList<>()));
        try{
            when(writerRepositoryMock.getById(1L)).thenReturn(writers.get(0));
            when(writerRepositoryMock.getById(2L)).thenReturn(writers.get(1));

            Writer testWriter = writerRepositoryMock.getById(1L);
            Writer testWriter2 = writerRepositoryMock.getById(2L);

            assertEquals("First Name", testWriter.getName());
            assertEquals("Second Name", testWriter2.getName());
        } catch (ClassNotFoundException | SQLException exception){
            System.out.println("Error occurred");
        }
    }

    @Test
    void givenNewWriter_whenSave_thenReturnNewWriter() {
        List<Writer> writers = new ArrayList<>();
        writers.add(new Writer(1, "First Name", new ArrayList<>()));
        writers.add(new Writer(2, "Second Name", new ArrayList<>()));
        Writer newWriter = new Writer(3, "Writer Name", new ArrayList<>());
        try{
            when(writerRepositoryMock.save(newWriter)).thenReturn(newWriter);

            writers.add(newWriter);

            assertEquals("Writer Name", writerRepositoryMock.save(newWriter).getName());
            assertEquals(newWriter, writers.get(2));
        } catch (ClassNotFoundException | SQLException exception){
            System.out.println("Error occurred");
        }
    }

    @Test
    void givenUpdate_whenUpdate_thenReturnUpdatedWriter() {
        List<Writer> writers = new ArrayList<>();
        writers.add(new Writer(1, "First Name", new ArrayList<>()));
        writers.add(new Writer(2, "Second Name", new ArrayList<>()));
        Writer newWriter = new Writer(3, "Writer Name", new ArrayList<>());
        try{
            when(writerRepositoryMock.update(newWriter)).thenReturn(newWriter);

            newWriter.setName("Third Name Updated");

            assertEquals("Third Name Updated", writerRepositoryMock.update(newWriter).getName());
            assertEquals(3, writerRepositoryMock.update(newWriter).getId());
        } catch (ClassNotFoundException | SQLException exception){
            System.out.println("Error occurred");
        }
    }

    @Test
    void givenId_whenRun_thenVerifyTimesOfInvocations() {
        try {
            doNothing().when(writerRepositoryMock).deleteById(isA(Long.class));

            writerRepositoryMock.deleteById(1L);

            verify(writerRepositoryMock, times(1)).deleteById(1L);
        } catch (ClassNotFoundException | SQLException exception){
            System.out.println("Error occurred");
        }
    }
}