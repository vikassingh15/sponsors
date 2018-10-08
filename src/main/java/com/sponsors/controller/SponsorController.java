package com.sponsors.controller;

import com.sponsors.dto.CandidateDto;
import com.sponsors.dto.StaffDto;
import com.sponsors.model.Sponsor;
import com.sponsors.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/sponsor")
public class SponsorController {

    @Autowired
    private SponsorService sponsorService;

    @GetMapping("/{id}/staff")
    public ResponseEntity<List<StaffDto>> getSponsorStaffBySponsorId(@PathVariable("id") long id) {
        return new ResponseEntity<>(sponsorService.getStaffDetails(id), HttpStatus.OK);
    }

    @GetMapping("/searchByName/{search}/staff")
    public ResponseEntity<List<StaffDto>> searchStaffBySponsorsName(@PathVariable("search") String search) {
        return new ResponseEntity<>(sponsorService.searchStaffByOrganizationName(search), HttpStatus.OK);
    }

    @GetMapping("/{id}/candidate")
    public ResponseEntity<List<CandidateDto>> getSponsorCandidatesBySponsorId(@PathVariable("id") long id) {
        return new ResponseEntity<>(sponsorService.getCandidatesForSponsor(id), HttpStatus.OK);
    }

    @GetMapping("/searchByName/{search}/candidate")
    public ResponseEntity<List<CandidateDto>> searchCandidateBySponsorsName(@PathVariable("search") String search) {
        return new ResponseEntity<>(sponsorService.searchCandiatesByOrganizationName(search), HttpStatus.OK);
    }

}
