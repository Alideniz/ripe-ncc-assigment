package com.ripencc.epigram.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Represents an Epigram entity with fields for id, text, and author.
 */
@Entity
public class Epigram {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "epigram_id_generator")
    @SequenceGenerator(name = "epigram_id_generator", sequenceName = "epigram_seq", allocationSize = 1)
    private Long id;

    @NotNull(message = "Text may not be null")
    @NotEmpty(message = "Text is mandatory")
    @Size(min = 3, max = 1024, message = "Text size must be between 3 and 1024")
    private String text;

    @Size(max = 255, message = "Author size must be smaller then 255")
    private String author;

    /**
     * Constructs a new Epigram with the specified text and author.
     *
     * @param text   the text of the epigram
     * @param author the author of the epigram
     */
    public Epigram(String text, String author) {
        this.text = text;
        this.author = author;
    }

    /**
     * Default constructor for Epigram.
     */
    public Epigram() {

    }

    /**
     * Constructs a new Epigram with the specified id, text, and author.
     *
     * @param id     the id of the epigram
     * @param text   the text of the epigram
     * @param author the author of the epigram
     */
    public Epigram(Long id, String text, String author) {
        this.id = id;
        this.text = text;
        this.author = author;
    }

    /**
     * Gets the id of the epigram.
     *
     * @return the id of the epigram
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the epigram.
     *
     * @param id the new id of the epigram
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the text of the epigram.
     *
     * @return the text of the epigram
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text of the epigram.
     *
     * @param text the new text of the epigram
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the author of the epigram.
     *
     * @return the author of the epigram
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the epigram.
     *
     * @param author the new author of the epigram
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Returns a string representation of the Epigram.
     *
     * @return a string representation of the Epigram
     */
    @Override
    public String toString() {
        return "Epigram{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
