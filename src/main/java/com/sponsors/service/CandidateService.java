package com.sponsors.service;

import com.sponsors.dto.CandidateDto;
import com.sponsors.model.Candidate;

import java.util.List;

public interface CandidateService {
    List<CandidateDto> getCandidatesByStatusId(long id);

    List<Candidate> getAllCandidates();

    Candidate getCandidateById(long id);

    void changeStatus(long candidateId, long currentStatusId);
}
