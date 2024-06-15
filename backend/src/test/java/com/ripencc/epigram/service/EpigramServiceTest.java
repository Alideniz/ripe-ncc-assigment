package com.ripencc.epigram.service;

import com.ripencc.epigram.model.Epigram;
import com.ripencc.epigram.repository.EpigramRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class EpigramServiceTest {

    @Mock
    private EpigramRepository epigramRepository;

    @InjectMocks
    private EpigramService epigramService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testSaveEpigram() {
        Epigram epigram = new Epigram("test-text", "test-author");

        when(epigramRepository.save(epigram)).thenReturn(epigram);

        Epigram result = epigramService.saveEpigram(epigram);

        assertEquals(result.getAuthor(), epigram.getAuthor());
        assertEquals(result.getText(), epigram.getText());

        verify(epigramRepository, times(1)).save(epigram);
    }

    @Test
    void testGetRandomEpigram() {
        Epigram epigram = new Epigram("test-text", "test-author");

        when(epigramRepository.count()).thenReturn(1L);
        when(epigramRepository.findById(anyLong())).thenReturn(Optional.of(epigram));

        Optional<Epigram> epigramOptional = epigramService.getRandomEpigram();
        if (epigramOptional.isPresent()) {
            var result = epigramOptional.get();
            assertEquals(result.getAuthor(), epigram.getAuthor());
            assertEquals(result.getText(), epigram.getText());
        }
    }

    @Test
    void testGetRandomEpigram_NoEpigrams() {
        when(epigramRepository.count()).thenReturn(0L);

        Optional<Epigram> result = epigramService.getRandomEpigram();

        assertFalse(result.isPresent());
    }
}
