package com.sponsors.service.impl;

import com.sponsors.exception.NotFoundException;
import com.sponsors.repository.CaseStatusRepository;
import com.sponsors.service.CaseStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.sponsors.exception.NotFoundException.NotFound.CASE_STATUS_NOT_FOUND;

@Service
public class CaseStatusServiceImpl  implements CaseStatusService {

    @Autowired
    private CaseStatusRepository caseStatusRepository;

    @Override
    public void validateStatusId(long newStatusId) {
        caseStatusRepository.findById(newStatusId)
                .orElseThrow( () -> new NotFoundException(CASE_STATUS_NOT_FOUND));
    }
}
