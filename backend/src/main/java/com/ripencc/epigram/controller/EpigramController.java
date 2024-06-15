package com.ripencc.epigram.controller;

import com.ripencc.epigram.model.Epigram;
import com.ripencc.epigram.service.EpigramService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * RestController for handling Epigram related operations.
 * Supports CRUD operations via HTTP methods.
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(path = "api/v1/epigrams")
public class EpigramController {

    private final EpigramService epigramService;

    /**
     * Constructor for EpigramController.
     *
     * @param epigramService the service used for Epigram operations.
     */
    public EpigramController(EpigramService epigramService) {
        this.epigramService = epigramService;
    }

    /**
     * Creates a new Epigram.
     *
     * @param epigram the Epigram object to be created.
     * @return ResponseEntity containing the created Epigram and HTTP status code CREATED.
     */
    @PostMapping
    public ResponseEntity<Epigram> createEpigram(@Validated @RequestBody Epigram epigram) {
        Epigram createdEpigram = epigramService.saveEpigram(epigram);
        return new ResponseEntity<>(createdEpigram, HttpStatus.CREATED);
    }

    /**
     * Retrieves a random Epigram.
     *
     * @return ResponseEntity containing a random Epigram and HTTP status code OK,
     * or NOT_FOUND if no Epigram is found.
     */
    @GetMapping("/random")
    public ResponseEntity<Epigram> getRandomEpigram() {
        Optional<Epigram> epigramOptional = epigramService.getRandomEpigram();
        return epigramOptional.map(epigram -> new ResponseEntity<>(epigram, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
