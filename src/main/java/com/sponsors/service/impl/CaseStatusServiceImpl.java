package com.sponsors.service.impl;

import com.sponsors.repository.CaseStatusRepository;
import com.sponsors.service.CaseStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaseStatusServiceImpl  implements CaseStatusService {

    @Autowired
    private CaseStatusRepository caseStatusRepository;
}
