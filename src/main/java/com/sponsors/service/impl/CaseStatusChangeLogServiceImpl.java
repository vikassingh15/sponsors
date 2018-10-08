package com.sponsors.service.impl;

import com.sponsors.dto.CandidateDto;
import com.sponsors.dto.CaseReportDto;
import com.sponsors.model.Candidate;
import com.sponsors.repository.CaseStatusChangeLogRepository;
import com.sponsors.service.CandidateService;
import com.sponsors.service.CaseStatusChangeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CaseStatusChangeLogServiceImpl implements CaseStatusChangeLogService {

    @Autowired
    private CaseStatusChangeLogRepository caseStatusChangeLogRepository;

    @Autowired
    private CandidateService candidateService;

    @Override
    public CaseReportDto getCandidateReportLongestTime(long id) {
        Candidate candidates = candidateService.getCandidateById(id);
        List<CaseReportDto> caseReportDtos = prepareCaseReportDtos(Collections.singletonList(candidates));
        if(caseReportDtos.isEmpty())
            return null;
        else
           return caseReportDtos.get(0);
    }

    @Override
    public List<CaseReportDto> getCandidateReportLongestTime() {
        List<Candidate> candidates =  candidateService.getAllCandidates();
        return prepareCaseReportDtos(candidates);
    }

    private List<CaseReportDto> prepareCaseReportDtos(List<Candidate> candidates) {
        if(candidates.isEmpty()){
            return Collections.emptyList();
        }

        Map<Long, CandidateDto> candidateMap = candidates.stream().collect(Collectors.toMap(Candidate::getId, Candidate::createCandidateDto));
        List<Object[]> caseReportDtos = caseStatusChangeLogRepository.findByLongestInterviewSchedule(candidateMap.keySet());

        return caseReportDtos
                .stream()
                .map(obj -> new CaseReportDto(
                        ((BigInteger) obj[0]).longValue(),
                        ((BigInteger) obj[1]).longValue(),
                        (String) obj[2], candidateMap.get(((BigInteger) obj[0]).longValue())))
                .collect(Collectors.toList());
    }
}
