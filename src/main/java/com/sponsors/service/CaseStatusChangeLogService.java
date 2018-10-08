package com.sponsors.service;

import com.sponsors.dto.CaseReportDto;

import java.util.List;

public interface CaseStatusChangeLogService {
    CaseReportDto getCandidateReportLongestTime(long id);

    List<CaseReportDto> getCandidateReportLongestTime();
}
