package controller;

import model.Writer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.WriterRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class WriterControllerTest {

    private final WriterRepository writerRepositoryMock = Mockito.mock(WriterRepository.class);
    private WriterController writerController = new WriterController(writerRepositoryMock);

    @Test
    void givenListOfWriters_whenRun_thenEqualListOfWritersReturned() {
        List<Writer> writers = new ArrayList<>();
        writers.add(new Writer(1, "First Name", new ArrayList<>()));
        writers.add(new Writer(2, "Second Name", new ArrayList<>()));
        when(writerController.getAll()).thenReturn(writers);

        List<Writer> testWriters = writerController.getAll();

        assertEquals(writers, testWriters);
    }

    @Test
    void givenId_whenRun_thenWriterWithThatIdReturned() {
        List<Writer> writers = new ArrayList<>();
        writers.add(new Writer(1, "First Name", new ArrayList<>()));
        writers.add(new Writer(2, "Second Name", new ArrayList<>()));

        when(writerController.getById(1L)).thenReturn(writers.get(0));
        when(writerController.getById(2L)).thenReturn(writers.get(1));

        Writer testWriter = writerController.getById(1L);
        Writer testWriter2 = writerController.getById(2L);

        assertEquals("First Name", testWriter.getName());
        assertEquals("Second Name", testWriter2.getName());
    }

    @Test
    void givenNewWriter_whenSave_thenReturnNewWriter() {
        WriterController writerController = Mockito.mock(WriterController.class);
        List<Writer> writers = new ArrayList<>();
        writers.add(new Writer(1, "First Name", new ArrayList<>()));
        writers.add(new Writer(2, "Second Name", new ArrayList<>()));
        Writer newWriter = new Writer(3, "Writer Name", new ArrayList<>());

        doNothing().when(writerController).save(isA(Writer.class));

        writerController.save(newWriter);

        verify(writerController, times(1)).save(isA(Writer.class));
    }

    @Test
    void givenUpdate_whenUpdate_thenReturnUpdatedWriter() {
        WriterController writerController = Mockito.mock(WriterController.class);
        List<Writer> writers = new ArrayList<>();
        writers.add(new Writer(1, "First Name", new ArrayList<>()));
        writers.add(new Writer(2, "Second Name", new ArrayList<>()));
        Writer newWriter = new Writer(3, "Writer Name", new ArrayList<>());

        doNothing().when(writerController).update(isA(Writer.class));

        writerController.update(newWriter);

        verify(writerController, times(1)).update(isA(Writer.class));
    }

    @Test
    void givenId_whenRun_thenVerifyTimesOfInvocations() {
        WriterController writerController = Mockito.mock(WriterController.class);
        doNothing().when(writerController).deleteById(isA(Long.class));

        writerController.deleteById(1L);

        verify(writerController, times(1)).deleteById(1L);
    }
}