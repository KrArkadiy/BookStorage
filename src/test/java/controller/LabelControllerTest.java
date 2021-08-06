package controller;

import liquibase.pro.packaged.L;
import model.Label;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.LabelRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

class LabelControllerTest {

    private final LabelRepository labelRepositoryMock = Mockito.mock(LabelRepository.class);
    private final LabelController labelController = new LabelController(labelRepositoryMock);

    @Test
    void givenListOfLabels_whenRun_thenEqualListOfLabelsReturned() {
        List<Label> labels = new ArrayList<>();
        labels.add(new Label(1, "First Label"));
        labels.add(new Label(2, "Second Label"));
        when(labelController.getAll()).thenReturn(labels);

        List<Label> testLabels = labelController.getAll();

        assertEquals(labels, testLabels);
    }

    @Test
    void givenId_whenRun_thenLabelWithThatIdReturned() {
        List<Label> labels = new ArrayList<>();
        labels.add(new Label(1, "First Label"));
        labels.add(new Label(2, "Second Label"));

        when(labelController.getById(1L)).thenReturn(labels.get(0));
        when(labelController.getById(2L)).thenReturn(labels.get(1));

        Label testLabel = labelController.getById(1L);
        Label testLabel2 = labelController.getById(2L);

        assertEquals("First Label", testLabel.getName());
        assertEquals("Second Label", testLabel2.getName());
    }

    @Test
    void givenNewLabel_whenSave_thenReturnNewLabel() {
        LabelController labelController = Mockito.mock(LabelController.class);
        List<Label> labels = new ArrayList<>();
        labels.add(new Label(1, "First Label"));
        labels.add(new Label(2, "Second Label"));
        Label newLabel = new Label(3, "Third Label");
        doNothing().when(labelController).save(isA(Label.class));

        labelController.save(newLabel);

        verify(labelController, times(1)).save(newLabel);
    }

    @Test
    void givenUpdate_whenUpdate_thenReturnUpdatedLabel() {
        LabelController labelController = Mockito.mock(LabelController.class);
        List<Label> labels = new ArrayList<>();
        labels.add(new Label(1, "First Label"));
        labels.add(new Label(2, "Second Label"));
        Label newLabel = new Label(3, "Third Label");

        doNothing().when(labelController).update(newLabel);

        labelController.update(newLabel);

        verify(labelController, times(1)).update(newLabel);
    }

    @Test
    void givenId_whenRun_thenVerifyTimesOfInvocations() {
        LabelController labelController = Mockito.mock(LabelController.class);
        doNothing().when(labelController).deleteById(isA(Long.class));

        labelController.deleteById(1L);

        verify(labelController, times(1)).deleteById(1L);
    }
}