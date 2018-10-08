package com.sponsors.controller;

import com.sponsors.dto.CaseReportDto;
import com.sponsors.service.CaseStatusChangeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private CaseStatusChangeLogService caseStatusChangeLogService;

    @GetMapping("/candidate/{id}")
    public ResponseEntity<CaseReportDto> getCaseReport(@PathVariable("id") long id) {
        return new ResponseEntity<>(caseStatusChangeLogService.getCandidateReportLongestTime(id), HttpStatus.OK);
    }

    @GetMapping("/candidate")
    public ResponseEntity<List<CaseReportDto>> getCaseReport() {
        return new ResponseEntity<>(caseStatusChangeLogService.getCandidateReportLongestTime(), HttpStatus.OK);
    }
}
