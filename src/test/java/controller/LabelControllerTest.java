package controller;

import model.Label;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.LabelRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

class LabelControllerTest {

    private final LabelRepository labelRepositoryMock = Mockito.mock(LabelRepository.class);

    @Test
    void givenListOfLabels_whenRun_thenEqualListOfLabelsReturned() {
        List<Label> labels = new ArrayList<>();
        labels.add(new Label(1, "First Label"));
        labels.add(new Label(2, "Second Label"));
        try {
            when(labelRepositoryMock.getAll()).thenReturn(labels);

            List<Label> testLabels = labelRepositoryMock.getAll();

            assertEquals(labels, testLabels);
        } catch (ClassNotFoundException | SQLException exception){
            System.out.println("Error occurred");
        }
    }

    @Test
    void givenId_whenRun_thenLabelWithThatIdReturned() {
        List<Label> labels = new ArrayList<>();
        labels.add(new Label(1, "First Label"));
        labels.add(new Label(2, "Second Label"));
        try{
            when(labelRepositoryMock.getById(1L)).thenReturn(labels.get(0));
            when(labelRepositoryMock.getById(2L)).thenReturn(labels.get(1));

            Label testLabel = labelRepositoryMock.getById(1L);
            Label testLabel2 = labelRepositoryMock.getById(2L);

            assertEquals("First Label", testLabel.getName());
            assertEquals("Second Label", testLabel2.getName());
        } catch (ClassNotFoundException | SQLException exception){
            System.out.println("Error occurred");
        }
    }

    @Test
    void givenNewLabel_whenSave_thenReturnNewLabel() {
        List<Label> labels = new ArrayList<>();
        labels.add(new Label(1, "First Label"));
        labels.add(new Label(2, "Second Label"));
        Label newLabel = new Label(3, "Third Label");
        try{
            when(labelRepositoryMock.save(newLabel)).thenReturn(newLabel);

            labels.add(newLabel);

            assertEquals("Third Label", labelRepositoryMock.save(newLabel).getName());
            assertEquals(newLabel, labels.get(2));
        } catch (ClassNotFoundException | SQLException exception){
            System.out.println("Error occurred");
        }
    }

    @Test
    void givenUpdate_whenUpdate_thenReturnUpdatedLabel() {
        List<Label> labels = new ArrayList<>();
        labels.add(new Label(1, "First Label"));
        labels.add(new Label(2, "Second Label"));
        Label newLabel = new Label(3, "Third Label");
        try{
            when(labelRepositoryMock.update(newLabel)).thenReturn(newLabel);

            newLabel.setName("Third Label Updated");

            assertEquals("Third Label Updated", labelRepositoryMock.update(newLabel).getName());
            assertEquals(3, labelRepositoryMock.update(newLabel).getId());
        } catch (ClassNotFoundException | SQLException exception){
            System.out.println("Error occurred");
        }
    }

    @Test
    void givenId_whenRun_thenVerifyTimesOfInvocations() {
        try {
            doNothing().when(labelRepositoryMock).deleteById(isA(Long.class));

            labelRepositoryMock.deleteById(1L);

            verify(labelRepositoryMock, times(1)).deleteById(1L);
        } catch (ClassNotFoundException | SQLException exception){
            System.out.println("Error occurred");
        }
    }
}