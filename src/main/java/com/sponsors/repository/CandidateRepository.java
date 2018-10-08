package com.sponsors.repository;

import com.sponsors.model.Candidate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Long> {
    List<Candidate> findAllByCaseStatusId(long id);
}