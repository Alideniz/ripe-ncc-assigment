package com.ripencc.epigram.repository;

import com.ripencc.epigram.model.Epigram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpigramRepository extends JpaRepository<Epigram, Long> {
}