package com.sponsors.service.impl;

import com.sponsors.dto.CandidateDto;
import com.sponsors.exception.NotFoundException;
import com.sponsors.model.Candidate;
import com.sponsors.repository.CandidateRepository;
import com.sponsors.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.sponsors.exception.NotFoundException.NotFound.CANDIDATE_NOT_FOUND;
import static com.sponsors.exception.NotFoundException.NotFound.SPONSOR_NOT_FOUND;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;


    @Override
    public List<CandidateDto> getCandidatesByStatusId(long id) {
        List<Candidate> candidates = candidateRepository.findAllByCaseStatusId(id);
        return candidates.stream().map(candidate -> candidate.createCandidateDto()).collect(Collectors.toList());
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return (List<Candidate>) candidateRepository.findAll();
    }

    @Override
    public Candidate getCandidateById(long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CANDIDATE_NOT_FOUND));
    }
}
