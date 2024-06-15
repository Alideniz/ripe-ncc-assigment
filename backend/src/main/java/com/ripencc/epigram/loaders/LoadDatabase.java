package com.ripencc.epigram.loaders;

import com.ripencc.epigram.model.Epigram;
import com.ripencc.epigram.repository.EpigramRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * Configuration class to load initial data into the Epigram database.
 */
@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    /**
     * Initializes the Epigram database with predefined data if it is empty.
     *
     * @param repository the Epigram repository
     * @return a CommandLineRunner that initializes the database
     */
    @Bean
    public CommandLineRunner initDatabase(EpigramRepository repository) {
        return args -> {
            if (repository.count() > 0) {
                log.info("Database already exists.");
                return;
            }
            log.info("Creating new database.");

            List<Epigram> epigrams = Arrays.asList(
                    new Epigram(
                            "What is an Epigram? a dwarfish whole, Its body brevity, and wit its soul.",
                            "Samuel Taylor Coleridge"
                    ),
                    new Epigram(
                            "Some can gaze and not be sick But I could never learn the trick. There's this to say for blood and breath; They give a man a taste for death.",
                            "A. E. Housman"
                    ),
                    new Epigram(
                            "Little strokes Fell great oaks.",
                            "Benjamin Franklin"
                    ),
                    new Epigram(
                            "Here lies my wife: here let her lie! Now she's at rest – and so am I.",
                            "John Dryden"
                    ),
                    new Epigram(
                            "Three Poets, in three distant Ages born, Greece, Italy, and England did adorn. The First in loftiness of thought surpassed; The Next in Majesty; in both the Last. The force of Nature could no farther go: To make a third she joined the former two.",
                            "John Dryden"
                    ),
                    new Epigram(
                            "We have a pretty witty king, Whose word no man relies on. He never said a foolish thing, And never did a wise one.",
                            "John Wilmot, 2nd Earl of Rochester"
                    ),
                    new Epigram(
                            "I am His Highness' dog at Kew; Pray tell me, sir, whose dog are you?",
                            "Alexander Pope"
                    ),
                    new Epigram(
                            "I'm tired of Love: I'm still more tired of Rhyme. But Money gives me pleasure all the time.",
                            "Hilaire Belloc"
                    ),
                    new Epigram(
                            "I hope for nothing. I fear nothing. I am free.",
                            "Nikos Kazantzakis"
                    ),
                    new Epigram(
                            "To define the beautiful is to misunderstand it.",
                            "Charles Robert Anon (Fernando Pessoa)"
                    ),
                    new Epigram(
                            "This Humanist whom no belief constrained Grew so broad-minded he was scatter-brained.",
                            "J.V. Cunningham"
                    ),
                    new Epigram(
                            "All things pass Love and mankind is grass.",
                            "Stevie Smith"
                    ),
                    new Epigram("If you can't be a good example, you'll just have to be a horrible warning.",
                            "Catherine the Great"),
                    new Epigram("If all the girls who attended the Yale prom were laid end to end, I wouldn't be a bit surprised.",
                            "Dorothy Parker"),
                    new Epigram("Grace Kelly did everything Fred Astaire did: walking backwards, in high heels!",
                            "Unknown"),
                    new Epigram("I'm not offended by dumb blonde jokes because I'm not dumb, and also I'm not blonde.",
                            "Dolly Parton"),
                    new Epigram("A man may be a fool and not know it, but not if he is married.",
                            "H. L. Mencken"),
                    new Epigram("Your children need your presence more than your presents.",
                            "Jesse Jackson")
            );

            repository.saveAll(epigrams);
            log.info("{} items have been pushed to the database.", epigrams.size());
        };
    }
}
