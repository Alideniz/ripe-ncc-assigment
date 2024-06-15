package com.ripencc.epigram.service;

import com.ripencc.epigram.model.Epigram;
import com.ripencc.epigram.repository.EpigramRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Service class for managing Epigram entities.
 */
@Service
public class EpigramService {

    private static final Logger log = LoggerFactory.getLogger(EpigramService.class);
    private final EpigramRepository epigramRepository;

    /**
     * Constructs a new EpigramService with the specified EpigramRepository.
     *
     * @param epigramRepository the repository used for Epigram operations
     */
    public EpigramService(EpigramRepository epigramRepository) {
        this.epigramRepository = epigramRepository;
    }

    /**
     * Saves an Epigram to the repository.
     *
     * @param epigram the Epigram to be saved
     * @return the saved Epigram
     */
    public Epigram saveEpigram(Epigram epigram) {
        log.info("Saving Epigram: {}", epigram);
        Epigram savedEpigram = epigramRepository.save(epigram);
        log.info("Saved Epigram: {}", savedEpigram);
        return savedEpigram;
    }

    /**
     * Retrieves a random Epigram from the repository.
     *
     * @return an Optional containing a random Epigram if found, or an empty Optional if no Epigram exists
     */
    public Optional<Epigram> getRandomEpigram() {
        long count = epigramRepository.count();
        log.info("Total number of Epigrams in the repository: {}", count);
        if (count == 0) {
            log.warn("No Epigrams found in the repository.");
            return Optional.empty();
        }
        long randomId = ThreadLocalRandom.current().nextLong(1, count + 1);
        log.info("Generated random ID for Epigram retrieval: {}", randomId);
        Optional<Epigram> epigramOptional = epigramRepository.findById(randomId);
        if (epigramOptional.isPresent()) {
            log.info("Retrieved Epigram: {}", epigramOptional.get());
        } else {
            log.warn("No Epigram found with ID: {}", randomId);
        }
        return epigramOptional;
    }
}
