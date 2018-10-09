package com.sponsors.controller;

import com.sponsors.dto.CandidateDto;
import com.sponsors.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping("/status/{id}")
    public ResponseEntity<List<CandidateDto>> getCandidatesByCaseStatus(@PathVariable("id") long id) {
        return new ResponseEntity<>(candidateService.getCandidatesByStatusId(id), HttpStatus.OK);
    }

    @PutMapping("{candidateId}/newStatus/{newStatusId}")
    public ResponseEntity getCandidatesByCaseStatus(@PathVariable("candidateId") long candidateId,
                                                      @PathVariable("newStatusId") long newStatusId) {
        candidateService.changeStatus(candidateId, newStatusId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
